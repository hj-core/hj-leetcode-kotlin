package com.hj.leetcode.kotlin.problem2466

/**
 * LeetCode page: [2466. Count Ways To Build Good Strings](https://leetcode.com/problems/count-ways-to-build-good-strings/);
 */
class Solution {

    private val mod = 1_000_000_007 // modulo required by the problem

    /* Complexity:
     * Time O(high) and Space O(high-low);
     */
    fun countGoodStrings(low: Int, high: Int, zero: Int, one: Int): Int {
        /* The idea is to apply dynamic programming. Let subResults[i] be the number of ways
         * to build a good string of length i.
         */
        val subResults = hashMapOf<Int, Int>().apply {
            updateBaseCases(this)
            updateRemainingCases(low, high, zero, one, this)
        }

        return originalProblem(low, high, subResults)
    }

    private fun updateBaseCases(subResults: HashMap<Int, Int>) {
        // There is only one way to build an empty string
        subResults[0] = 1
    }

    private fun updateRemainingCases(
        low: Int, high: Int,
        zero: Int, one: Int,
        subResults: HashMap<Int, Int>
    ) {
        /* Solve the sub results in increasing order of length using the relation that
         * subResults[i-zero] + subResults[i-one] = subResults[i].
         */
        for (length in 0..high) {
            val subResult = subResults[length] ?: continue

            // Contribute the sub result of length to the cases that are related to it
            subResults[length + zero] = subResults[length + zero]
                ?.let { (it + subResult) % mod }
                ?: subResult
            subResults[length + one] = subResults[length + one]
                ?.let { (it + subResult) % mod }
                ?: subResult

            // The sub result of length is no longer needed if it is less than low
            if (length < low) {
                subResults.remove(length)
            }
        }
    }

    private fun originalProblem(low: Int, high: Int, subResults: HashMap<Int, Int>): Int {
        // The original problem is the sum of sub results from low to high
        var originalProblem = 0
        for (length in low..high) {
            val subResult = subResults[length] ?: continue
            originalProblem = (originalProblem + subResult) % mod
        }
        return originalProblem
    }
}