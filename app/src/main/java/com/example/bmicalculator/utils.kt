package com.example.bmicalculator

import kotlin.math.roundToInt

fun convertFeetIntoMeter(feet: Int, inches: Int): Double {
    val feetInMeter = feet * 0.3048
    val inchesInMeter = inches * 0.0254

    return feetInMeter + inchesInMeter
}

fun roundOffMantissaToNearest(position: Int, number: Double): Double {
    val isPositionValid: Boolean = isPositionValidToRoundOffMantissa(position)

    if (isPositionValid) {
        return (((number * position).roundToInt()).toDouble() / position)
    } else {
        throw InvalidPosition(
            "Invalid position: $position! Position should be 10, 100, 1000, 10000 and on. " +
                    "For more info: https://www.freecodecamp.org/news/decimal-place-value-hundreds-thousandths-and-beyond"
        )
    }
}

fun isPositionValidToRoundOffMantissa(position: Int): Boolean {
    if (position < 10) {
        return false
    }

    val quotient: Double = position.toDouble() / 10

    if (isDoubleFraction(quotient)) {
        return false
    }

    val quotientInInt: Int = quotient.toInt()

    val intQuotientInString: String = quotientInInt.toString()

    if (intQuotientInString.length == 1 && quotientInInt != 1) {
        return false
    }

    if (intQuotientInString.length > 1) {
        val firstTwoCharsOfQuotient =
            "${intQuotientInString[0]}${intQuotientInString[1]}"

        if (firstTwoCharsOfQuotient != "10") {
            return false
        }
    }

    return true
}

fun isDoubleFraction(double: Double): Boolean {
    val doubleInString: String = double.toString()

    val indexOfDecimalPoint: Int = doubleInString.indexOf('.')
    val lengthOfCharsAfterDecimalPoint: Int = (doubleInString.length - 1) - indexOfDecimalPoint

    val firstCharAfterDecimalPoint: Char = doubleInString[indexOfDecimalPoint + 1]

    if (lengthOfCharsAfterDecimalPoint > 1 || firstCharAfterDecimalPoint != '0') {
        return true
    }

    return false
}

class InvalidPosition(message: String) : Exception(message)