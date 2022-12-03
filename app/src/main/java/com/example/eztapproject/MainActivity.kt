package com.example.eztapproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.util.networkinterface.models.Uidatum
import com.util.networkinterface.Retrofit.RequestHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.R.attr.button

import android.R
import android.R.attr
import android.content.Context
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginTop
import com.google.android.material.textfield.TextInputEditText
import android.net.NetworkInfo

import android.net.ConnectivityManager

import android.content.Intent
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.*
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.eztapproject.Constants.CITY_LABEL
import com.example.eztapproject.Constants.NAME_LABEL
import com.example.eztapproject.Constants.PHONE_LABEL
import com.example.eztapproject.databinding.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.lyt_edit_text.view.*


class MainActivity : AppCompatActivity() {
    val baseUrl = "https://demo.ezetap.com/mobileapps/"
    val UI_TYPE_EDITTEXT = "edittext"
    val UI_TYPE_TEXTVIEW = "label"
    val UI_TYPE_BUTTON = "button"
    var logoUrl = ""
    var uiData = ArrayList<Uidatum>()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isNetworkAvailable()) {

            lifecycleScope.launch {

                val requestHandler = RequestHandler()

                val uiResponse = requestHandler.fetchCustomUI(baseUrl)
                if (uiResponse.uidata != null) {
                    uiData = uiResponse.uidata as ArrayList<Uidatum>
                    logoUrl = uiResponse.logoUrl.toString()



                    withContext(Dispatchers.Main) {
                        extractUI(uiData)

                    }

                } else {
                    withContext(Dispatchers.Main) {
                        binding.lvWrong.visibility = View.VISIBLE
                    }

                }




            }

        } else {
            binding.lvWrong.visibility = View.VISIBLE
        }

    }

    fun extractUI(uiData: List<Uidatum>) {
        for (uiElement: Uidatum in uiData) {
            addUiElement(uiElement)

        }
    }

    fun addUiElement(uidatum: Uidatum) {
        var editTextname: EditText? = null
        var editTextphn: EditText? = null
        var editTextCIty: EditText? = null
        var editText: EditText? = null
        Glide.with(this).load(logoUrl).into(binding.ivLogo)
        when (uidatum.uitype) {
            UI_TYPE_EDITTEXT -> {
                val root = LytEditTextBinding.inflate(layoutInflater).root

                editText = root.name_edit_text
                editText.setTag(uidatum.key)
                root.setTag(uidatum.key + "p")

                if (uidatum.key.equals("text_phone")) {

                    editText.inputType = InputType.TYPE_CLASS_PHONE
                }
                binding.llDyamicViews.addView(root)


            }
            UI_TYPE_TEXTVIEW -> {


                val textView = LytTextViewtBinding.inflate(layoutInflater).root

                val params: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                params.setMargins(0, 30, 0, 0)
                textView.setLayoutParams(params)

                textView.setText(uidatum.value)
                binding.llDyamicViews.addView(textView)

            }
            UI_TYPE_BUTTON -> {


                val button = LytButtonBinding.inflate(layoutInflater).root
                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                params.setMargins(0, 100, 0, 0)
                params.gravity = Gravity.CENTER
                button.setLayoutParams(params)
                button.setText(uidatum.value)

                button.setOnClickListener {


                    val et_parentnme = ll_dyamicViews.findViewWithTag<ViewGroup>("text_namep")
                    val et_parentCity = ll_dyamicViews.findViewWithTag<ViewGroup>("text_cityp")
                    val et_parentPhn = ll_dyamicViews.findViewWithTag<ViewGroup>("text_phonep")

                    editTextname = et_parentnme.findViewWithTag<EditText>(NAME_LABEL)
                    editTextCIty = et_parentCity.findViewWithTag<EditText>(CITY_LABEL)
                    editTextphn = et_parentPhn.findViewWithTag<EditText>(PHONE_LABEL)

                    if (editTextname?.text.isNullOrBlank() || editTextphn?.text.isNullOrBlank() || editTextCIty?.text.isNullOrBlank()) {
                        Snackbar.make(
                            binding.root,
                            "Please Enter All The Fields",
                            Snackbar.LENGTH_LONG
                        ).show()
                        return@setOnClickListener
                    }

                    val intent = Intent(this, MainActivity2::class.java)

                    val name = editTextname?.text
                    intent.putExtra(NAME_LABEL, name.toString())


                    intent.putExtra(PHONE_LABEL, editTextphn?.text.toString())


                    intent.putExtra(CITY_LABEL, editTextCIty?.text.toString())

                    startActivity(intent)
                }

                binding.llDyamicViews.addView(button)

            }


        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


}