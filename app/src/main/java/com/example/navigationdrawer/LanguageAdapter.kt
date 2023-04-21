package com.example.navigationdrawer


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.model.Store


class LanguageAdapter(private val mList: List<Store>) :
    RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_language_data, parent, false)


        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]




        holder.textview.text = itemsViewModel.name
        holder.radiobutton.isChecked = itemsViewModel.isSelected == true


        holder.constarintstate.setOnClickListener {
            for (i in 0 until mList.size) {
                mList[i].isSelected = false
            }
            notifyDataSetChanged()
            notifyItemChanged(position)

            mList[position].isSelected = true
            notifyItemChanged(position)




        }
        holder.view.visibility = View.VISIBLE

        if (position < itemCount - 1) {
            holder.view.visibility = View.VISIBLE
        } else {
            holder.view.visibility = View.GONE

        }


    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        var textview: TextView = itemView.findViewById(R.id.txtStateName)

        var radiobutton: RadioButton = itemView.findViewById(R.id.radioLangugae)
        var constarintstate: ConstraintLayout = itemView.findViewById(R.id.constState)
        val view: View = itemView.findViewById(R.id.viewLanguage)


    }
}