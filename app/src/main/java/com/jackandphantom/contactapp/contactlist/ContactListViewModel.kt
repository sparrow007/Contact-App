package com.jackandphantom.contactapp.contactlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackandphantom.contactapp.data.Result
import com.jackandphantom.contactapp.data.model.Contact
import com.jackandphantom.contactapp.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _dataList = MutableLiveData<List<Contact>>()
    val dataList: LiveData<List<Contact>> = _dataList

    private val _dataFavList = MutableLiveData<List<Contact>>()
    val dataFavList: LiveData<List<Contact>> = _dataFavList

    fun getAllList() {
        viewModelScope.launch {
            val result = repository.getContactList()
            if (result is Result.Success) {
                _dataList.value = result.data!!
            }
        }
    }

    fun getAllFavList() {
        viewModelScope.launch {
            val result = repository.getFavContactList()
            if (result is Result.Success) {
                _dataFavList.value = result.data!!
            }
        }
    }

}