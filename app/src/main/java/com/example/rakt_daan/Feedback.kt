package com.example.rakt_daan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class Feedback : Fragment() {
    private var rootView: View? = null
    private var backImg: ImageView? = null
    private var doneButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_feedback, container, false)

        findIds()
        setOnClicks()

        return rootView
    }

    private fun findIds() {
        backImg = rootView?.findViewById(R.id.feedback_image_back)
        doneButton = rootView?.findViewById(R.id.done_btn)
    }

    private fun setOnClicks() {
        backImg?.setOnClickListener { requireActivity().onBackPressed() }

        doneButton?.setOnClickListener {
            Toast.makeText(context, "Thanks For Your Feedback", Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressed()
        }
    }
}
