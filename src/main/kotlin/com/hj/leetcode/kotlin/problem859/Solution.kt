package com.hj.leetcode.kotlin.problem859

/**
 * LeetCode page: [859. Buddy Strings](https://leetcode.com/problems/buddy-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(1) where L is the length of s;
     */
    fun buddyStrings(s: String, goal: String): Boolean {
        if (s.length != goal.length) {
            return false
        }

        val firstThreeDifferences = s.asSequence()
            .zip(goal.asSequence())
            .filter { it.first != it.second }
            .take(3)
            .toList()

        return when (firstThreeDifferences.size) {
            0 -> s.hasRepetition()
            2 -> firstThreeDifferences[0].reversed() == firstThreeDifferences[1]
            else -> false
        }
    }

    private fun String.hasRepetition(): Boolean {
        val visited = hashSetOf<Char>()
        for (char in this) {
            if (char in visited) {
                return true
            }
            visited.add(char)
        }
        return false
    }

    private fun <A, B> Pair<A, B>.reversed(): Pair<B, A> {
        return Pair(second, first)
    }
}