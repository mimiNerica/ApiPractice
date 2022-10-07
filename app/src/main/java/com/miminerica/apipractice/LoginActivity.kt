package com.miminerica.apipractice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.miminerica.apipractice.utils.ContextUtil
import com.miminerica.apipractice.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvent()
        setValue()
    }

    override fun setupEvent() {
        loginBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()

            ServerUtil.postRequestLogin(inputEmail, inputPassword, object : ServerUtil.Companion.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {
                    val code = jsonObj.getInt("code")

                    if (code == 200) {
                        val token = jsonObj.getJSONObject("data").getString("token")
                        ContextUtil.setToken(mContext, token)

                        val myIntent = Intent(mContext, MainActivity ::class.java)
                        startActivity(myIntent)
                        finish()

                    } else {
                        val message = jsonObj.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT ).show()
                        }
                    }
                }
            })
        }
        signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SingnUpActivity ::class.java)
            startActivity(myIntent)

        }

    }

    override fun setValue() {

    }


}