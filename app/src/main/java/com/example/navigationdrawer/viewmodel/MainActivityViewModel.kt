package com.example.navigationdrawer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationdrawer.cmsModel.UserCmsData

import com.example.navigationdrawer.model.UserDataModel
import com.example.navigationdrawer.notificationModel.UserNotification
import com.example.navigationdrawer.repositotry.MainActivityRepository


class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<UserDataModel>? = null
    var userCmsLiveData: MutableLiveData<UserCmsData>? = null
    var userNotificationData:MutableLiveData<UserNotification>?=null

    fun getUser(
        data: HashMap<String, String>

    ): LiveData<UserDataModel>? {
        servicesLiveData = MainActivityRepository.getServicesApiCall(data)
        return servicesLiveData
    }

    fun getUserCmsData(
        data3: HashMap<String, String>

    ): LiveData<UserCmsData>? {
        userCmsLiveData = MainActivityRepository.getCmsDataApiCall(data3)
        return userCmsLiveData
    }

    fun getUserNotification(
        data4:HashMap<String,String>
    ):LiveData<UserNotification>?{
        userNotificationData=MainActivityRepository.getNotificationApiCall(data4)
        return userNotificationData
    }


}