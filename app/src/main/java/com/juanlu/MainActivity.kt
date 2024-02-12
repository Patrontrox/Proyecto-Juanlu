package com.juanlu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.juanlu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun nextPage() {
        val btCredits = findViewById<Button>(R.id.btCredit)
        btCredits.setOnClickListener {
            val idUser = findViewById<EditText>(R.id.idUser)
            val intent = Intent(this@MainActivity, CreditsActivity::class.java)
            intent.putExtra("app_name", "AOSP")
            intent.putExtra("NAME", idUser.text.toString())
            startActivity(intent)
        }
    }
}