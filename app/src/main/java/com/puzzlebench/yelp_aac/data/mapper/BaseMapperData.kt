package com.puzzlebench.yelp_aac.data.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <S> the service model input type
 * @param <R> the Repository  model input type
 * @param <E> the Entity model input type
 */
interface BaseMapperData<S,  R,  E> {
    fun transformServiceToRepository(service: S): R
    fun transformEntityToRepository(entity: E): R
    fun transformRepositoryToEntity(repository: R): E
}
