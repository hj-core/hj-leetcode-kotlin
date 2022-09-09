package com.hj.leetcode.kotlin.problem1996

/**
 * LeetCode page: [1996. The Number of Weak Characters in the Game](https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of properties;
     */
    fun numberOfWeakCharacters(properties: Array<IntArray>): Int {
        val propertyComparator =
            compareByDescending<IntArray> { property -> property[0] }.thenComparing { property -> property[1] }
        val sortedProperties = properties
            .clone()
            .apply { sortWith(propertyComparator) }

        var countWeak = 0
        var currMaxDefense = sortedProperties[0][1]
        for (property in sortedProperties) {
            val defense = property[1]
            if (defense < currMaxDefense) countWeak++ else currMaxDefense = defense
        }

        return countWeak
    }
}