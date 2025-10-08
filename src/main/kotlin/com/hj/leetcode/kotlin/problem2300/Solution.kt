package com.hj.leetcode.kotlin.problem2300

/**
 * LeetCode page: [2300. Successful Pairs of Spells and Potions](https://leetcode.com/problems/successful-pairs-of-spells-and-potions/);
 */
class Solution {
    // Complexity:
    // Time O(NLogM+MLogM) and Space O(M) where N and M are
    // the size of spells and potions, respectively.
    fun successfulPairs(
        spells: IntArray,
        potions: IntArray,
        success: Long,
    ): IntArray {
        val sortedPotions = potions.sorted()
        return IntArray(spells.size) { index ->
            countSuccessfulPairs(spells[index], sortedPotions, success)
        }
    }

    private fun countSuccessfulPairs(
        spell: Int,
        sortedPotions: List<Int>,
        success: Long,
    ): Int {
        val target = (success + spell - 1) / spell
        // Binary search to find the number of potions that are
        // less than target, which is in range [left, right].
        var left = 0
        var right = sortedPotions.size
        while (left < right) {
            val mid = (left + right) ushr 1
            if (sortedPotions[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return sortedPotions.size - left
    }
}
