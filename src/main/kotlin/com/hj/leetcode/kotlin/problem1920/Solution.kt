package com.hj.leetcode.kotlin.problem1920

/**
 * LeetCode page: [1920. Build Array from Permutation](https://leetcode.com/problems/build-array-from-permutation/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums and note that
    // this is an in-place algorithm.
    fun buildArray(nums: IntArray): IntArray {
        val mask = 0xffff
        for (i in nums.indices) {
            val num = nums[i] and mask

            when {
                num < i -> {
                    val newValue = nums[num] ushr 16
                    nums[num] = nums[num] and mask
                    nums[i] = nums[i] xor (num shl 16) xor num xor newValue
                }

                num > i -> {
                    val newValue = nums[num]
                    nums[num] = (nums[num] shl 16) xor nums[num]
                    nums[i] = nums[i] xor (num shl 16) xor num xor newValue
                }
            }
        }
        return nums
    }
}
