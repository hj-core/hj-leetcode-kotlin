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
            2 -> {
                val diff1 = firstThreeDifferences[0]
                val diff2 = firstThreeDifferences[1]
                diff1.first == diff2.second && diff1.second == diff2.first
            }

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
}