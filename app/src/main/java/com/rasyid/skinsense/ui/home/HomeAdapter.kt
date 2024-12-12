package com.rasyid.skinsense.ui.home

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.rasyid.skinsense.data.Article
import com.rasyid.skinsense.databinding.ItemArticleBinding
import com.rasyid.skinsense.ui.detail.DetailActivity

class HomeAdapter(
    private val listArticle: ArrayList<Article>
) : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, source, image, desc) = listArticle[position]
        holder.binding.tvTitle.text = title
        holder.binding.tvSource.text = source
        holder.binding.sivPhoto.setImageResource(image)
        holder.binding.tvDesc.text = desc

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_ARTICLE, listArticle[holder.adapterPosition])

            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity,
                    Pair(holder.binding.sivPhoto, "photo")
                )

            holder.itemView.context.startActivity(intentDetail, optionsCompat.toBundle())
        }
    }

    override fun getItemCount(): Int = listArticle.size

    class ListViewHolder(var binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)
}