package com.hj.leetcode.kotlin.problem997

/**
 * LeetCode page: [997. Find the Town Judge](https://leetcode.com/problems/find-the-town-judge/);
 */
class Solution {
    /* Complexity:
     * Time O(n+M) and Space O(n) where M is the size of trust;
     */
    fun findJudge(n: Int, trust: Array<IntArray>): Int {
        // nodeDegrees[i] ::= the (in degrees, out degrees) of node i
        val nodeDegrees = Array(n + 1) { mutableListOf(0, 0) }

        for ((p1, p2) in trust) {
            nodeDegrees[p2][0]++
            nodeDegrees[p1][1]++
        }
        val judgeDegrees = listOf(n - 1, 0)
        return (1..n).find { nodeDegrees[it] == judgeDegrees } ?: -1
    }
}