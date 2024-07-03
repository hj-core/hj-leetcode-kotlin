package com.hj.leetcode.kotlin.problem1509

import kotlin.math.min

/**
 * LeetCode page: [1509. Minimum Difference Between Largest and Smallest Value in Three Moves](https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun minDifference(nums: IntArray): Int {
        val k = 3
        if (nums.size <= k + 1) {
            return 0
        }

        /* Actually we don't need to sort the whole array, what we need is the k+1 smallest
         * and k+1 largest elements;
         */
        val sortedNums = nums.sorted()
        var result = sortedNums.last() - sortedNums.first()

        for (left in 0..k) {
            val right = nums.size - (k + 1) + left
            result = min(result, sortedNums[right] - sortedNums[left])
        }
        return result
    }
}