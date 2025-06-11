package com.hj.leetcode.kotlin.problem3445

/**
 * LeetCode page: [3445. Maximum Difference Between Even and Odd Frequency II](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N*(M^2)) and Space O(1) where N is the length of s, and M is the
    // size of character set.
    fun maxDifference(
        s: String,
        k: Int,
    ): Int {
        var result = -s.length
        val chars = "01234"
        for (i in chars) {
            for (j in chars) {
                if (i != j) {
                    result = maxOf(result, maxDifference2(s, k, i, j))
                }
            }
        }
        check(result != -s.length) { "No valid pair (a, b) is found" }
        return result
    }

    // Returns the maximum difference between the freq[a] and freq[b] according to
    // the problem, or -len(s) if no valid difference is found.
    private fun maxDifference2(
        s: String,
        k: Int,
        a: Char,
        b: Char,
    ): Int {
        // minDiff[aCnt%2][bCnt%2]:=
        // the minimum aCntL-bCntL with the specific parities among the prefixes
        // of s[0..<left].
        val minDiff =
            arrayOf(
                intArrayOf(0, s.length),
                intArrayOf(s.length, s.length),
            )

        // The left pointer should not exceed right-k, nor cause zero frequency
        // of a or b in s[left+1..=right].
        var left = 0
        var aCntL = 0
        var bCntL = 0

        var aCntR = 0
        var bCntR = 0
        var result = -s.length

        for (right in s.indices) {
            if (s[right] == a) {
                aCntR++
            } else if (s[right] == b) {
                bCntR++
            }

            if (aCntR == 0 || bCntR == 0 || right < k - 1) {
                continue
            }

            while (left <= right - k) {
                if (s[left] == a) {
                    if (aCntL == aCntR - 1) {
                        break
                    } else {
                        aCntL++
                    }
                } else if (s[left] == b) {
                    if (bCntL == bCntR - 1) {
                        break
                    } else {
                        bCntL++
                    }
                } else {
                    left++
                    continue
                }

                minDiff[aCntL and 1][bCntL and 1] =
                    minOf(
                        aCntL - bCntL,
                        minDiff[aCntL and 1][bCntL and 1],
                    )
                left++
            }

            if (minDiff[1 - (aCntR and 1)][bCntR and 1] != s.length) {
                result =
                    maxOf(
                        result,
                        aCntR - bCntR - minDiff[1 - (aCntR and 1)][bCntR and 1],
                    )
            }
        }
        return result
    }
}
