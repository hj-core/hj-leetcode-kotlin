package com.hj.leetcode.kotlin.problem26

/**
 * LeetCode page: [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun removeDuplicates(nums: IntArray): Int {
        var tailPtr = if (nums.isEmpty()) -1 else 0

        for (index in 1 until nums.size) {
            if (nums[index] != nums[tailPtr]) {
                tailPtr++
                nums[tailPtr] = nums[index]
            }
        }

        return tailPtr + 1
    }
}