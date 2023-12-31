package com.hj.leetcode.kotlin.problem1220

/**
 * LeetCode page: [1220. Count Vowels Permutation](https://leetcode.com/problems/count-vowels-permutation/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(1);
     */
    fun countVowelPermutation(n: Int): Int {
        val modulo = 1_000_000_007
        /* dp@L::= the number of length L permutations ending with
         * 'a', 'e', 'i', 'o', and 'u' respectively
         */
        val dp = mutableListOf(1, 1, 1, 1, 1) // Base case where L equals 1
        for (length in 2..n) {
            val (a, e, i, o, u) = dp
            dp[0] = ((e + i) % modulo + u) % modulo
            dp[1] = (a + i ) % modulo
            dp[2] = (e + o) % modulo
            dp[3] = i
            dp[4] = (i + o) % modulo
        }
        return dp.fold(0) { acc, i -> (acc + i) % modulo }
    }
}