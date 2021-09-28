package com.example.coronavirus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException


const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getCorona()
            } catch(e: java.io.IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) run {

               val data :CoronaDataModel=response.body()!!
                cases.text=data.cases.toString()
                death.text=data.deaths.toString()
                recovered.text=data.recovered.toString()
                critical.text=data.critical.toString()
                active.text=data.active.toString()

            } else {
                Log.e(TAG, "Response not successful")
            }

        }


    }
}
