package com.atex.nytimesmovies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.atex.nytimesmovies.entities.Movie
import com.atex.nytimesmovies.network.RetroService

class CharacterPagingSource(val apiService: RetroService) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextOffset: Int = params.key ?: FIRST_OFFSET_INDEX
            val response = apiService.getDataFromAPI(nextOffset, API_KEY)

            val prevOffset = if (nextOffset == 0) null else nextOffset - OFFSET_STEP

            LoadResult.Page(
                data = response.results,
                prevKey = prevOffset,
                nextKey = nextOffset.plus(OFFSET_STEP)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_OFFSET_INDEX = 0
        private const val OFFSET_STEP = 20
        private const val API_KEY = "fkFqxGF9adrZxHfs8JznDwfwk5ec0nul"
    }


}