package com.hj.leetcode.kotlin.problem442

import kotlin.math.abs

/**
 * LeetCode page: [442. Find All Duplicates in an Array](https://leetcode.com/problems/find-all-duplicates-in-an-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun findDuplicates(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()

        // Use the sign of nums[i-1] to indicate if value i has been visited
        for (num in nums) {
            val rawNum = abs(num)
            val hasVisited = nums[rawNum - 1] < 0
            if (hasVisited) {
                result.add(rawNum)
            } else {
                nums[rawNum - 1] = -nums[rawNum - 1]
            }
        }
        // Restore the original array
        for (i in nums.indices) {
            nums[i] = abs(nums[i])
        }
        return result
    }
}