package com.example.lab_5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val backButton = view.findViewById<Button>(R.id.backButton)

        // Переход обратно на FirstFragment без удаления SecondFragment
        backButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .hide(this)
                .show(parentFragmentManager.findFragmentByTag("FirstFragment")!!)
                .commit()
        }

        return view
    }
}
