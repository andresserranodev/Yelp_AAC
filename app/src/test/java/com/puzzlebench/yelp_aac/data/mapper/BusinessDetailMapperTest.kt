package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getDummyBusinessDetailResponse
import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getDummyListCategoriesEntity
import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getDummyListPhotosEntity
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessDetailResponse
import com.puzzlebench.yelp_aac.presentation.model.BusinessDetails
import junit.framework.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BusinessDetailMapperTest {

    private lateinit var mapper: BusinessDetailMapper

    @Before
    fun setUp() {
        mapper = BusinessDetailMapper()
    }

    @Test
    fun transformEntityToRepository() {
        val categoriesEntity = getDummyListCategoriesEntity()
        val photoEntity = getDummyListPhotosEntity()

        val result = mapper.transformEntityToRepository(categoriesEntity, photoEntity)
        result.categories.forEachIndexed { index, s ->
            assertEquals(categoriesEntity[index].title, s)
        }
        result.photos.forEachIndexed { index, s ->
            Assert.assertEquals(photoEntity[index].photoUrl, s)
        }
    }

    @Test
    fun transformServiceToRepository() {
        val response = getDummyBusinessDetailResponse("1")
        val result = mapper.transformServiceToRepository(response)
        assertDataEquality(response, result)
    }

    @Test
    fun transformRepositoryToCategoriesEntity() {
        val bushinessId = "1"
        val title = "title"
        val result = mapper.transformRepositoryToCategoriesEntity(bushinessId, title)
        assertEquals(result.businessId, bushinessId)
        assertEquals(result.title, title)
    }

    @Test
    fun transformPhotoRepositoryToEntity() {
        val bushinessId = "1"
        val urlPhoto = "www.yelp.com/photo.png"
        val result = mapper.transformPhotoRepositoryToEntity(bushinessId, urlPhoto)
        assertEquals(result.businessId, bushinessId)
        assertEquals(result.photoUrl, urlPhoto)
    }

    private fun assertDataEquality(response: BusinessDetailResponse, repository: BusinessDetails) {
        assertEquals(repository.businessId, response.businessId)
        response.categories.forEachIndexed { index, responseTitle ->
            assertEquals(repository.categories[index], responseTitle.title)
        }
        response.photos.forEachIndexed { index, responseTitle ->
            assertEquals(repository.photos[index], responseTitle)
        }
    }
}