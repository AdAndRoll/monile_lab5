package com.example.lab_5


import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Task7 : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task7)

        // Инициализация WebView
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true  // Включаем поддержку JavaScript
        webView.webViewClient = WebViewClient()

        // Кнопка для открытия сайта
        val btnOpenSite: Button = findViewById(R.id.btnOpenSite)
        btnOpenSite.setOnClickListener {
            openFavoriteWebsite()
        }
        val NextBut= findViewById<Button>(R.id.nextBut)
        NextBut.setOnClickListener{
            val intent = Intent(this, Task8::class.java)
            startActivity(intent)
        }
    }

    // Функция для открытия любимого сайта
    private fun openFavoriteWebsite() {
        val url = "https://github.com/AdAndRoll?tab=repositories"  // Замените на ваш любимый сайт
        webView.loadUrl(url)
    }
}
