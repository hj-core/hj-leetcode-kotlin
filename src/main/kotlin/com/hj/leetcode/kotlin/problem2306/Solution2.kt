package com.hj.leetcode.kotlin.problem2306

/**
 * LeetCode page: [2306. Naming a Company](https://leetcode.com/problems/naming-a-company/);
 */
class Solution2 {
    /* Complexity:
     * Time O(L) and Space O(L) where L is the flat length of ideas;
     */
    fun distinctNames(ideas: Array<String>): Long {
        val numSwappable = buildNumSwappable(ideas)
        return countDistinctName(numSwappable)
    }

    /**
     * Return a 26 x 26 2D Array that cell(m, n) is the number of ideas that start with
     * letter ('a' + m) and is not in original ideas if replace it's first letter with
     * letter ('a' + n).
     */
    private fun buildNumSwappable(ideas: Array<String>): Array<IntArray> {
        val ideaSet = ideas.toHashSet()
        val numSwappable = Array(26) { IntArray(26) }

        for (idea in ideaSet) {
            for (c in 'a'..'z') {
                val swapped = idea.replaceRange(0..0, c.toString())
                val isSwappable = swapped !in ideaSet
                if (isSwappable) {
                    numSwappable[idea[0] - 'a'][c - 'a']++
                }
            }
        }
        return numSwappable
    }

    private fun countDistinctName(numSwappable: Array<IntArray>): Long {
        var numDistinctName = 0L
        for (i in 0 until 25) {
            for (j in i + 1 until 26) {
                // times 2 since if idea1+idea2 is valid, then idea2+idea1 is also valid
                numDistinctName += numSwappable[i][j] * numSwappable[j][i] * 2
            }
        }
        return numDistinctName
    }
}