package com.example.tictactoe

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.databinding.ActivityFirstLayoutBinding
import java.lang.reflect.Array.get

class FirstLayout : AppCompatActivity() {
    lateinit var myViewModel: MyViewModel
    lateinit var firstLayoutBinding: ActivityFirstLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       firstLayoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_first_layout)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        firstLayoutBinding.myMV = myViewModel
        firstLayoutBinding.lifecycleOwner = this

        firstLayoutBinding.start.setOnClickListener {
           var name1 = firstLayoutBinding.player1Name.text.toString()
            if(name1.isEmpty()){
                name1 = "Player 1"
            }
            var name2 = firstLayoutBinding.player2Name.text.toString()
            if(name2.isEmpty()){
                name2 = "Player 2"
            }
            var intent1 = Intent(this,MainActivity::class.java)
            intent1.putExtra("name1", name1);
            intent1.putExtra("name2", name2)
            startActivity(intent1)
        }
    }

    override fun onBackPressed() {
        val d = AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_baseline_warning_24)
            .setTitle("Quit")
            .setMessage("Do you want to quit from the app ?")
            .setPositiveButton("yes"){
                    dialog, which->finish()
            }
            .setNegativeButton("No",object:  DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                }

            }).show()
    }
}