package com.lyh.abroad.domain.entity

data class FeedEntity(
    /** 채팅 방 이름*/
    val chattingRoomId: String,
    /** 게시글 내용*/
    val contents: String,
    /** 게시글 등록 서버 시간*/
    val createDate: Long,
    /** 여행 마지막 날*/
    val endDate: String,
    /** 여행 시작 날*/
    val startDate: String,
    /** 게시글 제목*/
    val title: String,
    /** 작성자 uid*/
    val uid: String,
    /** 작성자 닉네임 */
    val userName: String
)
