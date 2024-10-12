package com.example.lab_5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    // Переменная для отслеживания глубины стека
    private var stackDepth: Int by Delegates.observable(0) { _, _, newValue ->
        findViewById<TextView>(R.id.depthTextView).text = "Текущая глубина стека: $newValue"
    }

    // Массив для хранения страниц (фрагментов)
    private val pageFragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация кнопок
        val addPageButton = findViewById<Button>(R.id.addPageButton)
        val removePageButton = findViewById<Button>(R.id.removePageButton)

        // Добавление новой страницы
        addPageButton.setOnClickListener {
            val newFragment = SampleFragment.newInstance(stackDepth + 1)
            pageFragments.add(newFragment) // Добавляем новый фрагмент в список
            stackDepth++
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, newFragment)
                .addToBackStack(null)
                .commit()
        }

        // Удаление последней страницы
        removePageButton.setOnClickListener {
            if (stackDepth > 0) {
                supportFragmentManager.popBackStack()
                pageFragments.removeAt(pageFragments.size - 1) // Удаляем последний фрагмент
                stackDepth--
            }
        }
        val next = findViewById<Button>(R.id.nextTask)
        next.setOnClickListener{
            val intent = Intent(this, Task2Activity::class.java)
            startActivity(intent)
        }

    }
}
