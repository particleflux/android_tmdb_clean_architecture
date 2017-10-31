package com.rodrigobresan.remote.movies.impl

import com.rodrigobresan.data.movie.model.MovieEntity
import com.rodrigobresan.data.movie.sources.MovieRemote
import com.rodrigobresan.remote.movies.mapper.MovieEntityMapper
import com.rodrigobresan.remote.movies.model.movies.MovieResponse
import com.rodrigobresan.remote.service.MovieService
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteImpl @Inject constructor(private val service: MovieService,
                                          private val movieEntityMapper: MovieEntityMapper)
    : MovieRemote {

    override fun getNowPlayingMovies(): Single<List<MovieEntity>> {
        return mapItems(service.getNowPlayingMovies())
    }

    override fun getTopRatedMovies(): Single<List<MovieEntity>> {
        return mapItems(service.getTopRatedMovies())
    }

    override fun getUpcomingMovies(): Single<List<MovieEntity>> {
        return mapItems(service.getUpcomingMovies())
    }

    override fun getPopularMovies(): Single<List<MovieEntity>> {
        return mapItems(service.getPopularMovies())
    }

    private fun mapItems(movieResponse: Single<MovieResponse>): Single<List<MovieEntity>> {
        return movieResponse.map {
            it.results.map {
                item ->
                movieEntityMapper.mapRemoteToEntity(item)
            }
        }
    }

}