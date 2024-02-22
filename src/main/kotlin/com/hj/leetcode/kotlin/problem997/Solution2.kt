package com.hj.leetcode.kotlin.problem997

/**
 * LeetCode page: [997. Find the Town Judge](https://leetcode.com/problems/find-the-town-judge/);
 */
class Solution2 {
    /* Complexity:
     * Time O(n+M) and Space O(n) where M is the size of trust;
     */
    fun findJudge(n: Int, trust: Array<IntArray>): Int {
        val netTrust = IntArray(n + 1)
        for ((p1, p2) in trust) {
            netTrust[p1]--
            netTrust[p2]++
        }

        for (person in 1..n) {
            if (netTrust[person] == n - 1) {
                return person
            }
        }
        return -1
    }
}