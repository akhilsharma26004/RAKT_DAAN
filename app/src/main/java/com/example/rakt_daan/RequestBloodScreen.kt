package com.example.rakt_daan

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rakt.DonorInformation.DonorRoot
import com.example.rakt_daan.DonorInformation.DonorInformation
import com.google.gson.Gson

class RequestBloodScreen() : Fragment(), Parcelable {

    // Renamed the variable to avoid conflict with Fragment's getView() method
    private var rootView: View? = null
    private var backButton: ImageView? = null
    private var askHelpButton: Button? = null

    // Parcelable constructor
    constructor(parcel: Parcel) : this()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_request_blood_screen, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        findIds()
        setOnClickListeners()

        // Setting up RecyclerView and loading data
        val gson = Gson()
        val recyclerView: RecyclerView = view.findViewById(R.id.donor_holder)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val sharedPreferences = requireContext().getSharedPreferences(
            MainScreen.donor_file,
            Context.MODE_PRIVATE
        )
        val storedData = sharedPreferences.getString(MainScreen.donor_key, "")

        if (!storedData.isNullOrEmpty()) {
            val donorRoot: DonorRoot = gson.fromJson(storedData, DonorRoot::class.java)
            val donorAdapter = DonorRecycleView(donorRoot.donorInformation, this)
            recyclerView.adapter = donorAdapter
        } else {
            Toast.makeText(context, "No donors found", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to find views by their IDs
    private fun findIds() {
        askHelpButton = rootView?.findViewById(R.id.ask_for_help_btn)
        backButton = rootView?.findViewById(R.id.image_back)
    }

    // Set up click listeners for the buttons
    private fun setOnClickListeners() {
        backButton?.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        askHelpButton?.setOnClickListener {
            // Add navigation action here if needed
        }
    }

    // Function to handle callback when an item is clicked
    fun callBack(donorInformation: DonorInformation?) {
        val bundle = Bundle().apply {
            putSerializable("key", donorInformation)
        }
        rootView?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_requestBloodScreen_to_donorScreen, bundle)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {}

    override fun describeContents(): Int = 0

    // Placeholder for item click handling (if needed)
    fun onItemClicked(donor: DonorInformation) {}

    companion object CREATOR : Parcelable.Creator<RequestBloodScreen> {
        override fun createFromParcel(parcel: Parcel): RequestBloodScreen {
            return RequestBloodScreen(parcel)
        }

        override fun newArray(size: Int): Array<RequestBloodScreen?> {
            return arrayOfNulls(size)
        }
    }
}
