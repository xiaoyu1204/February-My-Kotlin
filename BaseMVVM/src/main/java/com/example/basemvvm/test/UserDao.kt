package com.example.basemvvm.test

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {
    //查询user表中的所有数据
    @get:Query("select * from user")
    val all:List<User?>?
}