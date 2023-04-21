package com.example.navigationdrawer

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.navigationdrawer.databinding.ActivityNotifyBinding
import com.example.navigationdrawer.viewmodel.MainActivityViewModel

class NotifyActivity : AppCompatActivity() {
    var storeId = ""
    private lateinit var mainnotifyActivityBinding: ActivityNotifyBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainnotifyActivityBinding = ActivityNotifyBinding.inflate(layoutInflater)
        setContentView(mainnotifyActivityBinding.root)


        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("MY_APP", MODE_PRIVATE)
        storeId = sharedPreferences.getString("storeId", "").toString()


        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        val userNotification: HashMap<String, String> = HashMap()
        userNotification["storeId"] = storeId
        userNotification["currentPage"] = "1"
        userNotification["deviceId"] = "FCE2F65C-AEBD-4B04-A439-E3BBFD5EEC35"

        mainActivityViewModel.getUserNotification(userNotification)!!
            .observe(this) { userNotificationData ->

                if (userNotificationData.settings?.code == 200) {
                    val adapter =
                        NotificationAdapter(userNotificationData.response_data!!.notification_list)
                    mainnotifyActivityBinding.rvNotification.adapter = adapter


                }


            }


        /*    mainnotifyActivityBinding.rvNotification.adapter = Adapter(listOf(

               "Item 1: Delete",

           ))*/



    }




}