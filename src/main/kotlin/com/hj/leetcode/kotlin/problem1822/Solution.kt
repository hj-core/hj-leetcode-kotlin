package com.hj.leetcode.kotlin.problem1822

/**
 * LeetCode page: [1822. Sign of the Product of an Array](https://leetcode.com/problems/sign-of-the-product-of-an-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun arraySign(nums: IntArray): Int {
        if (nums.contains(0)) {
            return 0
        }

        val numNegatives = nums.count { it < 0 }
        return if (numNegatives.isOdd()) -1 else 1
    }

    private fun Int.isOdd(): Boolean = this and 1 == 1
}