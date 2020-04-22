package com.hugolefrant.miniproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hugolefrant.miniproject.R

class ChoiceFragment: Fragment() {

    private lateinit var recycler_view: RecyclerView

    lateinit var modeList: List<Any>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choices_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**var button = view.findViewById<Button>(R.id.btn_back)
        button.setOnClickListener {
            activity?.change(Categoriesfragment())
        }**/

        recycler_view = view.findViewById(R.id.recycler_view)
    }

    companion object {
        const val ARGS_MODE = "ARGS_MODE"
        fun newInstance(mode: List<Any>):ChoiceFragment {
            return ChoiceFragment().apply {
                modeList = mode
            }
        }
    }
}