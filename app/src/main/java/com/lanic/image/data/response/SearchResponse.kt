package com.lanic.image.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(
    @SerializedName("documents")
    val searchImages: List<SearchImage>,
    @SerializedName("meta")
    val meta: Meta
) : Parcelable

@Parcelize
data class SearchImage(
    @SerializedName("collection")
    val collection: String = "",
    @SerializedName("datetime")
    val datetime: String = "",
    @SerializedName("display_sitename")
    val displaySiteName: String = "",
    @SerializedName("image_url")
    val imageUrl: String = "",
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String = ""
) : Parcelable

@Parcelize
data class Meta(
    @SerializedName("is_end")
    val isEnd: Boolean,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("total_count")
    val totalCount: Int
) : Parcelable
