package com.jackandphantom.contactapp.contactdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackandphantom.contactapp.data.Result
import com.jackandphantom.contactapp.data.model.Contact
import com.jackandphantom.contactapp.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactDetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _contactData = MutableLiveData<Contact>()
    val contactData: LiveData<Contact> = _contactData

    private val _deleteData = MutableLiveData<Boolean>()
    val deleteData: LiveData<Boolean> = _deleteData

    private val _addToFavData = MutableLiveData<Boolean>()
    val addToFavData: LiveData<Boolean> = _addToFavData

    fun fetchContact(id: Int) {
        viewModelScope.launch {
            val result = repository.getContact(id)
            if (result is Result.Success) {
                _contactData.value = result.data!!
            }
        }
    }

    fun deleteContact(id: Int) {
        viewModelScope.launch {
            val result = repository.deleteContact(id)
            _deleteData.value = result is Result.Success
        }
    }

    fun updateFav(id: Int, fav: Boolean) {
        Log.e("MY TAG", "I AM IN fav")
        viewModelScope.launch {
            val result = repository.updateToFav(id, fav)
            Log.e("MY TAG", "I AM IN fav" + result)

            _addToFavData.value = result is Result.Success
        }
    }

}