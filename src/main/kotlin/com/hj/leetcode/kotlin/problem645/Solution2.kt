package com.hj.leetcode.kotlin.problem645

class Solution2 {

    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun findErrorNums(nums: IntArray): IntArray {
        // Denote the repeated number as a and the missing number as b;
        val n = nums.size
        val aMinusB = nums.sum() - seriesSum(n)
        val aPlusB = ((nums.fold(0L) { acc, i -> acc + i * i } - seriesSquareSum(n)) / aMinusB).toInt()
        val a = (aPlusB + aMinusB) / 2
        return intArrayOf(a, aPlusB - a)
    }

    private fun seriesSum(n: Int): Int {
        return n * (n + 1) / 2
    }

    private fun seriesSquareSum(n: Int): Long {
        return 1L * n * (n + 1) * (2 * n + 1) / 6
    }
}