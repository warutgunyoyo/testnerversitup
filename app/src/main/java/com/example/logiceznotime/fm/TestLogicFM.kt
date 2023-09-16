package com.example.logiceznotime.fm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.logiceznotime.R

class TestLogicFM : Fragment() {

    private lateinit var pincode: EditText
    private lateinit var validateBtn: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_teslogice, container, false)

        pincode = view.findViewById(R.id.pinCode_et)
        validateBtn = view.findViewById(R.id.btn_validate)
        validateBtn.setOnClickListener {
            if (pincode.text.isEmpty()) {
                pincode.error = "Please input"
            } else {
                val isValid = validatePincode(pincode.text.toString())
                Toast.makeText(
                    requireContext(),
                    isValid.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        return view
    }


    private fun validatePincode(pincode: String): Boolean {
        if (pincode.length < 6) {
            return false
        }


        for (i in 0 until pincode.length - 2) {
            if (pincode[i] == pincode[i + 1] && pincode[i + 1] == pincode[i + 2]) {
                return false
            }
        }

        for (i in 0 until pincode.length - 2) {
            val number1 = pincode[i].toInt()
            val number2 = pincode[i + 1].toInt()
            val number3 = pincode[i + 2].toInt()

            if (number2 - number1 == 1 && number3 - number2 == 1) {
                return false
            }
        }

        val inputGroups = mutableMapOf<Char, Int>()
        for (number in pincode) {
            if (inputGroups.containsKey(number)) {
                inputGroups[number] = inputGroups[number]!! + 1
            } else {
                inputGroups[number] = 1
            }
            if (inputGroups[number]!! > 2) {
                return false
            }
        }

        return true
    }
}
