package com.miminerica.apipractice.utils

import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    companion object {

        interface JsonResponseHandler {

            fun onResponse(jsonObj : JSONObject)
        }

        val BASE_URL = "http://54.180.52.26"

        fun postRequestLogin(email : String, pw : String, handler: JsonResponseHandler?) {

            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build()
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)

                    Log.d("응답본문", jsonObj.toString())

                    handler?.onResponse(jsonObj)


                }

            })

        }

        fun putRequestSignup(email: String, pw: String, nick : String, handler : JsonResponseHandler?) {

            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nick)
                .build()
            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodystring = response.body!!.string()
                    val jsonObj = JSONObject(bodystring)

                    handler?.onResponse(jsonObj)
                }

            })
        }

        fun getRequestDuplCheck(type : String, value : String, handler: JsonResponseHandler?) {

//            val urlString = "${BASE_URL}/user_check"
            val urlBulder = "${BASE_URL}/user_check".toHttpUrlOrNull()!!.newBuilder()
            urlBulder.addEncodedQueryParameter("type", type)
                .addEncodedQueryParameter("value", value)

            val urlString = urlBulder.build().toString()
            val request = Request.Builder()
                .url(urlString)
                .get()
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object  : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodystring = response.body!!.string()
                    val  jsonObj = JSONObject(bodystring)

                    handler?.onResponse(jsonObj)
                }

            })

        }
    }
}