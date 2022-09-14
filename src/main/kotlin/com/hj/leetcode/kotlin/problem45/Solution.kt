package com.hj.leetcode.kotlin.problem45

/**
 * LeetCode page: [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun jump(nums: IntArray): Int {
        return findMinNumberOfJumpsToLastIndex(nums)
    }

    private tailrec fun findMinNumberOfJumpsToLastIndex(
        jumpLengths: IntArray,
        currIndex: Int = 0,
        currNumberOfJumps: Int = 0
    ): Int {
        if (jumpLengths.size == 1) return 0

        val currFurthestReachable = currIndex + jumpLengths[currIndex]
        return if (currFurthestReachable >= jumpLengths.lastIndex) {
            currNumberOfJumps + 1
        } else {
            var optimalNextIndex = currIndex
            var nextFurthestReachable = currFurthestReachable
            for (index in currIndex + 1..currFurthestReachable) {
                val furthestReachable = index + jumpLengths[index]
                if (furthestReachable >= nextFurthestReachable) {
                    optimalNextIndex = index
                    nextFurthestReachable = furthestReachable
                }
            }
            findMinNumberOfJumpsToLastIndex(jumpLengths, optimalNextIndex, currNumberOfJumps + 1)
        }
    }
}