package com.example.navigationdrawer.repositotry

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.navigationdrawer.cmsModel.UserCmsData

import com.example.navigationdrawer.model.UserDataModel
import com.example.navigationdrawer.notificationModel.UserNotification
import com.example.navigationdrawer.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<UserDataModel>()

    val userCmsDataPage = MutableLiveData<UserCmsData>()

    val userNotification =MutableLiveData<UserNotification>()

    fun getServicesApiCall(data: HashMap<String, String>): MutableLiveData<UserDataModel> {

        val call = RetrofitClient.apiInterface.getServices(data)

        call.enqueue(object : Callback<UserDataModel> {
            override fun onFailure(call: Call<UserDataModel>, t: Throwable) {

                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<UserDataModel>,
                response: Response<UserDataModel>
            ) {

                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                val responseData = data?.response_data
                val responseSettings = data?.settings
                serviceSetterGetter.value = UserDataModel(responseData, responseSettings)


            }
        })


        return serviceSetterGetter
    }

    fun getCmsDataApiCall(data3: HashMap<String, String>): MutableLiveData<UserCmsData> {

        val cmsCall = RetrofitClient.apiInterface.getCmsData(data3)

        cmsCall.enqueue(object : Callback<UserCmsData> {
            override fun onFailure(call: Call<UserCmsData>, t: Throwable) {

                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<UserCmsData>,
                response: Response<UserCmsData>
            ) {

                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                val responseData = data?.response_data
                val responseSettings = data?.settings
                userCmsDataPage.value = UserCmsData(responseData, responseSettings)


            }
        })

        return userCmsDataPage


    }



    fun getNotificationApiCall(data4: HashMap<String, String>): MutableLiveData<UserNotification> {

        val cmsCall = RetrofitClient.apiInterface.getNotification(data4)

        cmsCall.enqueue(object : Callback<UserNotification> {
            override fun onFailure(call: Call<UserNotification>, t: Throwable) {

                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<UserNotification>,
                response: Response<UserNotification>
            ) {

                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                val responseData = data?.response_data
                val responseSettings = data?.settings
                userNotification.value = UserNotification(responseData, responseSettings)


            }
        })

        return userNotification


    }





}