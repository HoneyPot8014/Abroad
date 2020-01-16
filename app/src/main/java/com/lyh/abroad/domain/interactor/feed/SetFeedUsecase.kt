package com.lyh.abroad.domain.interactor.feed

import com.lyh.abroad.domain.entity.PostEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.FeedRepository
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetFeedUsecase(
    private val userRepository: UserRepository,
    private val feedRepository: FeedRepository
) : BaseUsecase<Unit, SetFeedUsecase.SetFeedParam>() {

    data class SetFeedParam(
        val countryId: String,
        val cityId: String,
        val contents: String,
        val endDate: String,
        val startDate: String,
        val title: String
    ) : Param()

    override suspend fun bindUsecase(param: SetFeedParam?): ResultModel<Unit> {
        return param?.let {
            withContext(Dispatchers.Main) {
                userRepository.fetchUser().data?.let {
                    feedRepository.setFeed(
                        PostEntity(
                            param.countryId,
                            param.cityId,
                            param.contents,
                            param.endDate,
                            param.startDate,
                            param.title,
                            it.uid,
                            it.userName
                        )
                    )
                }
            }
        } ?: ResultModel.onFailed()
    }
}