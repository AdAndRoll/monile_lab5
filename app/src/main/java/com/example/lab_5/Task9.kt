package com.example.lab_5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Task9 : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task9)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        textView = findViewById(R.id.textView)

        swipeRefreshLayout.setOnRefreshListener {
            // Открыть верхнее меню по свайпу
            showTopMenu()
            swipeRefreshLayout.isRefreshing = false // Остановить анимацию
        }

        // Установка слушателя для нижнего меню
        findViewById<View>(R.id.contentFrame).setOnClickListener {
            showBottomSheetDialog()
        }
        val NextBut= findViewById<Button>(R.id.nextBut)
        NextBut.setOnClickListener{
            val intent = Intent(this, Task10::class.java)
            startActivity(intent)
        }
    }

    private fun showTopMenu() {
        // Здесь вы можете указать логику для верхнего меню.
        // Например, вы можете показать диалог с выбором элементов.
        val items = arrayOf("Элемент 4", "Элемент 5", "Элемент 6")
        val dialog = TopMenuDialogFragment(items) { selectedItem ->
            textView.text = "Выбрано: $selectedItem"
        }
        dialog.show(supportFragmentManager, "TopMenuDialog")
    }

    private fun showBottomSheetDialog() {
        val dialog = BottomMenuDialogFragment { selectedItem ->
            textView.text = "Выбрано: $selectedItem"
        }
        dialog.show(supportFragmentManager, "BottomMenuDialog")
    }
}

class BottomMenuDialogFragment(private val onItemSelected: (String) -> Unit) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_menu, container, false)
        view.findViewById<View>(R.id.item1).setOnClickListener {
            onItemSelected("Элемент 1")
            dismiss()
        }
        view.findViewById<View>(R.id.item2).setOnClickListener {
            onItemSelected("Элемент 2")
            dismiss()
        }
        view.findViewById<View>(R.id.item3).setOnClickListener {
            onItemSelected("Элемент 3")
            dismiss()
        }
        return view
    }
}

class TopMenuDialogFragment(private val items: Array<String>, private val onItemSelected: (String) -> Unit) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.top_menu, container, false)
        // Здесь можно реализовать логику для отображения элементов верхнего меню
        for (i in items.indices) {
            view.findViewById<TextView>(view.context.resources.getIdentifier("item${i + 1}", "id", view.context.packageName)).apply {
                text = items[i]
                setOnClickListener {
                    onItemSelected(items[i])
                    dismiss()
                }
            }
        }
        return view
    }
}
