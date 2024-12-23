package com.example.rakt_daan

import android.content.Context
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import java.security.MessageDigest

class RegisterScreen(navController: NavHostController) : Fragment(R.layout.fragment_register_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        val fullNameEditText: TextInputEditText = view.findViewById(R.id.edtx_name)
        val usernameEditText: TextInputEditText = view.findViewById(R.id.edtx_user_name)
        val passwordEditText: TextInputEditText = view.findViewById(R.id.edtx_password)
        val registerButton: Button = view.findViewById(R.id.btn_register)
        val loginTextView: TextView = view.findViewById(R.id.txt_login)

        // Register button click listener
        registerButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Check if fields are empty
            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Hash the password
            val hashedPassword = hashPassword(password)

            // Save user data to SharedPreferences
            val sharedPreferences = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("name", fullName)
            editor.putString("username", username)
            editor.putString("password", hashedPassword)
            editor.apply()

            // Navigate to the login screen after successful registration
            Navigation.findNavController(view).navigate(R.id.action_registerScreen_to_loginScreen)

            // Clear input fields
            fullNameEditText.text?.clear()
            usernameEditText.text?.clear()
            passwordEditText.text?.clear()

            Toast.makeText(context, "Registered Successfully!", Toast.LENGTH_SHORT).show()
        }

        // Login TextView click listener (for navigation to login screen)
        loginTextView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerScreen_to_loginScreen)
        }
    }

    // Function to hash the password using SHA-256
    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(password.toByteArray())
        return Base64.encodeToString(hashedBytes, Base64.DEFAULT)
    }
}
