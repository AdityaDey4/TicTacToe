package com.example.tictactoe

import android.content.DialogInterface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var myViewModel: MyViewModel2
    lateinit var arr: Array<Int>
    lateinit var al: ArrayList<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        myViewModel = ViewModelProvider(this).get(MyViewModel2::class.java)

        val name1: String? = intent.getStringExtra("name1")
        val name2: String? = intent.getStringExtra("name2")

        if (name1 != null) {
            if (name2 != null) {
                myViewModel.updateData(name1, name2)
            }
        }
        activityMainBinding.myVM = myViewModel
        activityMainBinding.lifecycleOwner = this

        al = arrayListOf()
        al.add(activityMainBinding.button0)
        al.add(activityMainBinding.button1)
        al.add(activityMainBinding.button2)
        al.add(activityMainBinding.button3)
        al.add(activityMainBinding.button4)
        al.add(activityMainBinding.button5)
        al.add(activityMainBinding.button6)
        al.add(activityMainBinding.button7)
        al.add(activityMainBinding.button8)

        arr = myViewModel.getData()
        setData(arr, al)

        activityMainBinding.button0.setOnClickListener(this)
        activityMainBinding.button1.setOnClickListener(this)
        activityMainBinding.button2.setOnClickListener(this)
        activityMainBinding.button3.setOnClickListener(this)
        activityMainBinding.button4.setOnClickListener(this)
        activityMainBinding.button5.setOnClickListener(this)
        activityMainBinding.button6.setOnClickListener(this)
        activityMainBinding.button7.setOnClickListener(this)
        activityMainBinding.button8.setOnClickListener(this)

        activityMainBinding.restart.setOnClickListener {
            myViewModel.refresh()
            myViewModel.player1_isActive = true
            myViewModel.change_ActivePlayer()
            setData(arr, al)
        }
    }

    override fun onClick(v: View?) {
        val clickButton: Button? = v?.id?.let { findViewById(it) }
        val click: String = clickButton?.tag.toString()
        val clickTag: Int = click.toInt()
        val ans = myViewModel.buttonClick(clickTag)

        if(ans == 1){
            clickButton?.text = "X"
            clickButton?.gravity = Gravity.CENTER
            clickButton?.textSize = 80F
            clickButton?.setTextColor(resources.getColor(R.color.cross))
        }
        else if(ans == 0){
            clickButton?.text = "O"
            clickButton?.gravity = Gravity.CENTER
            clickButton?.textSize = 80F
            clickButton?.setTextColor(resources.getColor(R.color.circle))
        }
        else{
            Toast.makeText(this, "This Button has been Clicked", Toast.LENGTH_SHORT).show()
        }
        if(myViewModel.checkWin()){
            var winner: String
            if(myViewModel.player1_isActive){
                winner = myViewModel.player2data.value.toString()
            }else{
                winner = myViewModel.player1data.value.toString()
            }
            AlertDialog.Builder(this)
                .setTitle("WINNER")
                .setMessage("Hurrey... $winner is the Winner")
                .setCancelable(false)
                .setPositiveButton("Close"){
                        dialog, which-> finish()
                }
                .setNegativeButton("Restart"){
                        dialog, which->
                            myViewModel.refresh()
                            myViewModel.player1_isActive = true
                            myViewModel.change_ActivePlayer()
                            setData(myViewModel.getData(), al)
                            dialog?.dismiss()
                }.show()
        }else{
            if(myViewModel.checkDraw()){
                AlertDialog.Builder(this)
                    .setTitle("Match Draw")
                    .setMessage("Opps...The Match has been drawn")
                    .setCancelable(false)
                    .setPositiveButton("Close"){
                            dialog, which-> finish()
                    }
                    .setNegativeButton("Restart"){
                        dialog, which->
                            myViewModel.refresh()
                            myViewModel.player1_isActive = true
                            myViewModel.change_ActivePlayer()
                            setData(myViewModel.getData(), al)
                            dialog?.dismiss()
                    }.show()
            }
        }
    }

    private fun setData(arr:Array<Int>, al:ArrayList<Button>){
        for(i in 0 until al.size){
            val clickButton = al[i]
            val click: String = clickButton.tag.toString()
            val clickTag: Int = click.toInt()
            if(arr[clickTag] == 1){
                clickButton.text = "X"
                clickButton.gravity = Gravity.CENTER
                clickButton.textSize = 80F
                clickButton.setTextColor(resources.getColor(R.color.cross))
            }
            else if(arr[clickTag] == 0){
                clickButton.text = "O"
                clickButton.gravity = Gravity.CENTER
                clickButton.textSize = 80F
                clickButton.setTextColor(resources.getColor(R.color.circle))
            }else{
                clickButton.text=""
                clickButton.setTextColor(resources.getColor(R.color.empty))
            }
        }
    }

}

