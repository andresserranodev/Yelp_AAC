package com.puzzlebench.yelp_aac.data.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <S> the service model input type
 * @param <R> the Repository  model input type
 */
interface BaseMapperData<S, R> {
    fun transformServiceToRepository(service: S): R
}