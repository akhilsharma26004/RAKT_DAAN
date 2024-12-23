package com.example.rakt_daan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class MainScreen : Fragment() {

    // Declare the UI elements
    private lateinit var donateBloodBtn: RelativeLayout
    private lateinit var requestBloodBtn: RelativeLayout
    private lateinit var settingsBtn: RelativeLayout
    private lateinit var bloodBankBtn: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_screen, container, false)

        // Initialize UI components
        findIds(view)

        // Set up click listeners for the buttons
        setupOnClicks(view)

        return view
    }

    // Method to find and initialize the views
    private fun findIds(view: View) {
        donateBloodBtn = view.findViewById(R.id.tile_donate_blood)
        requestBloodBtn = view.findViewById(R.id.tile_request_blood)
        settingsBtn = view.findViewById(R.id.tile_settings)
        bloodBankBtn = view.findViewById(R.id.tile_blood_bank)
    }

    // Method to handle button clicks
    private fun setupOnClicks(view: View) {
        // Navigate to the Donate Blood Screen
        donateBloodBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home_to_donateBloodScreen)
        }

        // Navigate to the Request Blood Screen
        requestBloodBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home_to_requestBloodScreen)
        }

        // Navigate to the Settings Screen
        settingsBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home_to_settingsSreenFragment)
        }

        // Open Maps for the Blood Bank location
        bloodBankBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:30.897343,76.408004"))
            val chooser = Intent.createChooser(intent, "Launch Maps")
            startActivity(chooser)
        }
    }

    companion object {
        const val donor_file: String = "donor_file"
        const val donor_key: String = "donor_key"
    }
}
