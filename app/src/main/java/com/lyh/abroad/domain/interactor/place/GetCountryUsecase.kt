package com.lyh.abroad.domain.interactor.place


import com.lyh.abroad.domain.entity.CountryEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class GetCountryUsecase(

) : BaseUsecase<List<CountryEntity>, GetCountryUsecase.CountryParam>() {

    data class CountryParam(val query: String?) : Param()

    private val singleChosungMap = mapOf(
        'ㄱ' to 0x1100,
        'ㄲ' to 0x1101,
        'ㄴ' to 0x1102,
        'ㄷ' to 0x1103,
        'ㄸ' to 0x1104,
        'ㄹ' to 0x1105,
        'ㅁ' to 0x1106,
        'ㅂ' to 0x1107,
        'ㅃ' to 0x1108,
        'ㅅ' to 0x1109,
        'ㅆ' to 0x110A,
        'ㅇ' to 0x110B,
        'ㅈ' to 0x110C,
        'ㅉ' to 0x110D,
        'ㅊ' to 0x110E,
        'ㅋ' to 0x110F,
        'ㅌ' to 0x1110,
        'ㅍ' to 0x1111,
        'ㅎ' to 0x1112
    )

    private val test = Locale.getISOCountries().map {
        Locale("ko", it)
    }.sortedBy {
        it.displayName
    }.map {
        CountryEntity(
            it.country.toFlagEmoji(),
            it.country,
            it.displayCountry,
            null,
            mutableSetOf()
        )
    }

    override suspend fun bindUsecase(param: CountryParam?): ResultModel<List<CountryEntity>> {
        return param?.query?.let { query ->
            withContext(Dispatchers.Default) {
                test.filter {
                    if (it.countryName.any { char -> char in '가'..'힣' }) {
                        var lastIndex = -1
                        var count = 0
                        val set = mutableSetOf<Int>()
                        val matchedIndex = mutableSetOf<Int>()
                        for (queryIndex in query.indices) {
                            if (query[queryIndex] == ' ') {
                                continue
                            }
                            for (countryIndex in it.countryName.indices) {
                                val queryInt = singleChosungMap[query[queryIndex]] ?: query[queryIndex].toInt()
                                val countryFirstInt = it.countryName[countryIndex].toInt().toFirstUniCode()
                                if (queryInt == countryFirstInt) {
                                    if (lastIndex < countryIndex && !set.contains(queryInt)) {
                                        lastIndex = countryIndex
                                        set.add(queryInt)
                                        matchedIndex.add(countryIndex)
                                        count++
                                    }
                                } else {
                                    if (queryInt == it.countryName[countryIndex].toInt()) {
                                        if (lastIndex < countryIndex && !set.contains(queryInt)) {
                                            lastIndex = countryIndex
                                            set.add(queryInt)
                                            matchedIndex.add(countryIndex)
                                            count++
                                        }
                                    } else {
                                        val queryFirstInt = queryInt.toFirstUniCode()
                                        val querySecondInt = queryInt.toSecondUniCode()
                                        val countrySecondInt = it.countryName[countryIndex].toInt().toSecondUniCode()
                                        if (queryFirstInt == countryFirstInt && querySecondInt == countrySecondInt) {
                                            if (((queryInt - 44032) % 588 % 28) == 0) {
                                                if (lastIndex < countryIndex && !set.contains(queryInt)) {
                                                    lastIndex = countryIndex
                                                    set.add(queryInt)
                                                    matchedIndex.add(countryIndex)
                                                    count++
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (count == query.length) {
                            it.queryMatchIndexSet = matchedIndex
                            return@filter count == query.length
                        }
                    }
                    return@filter false
                }.let { ResultModel.onSuccess(it) }
            }
        } ?: ResultModel.onSuccess(test)
    }

    private fun String.toFlagEmoji(): String {
        if (this.length != 2) {
            return this
        }
        val countryCodeCaps = this.toUpperCase()
        val firstLetter = Character.codePointAt(countryCodeCaps, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(countryCodeCaps, 1) - 0x41 + 0x1F1E6
        if (!countryCodeCaps[0].isLetter() || !countryCodeCaps[1].isLetter()) {
            return this
        }

        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }

    private fun Int.toFirstUniCode(): Int = (this - 44032) / 588 + 0x1100
    private fun Int.toSecondUniCode(): Int = ((this - 44032) % 588) /28 + 0x1161

}
