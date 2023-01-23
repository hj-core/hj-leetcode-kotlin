package com.hj.leetcode.kotlin.problem997

/**
 * LeetCode page: [997. Find the Town Judge](https://leetcode.com/problems/find-the-town-judge/);
 */
class Solution2 {
    /* Complexity:
     * Time O(n + |trust|) and Space O(n);
     */
    fun findJudge(n: Int, trust: Array<IntArray>): Int {
        val netNumberOfTrust = IntArray(n + 1)
        for ((i, j) in trust) {
            netNumberOfTrust[i]--
            netNumberOfTrust[j]++
        }

        for (person in 1..n) {
            if (netNumberOfTrust[person] == n - 1) {
                return person
            }
        }
        return -1
    }
}