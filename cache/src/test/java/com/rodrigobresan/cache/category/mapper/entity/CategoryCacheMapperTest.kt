package com.rodrigobresan.cache.mapper

import com.rodrigobresan.cache.category.mapper.entity.CategoryCacheMapper
import com.rodrigobresan.cache.category.model.CategoryCached
import com.rodrigobresan.cache.test.factory.CategoryFactory
import com.rodrigobresan.data.category.model.CategoryEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

/**
 * Class for testing CategoryCacheMapper class
 */
@RunWith(JUnit4::class)
class CategoryCacheMapperTest {

    private lateinit var categoryCacheMapper: CategoryCacheMapper

    @Before
    fun setUp() {
        categoryCacheMapper = CategoryCacheMapper()
    }

    @Test
    fun mapFromCachedMapsData() {
        val categoryCached = CategoryFactory.makeCategoryCached()
        val categoryEntity = categoryCacheMapper.mapFromCached(categoryCached)

        assertCategoryDataEquality(categoryEntity, categoryCached)
    }

    @Test
    fun mapToCachedMapsData() {
        val categoryEntity = CategoryFactory.makeCategoryEntity()
        val categoryCached = categoryCacheMapper.mapToCached(categoryEntity)

        assertCategoryDataEquality(categoryEntity, categoryCached)
    }

    private fun assertCategoryDataEquality(categoryEntity: CategoryEntity, categoryCached: CategoryCached) {
        assertEquals(categoryEntity.id, categoryCached.id)
        assertEquals(categoryEntity.name, categoryCached.name)
    }
}