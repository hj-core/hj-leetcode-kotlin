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
        // The longest consecutive, ascending length ended at i=k-2
        var maxLength = 1
        for (i in k - 2 downTo 1) {
            if (canExtendPrev(nums, i)) maxLength += 1 else break
        }

        return IntArray(nums.size - k + 1) {
            val i = it + k - 1
            maxLength = if (canExtendPrev(nums, i)) maxLength + 1 else 1
            if (maxLength < k) -1 else nums[i]
        }
    }

    private fun canExtendPrev(
        nums: IntArray,
        i: Int,
    ): Boolean = nums[i] == nums[i - 1] + 1
}
