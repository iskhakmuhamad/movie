package com.movie.moviecatalog.core.data.source.remote

import android.util.Log
import com.movie.moviecatalog.core.data.source.remote.network.ApiResponse
import com.movie.moviecatalog.core.data.source.remote.network.ApiService
import com.movie.moviecatalog.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.result
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}