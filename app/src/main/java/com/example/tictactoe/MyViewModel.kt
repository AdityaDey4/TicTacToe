package com.example.tictactoe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var player1data = MutableLiveData<String>()
    var player2data = MutableLiveData<String>()
}