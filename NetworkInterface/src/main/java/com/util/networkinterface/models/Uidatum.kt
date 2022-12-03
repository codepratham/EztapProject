package com.util.networkinterface.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Uidatum:Serializable {



    @SerializedName("uitype")
    @Expose
    var uitype: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null

    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("hint")
    @Expose
    var hint: String? = null
}
