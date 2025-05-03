package com.hj.leetcode.kotlin.problem1007

/**
 * LeetCode page: [1007. Minimum Domino Rotations For Equal Row](https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of tops and bottoms.
    fun minDominoRotations(
        tops: IntArray,
        bottoms: IntArray,
    ): Int {
        // For each possible domino value i (1 to 6):
        // topFreq[i] is the number of dominoes where i appears on the top.
        // bottomFreq[i] is the number of dominoes where i appears on the bottom.
        // commonFreq[i] is the number of dominoes where i appears on both the top and bottom.
        val topFreq = IntArray(7)
        val bottomFreq = IntArray(7)
        val commonFreq = IntArray(7)

        for (i in tops.indices) {
            topFreq[tops[i]]++
            bottomFreq[bottoms[i]]++
            if (tops[i] == bottoms[i]) {
                commonFreq[tops[i]]++
            }
        }

        for (i in 1..6) {
            if (topFreq[i] + bottomFreq[i] - commonFreq[i] == tops.size) {
                return tops.size - maxOf(topFreq[i], bottomFreq[i])
            }
        }
        return -1
    }
}
