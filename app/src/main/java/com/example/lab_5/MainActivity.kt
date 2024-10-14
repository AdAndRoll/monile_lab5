package com.example.lab_5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    // Переменная для отслеживания глубины стека
    private var stackDepth: Int = 0

    // Массив для хранения страниц (фрагментов)
    private val pageFragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация кнопок
        val addPageButton = findViewById<Button>(R.id.addPageButton)
        val removePageButton = findViewById<Button>(R.id.removePageButton)

        val arguments = intent.extras
        if (arguments != null && arguments.containsKey("stackDepth")) {
            findViewById<TextView>(R.id.depthTextView).text = arguments.getInt("stackDepth").toString()
            stackDepth++
        } else {
            findViewById<TextView>(R.id.depthTextView).text = "0"
        }

        // Добавление новой страницы
        addPageButton.setOnClickListener {
            Log.d("MainActivity", "add button")
//            val newFragment = SampleFragment.newInstance(stackDepth + 1)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("stackDepth", stackDepth+1)
            startActivity(intent)

//            pageFragments.add(newFragment) // Добавляем новый фрагмент в список
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, newFragment)
//                .addToBackStack(null)
//                .commit()
        }

        // Удаление последней страницы
        removePageButton.setOnClickListener {
            if (stackDepth > 0) {
                finish()
//                supportFragmentManager.popBackStack()
//                pageFragments.removeAt(pageFragments.size - 1) // Удаляем последний фрагмент
//                stackDepth--
            }
        }
        val next = findViewById<Button>(R.id.nextTask)
        next.setOnClickListener{
            val intent = Intent(this, Task2Activity::class.java)
            startActivity(intent)
        }

    }
}
