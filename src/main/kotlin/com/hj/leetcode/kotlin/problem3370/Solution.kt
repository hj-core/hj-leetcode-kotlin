package com.hj.leetcode.kotlin.problem3370

/**
 * LeetCode page: [3370. Smallest Number With All Set Bits](https://leetcode.com/problems/smallest-number-with-all-set-bits/);
 */
class Solution {
    // Complexity:
    // Time O(LogLogN) and Space O(1) where is the maximum
    // value of n.
    fun smallestNumber(n: Int): Int {
        var result = n
        result = result or (result shr 1)
        result = result or (result shr 2)
        result = result or (result shr 4)
        result = result or (result shr 8) // 2^16 > 1000 >= n
        return result
    }
}
