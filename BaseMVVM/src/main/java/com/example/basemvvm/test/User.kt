package com.example.basemvvm.test

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    var userName:String,
    var password:String
){
    @PrimaryKey
    var id = 0
}