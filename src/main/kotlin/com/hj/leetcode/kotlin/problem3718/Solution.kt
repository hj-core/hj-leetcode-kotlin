package com.hj.leetcode.kotlin.problem3718

/**
 * LeetCode page: [3718. Smallest Missing Multiple of K](https://leetcode.com/problems/smallest-missing-multiple-of-k/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(M) where N is the length of nums and M is the
    // upper bound of maximum nums.
    fun missingMultiple(
        nums: IntArray,
        k: Int,
    ): Int {
        val maxNum = 100
        val seen = BooleanArray(maxNum + 1)
        for (num in nums) {
            seen[num] = true
        }

        for (num in k..maxNum step k) {
            if (!seen[num]) {
                return num
            }
        }
        return k * (maxNum / k + 1)
    }
}
