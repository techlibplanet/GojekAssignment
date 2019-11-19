package com.example.gojekassignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gojekassignment.database.dao.GithubRepositoriesDao
import com.example.gojekassignment.database.entities.GithubRepositories
import com.example.gojekassignment.helper.Converters

@Database(
    entities = [
        GithubRepositories::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class GojekDatabase : RoomDatabase() {
    abstract fun githubRepositoriesDao() : GithubRepositoriesDao
}