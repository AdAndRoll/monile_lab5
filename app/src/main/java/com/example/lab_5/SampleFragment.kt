package com.example.lab_5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SampleFragment : Fragment() {

    private var pageNumber: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sample, container, false)
        val pageTextView = view.findViewById<TextView>(R.id.pageTextView)

        // Отображаем номер страницы
        pageTextView.text = "Это страница номер $pageNumber"
        return view
    }

    // Метод для создания нового экземпляра фрагмента с номером страницы
    companion object {
        fun newInstance(pageNumber: Int): SampleFragment {
            val fragment = SampleFragment()
            val args = Bundle()
            args.putInt("page_number", pageNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageNumber = it.getInt("page_number")
        }
    }
}
