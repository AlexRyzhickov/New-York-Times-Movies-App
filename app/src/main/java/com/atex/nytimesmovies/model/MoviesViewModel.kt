package com.atex.nytimesmovies.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.atex.nytimesmovies.CharacterPagingSource
import com.atex.nytimesmovies.entities.Movie
import com.atex.nytimesmovies.network.RetroInstance
import com.atex.nytimesmovies.network.RetroService
import kotlinx.coroutines.flow.Flow

class MoviesViewModel: ViewModel() {

    lateinit var retroService: RetroService

    init {
        retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
    }

    fun getListData(): Flow<PagingData<Movie>> {
        return Pager (config = PagingConfig(pageSize = 1),
        pagingSourceFactory = { CharacterPagingSource(retroService) }).flow.cachedIn(viewModelScope)
    }
}