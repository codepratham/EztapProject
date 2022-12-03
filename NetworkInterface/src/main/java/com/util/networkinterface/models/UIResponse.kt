package com.util.networkinterface.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class UIResponse {
    @SerializedName("logo-url")
    @Expose
    var logoUrl: String? = null

    @SerializedName("heading-text")
    @Expose
    var headingText: String? = null

    @SerializedName("uidata")
    @Expose
    var uidata: List<Uidatum>? = null
}