package com.myarticleworld.mynytarticlesample

import android.text.Html
import android.text.Spanned
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.myarticleworld.mynytarticlesample.databinding.CellArticleBinding
import com.myarticleworld.mynytarticlesample.databinding.CellSectionBinding

class ArticleAdapter(private val articlesList: List<Article>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_SECTION = 1
    private val VIEW_TYPE_ARTICLE = 2

    override fun getItemViewType(position: Int): Int {
        return if (articlesList[position].type == "Section") VIEW_TYPE_SECTION
        else VIEW_TYPE_ARTICLE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SECTION) { // Section
            val view =
                CellSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderSection(view)
        } else {
            val view =
                CellArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderArticle(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (articlesList[position].type == "Section") (holder as ViewHolderSection).bind(
            articlesList[position]
        )
        else (holder as ViewHolderArticle).bind(articlesList[position])
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    class ViewHolderSection(private val binding: CellSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
//            binding.tvSectionName.text = article.title
        }
    }

    class ViewHolderArticle(private val binding: CellArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            val htmlText:Spanned = Html.fromHtml(article.title, Html.FROM_HTML_MODE_LEGACY)
            article.title = htmlText.toString()
            binding.article = article
            binding.tvArticleTitle.movementMethod = ScrollingMovementMethod()


            Log.d("RRRRRRRRRRRRRRRR", article.title)

            try {
                if (article.media?.isNotEmpty() == true && article.type != "Section")
                    binding.mediaMetadata = article.media[0].mediaMetadata?.get(1)

//             binding.imgArticleTitle.text = article.title

                article.media?.get(0)?.mediaMetadata?.get(1)?.imageUrl.let {
                    Glide.with(binding.imgArticleThumbnail.context).load(it?.toUri()).apply(
                        RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                    ).into(binding.imgArticleThumbnail)
                }
            } catch (e: Exception) {
                Log.d("RRRRRRRRRRRRRRRR", article.title)
            }
        }
    }
}