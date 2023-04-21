package com.example.navigationdrawer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.databinding.ListNotifyBinding
import com.example.navigationdrawer.notificationModel.Notification

class NotificationAdapter(private val mList: ArrayList<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    private lateinit var notifyListBinding: ListNotifyBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        notifyListBinding =
            ListNotifyBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return ViewHolder(notifyListBinding.root)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        notifyListBinding.model = itemsViewModel
        notifyListBinding.mSwipeReveal.dragEdge = SwipeRevealLayoutNew.DRAG_EDGE_RIGHT

        notifyListBinding.viewAction.setOnClickListener {
            println("@@@ $position")
            if (mList.size > 0) {
                mList.removeAt(position)
                notifyDataSetChanged()

            }
        }

    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


    }


}

