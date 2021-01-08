package com.movie.moviecatalog.core.data

import com.movie.moviecatalog.core.data.source.local.LocalDataSource
import com.movie.moviecatalog.core.data.source.remote.RemoteDataSource
import com.movie.moviecatalog.core.data.source.remote.network.ApiResponse
import com.movie.moviecatalog.core.data.source.remote.response.MovieResponse
import com.movie.moviecatalog.core.domain.model.Movie
import com.movie.moviecatalog.core.domain.repository.InterfaceMovieRepository
import com.movie.moviecatalog.core.utils.AppExecutors
import com.movie.moviecatalog.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : InterfaceMovieRepository {

    override fun getMovies(): Flow<Resource<List<Movie>>> =
        object :
            NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movies = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movies)
            }
        }.asFlow()

    override fun getFavMovies(): Flow<List<Movie>> {
        return localDataSource.getFavMovies().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavMovie(movie: Movie, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavMovie(movieEntity, newState) }
    }
}