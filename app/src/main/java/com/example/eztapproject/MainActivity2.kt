package com.example.eztapproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.eztapproject.Constants.CITY_LABEL
import com.example.eztapproject.Constants.NAME_LABEL
import com.example.eztapproject.Constants.PHONE_LABEL
import com.example.eztapproject.databinding.*
import com.util.networkinterface.models.Uidatum

class MainActivity2 : AppCompatActivity() {

    var name=""
    var phn=""
    var city=""
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        name=intent.extras?.getString(NAME_LABEL).toString()
        phn=intent.extras?.getString(PHONE_LABEL).toString()
        city= intent.extras?.getString(CITY_LABEL).toString()
        binding.cityEditText.text=city;
        binding.phnEditText.text=phn
        binding.nameEditText.text=name


    }



}