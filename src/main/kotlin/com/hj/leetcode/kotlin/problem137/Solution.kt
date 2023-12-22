package com.hj.leetcode.kotlin.problem137

/**
 * LeetCode page: [137. Single Number II](https://leetcode.com/problems/single-number-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun singleNumber(nums: IntArray): Int {
        var result = 0
        var bitMask = 1
        for (bitPosition in 0..31) {
            val totalOnes = nums.count { (it and bitMask) == bitMask }
            val isSingle = totalOnes % 3 == 1
            if (isSingle) {
                result += bitMask
            }
            bitMask = bitMask shl 1
        }
        return result
    }
}