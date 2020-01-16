package com.lyh.abroad.data.source.place

import android.content.Context
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.lyh.abroad.R
import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class CityRemoteSource private constructor(val context: Context) : CitySource {

    companion object {

        private var INSTANCE: CityRemoteSource? = null

        fun getInstance(context: Context) =
            INSTANCE ?: kotlin.run {
                CityRemoteSource(context).also {
                    INSTANCE = it
                }
            }
    }

    init {
        Places.initialize(context, context.getString(R.string.place_api_key))
        AutocompleteSessionToken.newInstance()
    }

    override suspend fun getCity(query: String): ResultModel<List<CityEntity>> =
        suspendCancellableCoroutine { continuation ->
            Places.createClient(context).findAutocompletePredictions(
                FindAutocompletePredictionsRequest.builder()
                    .setCountry("KR")
                    .setTypeFilter(TypeFilter.CITIES)
                    .setQuery(query)
                    .build()
            )
                .addOnSuccessListener {
                    continuation.resume(
                        ResultModel.onSuccess(
                            it.autocompletePredictions
                                .map { city ->
                                    CityEntity(
                                        city.placeId,
                                        city.getPrimaryText(null).toString(),
                                        city.getSecondaryText(null).toString()
                                    )
                                }
                        )
                    )
                }
                .addOnFailureListener {
                    continuation.resume(ResultModel.onFailed())
                }
        }
}
