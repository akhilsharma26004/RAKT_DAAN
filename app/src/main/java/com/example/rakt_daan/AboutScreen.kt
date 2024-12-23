package com.example.rakt_daan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

@Suppress("DEPRECATION")
class AboutScreen : Fragment() {
    private var rootView: View? = null
    private var backImg: ImageView? = null
    private var contactUsTxt: TextView? = null
    private var feedbackTxt: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_about_screen, container, false)

        initializeViews()

        setupClickListeners()

        return rootView
    }

    private fun initializeViews() {
        rootView?.let {
            backImg = it.findViewById(R.id.image_back)
            contactUsTxt = it.findViewById(R.id.txt_contact_us)
            feedbackTxt = it.findViewById(R.id.txt_FeedBack)
        }
    }

    private fun setupClickListeners() {
        backImg?.setOnClickListener { activity?.onBackPressed() }

        contactUsTxt?.setOnClickListener {
            rootView?.let { view ->
                Navigation.findNavController(view).navigate(R.id.action_about_to_contactUs)
            }
        }

        feedbackTxt?.setOnClickListener {
            rootView?.let { view ->
                Navigation.findNavController(view).navigate(R.id.action_about_to_feedback)
            }
        }
    }
}
