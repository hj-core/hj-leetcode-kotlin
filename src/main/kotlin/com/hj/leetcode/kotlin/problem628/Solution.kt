package com.hj.leetcode.kotlin.problem628

/**
 * LeetCode page: [628. Maximum Product of Three Numbers](https://leetcode.com/problems/maximum-product-of-three-numbers/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maximumProduct(nums: IntArray): Int {
        if (nums.size == 3) {
            return nums.reduce(Int::times)
        }

        val minThree = intArrayOf(nums[0], nums[1], nums[2], nums[3])
        minThree.sort()
        val maxThree = minThree.clone()

        for (i in 4..<nums.size) {
            minThree[3] = nums[i]
            minThree.sort()
            maxThree[0] = nums[i]
            maxThree.sort()
        }

        return maxOf(minThree[0] * minThree[1], maxThree[1] * maxThree[2]) * maxThree[3]
    }
}
