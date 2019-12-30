package com.mouhsin.kotlin.example.roomwordsamplekotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText

class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        editWordView = findViewById(R.id.edit_word)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editWordView.text)){
                setResult(Activity.RESULT_CANCELED , replyIntent)
            }else{
                val word = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK,  replyIntent)
            }

            clear(editWordView)
            button.hideKeyboard()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.mouhsin.kotlin.example.roomwordsamplekotlin.REPLY"
    }

    fun View.hideKeyboard(){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken , 0)
    }

    fun clear(edt: EditText){
        edt.text.clear()
    }
}
