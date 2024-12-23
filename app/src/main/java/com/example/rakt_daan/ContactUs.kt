package com.example.rakt_daan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ContactUs : Fragment() {
    private var rootView: View? = null
    private var backImg: ImageView? = null
    private var doneButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_contact_us, container, false)

        initializeViews()
        setupClickListeners()

        return rootView
    }

    private fun initializeViews() {
        rootView?.let {
            backImg = it.findViewById(R.id.contact_us_image_back)
            doneButton = it.findViewById(R.id.done_btn)
        }
    }

    private fun setupClickListeners() {
        backImg?.setOnClickListener { activity?.onBackPressed() }
    }
}
