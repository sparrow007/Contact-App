package com.jackandphantom.contactapp.contactmanipulate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackandphantom.contactapp.data.Result
import com.jackandphantom.contactapp.data.model.Contact
import com.jackandphantom.contactapp.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactManipulateViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _dataInserted = MutableLiveData<Boolean>()
    val dataInserted: LiveData<Boolean> = _dataInserted


    private val _contactData = MutableLiveData<Contact>()
    val contactData: LiveData<Contact> = _contactData

    fun insertContact(name: String, phone: String, image:String) {
        viewModelScope.launch {
            val contact = Contact(name = name, phone = phone, image = image)
            val result = repository.insertContact(contact)
            _dataInserted.value = result is Result.Success
        }
    }

    fun updateContact(id: Int, name: String, phone: String, image: String) {
        viewModelScope.launch {
            val contact = Contact(id, name, phone, image)
            val result = repository.updateContact(contact)
            _dataInserted.value = result is Result.Success
        }
    }

    fun fetchContact(id: Int) {
        viewModelScope.launch {
            val result = repository.getContact(id)
            if (result is Result.Success) {
                _contactData.value = result.data!!
            }
        }
    }

}