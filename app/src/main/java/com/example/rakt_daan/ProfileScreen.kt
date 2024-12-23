package com.example.rakt_daan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ProfileScreen : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var back: ImageView
    private lateinit var editTextName: EditText
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private var name: String? = null
    private var username: String? = null
    private var password: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Initialize sharedPreferences
        sharedPreferences = context.getSharedPreferences("userfiles", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_screen, container, false)

        findIds(view)
        onClicks()
        setData()

        return view
    }

    private fun findIds(view: View) {
        back = view.findViewById(R.id.image_back)
        editTextName = view.findViewById(R.id.edtx_name)
        editTextUsername = view.findViewById(R.id.edtx_user_name)
        editTextPassword = view.findViewById(R.id.edtx_password)
    }

    private fun onClicks() {
        back.setOnClickListener { requireActivity().onBackPressed() }
    }

    private fun setData() {
        // Retrieve stored data
        name = sharedPreferences.getString("name", null)
        username = sharedPreferences.getString("username", null)
        password = sharedPreferences.getString("password", null)

        // Set data to EditTexts
        editTextName.setText(name)
        editTextUsername.setText(username)
        editTextPassword.setText(password)
    }
}
