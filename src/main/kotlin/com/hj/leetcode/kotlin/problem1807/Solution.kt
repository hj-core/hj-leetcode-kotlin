package com.hj.leetcode.kotlin.problem1807

/**
 * LeetCode page: [1807. Evaluate the Bracket Pairs of a String](https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(N+M) where N is the length of s and M is
    // the flattened string length of knowledge.
    fun evaluate(
        s: String,
        knowledge: List<List<String>>,
    ): String {
        val knowledgeMap = knowledge.associateBy { it[0] }

        val builder = StringBuilder()
        var i = 0
        while (i < s.length) {
            if (s[i] == '(') {
                val closedAt = s.indexOf(')', i + 1)
                val key = s.substring(i + 1, closedAt)
                val value = knowledgeMap[key]?.get(1) ?: '?'
                builder.append(value)
                i = closedAt
            } else {
                builder.append(s[i])
            }

            i++
        }

        return builder.toString()
    }
}
