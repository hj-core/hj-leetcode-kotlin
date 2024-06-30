package com.hj.leetcode.kotlin.problem330

import kotlin.math.min

/**
 * LeetCode page: [330. Patching Array](https://leetcode.com/problems/patching-array/);
 */
class Solution {
    /* Complexity:
     * Time O(M+Log(n)) and Space O(1) where M is the size of nums;
     */
    fun minPatches(nums: IntArray, n: Int): Int {
        var result = 0
        var index = 0
        var covered = 0

        while (covered < n) {
            if (index < nums.size && nums[index] <= covered + 1) {
                covered += min(nums[index], Int.MAX_VALUE - covered)
                index++
            } else {
                covered += min(covered + 1, Int.MAX_VALUE - covered)
                result++
            }
        }
        return result
    }
}