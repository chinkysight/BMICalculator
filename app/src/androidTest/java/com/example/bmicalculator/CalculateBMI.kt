package com.example.bmicalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.isEmptyString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculateBMI {

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun snackBar_popsUp_whenEveryTextInputEditText_isEmpty() {
        onView(withId(R.id.weight_textInputEditText)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.height_in_feet_textInputEditText)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.height_in_inch_textInputEditText)).check(matches(withText(isEmptyString())))

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText("Please enter required information")))
    }

    @Test
    fun snackBar_popsUp_whenWeightTextInputEditText_isEmpty() {
        onView(withId(R.id.weight_textInputEditText)).check(matches(withText(isEmptyString())))

        onView(withId(R.id.height_in_feet_textInputEditText)).perform(typeText("5"))

        onView(withId(R.id.height_in_inch_textInputEditText)).perform(typeText("11")).perform(
            closeSoftKeyboard()
        )

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText("Please enter your weight")))
    }

    @Test
    fun snackBar_popsUp_whenHeightInFeetTextInputEditText_isEmpty() {
        onView(withId(R.id.height_in_feet_textInputEditText)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.height_in_inch_textInputEditText)).check(matches(withText(isEmptyString())))

        onView(withId(R.id.weight_textInputEditText)).perform(typeText("85")).perform(
            closeSoftKeyboard()
        )

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText("Please enter your height")))
    }

    @Test
    fun calculate_BMI() {
        onView(withId(R.id.weight_textInputEditText)).perform(typeText("85"))
        onView(withId(R.id.height_in_feet_textInputEditText)).perform(typeText("5"))
            .perform(closeSoftKeyboard())

        // Value in height_in_inch_textInputEditText is optional, so we are not giving it a value

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.bmi_result)).check(matches(withText(containsString("36.6 (Overweight)"))))

        // Providing value to height_in_inch_textInputEditText
        onView(withId(R.id.height_in_inch_textInputEditText)).perform(typeText("11"))

        onView(withId(R.id.weight_textInputEditText)).perform(clearText()).perform(typeText("75"))
        onView(withId(R.id.height_in_feet_textInputEditText)).perform(clearText())
            .perform(typeText("5")).perform(closeSoftKeyboard())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.bmi_result)).check(matches(withText(containsString("23.1 (Normal)"))))
    }

}