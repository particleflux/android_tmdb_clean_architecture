package com.rodrigobresan.presentation.movies.presenter

import com.nhaarman.mockito_kotlin.*
import com.rodrigobresan.domain.movie_detail.interactor.GetMovieDetails
import com.rodrigobresan.domain.movie_detail.model.MovieDetail
import com.rodrigobresan.presentation.movie_details.contract.MovieDetailsContract
import com.rodrigobresan.presentation.movie_details.mapper.MovieDetailsMapper
import com.rodrigobresan.presentation.movie_details.presenter.MovieDetailsPresenter
import com.rodrigobresan.presentation.movies.factory.MovieDetailFactory
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Class for testing MoviePresenter class
 */
@RunWith(JUnit4::class)
class MovieDetailsPresenterTest {

    private lateinit var movieDetailsPresenter: MovieDetailsPresenter
    private lateinit var movieDetailsView: MovieDetailsContract.View
    private lateinit var getMovieDetails: GetMovieDetails
    private lateinit var movieDetailsMapper: MovieDetailsMapper

    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<MovieDetail>>

    @Before
    fun setUp() {
        captor = argumentCaptor<DisposableSingleObserver<MovieDetail>>()

        movieDetailsView = mock()
        getMovieDetails = mock()
        movieDetailsMapper = mock()

        movieDetailsPresenter = MovieDetailsPresenter(movieDetailsView, getMovieDetails, movieDetailsMapper)
    }

    @Test
    fun loadMovieDetailsHideErrorState() {
        movieDetailsPresenter.loadMovieDetails(0)

        verify(getMovieDetails).execute(captor.capture(), eq(0))
        captor.firstValue.onSuccess(MovieDetailFactory.makeMovieDetail())
        verify(movieDetailsView).hideErrorState()
    }

    @Test
    fun loadMovieDetailsShowMovie() {
        val movie = MovieDetailFactory.makeMovieDetail()
        movieDetailsPresenter.loadMovieDetails(0)

        verify(getMovieDetails).execute(captor.capture(), eq(0))
        captor.firstValue.onSuccess(movie)

        verify(movieDetailsView).showMovieDetails(movieDetailsMapper.mapToView(movie))
    }

    @Test
    fun loadMovieDetailsShowErrorState() {
        movieDetailsPresenter.loadMovieDetails(0)

        verify(getMovieDetails).execute(captor.capture(), eq(0))
        captor.firstValue.onError(RuntimeException())
        verify(movieDetailsView).showErrorState()
    }
}