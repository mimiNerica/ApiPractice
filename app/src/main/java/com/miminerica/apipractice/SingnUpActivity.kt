package com.miminerica.apipractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.miminerica.apipractice.utils.ServerUtil
import com.miminerica.apipractice.utils.ServerUtil.Companion.JsonResponseHandler
import kotlinx.android.synthetic.main.activity_singn_up.*
import org.json.JSONObject

class SingnUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singn_up)

        setupEvent()
        setValue()
    }

    override fun setupEvent() {

        signUpBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()
            val inputNickname = nicknameEdt.text.toString()

            ServerUtil.putRequestSignup(inputEmail, inputPassword, inputNickname, object : JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    Log.d("msg", jsonObj.toString())
                    if (jsonObj.getInt("code") == 200) {
                        val name = jsonObj.getJSONObject("data").getJSONObject("user").getString("nick_name")
                        Log.d("msg", name)
                        runOnUiThread {
                            Toast.makeText(mContext, "${name}님 환영합니다", Toast.LENGTH_LONG).show()
                        }
                        finish()

                    } else {
                        val message = jsonObj.getString("message")


                        runOnUiThread{
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }

        emailCheckBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()

            ServerUtil.getRequestDuplCheck("EMAIL", inputEmail, object : JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    Log.d("msg", jsonObj.toString())

                    runOnUiThread {
                        if (jsonObj.getInt("code") == 200) {
                            Toast.makeText(mContext, jsonObj.getString("message"), Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(mContext, jsonObj.getString("message"), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }



    }

    override fun setValue() {

    }


}