package com.example.rakt_daan

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rakt_daan.DonorInformation.DonorInformation

class DonorScreen : Fragment() {
    private var rootView: View? = null
    private var backArrow: ImageView? = null
    private var imgDonor: ImageView? = null
    private var txtPersonName: TextView? = null
    private var txtPersonPhone: TextView? = null
    private var txtPersonGender: TextView? = null
    private var gender: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_donor_screen, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findIds()
        setOnClickListeners()

        val donorInformation = requireArguments()["key"] as DonorInformation?

        donorInformation?.let {
            if (it.image != "no image") {
                imgDonor?.setImageURI(Uri.parse(it.image))
            }

            txtPersonName?.text = it.name
            txtPersonPhone?.text = it.phone
            txtPersonGender?.text = it.city
            gender?.text = it.gender
        }
    }

    private fun findIds() {
        backArrow = requireView().findViewById(R.id.image_back)
        imgDonor = requireView().findViewById(R.id.img_donor)
        txtPersonName = requireView().findViewById(R.id.txt_person_name)
        txtPersonPhone = requireView().findViewById(R.id.txt_person_phone)
        txtPersonGender = requireView().findViewById(R.id.txt_person_gender)
        gender = requireView().findViewById(R.id.gender)
    }

    private fun setOnClickListeners() {
        backArrow?.setOnClickListener { requireActivity().onBackPressed() }
    }
}
