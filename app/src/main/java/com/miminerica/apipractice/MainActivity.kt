package com.miminerica.apipractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miminerica.apipractice.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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