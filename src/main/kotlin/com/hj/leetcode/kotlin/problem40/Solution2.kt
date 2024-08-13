package com.hj.leetcode.kotlin.problem40

import kotlin.math.min

/**
 * LeetCode page: [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/);
 */
class Solution2 {
    /* Complexity:
     * TODO()
     */
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        val sortedCandidates = candidates.sorted()
        val result = mutableListOf<List<Int>>()
        dfs(sortedCandidates, target, 0, mutableListOf(), result)
        return result
    }

    private fun dfs(
        sortedCandidates: List<Int>,
        target: Int,
        index: Int,
        path: MutableList<Int>,
        result: MutableList<List<Int>>,
    ) {
        if (target == 0) {
            result.add(path.toList())
            return
        }
        if (target < 0 || index == sortedCandidates.size) {
            return
        }

        val candidate = sortedCandidates[index]
        var candidateFreq = 0
        for (i in index..<sortedCandidates.size) {
            if (sortedCandidates[i] != candidate) {
                break
            }
            candidateFreq++
        }

        // Include one or multiple times of the candidate
        val maxInclusion = min(candidateFreq, target / candidate)
        for (count in 1..maxInclusion) {
            path.add(candidate)
            dfs(sortedCandidates, target - candidate * count, index + candidateFreq, path, result)
        }
        // Don't include the candidate
        repeat(maxInclusion) {
            path.removeLast()
        }
        dfs(sortedCandidates, target, index + candidateFreq, path, result)
    }
}