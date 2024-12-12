package com.rasyid.skinsense.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rasyid.skinsense.data.Article
import com.rasyid.skinsense.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val dataArticle = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ARTICLE, Article::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ARTICLE)
        }

        if (dataArticle != null) {
            binding.sivPhoto.setImageResource(dataArticle.image)
            binding.tvSource.text = dataArticle.source
            binding.tvTitle.text = dataArticle.title
            binding.tvDesc.text = dataArticle.desc
        }
    }

    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }
}