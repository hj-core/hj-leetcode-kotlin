package com.hj.leetcode.kotlin.problem953

/**
 * LeetCode page: [953. Verifying an Alien Dictionary](https://leetcode.com/problems/verifying-an-alien-dictionary/);
 */
class Solution {
    /* Complexity:
     * Time O(M) and Space O(1) where M is the flat length of words;
     */
    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        val comparator = getComparatorFollowAlienOrder(order)
        for (index in 1 until words.size) {
            val currWord = words[index]
            val prevWord = words[index - 1]
            val isOutOfOrder = comparator.compare(currWord, prevWord) < 0
            if (isOutOfOrder) return false
        }
        return true
    }

    private fun getComparatorFollowAlienOrder(order: String): Comparator<String> {
        val intFormOrder = buildIntFormOrder(order)
        return Comparator { o1, o2 ->
            val shorterLength = minOf(o1.length, o2.length)
            for (index in 0 until shorterLength) {
                val order1 = intFormOrder[o1[index] - 'a']
                val order2 = intFormOrder[o2[index] - 'a']
                if (order1 != order2) return@Comparator order1.compareTo(order2)
            }
            o1.length.compareTo(o2.length)
        }
    }

    private fun buildIntFormOrder(order: String): IntArray {
        val intFormOrder = IntArray(26)
        for ((index, char) in order.withIndex()) {
            intFormOrder[char - 'a'] = index
        }
        return intFormOrder
    }
}