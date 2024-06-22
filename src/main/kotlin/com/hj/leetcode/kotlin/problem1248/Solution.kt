package com.hj.leetcode.kotlin.problem1248

/**
 * LeetCode page: [1248. Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where
     */
    fun numberOfSubarrays(nums: IntArray, k: Int): Int {
        // (# of odds) to (# of such prefix arrays)
        val countPrefixOdds = mutableMapOf<Int, Int>()
        var result = 0
        var prefixOdds = 0
        countPrefixOdds[0] = 1

        for (prefixEnd in nums.indices) {
            if (nums[prefixEnd].isOdd()) {
                prefixOdds++
            }
            result += countPrefixOdds[prefixOdds - k] ?: 0
            countPrefixOdds.compute(prefixOdds) { _, v -> 1 + (v ?: 0) }
        }
        return result
    }

    private fun Int.isOdd(): Boolean = this.mod(2) == 1
}