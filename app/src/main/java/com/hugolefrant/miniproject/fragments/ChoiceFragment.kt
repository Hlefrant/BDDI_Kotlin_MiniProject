package com.hugolefrant.miniproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hugolefrant.miniproject.R
import com.hugolefrant.miniproject.adapters.ChoiceAdapter
import com.hugolefrant.miniproject.adapters.ModeAdapter
import com.hugolefrant.miniproject.adapters.OnItemClickListener
import com.hugolefrant.miniproject.change
import models.*

class ChoiceFragment: Fragment(), OnItemClickListener {

    private lateinit var recycler_view: RecyclerView

    lateinit var modeList: List<Any>

    lateinit var choice:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choices_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var button = view.findViewById<Button>(R.id.btn_back)
        button.setOnClickListener {
            activity?.change(ModesFragment())
        }

        recycler_view = view.findViewById(R.id.recycler_view)
        bindRecyclerView()
    }

    override fun onItemClicked(element:Any) {
        when(element) {
            is SourceItem -> {
                Toast.makeText(context, "Mode ${element.name}", Toast.LENGTH_LONG)
                    .show()
                choice = element.name
            }
            is Category -> {
                Toast.makeText(context, "Mode ${element.title}", Toast.LENGTH_LONG)
                    .show()
                choice = element.title
            }
            is Country -> {
                Toast.makeText(context, "Mode ${element.name}", Toast.LENGTH_LONG)
                    .show()
                choice = element.name
            }
        }

        activity?.change(ListArticleFragment.newInstance(choice))
    }

    private fun bindRecyclerView() {

        val adapterRecycler = ChoiceAdapter(modeList, this)

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapterRecycler
    }

    companion object {
        fun newInstance(mode: List<Any>):ChoiceFragment {
            return ChoiceFragment().apply {
                modeList = mode
            }
        }
    }
}