package com.lyh.abroad.data.model

data class FeedDataModel(
    var countryId: String? = null,
    var cityId: String? = null,
    /** 채팅 방 이름*/
    val chattingRoomId: String? = null,
    /** 게시글 내용*/
    val contents: String? = null,
    /** 게시글 등록 서버 시간*/
    val createDate: Long? = null,
    /** 여행 마지막 날*/
    val endDate: Long? = null,
    /** 여행 시작 날*/
    val startDate: Long? = null,
    /** 게시글 제목*/
    val title: String? = null,
    /** 작성자 uid*/
    val uid: String? = null,
    /** 작성자 닉네임 */
    val userName: String? = null,
    /** 작성자 UID */
    val postId: String? = null
) {
    fun isEmpty(): Boolean =
        countryId == null ||
                cityId == null ||
                chattingRoomId == null ||
                contents == null ||
                createDate == null ||
                endDate == null ||
                startDate == null ||
                title == null ||
                uid == null ||
                userName == null ||
                postId == null
}
