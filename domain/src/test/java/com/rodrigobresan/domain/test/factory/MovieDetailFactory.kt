package com.rodrigobresan.domain.test.factory

import com.rodrigobresan.base.DataFactory
import com.rodrigobresan.domain.movie_detail.model.MovieDetail
import com.rodrigobresan.domain.movies.model.Movie

class MovieDetailFactory {
    companion object Factory {
        fun makeMovie(): MovieDetail {
            return MovieDetail(DataFactory.randomLong(), DataFactory.randomUuid(),
                    DataFactory.randomDouble(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                    DataFactory.randomUuid(), DataFactory.randomUuid())
        }
    }
}