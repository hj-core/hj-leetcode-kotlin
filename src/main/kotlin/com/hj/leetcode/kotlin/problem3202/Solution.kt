package com.hj.leetcode.kotlin.problem3202

/**
 * LeetCode page: [3202. Find the Maximum Length of Valid Subsequence II](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/);
 */
class Solution {
    // Complexity:
    // Time O(kN+k^2) and Space O(k^2) where N is the length
    // of nums.
    fun maximumLength(
        nums: IntArray,
        k: Int,
    ): Int {
        // greedy[i][j]:= the longest length of a sequence with a
        // remainder matching the alternating i-j pattern.
        val greedy = Array(k) { IntArray(k) }

        var result = 0
        for (num in nums) {
            val r = num % k
            for (i in 0..<k) {
                if (i == r) {
                    greedy[r][r]++
                    result = maxOf(result, greedy[r][r])
                    continue
                }
                if (greedy[r][i] and 1 == 0) {
                    greedy[r][i]++
                    result = maxOf(result, greedy[r][i])
                }
                if (greedy[i][r] and 1 == 1) {
                    greedy[i][r]++
                    result = maxOf(result, greedy[i][r])
                }
            }
        }
        return result
    }
}
