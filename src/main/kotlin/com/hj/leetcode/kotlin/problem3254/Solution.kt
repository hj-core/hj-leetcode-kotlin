package com.hj.leetcode.kotlin.problem3254

/**
 * LeetCode page: [3254. Find the Power of K-Size Subarrays I](https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the size of nums.
     */
    fun resultsArray(
        nums: IntArray,
        k: Int,
    ): IntArray {
        if (k == 1) {
            return nums.clone()
        }

        val result = IntArray(nums.size - k + 1)
        var accLength = 1 // The longest consecutive, ascending length ended at i=0
        for (i in 1..<k - 1) {
            accLength = if (canExtend(nums, i)) accLength + 1 else 1
        }

        for (i in k - 1..<nums.size) {
            accLength = if (canExtend(nums, i)) accLength + 1 else 1
            result[i - k + 1] = if (k <= accLength) nums[i] else -1
        }
        return result
    }

    private fun canExtend(
        nums: IntArray,
        i: Int,
    ): Boolean = nums[i - 1] + 1 == nums[i]
}
