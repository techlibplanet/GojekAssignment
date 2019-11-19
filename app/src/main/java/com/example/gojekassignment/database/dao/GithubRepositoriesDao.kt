package com.example.gojekassignment.database.dao

import androidx.room.Dao

@Dao
interface GithubRepositoriesDao {

//    @Query("SELECT * FROM comments")
//    fun getAll(): List<Comments>
//
//    // If the user received and deal request
//    @Query("SELECT * FROM comments WHERE email_share_to = :email AND dealId = :dealId")
//    fun getCommentsByEmail(email: String, dealId : String?): List<Comments>
//
//    // To get comments by deald
//    @Query("SELECT * FROM comments WHERE dealId = :dealId")
//    fun getCommentsByDealId(dealId: String?): List<Comments>
//
//    // To get the comments for trip lead
//    @Query("SELECT * FROM comments WHERE dealId = :dealId AND email_share_to = :email")
//    fun getCommentsByDealIdAndEmailShareTo(email: String, dealId: String?): MutableList<Comments>
//
//    // To get the comments for User
//    @Query("SELECT * FROM comments WHERE dealId = :dealId AND email_share_from = :email")
//    fun getCommentsByDealIdAndEmailShareFrom(email: String, dealId: String?): MutableList<Comments>
//
//
//    @Insert
//    fun insert(vararg comments: Comments)
//
//    @Query("DELETE FROM comments")
//    fun deleteComments()
}