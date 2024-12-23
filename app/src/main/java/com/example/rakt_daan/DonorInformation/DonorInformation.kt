package com.example.rakt_daan.DonorInformation

import java.io.Serializable

class DonorInformation : Serializable {
    var name: String? = null
    var phone: String? = null
    var city: String? = null
    var gender: String? = null
    var blood_type: String? = null
    var image: String? = null


    var donorInformation: ArrayList<DonorInformation> = ArrayList()
}