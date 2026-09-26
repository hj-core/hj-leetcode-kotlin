package com.hj.leetcode.kotlin.problem1807

/**
 * LeetCode page: [1807. Evaluate the Bracket Pairs of a String](https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/);
 */
class Solution2 {
    // Complexity:
    // Time O(N+M) and Space O(N+M) where N is the length of s and M is
    // the flattened string length of knowledge.
    fun evaluate(
        s: String,
        knowledge: List<List<String>>,
    ): String {
        val knowledgeMap = knowledge.associateBy { it[0] }
        val pattern = """\(([a-z]+)\)""".toRegex()
        return pattern.replace(s) { knowledgeMap[it.groupValues[1]]?.get(1) ?: "?" }
    }
}
