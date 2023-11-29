package com.juanlu

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CreditsActivity : AppCompatActivity() {
    @SuppressLint("IntentReset", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        val appName = findViewById<TextView>(R.id.App_Name)

        val name = intent.getStringExtra("NAME")
        appName.apply {
            text = getString(R.string.app_description, name)
        }

        val btContact = findViewById<Button>(R.id.btContact)
        btContact!!.setOnClickListener {
            val email = "ajldominguez@ieslamarisma.net"
            val appName = "AOSP PROJECT"
            val intentMail = Intent(Intent.ACTION_SEND, Uri.parse(email))

            intentMail.type = "plain/text"
            intentMail.putExtra(Intent.EXTRA_SUBJECT, "Consulta de la app $appName")
            startActivity(Intent.createChooser(intentMail, "Elige un cliente de correo"))
        }
    }
}