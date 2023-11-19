package com.hj.leetcode.kotlin.problem1887

/**
 * LeetCode page: [1887. Reduction Operations to Make the Array Elements Equal](https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun reductionOperations(nums: IntArray): Int {
        val sorted = nums.sorted()
        var result = 0
        var localOps = 0

        for (index in 1..<sorted.size) {
            if (sorted[index] != sorted[index - 1]) {
                localOps++
            }
            result += localOps
        }
        return result
    }
}