package com.hugolefrant.miniproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hugolefrant.miniproject.R
import com.hugolefrant.miniproject.adapters.ModeAdapter
import com.hugolefrant.miniproject.adapters.OnItemClickListener
import com.hugolefrant.miniproject.change
import com.hugolefrant.miniproject.update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import models.*
import repositories.SourceRepository

class ModesFragment: Fragment(), OnItemClickListener {
    private val repository = SourceRepository()
    private lateinit var recycler_view: RecyclerView

    lateinit var listModeResult: List<Any>
    var isLoad: Boolean = false

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

    private suspend fun getSources() {
        withContext(Dispatchers.IO) {
            val result = repository.list()
            bindData(result)
        }
    }
    //S'execute sur le thread principal
    private suspend fun bindData(result: List<SourceItem>) {
        withContext(Dispatchers.Main) {
            //afficher les données dans le recycler
            Log.d("Sources", result.toString())

            listModeResult = result
            activity?.change(ChoiceFragment.newInstance(listModeResult))
        }
    }

    override fun onItemClicked(element:Any) {
        element as Mode
        //Toast.makeText(context,"Mode ${element.name}",Toast.LENGTH_LONG).show()

        when(element.name) {
            "Sources" -> {
                lifecycleScope.launch {
                    getSources()
                }
            }
            "Catégories" -> {
                listModeResult = listOf(
                    Category("Politique","C'est ça la politique", "https://cdn.1min30.com/wp-content/uploads/2018/12/shutterstock_1075959185-copie.jpg"),
                    Category("Economie","c'est l'économie", "https://lobservateur.info/wp-content/uploads/2019/04/5b02e7a498cdc.jpg"),
                    Category("Education","c'est l'éducation", "https://www.touteleurope.eu/fileadmin/_processed_/0/7/politique-euro-formation-education-a387ed0502.jpg"),
                    Category("Pandémie","c'est la pandémie", "https://cdn.futura-sciences.com/buildsv6/images/wide1920/5/5/a/55adb6fe27_50161581_pandemie-denisismagilov-adobe-stock.jpg"),
                    Category("Sciences","c'est la sciences", "https://cdn.futura-sciences.com/buildsv6/images/wide1920/a/0/2/a0269d7a2e_50155960_science-20e-siecle-artinspiring-fotolia.jpg"),
                    Category("Ecologie","c'est l'écologie", "https://youmatter.world/app/uploads/sites/3/2018/08/ecologie-solutions.jpg")
                )
                activity?.change(ChoiceFragment.newInstance(listModeResult))

            }
            "Pays" -> {
                listModeResult = listOf(
                    Country("US"),
                    Country("France"),
                    Country("Italie"),
                    Country("Grande Bretagne"),
                    Country("Finlande"),
                    Country("Chine")
                )
                activity?.change(ChoiceFragment.newInstance(listModeResult))


            }
        }
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