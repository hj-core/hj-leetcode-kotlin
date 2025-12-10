package com.hj.leetcode.kotlin.problem3577

/**
 * LeetCode page: [3577. Count the Number of Computer Unlocking Permutations](https://leetcode.com/problems/count-the-number-of-computer-unlocking-permutations/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of complexity.
    fun countPermutations(complexity: IntArray): Int {
        // The result is either 0 or (n-1)!
        var result = 1L
        val modulo = 1_000_000_007

        for (i in 1..<complexity.size) {
            if (complexity[i] <= complexity[0]) {
                return 0
            }
            result = result * i % modulo
        }
        return result.toInt()
    }
}
