package com.hj.leetcode.kotlin.problem2007

/**
 * LeetCode page: [2007. Find Original Array From Doubled Array](https://leetcode.com/problems/find-original-array-from-doubled-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Aux_Space O(M) where N is size of changed and M is maximum value of changed;
     */
    fun findOriginalArray(changed: IntArray): IntArray {
        val isSizeOfChangedOdd = changed.size and 1 == 1
        if (isSizeOfChangedOdd) return intArrayOf()

        val sortedNumberToChangedFrequency = getSortedNumberToFrequency(changed)
        return constructOriginalArray(changed.size shr 1, sortedNumberToChangedFrequency)
    }

    private fun getSortedNumberToFrequency(numbers: IntArray): IntArray {
        val maxNumber = checkNotNull(numbers.max())
        val sortedNumberToFrequency = IntArray(maxNumber + 1)
        for (number in numbers) sortedNumberToFrequency[number]++
        return sortedNumberToFrequency
    }

    private fun constructOriginalArray(size: Int, sortedNumberToChangedFrequency: IntArray): IntArray {
        val attemptRevertZeroFailed =
            !attemptRevertFrequencyChangeOfZero(sortedNumberToChangedFrequency)
        if (attemptRevertZeroFailed) return intArrayOf()

        val original = IntArray(size)
        var currIndexOfOriginal = sortedNumberToChangedFrequency[0]

        for (number in 1..sortedNumberToChangedFrequency.lastIndex) {
            val frequencyOfNumber = sortedNumberToChangedFrequency[number]
            if (frequencyOfNumber == 0) continue

            val attemptRevertDoubleFailed =
                !attemptRevertFrequencyChangeOfItsDoubled(number, sortedNumberToChangedFrequency)
            if (attemptRevertDoubleFailed) return intArrayOf()

            repeat(frequencyOfNumber) { original[currIndexOfOriginal++] = number }
        }
        return original
    }

    private fun attemptRevertFrequencyChangeOfZero(numberToFrequency: IntArray): Boolean {
        val frequencyOfZero = numberToFrequency[0]
        val isFrequencyOfZeroOdd = frequencyOfZero and 1 == 1
        if (isFrequencyOfZeroOdd) return false

        numberToFrequency[0] = frequencyOfZero shr 1
        return true
    }

    private fun attemptRevertFrequencyChangeOfItsDoubled(positiveNumber: Int, numberToFrequency: IntArray): Boolean {
        val maxNumber = numberToFrequency.lastIndex
        val frequencyOfNumber = numberToFrequency[positiveNumber]

        val double = positiveNumber shl 1
        val isInvalidDouble = double > maxNumber || numberToFrequency[double] < frequencyOfNumber
        if (isInvalidDouble) return false

        numberToFrequency[double] -= frequencyOfNumber
        return true
    }
}