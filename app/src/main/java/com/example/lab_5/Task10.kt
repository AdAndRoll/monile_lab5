package com.example.lab_5

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Task10 : AppCompatActivity() {

    private lateinit var listView: ListView
    private val items = listOf("Элемент 1", "Элемент 2", "Элемент 3", "Элемент 4", "Элемент 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task10)

        listView = findViewById(R.id.listView)

        // Инициализация адаптера для списка
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        // Обработка клика по элементу списка
        listView.setOnItemClickListener { _, view, position, _ ->
            showPopupMenu(view, position)
        }
        val NextBut= findViewById<Button>(R.id.nextBut)
        NextBut.setOnClickListener{
            val intent = Intent(this, Task11::class.java)
            startActivity(intent)
        }
    }

    private fun showPopupMenu(view: View, position: Int) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.context_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.option1 -> {
                    // Действие для опции 1
                    Toast.makeText(this, "Вы выбрали опцию 1 для ${items[position]}", Toast.LENGTH_SHORT).show()
                }
                R.id.option2 -> {
                    // Действие для опции 2
                    Toast.makeText(this, "Вы выбрали опцию 2 для ${items[position]}", Toast.LENGTH_SHORT).show()
                }
                else -> false
            }
            true
        }
        popupMenu.show()
    }
}
