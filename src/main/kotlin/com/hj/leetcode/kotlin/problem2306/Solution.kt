package com.hj.leetcode.kotlin.problem2306

/**
 * LeetCode page: [2306. Naming a Company](https://leetcode.com/problems/naming-a-company/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(L) where L is the flat length of ideas;
     */
    fun distinctNames(ideas: Array<String>): Long {
        val suffixGroups = groupIdeaSuffixByPrefix(ideas)
        var numDistinctNames = 0L
        for (i in 0 until suffixGroups.lastIndex) {
            val group1 = suffixGroups[i]
            if (group1.isEmpty()) continue

            for (j in i + 1 until suffixGroups.size) {
                val group2 = suffixGroups[j]
                if (group2.isEmpty()) continue

                val numCandidates1 = group1.count { it !in group2 }
                val numCandidates2 = group2.count { it !in group1 }
                // times 2 since if idea1+idea2 is valid, then idea2+idea1 is also valid
                numDistinctNames += numCandidates1 * numCandidates2 * 2
            }
        }
        return numDistinctNames
    }

    /**
     * Return a list of size 26 that element at index 0 to 25 is corresponding to the found
     * suffix of prefix 'a' to 'z'.
     *
     * Require the constraints that each idea starts with a lowercase english letter.
     */
    private fun groupIdeaSuffixByPrefix(ideas: Array<String>): List<Set<String>> {
        val groups = List(26) { hashSetOf<String>() }
        for (idea in ideas) {
            val prefix = idea[0]
            val suffix = idea.slice(1 until idea.length)
            groups[prefix - 'a'].add(suffix)
        }
        return groups
    }
}