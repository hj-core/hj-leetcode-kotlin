package com.hj.leetcode.kotlin.problem191

/**
 * LeetCode page: [191. Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/);
 */
class Solution {
    // you need treat n as an unsigned value

    /* Complexity:
     * Time O(N^2) and Space O(1) where N is the number of bits in n's
     * binary representation;
     */
    fun hammingWeight(n:Int):Int {
        var result = 0
        var num = n
        while (num != 0) {
            num = num and (num - 1)
            result += 1
        }
        return result
    }
}