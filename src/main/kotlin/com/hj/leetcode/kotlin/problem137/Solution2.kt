package com.hj.leetcode.kotlin.problem137

/**
 * LeetCode page: [137. Single Number II](https://leetcode.com/problems/single-number-ii/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun singleNumber(nums: IntArray): Int {
        /* The core of this algorithm is still to find the single number through the frequency of each bit.
         * However, it uses two integers to track the frequency of each bit. Take nth bit as example:
         *   | nth bit freq     | 0 | 1 | 2 | 3 |
         *   | nth bit of once  | 0 | 1 | 0 | 0 |
         *   | nth bit of twice | 0 | 0 | 1 | 0 |
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