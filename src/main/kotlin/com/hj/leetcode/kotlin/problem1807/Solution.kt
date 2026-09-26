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
        var openIdx = -1
        for ((i, c) in s.withIndex()) {
            when (c) {
                '(' -> {
                    openIdx = i
                }

                ')' -> {
                    val key = s.substring(openIdx + 1, i)
                    val value = knowledgeMap[key]?.get(1) ?: '?'
                    builder.append(value)
                    openIdx = -1
                }

                else -> {
                    if (openIdx == -1) {
                        builder.append(c)
                    }
                }
            }
        }

        return builder.toString()
    }
}
