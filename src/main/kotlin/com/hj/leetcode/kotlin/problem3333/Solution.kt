package com.hj.leetcode.kotlin.problem3333

/**
 * LeetCode page: [3333. Find the Original Typed String II](https://leetcode.com/problems/find-the-original-typed-string-ii/);
 */
class Solution {
    private val modulo = 1_000_000_007

    // Complexity:
    // Time O(N+k^2) and Space O(N) where N is the length of word.
    fun possibleStringCount(
        word: String,
        k: Int,
    ): Int {
        if (word.length < k) {
            return 0
        }
        if (word.length == k) {
            return 1
        }

        // We solve an equivalent problem:
        //
        // Given an array of bags, each with bags[i] balls, what is the
        // number of ways to pick at least minBalls?

        val bags = mapWordToBags(word)
        val n = bags.size
        val minBalls = k - n

        val totalWays = countTotalWays(bags)
        if (minBalls <= 0) {
            return totalWays
        }

        val invalidWays = countWays(bags, minBalls - 1)
        return (totalWays - invalidWays).mod(modulo)
    }

    // Returns an array containing len(part) - 1 for each part if we
    // split the word when the character changes.
    private fun mapWordToBags(word: String): IntArray {
        val parts = mutableListOf(0)
        for (i in 1..<word.length) {
            if (word[i] != word[i - 1]) {
                parts.add(0)
            } else {
                parts[parts.lastIndex]++
            }
        }
        return parts.toIntArray()
    }

    // Returns the number of ways (mod the given modulo) to pick zero or
    // more balls from bags.
    private fun countTotalWays(bags: IntArray): Int {
        var result = 1L
        for (balls in bags) {
            result = (result * (1 + balls)) % modulo
        }
        return result.toInt()
    }

    // Returns the number of ways (mod the given modulo) to pick at most
    // maxBalls from bags.
    private fun countWays(
        bags: IntArray,
        maxBalls: Int,
    ): Int {
        // dp[j]@i := the number of ways to pick j balls from bags[0..=i]
        val dp = LongArray(maxBalls + 1)
        dp[0] = 1 // base case i equals -1

        for (i in bags.indices) {
            var wndSum = 0L // Sum of dp[j-bags[i]+1..=j]@i-1
            for (j in maxOf(0, maxBalls - bags[i] + 1)..maxBalls) {
                wndSum = (wndSum + dp[j]) % modulo
            }

            for (j in maxBalls downTo 0) {
                wndSum = (wndSum - dp[j]).mod(modulo).toLong()
                if (j >= bags[i]) {
                    wndSum = (wndSum + dp[j - bags[i]]) % modulo
                }
                dp[j] = (dp[j] + wndSum) % modulo
            }
        }
        return dp.fold(0L) { acc, cnt -> (acc + cnt) % modulo }.toInt()
    }
}
