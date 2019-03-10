package dev.dvdciri.sampleapp.data.model

import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("id") val id: Int,
    @SerializedName("postId") val postId: Int,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String
)