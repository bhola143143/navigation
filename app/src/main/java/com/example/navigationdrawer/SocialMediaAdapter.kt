package com.example.navigationdrawer


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navigationdrawer.model.SocialMedia

import com.example.navigationdrawer.model.UserDataModel


class SocialMediaAdapter(private val mList: List<SocialMedia>, private var context: Context) :
    RecyclerView.Adapter<SocialMediaAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_social_data, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]


        Glide.with(holder.imageview).load(itemsViewModel.imageurl)
            .error(R.drawable.youtube)
            .into(holder.imageview)

        holder.constraintLayout.setOnClickListener {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(itemsViewModel.handle_url)
                )
            )
        }


    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        var imageview: ImageView = itemView.findViewById(R.id.imgSocial)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constSocial)

    }
}