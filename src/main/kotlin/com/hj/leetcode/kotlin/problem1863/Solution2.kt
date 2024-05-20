package com.hj.leetcode.kotlin.problem1863

/**
 * LeetCode page: [1863. Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun subsetXORSum(nums: IntArray): Int {
        return nums.reduce(Int::or) shl (nums.size - 1)
    }
}