package com.hj.leetcode.kotlin.problem2007

/**
 * LeetCode page: [2007. Find Original Array From Doubled Array](https://leetcode.com/problems/find-original-array-from-doubled-array/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is size of changed;
     */
    fun findOriginalArray(changed: IntArray): IntArray {
        val isSizeOfChangedOdd = changed.size and 1 == 1
        if (isSizeOfChangedOdd) return intArrayOf()

        val sortedNumberToChangedFrequency = getSortedNumberToFrequency(changed)
        return constructOriginalArray(changed.size shr 1, sortedNumberToChangedFrequency)
    }

    private fun getSortedNumberToFrequency(numbers: IntArray): MutableMap<Int, Int> {
        val sortedNumberToFrequency = sortedMapOf<Int, Int>()
        for (number in numbers) {
            sortedNumberToFrequency[number] = sortedNumberToFrequency.getOrDefault(number, 0) + 1
        }
        return sortedNumberToFrequency
    }

    private fun constructOriginalArray(size: Int, sortedNumberToChangedFrequency: MutableMap<Int, Int>): IntArray {
        val attemptRevertZeroFailed =
            !attemptRevertFrequencyChangeOfZero(sortedNumberToChangedFrequency)
        if (attemptRevertZeroFailed) return intArrayOf()

        val original = IntArray(size)
        var currIndexOfOriginal = sortedNumberToChangedFrequency[0] ?: 0
        sortedNumberToChangedFrequency.remove(0)

        for (number in sortedNumberToChangedFrequency.keys) {
            val frequencyOfNumber = checkNotNull(sortedNumberToChangedFrequency[number])
            if (frequencyOfNumber == 0) continue

            val attemptRevertDoubleFailed =
                !attemptRevertFrequencyChangeOfItsDoubled(number, sortedNumberToChangedFrequency)
            if (attemptRevertDoubleFailed) return intArrayOf()

            repeat(frequencyOfNumber) { original[currIndexOfOriginal++] = number }
        }
        return original
    }

    private fun attemptRevertFrequencyChangeOfZero(numberToFrequency: MutableMap<Int, Int>): Boolean {
        val frequencyOfZero = numberToFrequency[0]
        if (frequencyOfZero != null) {
            val isFrequencyOfZeroOdd = frequencyOfZero and 1 == 1
            if (isFrequencyOfZeroOdd) return false

            numberToFrequency[0] = frequencyOfZero shr 1
        }
        return true
    }

    private fun attemptRevertFrequencyChangeOfItsDoubled(
        positiveNumber: Int,
        numberToFrequency: MutableMap<Int, Int>
    ): Boolean {
        val double = positiveNumber shl 1
        val frequencyOfDouble = numberToFrequency[double]
        val frequencyOfNumber = requireNotNull(numberToFrequency[positiveNumber])
        val isInvalidDouble = frequencyOfDouble == null || frequencyOfDouble < frequencyOfNumber
        if (isInvalidDouble) return false

        numberToFrequency[double] = checkNotNull(frequencyOfDouble) - frequencyOfNumber
        return true
    }
}