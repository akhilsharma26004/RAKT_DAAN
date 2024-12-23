package com.example.rakt_daan

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rakt_daan.R

class HelpScreen : Fragment() {
    private var rootView: View? = null
    private var backArrowImage: ImageView? = null
    private var howToDonateBlood: TextView? = null
    private var howToRequestBlood: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_help_screen, container, false)

        findIds()
        setOnClicks()

        return rootView
    }

    private fun findIds() {
        howToDonateBlood = rootView?.findViewById(R.id.question1)
        howToRequestBlood = rootView?.findViewById(R.id.question2)
        backArrowImage = rootView?.findViewById(R.id.image_back)
    }

    private fun setOnClicks() {
        howToDonateBlood?.setOnClickListener {
            showCustomDialog(R.layout.how_to_donate_blood_dialogue_box)
        }

        howToRequestBlood?.setOnClickListener {
            showCustomDialog(R.layout.how_to_request_blood_dialogue_box)
        }

        backArrowImage?.setOnClickListener { requireActivity().onBackPressed() }
    }

    private fun showCustomDialog(layoutId: Int) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(layoutId)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.show()
        dialog.findViewById<View>(R.id.got_it_button)?.setOnClickListener {
            dialog.dismiss()
        }
    }
}
