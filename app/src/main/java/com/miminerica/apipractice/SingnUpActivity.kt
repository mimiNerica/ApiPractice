package com.miminerica.apipractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.miminerica.apipractice.utils.ServerUtil
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

            ServerUtil.putRequestSignup(inputEmail, inputPassword, inputNickname, object : ServerUtil.Companion.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {
                    if (jsonObj.getInt("code") == 200) {

                    } else {
                        val message = jsonObj.getString("message")
                        Log.d("msg", jsonObj.toString())

                        runOnUiThread{
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }



    }

    override fun setValue() {

    }


}