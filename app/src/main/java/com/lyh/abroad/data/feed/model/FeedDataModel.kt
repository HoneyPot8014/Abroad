package com.lyh.abroad.data.feed.model

data class FeedDataModel(
    val chattingRoomId: String? = null, /** 채팅 방 이름*/
    val contents: String? = null, /** 게시글 내용*/
    val createDate: Long? = null, /** 게시글 등록 서버 시간*/
    val endDate: String? = null, /** 여행 마지막 날*/
    val startDate: String? = null, /** 여행 시작 날*/
    val title: String? = null, /** 게시글 제목*/
    val uid: String ? = null/** 작성자 uid*/
) {
    fun isEmpty(): Boolean =
        chattingRoomId == null &&
                contents == null &&
                createDate == null &&
                endDate == null &&
                startDate == null &&
                title == null &&
                uid == null
}