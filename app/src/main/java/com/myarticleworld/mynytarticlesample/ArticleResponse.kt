package com.myarticleworld.mynytarticlesample

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    val status: String = "",
    @SerializedName("num_results") val count: String,
    @SerializedName("results") val Articles: List<Article>
)

data class Article(
    val id: String = "1",
    val source: String = "",
    var type: String = "",
    var title: String = "",
    @SerializedName("media") val media: List<Media>? = listOf()
)

data class Media(
    val caption: String, @SerializedName("media-metadata") val mediaMetadata: List<MediaMetadata>
)

data class MediaMetadata(
    @SerializedName("url") val imageUrl: String?, val format: String
) {
    companion object {
        @JvmStatic
        @BindingAdapter("loadArticleImage")
        fun loadArticleImage(imgView: AppCompatImageView, imgUrl: String) {
            try {
                if (!imgUrl.isNullOrEmpty()) {
                    imgUrl.let {
                        Glide.with(imgView.context).load(imgUrl.toUri()).apply(
                            RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_background)
                        ).into(imgView)
                    }
                }
            } catch (e: Exception) {
                Log.d("RRRRRRR", e.printStackTrace().toString())
            }
        }
    }
}
