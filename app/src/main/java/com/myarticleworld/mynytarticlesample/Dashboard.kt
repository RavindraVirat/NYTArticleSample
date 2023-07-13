package com.myarticleworld.mynytarticlesample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.myarticleworld.mynytarticlesample.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Dashboard : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding.rvArticlesList.isVisible = false

        binding.viewModel = viewModel
        viewModel.fetchArticles()

        viewModel.articlesList.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressIndicator.isVisible = true
                }

                is NetworkResult.Success -> {
                    showArticles(it.data)
                    binding.rvArticlesList.isVisible = true
                    binding.progressIndicator.isVisible = false
                }

                is NetworkResult.Failure -> {
                    binding.progressIndicator.isVisible = false
                    Toast.makeText(this, it.erroeMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showArticles(articleList: List<Article>) {
        binding.rvArticlesList.isVisible = true
        binding.rvArticlesList.setHasFixedSize(true)
        binding.rvArticlesList.adapter = ArticleAdapter(articleList)

    }
}