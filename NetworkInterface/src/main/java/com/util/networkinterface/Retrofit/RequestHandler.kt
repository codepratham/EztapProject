package com.util.networkinterface.Retrofit

import com.util.networkinterface.models.UIResponse

public class RequestHandler {

    private lateinit var uiResponse: UIResponse;

  suspend fun fetchCustomUI (URL:String): UIResponse
    {
        uiResponse= RetrofitInstance.getInstance(URL).fetchCustomUI()
       return uiResponse
   }

    suspend fun fetchImage(URL: String): String?
    {
        return fetchCustomUI(URL).logoUrl

    }


}
