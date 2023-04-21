package com.example.navigationdrawer.Dailog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.Dailoglanguage1Binding

class SwipeDailog() : DialogFragment() {

    companion object {
        fun newInstance(): SwipeDailog {
            val f = SwipeDailog()
            return f
        }
    }

    private lateinit var binding: Dailoglanguage1Binding


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
        binding = Dailoglanguage1Binding.inflate(
            inflater, container, false
        )


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.btnYes.setOnClickListener {



        }


    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }


}
