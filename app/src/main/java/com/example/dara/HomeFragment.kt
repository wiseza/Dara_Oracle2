package com.example.dara

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnMain = view.findViewById<Button>(R.id.btn_main)
        val btnset = view.findViewById<View>(R.id.btn_setting)

        btnMain.setOnClickListener {
            val infoFragment = InfoFragment()
            val transaction = parentFragmentManager.beginTransaction()

            transaction.replace(R.id.contentContainer, infoFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnset.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, SettingFragment())
                .addToBackStack(null)
                .commit()
        }

    }
}
