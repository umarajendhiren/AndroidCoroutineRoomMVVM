package com.devtides.coroutinesroom.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    /*this insert function takes User as parameter and returns Primary kay which is Long
    * if the User already exist, new user will override old User because we are using "OnConflictStrategy.REPLACE"
    * all this function called by coroutine so this fun should be as suspend fun */
    @Insert(onConflict =OnConflictStrategy.REPLACE)
   suspend fun insertUser(user:User):Long


    @Query("SELECT * FROM user where username=:username")
   suspend fun getUser(username:String):User


    /*when we call this deleteUser(),this query will run .*/
    @Query("Delete From user where id=:id")
   suspend fun deleteUser(id:Long)

}