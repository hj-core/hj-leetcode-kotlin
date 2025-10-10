package com.hj.leetcode.kotlin.problem2327

/**
 * LeetCode page: [2327. Number of People Aware of a Secret](https://leetcode.com/problems/number-of-people-aware-of-a-secret/);
 */
class Solution {
    // Complexity:
    // Time O(n) and Space O(forget).
    fun peopleAwareOfSecret(
        n: Int,
        delay: Int,
        forget: Int,
    ): Int {
        val modulo = 1_000_000_007

        // dp[i]:= the number of people who hear the secret on day i.
        // dp[i] = sum(dp[j] for i-forget < j <= i-delay).
        //
        // We implement dp using a ringBuf and update dp[i] with a
        // sliding window.
        val dp = RingBuf(forget)
        dp[1] = 1
        var i = delay

        var wndStart = dp.nextIndex(delay) // i-forget+1
        var wndEnd = 0 // i-delay, inclusive
        var wndSum = 0

        repeat(n - delay) {
            wndEnd = dp.nextIndex(wndEnd)

            wndSum += dp[wndEnd] - dp[wndStart]
            if (wndSum < 0) {
                wndSum += modulo
            } else if (wndSum >= modulo) {
                wndSum -= modulo
            }

            i = wndStart
            wndStart = dp.nextIndex(wndStart)
            dp[i] = wndSum
        }

        var result = dp[i]
        repeat(delay) {
            result += dp[i]
            if (result >= modulo) {
                result -= modulo
            }

            i = dp.prevIndex(i)
        }

        return result
    }
}

private typealias RingBuf = IntArray

private fun RingBuf.nextIndex(currIndex: Int): Int = if (currIndex == lastIndex) 0 else currIndex + 1

private fun RingBuf.prevIndex(currIndex: Int): Int = if (currIndex == 0) lastIndex else currIndex - 1
