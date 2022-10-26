package com.example.nytimes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimes.R
import com.example.nytimes.adapter.ArticlesAdapter
import com.example.nytimes.data.ArticlesDetails
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllArticlesActivity : AppCompatActivity() {
    private val viewModel: ArticlesViewModel by viewModels()
    @Inject
    lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restaurantsList: RecyclerView = findViewById(R.id.recycler_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val noConnection:ImageView = findViewById(R.id.noNetWork)

        restaurantsList.adapter = articlesAdapter
        viewModel.getArticles()

        viewModel.restaurants.observe(this, Observer {
            Log.d("bego",it.size.toString())
            articlesAdapter.submitList(it)
        })


        viewModel.isLoading.observe(this, Observer {
            if (it)
                progressBar.visibility = View.VISIBLE
            else {
                noConnection.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })

        articlesAdapter.onArticlesClick = {
            val intent = Intent(this@AllArticlesActivity,ArticlesDescriptionActivity::class.java)
            val article = ArticlesDetails(it.title,it.abstract,it.byline,it.publishedDate,it.adxKeywords,it.media)
            intent.putExtra("article", article)
            startActivity(intent)
        }

    }



}