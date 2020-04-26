package com.hugolefrant.miniproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.hugolefrant.miniproject.R
import models.Article


@GlideModule

class ArticleAdapter(private val dataset: List<Article>, val itemClickListener: OnArticleClickListener) :
RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataset[position], itemClickListener)

    override fun getItemCount(): Int = dataset.size

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(
            item: Article,
            itemClickListener: OnArticleClickListener
        ) {
            val txttitle = root.findViewById<TextView>(R.id.article_title)
            val txt_description = root.findViewById<TextView>(R.id.article_description)
            val txtdate = root.findViewById<TextView>(R.id.article_date)
            val txtsource = root.findViewById<TextView>(R.id.article_source)

            txttitle.text = item.title
            txt_description.text = item.description
            txtdate.text = item.publishedAt
            txtsource.text = item.source.name

            val img = root.findViewById<ImageView>(R.id.article_image)
            Glide.with(root).load(item.urlToImage).into(img)

            val shareButton = root.findViewById<Button>(R.id.share)
            shareButton.setOnClickListener {
                itemClickListener.shareButtonClicked(item)
            }

            root.setOnClickListener {
                itemClickListener.onItemClicked(item)
            }
        }
    }
}

interface OnArticleClickListener{
    fun onItemClicked(article: Article)
    fun shareButtonClicked(article: Article)
}