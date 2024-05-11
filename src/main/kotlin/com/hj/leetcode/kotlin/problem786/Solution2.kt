package com.hj.leetcode.kotlin.problem786

/**
 * LeetCode page: [786. K-th Smallest Prime Fraction](https://leetcode.com/problems/k-th-smallest-prime-fraction/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NLogM) and Space O(1) where N is the size of arr and M is arr.last();
     */
    fun kthSmallestPrimeFraction(arr: IntArray, k: Int): IntArray {
        var lowerBound = 0.0
        var upperBound = 1.0

        while (lowerBound < upperBound) {
            val guessFraction = (lowerBound + upperBound) / 2
            var countSmallerFractions = 0
            var maxFractionIndices = Pair(0, arr.lastIndex) // the arr indices of the numerator and denominator

            // Count the number of valid fractions that are smaller than the guess
            var leastDenominatorIndex = 1
            for ((numeratorIndex, numerator) in arr.withIndex()) {
                while (leastDenominatorIndex < arr.size
                    && numerator >= arr[leastDenominatorIndex] * guessFraction
                ) {
                    leastDenominatorIndex++
                }

                if (leastDenominatorIndex == arr.size) {
                    break
                }

                countSmallerFractions += arr.size - leastDenominatorIndex
                if (numerator * arr[maxFractionIndices.second] >
                    arr[leastDenominatorIndex] * arr[maxFractionIndices.first]
                ) {
                    maxFractionIndices = Pair(numeratorIndex, leastDenominatorIndex)
                }
            }

            when {
                countSmallerFractions < k -> lowerBound = guessFraction
                countSmallerFractions > k -> upperBound = guessFraction
                else -> return intArrayOf(arr[maxFractionIndices.first], arr[maxFractionIndices.second])
            }
        }
        throw IllegalArgumentException()
    }
}