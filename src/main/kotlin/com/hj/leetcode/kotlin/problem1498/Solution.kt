package com.hj.leetcode.kotlin.problem1498

/**
 * LeetCode page: [1498. Number of Subsequences That Satisfy the Given Sum Condition](https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun numSubseq(nums: IntArray, target: Int): Int {
        /* Instead of finding the result of nums, we can find the result of the sorted nums
         * since their subsequences are one to one mapping.
         */
        val sortedNums = nums.sorted()
        var result = 0
        val mod = 1_000_000_007

        /* For each suffix array of sorted nums, find the result of it with a constraint that
         * its first element must be included, and add to the final result.
         *
         * Two pointers technique is used to determine the maximum index that the subsequence
         * can extend to.
         */
        var suffixStart = 0
        var maxEndIndex = nums.lastIndex

        while (suffixStart <= maxEndIndex) {
            // Find the max index that the subsequence can extend to
            while (
                suffixStart <= maxEndIndex &&
                sortedNums[suffixStart] + sortedNums[maxEndIndex] > target
            ) {
                maxEndIndex--
            }

            // Compute the sub result and add it to the final result
            val maxTailLength = maxEndIndex - suffixStart
            val subResult = when {
                maxTailLength < 0 -> 0
                maxTailLength == 0 -> 1
                else -> normalizedNumSubsequences(maxTailLength, mod)
            }
            result = (result + subResult) % mod

            suffixStart++
        }

        // Return the final result.
        return result
    }

    private fun normalizedNumSubsequences(length: Int, mod: Int): Int {
        if (length <= 0) {
            return 0
        }

        if (length == 1) {
            return 2 % mod
        }

        val halfLength = length / 2
        val halfLengthResult = normalizedNumSubsequences(halfLength, mod).toLong()
        val oddFactor = if (length.isOdd()) 2 else 1

        return halfLengthResult.let {
            (((it * it) % mod) * oddFactor) % mod
        }.toInt()
    }

    private fun Int.isOdd() = this and 1 == 1
}