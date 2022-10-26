package com.example.nytimes.ui
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.nytimes.data.ArticlesRepository
import com.example.nytimes.data.Result


@HiltViewModel
class ArticlesViewModel @Inject constructor(private val articlesRepository: ArticlesRepository) : ViewModel() {

    val isLoading: LiveData<Boolean> = articlesRepository.isLoading
    val restaurants: LiveData<List<Result>> = articlesRepository.articles

    fun getArticles()=articlesRepository.getArticles()
}
