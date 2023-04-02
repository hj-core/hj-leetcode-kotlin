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
            numSuccessPairs(spells[index], sortedPotions, success)
        }
    }

    private fun numSuccessPairs(spell: Int, sortedPotions: List<Int>, success: Long): Int {
        var left = 0
        var right = sortedPotions.lastIndex
        while (left <= right) {
            val mid = (left + right) ushr 1
            val potion = sortedPotions[mid]
            val product = spell.toLong() * potion
            if (product < success) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        // left is the first index in sortedPotions that forms a successful pair
        return sortedPotions.size - left
    }
}