package com.hj.leetcode.kotlin.problem1674

/**
 * LeetCode page: [1674. Minimum Moves to Make Array Complementary](https://leetcode.com/problems/minimum-moves-to-make-array-complementary/);
 */
class Solution {
    // Complexity:
    // Time O(N+limit) and Space O(limit) where N is the length of nums.
    fun minMoves(
        nums: IntArray,
        limit: Int,
    ): Int {
        val n = nums.size
        val line = IntArray(2 * limit + 2)
        line[1] = n
        for (i in 0..<n / 2) {
            val a = minOf(nums[i], nums[n - 1 - i])
            val b = maxOf(nums[i], nums[n - 1 - i])
            val sum = nums[i] + nums[n - 1 - i]
            line[a + 1] -= 1
            line[sum] -= 1
            line[sum + 1] += 1
            line[b + limit + 1] += 1
        }

        var result = line[1]
        for (i in 2..<line.size) {
            line[i] += line[i - 1]
            result = minOf(result, line[i])
        }

        return result
    }
}
