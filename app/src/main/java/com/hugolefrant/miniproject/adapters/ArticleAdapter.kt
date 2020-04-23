package com.hugolefrant.miniproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.hugolefrant.miniproject.R
import models.Article


@GlideModule

class ArticleAdapter(private val dataset: List<Article>) :
RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataset[position])

    override fun getItemCount(): Int = dataset.size

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(item: Article) {
            val txttitle = root.findViewById<TextView>(R.id.article_title)
            val txt_description = root.findViewById<TextView>(R.id.article_description)
            //val txt_author = root.findViewById<TextView>(R.id.article_author)
            val txtdate = root.findViewById<TextView>(R.id.article_date)
            //val txt_content = root.findViewById<TextView>(R.id.article_content)
            val txtsource = root.findViewById<TextView>(R.id.article_source)

            val img = root.findViewById<ImageView>(R.id.article_image)
            Glide.with(root.context).load(item.urlToImage).fitCenter().into(img)

            txttitle.text = item.name
            txt_description.text = item.description
            //txt_author.text = item.author
            txtdate.text = item.publishedAt
            //txt_content.text = item.content
            txtsource.text = item.source.name

        }
    }
}