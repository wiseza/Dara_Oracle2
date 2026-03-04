package com.example.dara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dara.R

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnop = view.findViewById<View>(R.id.btn_open)

        btnop.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, WorkFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
