package com.devtides.coroutinesroom.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    /*this insert function takes User as parameter and returns Primary kay which is Long
    * if the User already exist, new user will override old User because we are using "OnConflictStrategy.REPLACE" */
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun insertUser(user:User):Long


    @Query("SELECT * FROM user where username=:username")
    fun getUser(username:String):User


    /*when we call this deleteUser(),this query will run .*/
    @Query("Delete From user where id=:id")
    fun deleteUser(id:Long)

}