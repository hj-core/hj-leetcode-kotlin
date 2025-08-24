package com.hj.leetcode.kotlin.problem1493

class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the size of nums.
    fun longestSubarray(nums: IntArray): Int {
        var countZeros = 0
        var left = 0
        for (num in nums) {
            countZeros += num xor 1

            if (countZeros > 1) {
                countZeros -= nums[left] xor 1
                left++
            }
        }
        // The largest window can contain no zeros if nums
        // are all ones. Otherwise, it must contain exactly
        // one zero. In both cases, we need to subtract one
        // from the width.
        return nums.size - left - 1
    }
}
