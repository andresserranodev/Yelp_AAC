package com.puzzlebench.yelp_aac.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.data.local.room.dao.CategoriesDao
import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.data.local.room.entity.CategoriesEntity

@Database(
    entities = [BusinessEntity::class, CategoriesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class YelpDataBase : RoomDatabase() {
    abstract fun businessDao(): BusinessDao
    abstract fun categoriesDao(): CategoriesDao

}