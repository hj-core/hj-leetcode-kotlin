package com.hj.leetcode.kotlin.problem1437

/**
 * LeetCode page: [1437. Check If All 1's Are at Least Length K Places Away](https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun kLengthApart(
        nums: IntArray,
        k: Int,
    ): Boolean {
        var minNextOne = 0
        for ((i, num) in nums.withIndex()) {
            if (num == 1) {
                if (i < minNextOne) {
                    return false
                }
                minNextOne = i + k + 1
            }
        }
        return true
    }
}
