package com.example.navigationdrawer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.navigationdrawer.Dailog.LanguageDailog
import com.example.navigationdrawer.Dailog.StateDailog
import com.example.navigationdrawer.databinding.ActivityMainBinding
import com.example.navigationdrawer.model.SocialMedia
import com.example.navigationdrawer.model.Store
import com.example.navigationdrawer.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var context: Context

    lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var mainActivityBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navView: BottomNavigationView = mainActivityBinding.navView

        mainActivityBinding.layToolbar.clickListener = this
        // mainActivityBinding.included.recOther.isClickable=true


        mainActivityBinding.layToolbar.imgNotify.setOnClickListener {
            val intent = Intent(this, NotifyActivity::class.java)
            startActivity(intent)
        }



        mainActivityBinding.included.recHome.layoutManager = LinearLayoutManager(this)
        val data1 = ArrayList<ItemsViewModel>()

        data1.add(
            ItemsViewModel(
                R.drawable.home,
                getString(R.string.home)
            )
        )
        data1.add(
            ItemsViewModel(
                R.drawable.cate,
                getString(R.string.categ)
            )
        )
        data1.add(
            ItemsViewModel(
                R.drawable.set,
                getString(R.string.settings)
            )
        )
        val adapter = CustomAdapter(data1)
        mainActivityBinding.included.recHome.adapter = adapter

        mainActivityBinding.clickListener = this
        mainActivityBinding.navView.itemIconTintList = null

        context = this@MainActivity

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        val data: HashMap<String, String> = HashMap()
        data["deviceType"] = "Android"
        data["languageData"] = "en"
        data["storeId"] = "1"
        data["deviceId"] = "2050d672e1d9538e"
        data["deviceToken"] =
            "cRQl_5dKS4SkZfETQFCXbn%3AAPA91bGBKydk7dLFgw2sPbmZdy4rFrBR4c5SNejxiy1VubcEMRj-X7EncUJ_8bgnL89bSeS4KMXR7fNJ4QhI4Cmgw1dQaICkuy2OpoFlip4NNQjqT3xHCOFf5zjARoAXvuui3nQ6wxRx"
        mainActivityViewModel.getUser(data)!!.observe(this) { userData ->


            val adapter1 = CmsDataAdapter(userData.response_data!!.cmsData)
            mainActivityBinding.included.recOther.adapter = adapter1

            val size = userData.response_data!!.socialMedia.size

            val data2 = ArrayList<SocialMedia>()
            for ((pos, i) in userData.response_data!!.socialMedia.withIndex()) {
                val item = SocialMedia(i.handle_url, i.handler_type, R.drawable.youtube)
                when (i.handler_type) {
                    "youtube" -> {
                        item.imageurl = R.drawable.youtube
                    }
                    "tiktok" -> {
                        item.imageurl = R.drawable.tittok
                    }
                    "snapchat" -> {
                        item.imageurl = R.drawable.snap
                    }
                    "instagram" -> {
                        item.imageurl = R.drawable.inst
                    }
                    "linkedin" -> {
                        item.imageurl = R.drawable.li
                    }
                    "twitter" -> {
                        item.imageurl = R.drawable.tiw
                    }
                    "facebook" -> {
                        item.imageurl = R.drawable.face
                    }
                    "pinterest" -> {
                        item.imageurl = R.drawable.pina
                    }
                }
                data2.add(item)
            }


            val adapter2 = SocialMediaAdapter(data2, context)
            mainActivityBinding.included.recList.adapter = adapter2

            for (storeData in userData.response_data!!.stores) {

                if (storeData.isSelected == true) {
                    mainActivityBinding.included.selectedStore = storeData.name
                    Glide.with(mainActivityBinding.included.imgCountry).load(storeData.flagURL)
                        .error(R.drawable.ic_launcher_background)
                        .into(mainActivityBinding.included.imgCountry)



                    if (storeData.storeId == "1") {
                        //    println("@@@ Settings: " + "${storeData.storeId}")
                        val storeId = "${storeData.storeId}"
                        val preferences: SharedPreferences =
                            this.getSharedPreferences("MY_APP", MODE_PRIVATE)
                        preferences.edit().putString("storeId", storeId).apply()

                    }
                }


            }


            mainActivityBinding.included.clCountryContainer.setOnClickListener {

                val emptyList = ArrayList<Store>()
                for (i in 0 until userData.response_data!!.stores.size) {

                    if (userData.response_data!!.stores[i].language == "en") {

                        emptyList.add(userData.response_data!!.stores[i])
                    }
                }

                val dialog = StateDailog.newInstance(emptyList as List<Store>)

                dialog.show(this.supportFragmentManager, "")
            }


            mainActivityBinding.included.constLanguageContainer.setOnClickListener {

                val emptyList = ArrayList<Store>()
                for (i in 0 until userData.response_data!!.stores.size) {

                    if ((userData.response_data!!.stores[i].storeCode == "jo_en") || (userData.response_data!!.stores[i].storeCode == "jo_ar")) {

                        emptyList.add(userData.response_data!!.stores[i])
                    }
                }

                val dialog = LanguageDailog.newInstance(emptyList as List<Store>)

                dialog.show(this.supportFragmentManager, "")
            }
        }


    }

    override fun onClick(p0: View?) {
        when (p0) {
            mainActivityBinding.layToolbar.ivSideMenu -> {
                if (!mainActivityBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
                    mainActivityBinding.drawerLayout.openDrawer(GravityCompat.START) else mainActivityBinding.drawerLayout.closeDrawer(
                    GravityCompat.START
                )


            }


        }
    }
}