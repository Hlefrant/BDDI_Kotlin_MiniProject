package com.hugolefrant.miniproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hugolefrant.miniproject.R
import com.hugolefrant.miniproject.adapters.ArticleAdapter
import com.hugolefrant.miniproject.change
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import models.Article
import repositories.ArticleRepository


class ListArticleFragment : Fragment() {
    private val repository = ArticleRepository()

    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var recycler_view: RecyclerView

    private lateinit var result: List<Article>

    val categorie: String by lazy {
        arguments?.getString(ARGS_CHOICE, "+") ?: "+"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.articles_view,container,false)
    }

    companion object {
        const val ARGS_CHOICE = "ARGS_CHOICE"
        fun newInstance(categorie: String):ListArticleFragment {
            return ListArticleFragment().apply {
                //On sauvegarde l’opération dans les arguments,
                //Si le fragment se recrée, la valeur sera restaurée
                arguments = bundleOf(ARGS_CHOICE to categorie)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view = view.findViewById(R.id.recycler_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            getData()
        }
    }

    //S'execute dans un thread secondaire
    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            result = repository.list(arguments?.getString(ARGS_CHOICE).toString())
            bindData(result)
        }
    }

    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            val adapterRecycler = ArticleAdapter(result)
            recycler_view.layoutManager = LinearLayoutManager(context)
            recycler_view.adapter = adapterRecycler
        }
    }
}
