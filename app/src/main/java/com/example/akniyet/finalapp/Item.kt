package com.example.akniyet.finalapp

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "items")

data class Item (

    @PrimaryKey(autoGenerate = true)
    val id : Int,

    val name : String,
    val number : String,
    val home : String,
    val mobile : String

)