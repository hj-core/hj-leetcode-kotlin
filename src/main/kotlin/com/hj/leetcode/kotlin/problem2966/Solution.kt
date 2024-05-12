package com.hj.leetcode.kotlin.problem2966

/**
 * LeetCode page: [2966. Divide Array Into Arrays With Max Difference](https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun divideArray(nums: IntArray, k: Int): Array<IntArray> {
        val sorted = nums.sorted()
        val isImpossible = sorted.indices.step(3).any { sorted[it] + k < sorted[it + 2] }
        if (isImpossible) {
            return emptyArray()
        }

        return Array(sorted.size / 3) { i ->
            intArrayOf(sorted[i * 3], sorted[i * 3 + 1], sorted[i * 3 + 2])
        }
    }
}