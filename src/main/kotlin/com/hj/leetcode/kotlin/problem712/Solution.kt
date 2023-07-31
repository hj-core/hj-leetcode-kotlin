package com.hj.leetcode.kotlin.problem712

/**
 * LeetCode page: [712. Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the length of s1 and s2 respectively;
     */
    fun minimumDeleteSum(s1: String, s2: String): Int {
        // dp[lengthPrefix1][lengthPrefix2] ::= minimumDeleteSum(s1[:lengthPrefix1], s2[:lengthPrefix2])
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }

        // Base cases that all s1[:lengthPrefix1] is deleted since s2[:0] is empty
        for (lengthPrefix1 in 1..s1.length) {
            dp[lengthPrefix1][0] = s1[lengthPrefix1 - 1].ascii() + dp[lengthPrefix1 - 1][0]
        }
        // Base cases that all s2[:lengthPrefix2] is deleted since s1[:0] is empty
        for (lengthPrefix2 in 1..s2.length) {
            dp[0][lengthPrefix2] = s2[lengthPrefix2 - 1].ascii() + dp[0][lengthPrefix2 - 1]
        }

        for (lengthPrefix1 in 1..s1.length) {
            for (lengthPrefix2 in 1..s2.length) {
                /* If s1[lengthPrefix1-1] equals s2[lengthPrefix2-1], there is a minimumDeleteSum case
                 * that keeps both characters; otherwise, one of the two characters must be deleted.
                 */
                dp[lengthPrefix1][lengthPrefix2] = if (s1[lengthPrefix1 - 1] == s2[lengthPrefix2 - 1]) {
                    dp[lengthPrefix1 - 1][lengthPrefix2 - 1]
                } else {
                    minOf(
                        s1[lengthPrefix1 - 1].ascii() + dp[lengthPrefix1 - 1][lengthPrefix2],
                        s2[lengthPrefix2 - 1].ascii() + dp[lengthPrefix1][lengthPrefix2 - 1]
                    )
                }
            }
        }
        return dp[s1.length][s2.length]
    }

    private fun Char.ascii(): Int {
        require(this in 'a'..'z')
        return 97 + (this - 'a')
    }
}