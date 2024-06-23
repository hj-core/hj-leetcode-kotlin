package com.hj.leetcode.kotlin.problem1248

/**
 * LeetCode page: [1248. Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun numberOfSubarrays(nums: IntArray, k: Int): Int {
        return numberOfAtMostSubArrays(nums, k) - numberOfAtMostSubArrays(nums, k - 1)
    }

    private fun numberOfAtMostSubArrays(nums: IntArray, k: Int): Int {
        var result = 0
        var start = 0
        var countOdds = 0
        for (end in nums.indices) {
            countOdds += if (nums[end].isOdd()) 1 else 0
            while (countOdds > k) {
                countOdds -= if (nums[start].isOdd()) 1 else 0
                start++
            }
            result += end - start + 1
        }
        return result
    }

    private fun Int.isOdd(): Boolean = this.mod(2) == 1
}