package com.hj.leetcode.kotlin.problem3513

/**
 * LeetCode page: [3513. Number of Unique XOR Triplets I](https://leetcode.com/problems/number-of-unique-xor-triplets-i/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun uniqueXorTriplets(nums: IntArray): Int =
        nums.size.let { if (it < 3) it else it.takeHighestOneBit() shl 1 }
}
