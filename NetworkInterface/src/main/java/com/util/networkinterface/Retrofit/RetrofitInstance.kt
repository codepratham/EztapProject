package com.util.networkinterface.Retrofit

import com.util.networkinterface.interfaces.UIAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


    object RetrofitInstance {

        fun getInstance(url:String): UIAPI {
            return Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build().create(UIAPI::class.java)
        }
    }
