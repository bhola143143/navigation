package com.example.navigationdrawer.Dailog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdrawer.MainActivity
import com.example.navigationdrawer.R
import com.example.navigationdrawer.StateAdapter
import com.example.navigationdrawer.databinding.DailogstateBinding
import com.example.navigationdrawer.model.Store

class StateDailog(private var list: List<Store>) : DialogFragment() {

    companion object {
        fun newInstance(list: List<Store>): StateDailog {
            val f = StateDailog(list)
            return f
        }
    }

    private lateinit var binding: DailogstateBinding


    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DailogstateBinding.inflate(
            inflater, container, false
        )


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.recState.layoutManager = LinearLayoutManager(requireContext())
        val adapter = StateAdapter(list)
        binding.recState.adapter = adapter


    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }


}
