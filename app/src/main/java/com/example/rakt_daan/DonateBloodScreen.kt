package com.example.rakt_daan
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.rakt_daan.DonorInformation.DonorInformation
import com.example.rakt.DonorInformation.DonorRoot
import java.io.File
class DonateBloodScreen : Fragment() {
    private var imgpath: String? = null
    private var editText_name: EditText? = null
    private var editText_phone: EditText? = null
    private var editText_city: EditText? = null
    private var back: ImageView? = null
    private var submit_button: Button? = null
    private var btn_A: Button? = null
    private var btn_B: Button? = null
    private var btn_AB: Button? = null
    private var btn_O_pos: Button? = null
    private var btn_O_neg: Button? = null
    private var add_profile_image: ImageView? = null
    private var radio_btn_male: RadioButton? = null
    private var radio_btn_female: RadioButton? = null
    private var type: String? = null
    private var blood_type: String? = null
    private var image: Uri? = null
    private var donorInformation: DonorInformation? = null
    private var donorInformation1: ArrayList<DonorInformation>? = null
    private var donorRoot: DonorRoot? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donate_blood_screen, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        type = null
        blood_type = null

        FindId(view)
        OnClicks()
    }
    private fun FindId(view: View) {
        back = view.findViewById(R.id.image_back)
        submit_button = view.findViewById(R.id.done_btn)
        add_profile_image = view.findViewById(R.id.Add_profilepic_imageView)
        profile_image = view.findViewById(R.id.img_donor)
        editText_name = view.findViewById(R.id.edtx_name_doner)
        editText_phone = view.findViewById(R.id.edtx_phone_doner)
        editText_city = view.findViewById(R.id.edtx_city_doner)
        radio_btn_male = view.findViewById(R.id.radio_btn_male)
        radio_btn_female = view.findViewById(R.id.radio_btn_female)
        btn_A = view.findViewById(R.id.btn_A)
        btn_B = view.findViewById(R.id.btn_B)
        btn_AB = view.findViewById(R.id.btn_AB)
        btn_O_pos = view.findViewById(R.id.btn_O_pos)
        btn_O_neg = view.findViewById(R.id.btn_O_neg)
    }
    private fun OnClicks() {
        back?.setOnClickListener {
            val upload_box = Dialog(requireContext())
            upload_box.setContentView(R.layout.exit_without_submitdetails_dialogue_box)
            upload_box.setCanceledOnTouchOutside(false)
            upload_box.window?.setGravity(Gravity.CENTER)
            upload_box.show()
            upload_box.findViewById<View>(R.id.yes_btn).setOnClickListener {
                requireActivity().onBackPressed()
                upload_box.dismiss()
            }
            upload_box.findViewById<View>(R.id.no_btn).setOnClickListener {
                Toast.makeText(context, "Please enter details and submit", Toast.LENGTH_SHORT)
                    .show()
                upload_box.dismiss()
            }}
                btn_A?.setOnClickListener { blood_type = "A+" }
        btn_B?.setOnClickListener { blood_type = "B+" }
        btn_AB?.setOnClickListener { blood_type = "AB+" }
        btn_O_pos?.setOnClickListener { blood_type = "O+" }
        btn_O_neg?.setOnClickListener { blood_type = "O-" }
        radio_btn_male?.setOnClickListener { type = "Male" }
        radio_btn_female?.setOnClickListener { type = "Female" }
        submit_button?.setOnClickListener {
            if (editText_name?.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show()
            } else if (editText_phone?.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter phone", Toast.LENGTH_SHORT).show()
            } else if (editText_city?.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter city", Toast.LENGTH_SHORT).show()
            } else if (type == null) {
                Toast.makeText(context, "Please select gender", Toast.LENGTH_SHORT).show()
            } else if (blood_type == null) {
                Toast.makeText(context, "Please select blood type", Toast.LENGTH_SHORT).show()
            } else {
                val gson = com.google.gson.Gson()
                val sharedPreferences = requireContext().getSharedPreferences(
                    MainScreen.donor_file,
                    Context.MODE_PRIVATE
                )
                val editData = sharedPreferences.getString(MainScreen.donor_key, "")

                if (!editData.isNullOrEmpty()) {
                    donorRoot = gson.fromJson(editData, DonorRoot::class.java)
                    donorInformation1 = ArrayList(donorRoot?.donorInformation ?: emptyList())
                } else {
                    donorRoot = DonorRoot()
                    donorInformation1 = ArrayList()
                }

                donorInformation = DonorInformation().apply {
                    name = editText_name?.text.toString()
                    phone = editText_phone?.text.toString()
                    city = editText_city?.text.toString()
                    gender = type
                    blood_type = this@DonateBloodScreen.blood_type
                    image = this@DonateBloodScreen.image?.toString() ?: "no image"
                }

                donorInformation1?.add(donorInformation!!)
                donorRoot?.donorInformation = donorInformation1!!

                val editor = sharedPreferences.edit()
                editor.putString(MainScreen.donor_key, gson.toJson(donorRoot))
                editor.apply()

                Toast.makeText(context, "Details Submitted Successfully.", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            }
        }

        add_profile_image?.setOnClickListener {
            val permission = ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), 1
                )
            } else {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 1)
            }
            add_profile_image?.visibility = View.GONE
        }
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.data?.let {
                image = it
                profile_image?.setImageURI(image)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 1)
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    requestPermissions(
                        arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ), 1
                    )
                } else {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", requireContext().packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
            }
        }
    }

    companion object {
        private const val TAG = "DonateBloodScreen"
        var profile_image: ImageView? = null
    }
}
