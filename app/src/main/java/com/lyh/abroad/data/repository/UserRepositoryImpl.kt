package com.lyh.abroad.data.repository

import android.net.Uri
import com.lyh.abroad.data.mapper.SignUpToUserMapper
import com.lyh.abroad.data.mapper.UserDataMapper
import com.lyh.abroad.data.source.user.UserAuth
import com.lyh.abroad.data.source.user.UserSource
import com.lyh.abroad.domain.entity.SignUpEntity
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.model.ResultModel.Status.SUCCESS
import com.lyh.abroad.domain.repository.UserRepository

class UserRepositoryImpl private constructor(
    private val userAuth: UserAuth,
    private val userRemoteSource: UserSource
) : UserRepository {

    private val cache = mutableMapOf<String, UserEntity>()

    companion object {

        private var INSTANCE: UserRepositoryImpl? = null

        fun getInstance(userAuth: UserAuth, userRemoteSource: UserSource): UserRepositoryImpl =
            INSTANCE ?: kotlin.run {
                UserRepositoryImpl(userAuth, userRemoteSource).also {
                    INSTANCE = it
                }
            }
    }

    override suspend fun getUid(): ResultModel<String> = userAuth.getUserUid()

    override suspend fun fetchUser(): ResultModel<UserEntity> =
        userAuth.getUid().data?.let {
            if (cache[it] != null) {
                return@let ResultModel.onSuccess(cache[it])
            }
            return@let fetchUser(it)
        } ?: ResultModel.onFailed()

    override suspend fun fetchUser(uid: String): ResultModel<UserEntity> =
        userRemoteSource.fetchUser(uid).let { userModel ->
            if (userModel.status == SUCCESS) {
                UserDataMapper.toEntity(userModel.data)?.let { userEntity ->
                    cache[uid] = userEntity
                    ResultModel.onSuccess(userEntity)
                } ?: ResultModel.onFailed()
            } else {
                ResultModel.onFailed(userModel.error)
            }
        }

    override suspend fun fetchUserWithLogIn(
        email: String,
        password: String
    ): ResultModel<UserEntity> {
        return userAuth.signIn(email, password).let {
            if (it.status == SUCCESS) {
                fetchUser()
            } else {
                ResultModel.onFailed(it.error)
            }
        }
    }

    override suspend fun setUser(userEntity: UserEntity): ResultModel<Unit> =
        userRemoteSource.setUser(userEntity)

    override suspend fun setUserWithSignUp(signUpEntity: SignUpEntity): ResultModel<Unit> =
        userAuth.createUser(signUpEntity.email, signUpEntity.password).let { authResult ->
            // auth 성공
            if (authResult.status == SUCCESS) {
                authResult.data?.user?.uid?.let { uid ->
                    // auth 성공
                    val profileResult = setProfile(uid, signUpEntity.profileUri)
                    if (profileResult.status == SUCCESS) {
                        profileResult.data?.let { url ->
                            // 프로필 저장 성공
                            val dbResult =
                                setUser(SignUpToUserMapper.toEntity(signUpEntity, uid, url))
                            if (dbResult.status == SUCCESS) {
                                ResultModel.onSuccess(Unit) //db 저장 성공
                            } else {
                                ResultModel.onFailed(dbResult.error) // db 저장 실패
                            }
                        } ?: ResultModel.onFailed(profileResult.error) // 프로필 url null
                    } else {
                        ResultModel.onFailed(profileResult.error) // 프로필 저장 실패
                    }
                } ?: ResultModel.onFailed(authResult.error) // auth data null
            } else {
                // auth 실패
                ResultModel.onFailed(authResult.error)
            }
        }

    override suspend fun setProfile(uid: String, uri: String): ResultModel<String> =
        userRemoteSource.setProfile(uid, Uri.parse(uri))

}
