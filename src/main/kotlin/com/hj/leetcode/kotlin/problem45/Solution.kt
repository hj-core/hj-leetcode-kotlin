package com.hj.leetcode.kotlin.problem45

/**
 * LeetCode page: [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(|nums|) and Space O(1);
     */
    fun jump(nums: IntArray): Int {
        return findMinJump(nums)
    }

    private tailrec fun findMinJump(
        jumpLengths: IntArray,
        currJumps: Int = 0,
        currFurthest: Int = 0,
        nextFurthest: Int = currFurthest + jumpLengths[currFurthest]
    ): Int {

        if (currFurthest >= jumpLengths.lastIndex) return currJumps
        if (nextFurthest >= jumpLengths.lastIndex) return currJumps + 1
        if (currFurthest == nextFurthest) {
            throw IllegalArgumentException("Cannot reach last index although is guaranteed in the problem statement.")
        }

        var newFurthest = nextFurthest
        for (i in currFurthest + 1..nextFurthest) {
            val maxReachable = i + jumpLengths[i]
            if (maxReachable > newFurthest) {
                newFurthest = maxReachable
            }
        }
        return findMinJump(jumpLengths, currJumps + 1, nextFurthest, newFurthest)
    }
}