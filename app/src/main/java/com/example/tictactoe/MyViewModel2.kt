package com.example.tictactoe
//for Main Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel2: ViewModel() {
    var player1_isActive = true
    var player1data = MutableLiveData<String>()
    var player2data = MutableLiveData<String>()
    var activePlayer = MutableLiveData<String>()
    fun updateData(name1: String, name2: String){
        player1data.value = name1
        player2data.value = name2
        change_ActivePlayer()
    }

    var arr : Array<Int> = arrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
    var matching = arrayOf(intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8),
                            intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8),
                            intArrayOf(0,4,8), intArrayOf(2,4,6))

    fun buttonClick(tag: Int): Int{
        if(arr[tag] != -1){
            return -1
        }

        if(activePlayer.value == player1data.value){
            arr[tag] = 0
            player1_isActive = false
            activePlayer.value = player2data.value
        }else {
            arr[tag] = 1
            player1_isActive = true
            activePlayer.value = player1data.value
        }

        return arr[tag]
    }

    fun refresh(){
        player1_isActive = true
        for(i in arr.indices){
            arr[i] = -1
        }
    }

    fun getData() = arr

    fun change_ActivePlayer(){
        if(player1_isActive){
            activePlayer.value = player1data.value
        }else{
            activePlayer.value = player2data.value
        }
    }

    fun checkWin(): Boolean{

        for(i in matching.indices){
            if(arr[matching[i][0]] == 1 && arr[matching[i][1]] == 1 && arr[matching[i][2]] == 1){
                return true
            }
            if(arr[matching[i][0]] == 0 && arr[matching[i][1]] == 0 && arr[matching[i][2]] == 0) {
                return true
            }
        }

        return false
    }

    fun checkDraw(): Boolean{
        var isEmpty = true
        for(i in arr.indices){
            if(arr[i] == -1){
                isEmpty = false
                break
            }
        }
        return isEmpty
    }
}