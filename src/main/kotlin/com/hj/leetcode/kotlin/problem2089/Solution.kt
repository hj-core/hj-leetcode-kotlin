package com.hj.leetcode.kotlin.problem2089

/**
 * LeetCode page: [2089. Find Target Indices After Sorting Array](https://leetcode.com/problems/find-target-indices-after-sorting-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Auxiliary Space O(1) where N is the length of nums.
    fun targetIndices(
        nums: IntArray,
        target: Int,
    ): List<Int> {
        var cntLess = 0
        var cntTarget = 0
        for (num in nums) {
            if (num < target) {
                cntLess++
            } else if (num == target) {
                cntTarget++
            }
        }

        return List(cntTarget) { cntLess + it }
    }
}
