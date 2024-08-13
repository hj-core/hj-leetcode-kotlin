package com.hj.leetcode.kotlin.problem40

/**
 * LeetCode page: [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/);
 */
class Solution {
    /* Complexity:
     * TODO()
     */
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        val sortedCandidates = candidates.sorted()
        // dp[i]@j ::= the unique combinations from candidates[0..j] that sum to i
        val dp = Array(target + 1) { mutableSetOf<List<Int>>() }

        dp[0].add(emptyList()) // Allow each candidate to append the list of itself
        for ((j, candidate) in sortedCandidates.withIndex()) {
            for (i in (target - candidate) downTo 0) {
                for (combination in dp[i]) {
                    /* Sorted candidates ensure that the combinations we add are also sorted.
                     * Together with the set, we can avoid duplication.
                     */
                    dp[i + candidate].add(combination.plus(candidate))
                }
            }
        }
        return dp[target].toList()
    }
}