package com.hj.leetcode.kotlin.problem3264

/**
 * LeetCode page: [3264. Final Array State After K Multiplication Operations I](https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/);
 */
class Solution {
    /* Complexity:
     * Time O(kN) and Auxiliary Space O(1) where N is the length of nums.
     */
    fun getFinalState(
        nums: IntArray,
        k: Int,
        multiplier: Int,
    ): IntArray =
        nums.clone().apply {
            repeat(k) { this[firstIndexOfMin()] *= multiplier }
        }

    private fun IntArray.firstIndexOfMin(): Int =
        this.foldIndexed(0) { i, acc, elem ->
            if (elem < this[acc]) i else acc
        }
}
