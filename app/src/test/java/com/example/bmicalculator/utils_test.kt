package com.example.bmicalculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class ConvertFeetIntoMeter{
    @Test
    fun convertFeetIntoMeter(){
        val result : Double = convertFeetIntoMeter(5,11)
        assertEquals("1.8034", result.toString())
    }
}

class RoundOffMantissaToNearest {
    @Test
    fun throwsExceptionWhenPositionIsInvalid() {
        val position = 19
        val exception: InvalidPosition = assertThrows(InvalidPosition::class.java) {
            roundOffMantissaToNearest(position, 0.0)
        }
        assertEquals(
            "Invalid position: $position! Position should be 10, 100, 1000, 10000 and on. " +
                    "For more info: https://www.freecodecamp.org/news/decimal-place-value-hundreds-thousandths-and-beyond",
            exception.message
        )
    }

    @Test
    fun roundOffMantissaToNearest10() {
        val result: Double = roundOffMantissaToNearest(10, 159.99)
        assertEquals("160.0", result.toString())
    }

    @Test
    fun roundOffMantissaToNearest100() {
        val result: Double = roundOffMantissaToNearest(100, 256.1216)
        assertEquals("256.12", result.toString())
    }

    @Test
    fun roundOffMantissaToNearest1000() {
        val result: Double = roundOffMantissaToNearest(1000, 92.13668)
        assertEquals("92.137", result.toString())
    }

}

class IsPositionValidToRoundOffMantissa {
    @Test
    fun returnsFalseWhenThePositionIsLessThan10() {
        val isPositionValid: Boolean = isPositionValidToRoundOffMantissa(9)
        assertEquals(false, isPositionValid)
    }

    @Test
    fun returnsTrueWhenThePositionIsGreaterThan9() {
        val isPositionValid: Boolean = isPositionValidToRoundOffMantissa(10)
        assertEquals(true, isPositionValid)
    }

    @Test
    fun returnsFalseWhenTheQuotientOfPositionDividedBy10IsAFraction() {
        // 13/10 = 1.3 (It is a fraction)
        val isPositionValid: Boolean = isPositionValidToRoundOffMantissa(13)
        assertEquals(false, isPositionValid)
    }

    @Test
    fun returnsTrueWhenTheQuotientOfPositionDividedBy10IsNotFraction() {
        // 1000/10 = 100 (It is not a fraction)
        val isPositionValid: Boolean = isPositionValidToRoundOffMantissa(1000)
        assertEquals(true, isPositionValid)
    }

    @Test
    fun returnsFalseWhenTheLengthOfQuotientOfPositionDividedBy10_is1_butTheValueIsNot() {
        // 20/10 = 2 (The length of quotient is 1, but the value is not 1. The value is 2)
        val isPositionValid: Boolean = isPositionValidToRoundOffMantissa(20)
        assertEquals(false, isPositionValid)
    }

    @Test
    fun returnsTrueWhenTheLengthOfQuotientOfPositionDividedBy10_is1_andTheValueIsAlso1() {
        // 10/10 = 1 (The length of quotient is 1, and the value is also 1)
        val isPositionValid: Boolean = isPositionValidToRoundOffMantissa(10)
        assertEquals(true, isPositionValid)
    }

    @Test
    fun returnsFalseWhenTheLengthOfQuotientOfPositionDividedBy10_isGreaterThan1_butTheFirstTwoCharsOfTheQuotient_areNot1And0() {
        /*
            130/10 = 13 (The length of quotient is greater than 1, which is 2 in this case, but the first two chars are not 1 & 0.
            The first two chars in this case is 1 & 3)
         */
        val isPositionValid: Boolean = isPositionValidToRoundOffMantissa(130)
        assertEquals(false, isPositionValid)
    }

    @Test
    fun returnsTrueWhenTheLengthOfQuotientOfPositionDividedBy10_isGreaterThan1_andTheFirstTwoCharsOfTheQuotient_are1And0() {
        // 10000/10 = 1000 (The length of quotient is greater than 1, which is 4 in this case and the first two chars are 1 & 0)
        val isPositionValid: Boolean = isPositionValidToRoundOffMantissa(10000)
        assertEquals(true, isPositionValid)
    }

}

class IsDoubleFraction {
    @Test
    fun returnsTrueIfDoubleIsFraction() {
        val isDoubleFraction: Boolean = isDoubleFraction(12.5)
        assertEquals(true, isDoubleFraction)
    }

    @Test
    fun returnsFalseIfDoubleIsNotFraction() {
        val isDoubleFraction: Boolean = isDoubleFraction(19.0)
        assertEquals(false, isDoubleFraction)
    }

}