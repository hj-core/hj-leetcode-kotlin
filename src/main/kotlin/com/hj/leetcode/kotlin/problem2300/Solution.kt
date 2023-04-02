package com.hj.leetcode.kotlin.problem2300

/**
 * LeetCode page: [2300. Successful Pairs of Spells and Potions](https://leetcode.com/problems/successful-pairs-of-spells-and-potions/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogM+MLogM) and Space O(M) where N and M are the size of spells and potions;
     */
    fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {
        val sortedPotions = potions.sorted()
        return IntArray(spells.size) { index ->
            numSuccessfulPairs(spells[index], sortedPotions, success)
        }
    }

    private fun numSuccessfulPairs(spell: Int, sortedPotions: List<Int>, success: Long): Int {
        var left = 0
        var right = sortedPotions.lastIndex
        while (left <= right) {
            val mid = (left + right) ushr 1
            val midPotion = sortedPotions[mid]
            val isSuccess = spell.toLong() * midPotion >= success
            if (isSuccess) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        // left is the first index in sortedPotions that forms a successful pair
        return sortedPotions.size - left
    }
}