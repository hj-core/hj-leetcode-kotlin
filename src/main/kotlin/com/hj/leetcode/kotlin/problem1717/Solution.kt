package com.hj.leetcode.kotlin.problem1717

/**
 * LeetCode page: [1717. Maximum Score From Removing Substrings](https://leetcode.com/problems/maximum-score-from-removing-substrings/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun maximumGain(
        s: String,
        x: Int,
        y: Int,
    ): Int =
        if (x < y) {
            val (cntY, cntX) = prioritizePQ(s, 'b', 'a')
            x * cntX + y * cntY
        } else {
            val (cntX, cntY) = prioritizePQ(s, 'a', 'b')
            x * cntX + y * cntY
        }

    // Returns the maximum number of (p,q) and (q,p) pairs that
    // can be removed from s, prioritizing (p,q) over (q,p).
    private fun prioritizePQ(
        s: String,
        p: Char,
        q: Char,
    ): Pair<Int, Int> {
        var cntPQ = 0
        var cntQP = 0
        var liveP = 0
        var deadQ = 0
        // Our stack has deadQ `q`s followed by liveP `p`s (top)
        for (c in s) {
            when (c) {
                q -> {
                    if (liveP > 0) {
                        liveP--
                        cntPQ++
                    } else {
                        deadQ++
                    }
                }

                p -> liveP++

                else -> {
                    cntQP += minOf(deadQ, liveP)
                    liveP = 0
                    deadQ = 0
                }
            }
        }
        cntQP += minOf(deadQ, liveP)
        return cntPQ to cntQP
    }
}
