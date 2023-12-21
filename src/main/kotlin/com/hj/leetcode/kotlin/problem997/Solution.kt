package com.hj.leetcode.kotlin.problem997

/**
 * LeetCode page: [997. Find the Town Judge](https://leetcode.com/problems/find-the-town-judge/);
 */
class Solution {
    /* Complexity:
     * Time O(n + |trust|) and Space O(n);
     */
    fun findJudge(n: Int, trust: Array<IntArray>): Int {
        val degreeTable = buildDegreeTable(n, trust)
        return degreeTable.indexOf(mutableListOf(0, n - 1))
    }

    private fun buildDegreeTable(n: Int, trust: Array<IntArray>): List<List<Int>> {
        /* degreeTable[i] is a size two list that stores the number of people person i trust
         * and the number of people who trust person i;
         */
        val degreeTable = List(n + 1) { mutableListOf(0, 0) }

        degreeTable[0][0] = -1 // no person is labelled 0
        degreeTable[0][1] = -1 // no person is labelled 0

        for ((i, j) in trust) {
            degreeTable[i][0]++
            degreeTable[j][1]++
        }
        return degreeTable
    }
}