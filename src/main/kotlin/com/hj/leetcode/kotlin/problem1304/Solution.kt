package com.hj.leetcode.kotlin.problem1304

/**
 * LeetCode page: [1304. Find N Unique Integers Sum up to Zero](https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/);
 */
class Solution {
    // Complexity:
    // Time O(n) and Auxiliary Space O(1).
    fun sumZero(n: Int): IntArray {
        val result = IntArray(n)
        for (i in 1..<n step 2) {
            result[i] = i
            result[i - 1] = -i
        }
        return result
    }
}
