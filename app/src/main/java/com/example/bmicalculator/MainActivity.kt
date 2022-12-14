package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow


class MainActivity : AppCompatActivity() {

//    private val tag: String = "MainActivity"

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateBMI() }
    }

    private fun calculateBMI() {
        val weight: String = binding.weightTextInputEditText.text.toString()
        val heightInFoot: String = binding.heightInFeetTextInputEditText.text.toString()
        var heightInInch: String = binding.heightInInchTextInputEditText.text.toString()

        if (weight.isEmpty() || heightInFoot.isEmpty()) {
            if (weight.isEmpty() && heightInFoot.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Please enter required information",
                    Snackbar.LENGTH_SHORT
                )
                    .show()

            } else if (weight.isEmpty()) {
                Snackbar.make(binding.root, "Please enter your weight", Snackbar.LENGTH_SHORT)
                    .show()

            } else {
                Snackbar.make(binding.root, "Please enter your height", Snackbar.LENGTH_SHORT)
                    .show()

            }
            return
        }

        if (heightInInch.isEmpty()) {
            heightInInch = "0"
        }

        val heightInMeter: Double = convertFeetIntoMeter(
            heightInFoot.toInt(),
            heightInInch.toInt()
        )

        var bmi: Double = weight.toDouble() / heightInMeter.pow(2)


        bmi = roundOffMantissaToNearest(10, bmi)

        val bmiStatus: String = when {
            bmi <= 18.4 -> resources.getString(R.string.underweight)
            bmi > 18.4 && bmi <= 24.9 -> resources.getString(R.string.normal)
            bmi > 24.9 && bmi <= 39.9 -> resources.getString(R.string.overweight)
            else -> resources.getString(R.string.obese)
        }

        binding.bmiResult.text = getString(R.string.bmi_result, bmi.toString(), "($bmiStatus)")

    }
}







