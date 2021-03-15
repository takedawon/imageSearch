package com.lanic.image.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class SearchResponse(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
) : Parcelable

@Parcelize
data class Document(
    @SerializedName("collection")
    val collection: String,
    @SerializedName("datetime")
    val datetime: String = "",
    @SerializedName("display_sitename")
    val displaySiteName: String,
    @SerializedName("doc_url")
    val docUrl: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("width")
    val width: Int
) : Parcelable {
    fun getDateFormat(): String {
        return try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(datetime)
            SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format(date)
        } catch (e: Exception) {
            datetime
        }
    }
}

@Parcelize
data class Meta(
    @SerializedName("is_end")
    val isEnd: Boolean,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("total_count")
    val totalCount: Int
) : Parcelable
