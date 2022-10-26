package com.example.nytimes.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.nytimes.R
import com.example.nytimes.data.ArticlesDetails


class ArticlesDescriptionActivity : AppCompatActivity() {
    private val imageList = ArrayList<SlideModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_description)

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        val tvTitle : TextView = findViewById(R.id.titleTv)
        val tvDescription : TextView = findViewById(R.id.descriptionTv)
        val tvWriter : TextView = findViewById(R.id.article_writer)
        val tvDate : TextView = findViewById(R.id.article_date)
        val tvKeyWords : TextView = findViewById(R.id.keywordsTv)

        val article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("article",ArticlesDetails::class.java)
        } else {
            intent.getSerializableExtra("article") as ArticlesDetails

        }

/*

 واعمل السيرش ف الاكتفتي الاوله و ظبط الريد مي و الاسكرينات و اشرح انت مستخدم اى فى البروجيكت

*/

        if (article != null) {
            if(article.media.isEmpty())
                imageSlider.visibility=View.GONE
            else
                for (image in article.media[0].mediaMetadata) {
                    imageList.add(SlideModel(image.url, ""))
                }

            imageSlider.setImageList(imageList)
            tvTitle.text=article.title
            tvDescription.text=article.abstract
            tvWriter.text=article.byline
            tvDate.text=article.publishedDate
            tvKeyWords.text=article.adxKeywords
        }


    }
}