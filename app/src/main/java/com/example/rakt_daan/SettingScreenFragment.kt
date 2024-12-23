package com.example.rakt_daan

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class SettingsSreenFragment : Fragment() {
    // Renamed variable to avoid conflict with Fragment's getView()
    private var rootView: View? = null
    private var backImg: ImageView? = null
    private var helpTxt: TextView? = null
    private var logoutTxt: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_settings_sreen, container, false)

        // Initialize views
        findIds()
        setOnClickListeners()

        return rootView
    }

    private fun findIds() {
        backImg = rootView?.findViewById(R.id.image_back)
        helpTxt = rootView?.findViewById(R.id.txt_help)
        logoutTxt = rootView?.findViewById(R.id.txt_Logout)
    }

    private fun setOnClickListeners() {
        backImg?.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        helpTxt?.setOnClickListener {
            rootView?.let {
                Navigation.findNavController(it)
                    .navigate(R.id.action_settingsSreenFragment_to_helpScreen)
            }
        }

        logoutTxt?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setIcon(R.drawable.exit)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit the app?")
                .setPositiveButton("Yes") { _, _ ->
                    val intent = Intent(requireContext(), StartingUiActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}
