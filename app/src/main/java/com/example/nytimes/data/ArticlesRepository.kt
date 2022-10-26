package com.example.nytimes.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.nytimes.api.ArticlesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


class ArticlesRepository @Inject constructor(private val api: ArticlesApi)   {




    private val _isLoading = MutableStateFlow(true)

    val isLoading: LiveData<Boolean>
        get() = _isLoading.asLiveData()


    private val articlesLiveData = MutableStateFlow<List<Result>>(listOf())

    val articles: LiveData<List<Result>>
        get() = articlesLiveData.asLiveData()




    fun getArticles() {

        CoroutineScope(Dispatchers.IO).launch {
            _isLoading.emit(true)
            try {
                articlesLiveData.emit(api.getArticles().results)
                _isLoading.emit(false)
            } catch (e: Exception) {
                Log.d("bego",e.message.toString())
                _isLoading.emit(false)
            }

        }

    }

}