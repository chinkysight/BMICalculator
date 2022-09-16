package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


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

        val weightInString: String = binding.weightTextInputEditText.text.toString()
        val heightInString: String = binding.heightTextInputLayoutEditText.text.toString()

        var weightInDouble: Double? = weightInString.toDoubleOrNull()
        var heightInDouble: Double? = heightInString.toDoubleOrNull()


        if (weightInDouble == null || heightInDouble == null) {
            if (weightInDouble == null && heightInDouble == null) {

                Snackbar.make(binding.root, "Please enter required info", Snackbar.LENGTH_SHORT)
                    .show()

            } else if (weightInDouble == null) {
                Snackbar.make(binding.root, "Please enter your weight", Snackbar.LENGTH_SHORT)
                    .show()

            } else {
                Snackbar.make(binding.root, "Please enter your height", Snackbar.LENGTH_SHORT)
                    .show()

            }
            return
        }

        // Write BMI calculating logic below


    }

}
