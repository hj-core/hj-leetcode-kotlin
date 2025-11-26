package com.hj.leetcode.kotlin.problem1015

/**
 * LeetCode page: [1015. Smallest Integer Divisible by K](https://leetcode.com/problems/smallest-integer-divisible-by-k/);
 */
class Solution {
    // Complexity:
    // Time O(k) and Space O(1).
    fun smallestRepunitDivByK(k: Int): Int {
        if (k and 1 == 0) {
            return -1
        }
        if (k == 1) {
            return 1
        }

        var rem = 1
        for (len in 2..k) {
            rem = (rem * 10 + 1) % k
            if (rem == 0) {
                return len
            }
        }
        return -1
    }
}
