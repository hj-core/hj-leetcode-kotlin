package com.hj.leetcode.kotlin.problem3190

/**
 * LeetCode page: [3190. Find Minimum Operations to Make All Elements Divisible by Three](https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minimumOperations(nums: IntArray): Int =
        nums.count { it % 3 != 0 }
}
