package com.example.lab_5

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Task3 : AppCompatActivity() {

    private lateinit var txtInputText: TextView
    private lateinit var txtSelectedDate: TextView
    private lateinit var txtSelectedTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task3)

        // Инициализация элементов интерфейса
        txtInputText = findViewById(R.id.txtInputText)
        txtSelectedDate = findViewById(R.id.txtSelectedDate)
        txtSelectedTime = findViewById(R.id.txtSelectedTime)

        // Кнопка для ввода текста через диалог
        val btnInputText: Button = findViewById(R.id.btnInputText)
        btnInputText.setOnClickListener {
            showTextInputDialog()
        }

        // Кнопка для выбора даты через диалог
        val btnSelectDate: Button = findViewById(R.id.btnSelectDate)
        btnSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Кнопка для выбора времени через диалог
        val btnSelectTime: Button = findViewById(R.id.btnSelectTime)
        btnSelectTime.setOnClickListener {
            showTimePickerDialog()
        }
        val NextBut= findViewById<Button>(R.id.nextBut)
        NextBut.setOnClickListener{
            val intent = Intent(this, Task6::class.java)
            startActivity(intent)
        }
    }

    // Диалог для ввода текста
    private fun showTextInputDialog() {
        val inputField = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("Введите текст")
            .setView(inputField)
            .setPositiveButton("OK") { dialog, _ ->
                val enteredText = inputField.text.toString()
                txtInputText.text = enteredText
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    // Диалог для выбора даты
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                txtSelectedDate.text = selectedDate
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    // Диалог для выбора времени
    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                txtSelectedTime.text = selectedTime
            },
            hour, minute, true
        )
        timePickerDialog.show()
    }

}
