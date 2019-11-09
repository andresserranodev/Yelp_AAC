package com.puzzlebench.yelp_aac.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.data.local.room.dao.CategoriesDao
import com.puzzlebench.yelp_aac.data.local.room.dao.PhotoDao
import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.data.local.room.entity.CategoriesEntity
import com.puzzlebench.yelp_aac.data.local.room.entity.PhotoEntity

@Database(
    entities = [BusinessEntity::class, CategoriesEntity::class, PhotoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class YelpDataBase : RoomDatabase() {
    abstract fun businessDao(): BusinessDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun photoDao(): PhotoDao


}