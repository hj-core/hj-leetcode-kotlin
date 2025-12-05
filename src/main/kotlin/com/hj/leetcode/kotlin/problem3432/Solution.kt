package com.hj.leetcode.kotlin.problem3432

/**
 * LeetCode page: [3432. Count Partitions with Even Sum Difference](https://leetcode.com/problems/count-partitions-with-even-sum-difference/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun countPartitions(nums: IntArray): Int =
        nums.reduce(Int::xor).let {
            val isEvenArrSum = it and 1 == 0
            if (isEvenArrSum) nums.size - 1 else 0
        }
}
