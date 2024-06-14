package com.hj.leetcode.kotlin.problem945

import kotlin.math.max

/**
 * LeetCode page: [945. Minimum Increment to Make Array Unique](https://leetcode.com/problems/minimum-increment-to-make-array-unique/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun minIncrementForUnique(nums: IntArray): Int {
        val sortedNums = nums.sorted()
        var result = 0
        var previousNumber = sortedNums[0] - 1

        for (num in sortedNums) {
            previousNumber = max(num, previousNumber + 1)
            result += previousNumber - num
        }
        return result
    }
}