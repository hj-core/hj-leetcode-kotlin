package com.hj.leetcode.kotlin.problem3644

/**
 * LeetCode page: [3644. Maximum K to Sort a Permutation](https://leetcode.com/problems/maximum-k-to-sort-a-permutation/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun sortPermutation(nums: IntArray): Int {
        if (nums[0] != 0) {
            return 0
        }

        var maxK = Int.MAX_VALUE
        for (i in nums.indices) {
            if (nums[i] != i) {
                maxK = maxK and nums[i]
            }
        }
        return if (maxK == Int.MAX_VALUE) 0 else maxK
    }
}
