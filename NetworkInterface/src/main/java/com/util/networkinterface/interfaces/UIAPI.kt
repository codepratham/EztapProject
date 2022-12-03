package com.util.networkinterface.interfaces

import com.util.networkinterface.models.UIResponse
import retrofit2.http.GET

interface UIAPI {

    @GET("android_assignment.json")
    suspend  fun fetchCustomUI(): UIResponse

}