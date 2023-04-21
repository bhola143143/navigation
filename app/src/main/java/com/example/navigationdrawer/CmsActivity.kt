package com.example.navigationdrawer

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.navigationdrawer.databinding.ActivityCmsBinding
import com.example.navigationdrawer.databinding.ActivityMainBinding
import com.example.navigationdrawer.viewmodel.MainActivityViewModel

class CmsActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    private lateinit var mainCmsActivityBinding: ActivityCmsBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    var storeId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainCmsActivityBinding = ActivityCmsBinding.inflate(layoutInflater)
        mainCmsActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_cms)


        val pageId = intent.getStringExtra("PageId")

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("MY_APP", MODE_PRIVATE)
        storeId = sharedPreferences.getString("storeId", "").toString()


        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        val userCmsdata: HashMap<String, String> = HashMap()
        userCmsdata["storeId"] = storeId
        userCmsdata["pageId"] = pageId!!


        mainActivityViewModel.getUserCmsData(userCmsdata)!!.observe(this) { userCmsData ->


            if (userCmsData.settings?.code == 200) {
             //   println("@@@ method1: " + "${userCmsData?.settings}")
                userCmsData.response_data?.content?.let {
                    mainCmsActivityBinding.web.loadData(
                        it,
                        "text/html",
                        "UTF-8"
                    )
                }

                mainCmsActivityBinding.title = userCmsData.response_data?.title
            }

        }
    }
}