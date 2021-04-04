package com.jackandphantom.contactapp.data.db

import androidx.room.*
import com.jackandphantom.contactapp.data.model.Contact


@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("DELETE  FROM contact WHERE id=:id")
    suspend fun deleteContact(id: Int)

    @Query("SELECT * FROM contact")
    suspend fun getAllContact() : List<Contact>

    @Query("SELECT * FROM contact where id =:id")
    suspend fun getContact(id: Int): Contact

    @Query("UPDATE contact SET fav=:fav WHERE id=:id")
    suspend fun update(id: Int, fav:Boolean)

    @Query("SELECT * FROM contact Where fav=:fav")
    suspend fun getFavContactList(fav: Boolean = true): List<Contact>

}