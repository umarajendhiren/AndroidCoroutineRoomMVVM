package com.devtides.coroutinesroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*This is the structure of table in room database*/
@Entity
data class User(
    val username: String,

    //when we send passwordHash to database ,it will stored as password_hash
    @ColumnInfo(name="password_hash")
    val passwordHash: String,

    val info: String
)
{

    /*primary key will be autogenerate and default value is 0*/
    @PrimaryKey(autoGenerate = true)
    var id:Long=0
}