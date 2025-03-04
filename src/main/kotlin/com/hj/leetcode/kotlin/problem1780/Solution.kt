package com.hj.leetcode.kotlin.problem1780

/**
 * LeetCode page: [1780. Check if Number is a Sum of Powers of Three](https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/);
 */
class Solution {
    // Complexity:
    // Time O(Log(n)) and Space O(Log(n)).
    fun checkPowersOfThree(n: Int): Boolean = n.toString(radix = 3).all { it == '0' || it == '1' }
}
