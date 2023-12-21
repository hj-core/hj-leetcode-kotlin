package com.hj.leetcode.kotlin.problem2306

/**
 * LeetCode page: [2306. Naming a Company](https://leetcode.com/problems/naming-a-company/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(L) where L is the flat length of ideas;
     */
    fun distinctNames(ideas: Array<String>): Long {
        val suffixGroups = groupSuffixByFirstLetter(ideas)
        var numDistinctNames = 0L
        for (i in 0 until suffixGroups.lastIndex) {
            val group1 = suffixGroups[i]
            if (group1.isEmpty()) continue

            for (j in i + 1 until suffixGroups.size) {
                val group2 = suffixGroups[j]
                if (group2.isEmpty()) continue

                val numCommonSuffix = group1.count { it in group2 }
                val numExclusiveSuffix1 = group1.size - numCommonSuffix
                val numExclusiveSuffix2 = group2.size - numCommonSuffix
                // times 2 since if idea1+idea2 is valid, then idea2+idea1 is also valid
                numDistinctNames += numExclusiveSuffix1 * numExclusiveSuffix2 * 2
            }
        }
        return numDistinctNames
    }

    /**
     * Group the suffix (from index 1) of each idea by first letter of the idea.
     *
     * Return a list of size 26 that element at index 0 to 25 corresponding to the found suffix
     * of first letter 'a' to 'z'.
     *
     * Require each idea starts with a lowercase english letter, which is stated in the problem
     * constraints.
     */
    private fun groupSuffixByFirstLetter(ideas: Array<String>): List<Set<String>> {
        val groups = List(26) { hashSetOf<String>() }
        for (idea in ideas) {
            val firstLetter = idea[0]
            val suffix = idea.slice(1 until idea.length)
            groups[firstLetter - 'a'].add(suffix)
        }
        return groups
    }
}