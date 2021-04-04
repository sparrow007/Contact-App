package com.jackandphantom.contactapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @ColumnInfo(name = "id")@PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "phone") var phone: String = "",
    @ColumnInfo(name = "image") var image: String = "",
    @ColumnInfo(name = "fav") var fav: Boolean = false
)
