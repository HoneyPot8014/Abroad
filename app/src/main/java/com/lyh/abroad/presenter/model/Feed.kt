package com.lyh.abroad.presenter.model

data class Feed(
    val chattingRoomId: String, /** 채팅 방 이름*/
    val contents: String, /** 게시글 내용*/
    val createDate: String, /** 게시글 등록 서버 시간 yyyy-MM-dd Date*/
    val endDate: String, /** 여행 마지막 날*/
    val startDate: String, /** 여행 시작 날*/
    val title: String, /** 게시글 제목*/
    val id: String /** 작성자 Id*/
)
