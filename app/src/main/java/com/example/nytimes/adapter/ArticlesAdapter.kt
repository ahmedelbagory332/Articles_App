package com.example.nytimes.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytimes.R
import com.example.nytimes.data.Result
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject

class ArticlesAdapter @Inject constructor(private val ctx:Application) :
    ListAdapter<Result, ArticlesAdapter.DataViewHolder>(ArticlesDiffCallback) {
     var onArticlesClick: ((Result) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.articles_item, parent, false))

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val articles = getItem(position)

        if(articles.media.isNotEmpty())
        Glide.with(ctx)
            .load(articles.media[0].mediaMetadata[0].url)
            .into( holder.articlesImage)
        else
            holder.articlesImage.setImageResource(R.drawable.ic_baseline_broken_image_24)

        holder.articlesTitle.text = articles.title
        holder.articlesWriter.text = articles.byline
        holder.articlesDate.text = articles.publishedDate
        holder.articlesDescription.setOnClickListener {
            onArticlesClick!!.invoke(articles)
        }
    }


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val articlesTitle : TextView = itemView.findViewById(R.id.article_title)
        val articlesWriter : TextView = itemView.findViewById(R.id.article_writer)
        val articlesDate : TextView = itemView.findViewById(R.id.article_date)
        val articlesImage : CircleImageView = itemView.findViewById(R.id.article_image)
        val articlesDescription : ImageView = itemView.findViewById(R.id.article_description)

    }




}

object ArticlesDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }
}