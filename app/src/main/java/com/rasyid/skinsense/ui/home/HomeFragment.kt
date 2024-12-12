package com.rasyid.skinsense.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyid.skinsense.R
import com.rasyid.skinsense.data.Article
import com.rasyid.skinsense.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var list = ArrayList<Article>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        list.addAll(getArticle())

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvArticle.layoutManager = layoutManager
        setArticle()

        return root
    }

    private fun getArticle(): ArrayList<Article> {
        val title = resources.getStringArray(R.array.title_article)
        val source = resources.getStringArray(R.array.source_article)
        val image = resources.obtainTypedArray(R.array.image_article)
        val desc = resources.getStringArray(R.array.description_article)
        val url = resources.getStringArray(R.array.url_article)
        val listArticle = ArrayList<Article>()
        for (i in title.indices) {
            val article = Article(title[i], source[i],image.getResourceId(i, - 1), desc[i], url[i])
            listArticle.add(article)
        }
        image.recycle()
        return listArticle
    }

    private fun setArticle() {
        val adapter = HomeAdapter(list)
        binding.rvArticle.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}