package com.example.testeve

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    /* **********************************************************************
     * Variable
     ********************************************************************** */

    var isCheck = MutableLiveData<Boolean?>()
}