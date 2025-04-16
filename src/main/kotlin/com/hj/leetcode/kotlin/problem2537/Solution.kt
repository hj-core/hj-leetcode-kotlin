package com.hj.leetcode.kotlin.problem2537

/**
 * LeetCode page: [2537. Count the Number of Good Subarrays](https://leetcode.com/problems/count-the-number-of-good-subarrays/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun countGood(
        nums: IntArray,
        k: Int,
    ): Long {
        // We compute and sum the number of good subarrays for each potential
        // end index. (sliding window, two-pointer)
        var result = 0L
        val numFreqs = mutableMapOf<Int, Int>()
        var pairs = 0
        var maxStart = 0 // The valid start indices are in the range [0,maxStart).

        for (endInclusive in nums.indices) {
            // Update pairs and freq for the newly joined number
            pairs += numFreqs[nums[endInclusive]] ?: 0
            numFreqs[nums[endInclusive]] = (numFreqs[nums[endInclusive]] ?: 0) + 1

            // Update the maxStart and result
            while (pairs >= k) {
                numFreqs[nums[maxStart]] = checkNotNull(numFreqs[nums[maxStart]]) - 1
                pairs -= checkNotNull(numFreqs[nums[maxStart]])
                maxStart++
            }
            result += maxStart
        }
        return result
    }
}
