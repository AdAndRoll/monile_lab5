package com.example.lab_5


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var isSecondFragmentAdded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val addPageButton = view.findViewById<Button>(R.id.addPageButton)
        val removePageButton = view.findViewById<Button>(R.id.removePageButton)

        // Добавляем SecondFragment и скрываем FirstFragment
        addPageButton.setOnClickListener {
            if (!isSecondFragmentAdded) {
                parentFragmentManager.beginTransaction()
                    .hide(this)  // Скрываем текущий фрагмент (FirstFragment)
                    .add(R.id.fragmentContainer, SecondFragment(), "SecondFragment")
                    .addToBackStack(null)  // Для возможности возврата
                    .commit()
                isSecondFragmentAdded = true
            }
        }

        // Удаляем SecondFragment и показываем FirstFragment
        removePageButton.setOnClickListener {
            val secondFragment = parentFragmentManager.findFragmentByTag("SecondFragment")
            if (secondFragment != null && isSecondFragmentAdded) {
                parentFragmentManager.beginTransaction()
                    .remove(secondFragment)  // Удаляем SecondFragment
                    .show(this)  // Показываем FirstFragment
                    .commit()
                isSecondFragmentAdded = false
            }
        }

        return view
    }

}



