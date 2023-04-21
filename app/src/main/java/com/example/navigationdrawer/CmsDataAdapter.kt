package com.example.navigationdrawer


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.model.CmsData


class CmsDataAdapter(private val mList: List<CmsData>) :
    RecyclerView.Adapter<CmsDataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_cms_data, parent, false)


        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]




        holder.textview.text = itemsViewModel.title.toString()

        holder.constraintLayout.setOnClickListener {


            val intent = Intent(MyApp.getinstance()?.applicationContext, CmsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("PageId",itemsViewModel.id)
            MyApp.getinstance()?.applicationContext?.startActivity(intent)

        }
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        var textview: TextView = itemView.findViewById(R.id.txtHomeData)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constData)

    }
}