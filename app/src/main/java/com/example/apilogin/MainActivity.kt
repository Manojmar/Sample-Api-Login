
package com.example.apilogin

import android.content.Intent
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            var email = editText.text.toString()
            var password = editText2.text.toString()

            signin(email, password)
        }
    }

    private fun signin(email: String, password: String) {
        val jsonObject = JsonObject()
        try {
            jsonObject.addProperty("email", email)
            jsonObject.addProperty("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        try {
            Api().loginn(jsonObject)
                .enqueue(object : Callback<Modal> {
                    override fun onResponse(call: Call<Modal>, response: Response<Modal>) {
                        if (response.isSuccessful) {
                            if (response.body()?.status_code == 200) {
                                Toast.makeText(this@MainActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
                                val intent=Intent(this@MainActivity,login::class.java)
                                startActivity(intent)
                            }else {
                                Toast.makeText(this@MainActivity,response.body()?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    override fun onFailure(call: Call<Modal>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
        } catch (e:Exception) {
               Log.d("exe",e.toString())
        }
    }
}