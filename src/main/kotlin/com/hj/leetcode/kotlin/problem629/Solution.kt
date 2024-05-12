package com.hj.leetcode.kotlin.problem629

/**
 * LeetCode page: [629. K Inverse Pairs Array](https://leetcode.com/problems/k-inverse-pairs-array/);
 */
class Solution {
    /* Complexity:
     * Time O(nk) and Space O(k);
     */
    fun kInversePairs(n: Int, k: Int): Int {
        val modulo = 1_000_000_007
        // dp[j]@i::= kInversePairs(i, j), start with i_prev=0 and i_curr=1
        var dpPrev = IntArray(k + 1).apply { this[0] = 1 }
        var dpCurr = IntArray(k + 1).apply { this[0] = 1 }
        for (i in 2..n) {
            dpCurr = dpPrev.also { dpPrev = dpCurr }
            for (j in 1..k) {
                dpCurr[j] = (dpCurr[j - 1]
                        - dpPrev.getOrElse(j - i) { 0 }
                        + dpPrev[j]
                        ).let { if (it < 0) it + modulo else it % modulo }
            }
        }
        return dpCurr[k]
    }
}