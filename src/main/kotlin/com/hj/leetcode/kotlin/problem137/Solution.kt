package com.hj.leetcode.kotlin.problem137

/**
 * LeetCode page: [137. Single Number II](https://leetcode.com/problems/single-number-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun singleNumber(nums: IntArray): Int {
        var single = 0
        var bitMask = 1
        for (shift in 0..31) {
            val bitFrequency = nums.count { num -> num and bitMask == bitMask }
            val isSingle = bitFrequency % 3 != 0
            if (isSingle) single += bitMask
            bitMask = bitMask shl 1
        }
        return single
    }
}
