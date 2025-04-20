package com.hj.leetcode.kotlin.problem781

/**
 * LeetCode page: [781. Rabbits in Forest](https://leetcode.com/problems/rabbits-in-forest/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of answers.
    fun numRabbits(answers: IntArray): Int {
        val groups = mutableMapOf<Int, Int>() // entry=(size, known members)
        var result = 0

        for (answer in answers) {
            val knownMembers = 1 + (groups[answer] ?: 0)

            if (knownMembers == 1) {
                result += answer + 1
            }
            groups[answer] = if (knownMembers == answer + 1) 0 else knownMembers
        }
        return result
    }
}
