package com.lyh.abroad.domain.entity

data class FeedEntity(
    val chattingRoomId: String, /** 채팅 방 이름*/
    val content: String, /** 게시글 내용*/
    val createDate: String, /** 게시글 등록 서버 시간*/
    val endDate: String, /** 여행 마지막 날*/
    val startDate: String, /** 여행 시작 날*/
    val title: String, /** 게시글 제목*/
    val uid: String /** 작성자 uid*/
)