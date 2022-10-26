package com.chama.chefcito_2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private var pink = "#FE6C6C"
    private var grey = "#4E4E4E"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sing_up_fragment)
    }
}