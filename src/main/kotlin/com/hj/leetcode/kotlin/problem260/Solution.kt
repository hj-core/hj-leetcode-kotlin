package com.hj.leetcode.kotlin.problem260

/**
 * LeetCode page: [260. Single Number III](https://leetcode.com/problems/single-number-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun singleNumber(nums: IntArray): IntArray {
        val xorOfSingles = nums.reduce { acc, i -> acc xor i }
        val maskForSeparation = xorOfSingles.getRightmostSetBit()

        var single1 = 0
        var single2 = 0
        for (num in nums) {
            val isGroup1 = num.hasBitMaskSet(maskForSeparation)
            if (isGroup1) {
                single1 = single1 xor num
            } else {
                single2 = single2 xor num
            }
        }
        return intArrayOf(single1, single2)
    }

    private fun Int.getRightmostSetBit(): Int {
        return if (this == Int.MIN_VALUE) Int.MIN_VALUE else this - (this and (this - 1))
    }

    private fun Int.hasBitMaskSet(mask: Int): Boolean = this and mask == mask
}