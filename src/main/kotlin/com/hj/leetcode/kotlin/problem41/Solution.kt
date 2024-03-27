package com.hj.leetcode.kotlin.problem41

/**
 * LeetCode page: [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the size of nums;
     */
    fun firstMissingPositive(nums: IntArray): Int {
        /* The result is within the range from 1 to n+1.
         * Use nums[i] != i+1 to indicate the value i+1 is missing.
         */
        for (i in nums.indices) {
            while (nums[i] in 1..nums.size
                && nums[i] != nums[nums[i] - 1]
            ) {
                nums.swap(i, nums[i] - 1)
            }
        }

        return 1 + (nums
            .indices
            .firstOrNull { nums[it] != it + 1 }
            ?: nums.size)
    }

    private fun IntArray.swap(i: Int, j: Int) {
        this[i] = this[j].also { this[j] = this[i] }
    }
}