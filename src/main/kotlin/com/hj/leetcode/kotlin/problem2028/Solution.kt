package com.hj.leetcode.kotlin.problem2028

/**
 * LeetCode page: [2028. Find Missing Observations](https://leetcode.com/problems/find-missing-observations/);
 */
class Solution {
    /* Complexity:
     * Time O(m+n) and Auxiliary Space O(1) where m is the size of rolls.
     */
    fun missingRolls(
        rolls: IntArray,
        mean: Int,
        n: Int,
    ): IntArray {
        val m = rolls.size
        val mRollsSum = rolls.sum()
        val nRollsSum = mean * (m + n) - mRollsSum

        if (nRollsSum !in n..(6 * n)) {
            return IntArray(0)
        }

        val (average, shortage) = Pair(nRollsSum / n, nRollsSum % n)
        return IntArray(n) { i ->
            if (i < shortage) average + 1 else average
        }
    }
}
