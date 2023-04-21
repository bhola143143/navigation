package com.example.navigationdrawer.retrofit

import com.example.navigationdrawer.cmsModel.UserCmsData
import com.example.navigationdrawer.model.UserDataModel
import com.example.navigationdrawer.notificationModel.UserNotification
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ApiInterface {

    @GET("configurationdata")
    fun getServices(
        @QueryMap data: HashMap<String, String>
    ): Call<UserDataModel>


    @GET("cmsdata")
    fun getCmsData(
        @QueryMap data3: HashMap<String, String>
    ): Call<UserCmsData>

    @GET("pushnotification")
    fun getNotification(
        @QueryMap data4 :HashMap<String,String>

    ):Call<UserNotification>


}