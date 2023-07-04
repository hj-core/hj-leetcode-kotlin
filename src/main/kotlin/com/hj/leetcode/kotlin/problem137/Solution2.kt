package com.hj.leetcode.kotlin.problem137

/**
 * LeetCode page: [137. Single Number II](https://leetcode.com/problems/single-number-ii/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun singleNumber(nums: IntArray): Int {
        /* The idea is to represent each number in binary, and each bit of the single number is
         * one iff the total number of ones at that bit modulo three equals one.
         *
         * While computing the number of ones modulo three at each bit,
         * the accumulated value is represented by the bit values of two integers as state, and
         * proper bitwise operation is conceived to handle the state transition.
         *
         * Take nth bit as example:
         *   | totalOnes % 3 | state as (bit_int1, bit_int2)
         *   |       0       |        (0, 0)
         *   |       1       |        (1, 0)
         *   |       2       |        (0, 1)
         */
        var once = 0
        var twice = 0
        for (num in nums) {
            once = (once xor num) and twice.inv()
            twice = (twice xor num) and once.inv()
        }
        return once
    }
}