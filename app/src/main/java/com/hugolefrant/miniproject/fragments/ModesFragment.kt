package com.hugolefrant.miniproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hugolefrant.miniproject.R
import com.hugolefrant.miniproject.adapters.ModeAdapter
import com.hugolefrant.miniproject.adapters.OnItemClickListener
import models.Mode

class ModesFragment: Fragment(), OnItemClickListener {

    private lateinit var recycler_view: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_modes_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view = view.findViewById(R.id.recycler_view)
        bindRecyclerView()
    }

    override fun onItemClicked(mode:Mode) {
        Toast.makeText(context,"Mode ${mode.name}",Toast.LENGTH_LONG)
            .show()

        //activity?.change(ListArticleFragment.newInstance(category.title))

    }

    private fun bindRecyclerView() {
        val modes = listOf(
            Mode("Sources"),Mode("Catégories"),Mode("Pays")
        )

        val adapterRecycler = ModeAdapter(modes, this)

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapterRecycler
    }
}