package com.lyh.abroad.presenter.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feed(
    /** 채팅 방 이름*/
    val chattingRoomId: String,
    /** 게시글 내용*/
    val contents: String,
    /** 게시글 등록 서버 시간 yyyy-MM-dd Date*/
    val createDate: String,
    /** 여행 마지막 날*/
    val endDate: String,
    /** 여행 시작 날*/
    val startDate: String,
    /** 게시글 제목*/
    val title: String,
    /** 작성자 Id*/
    val id: String,
    /** 작성자 닉네임*/
    val userName: String
) : Parcelable
