package com.hj.leetcode.kotlin.problem2404

/**
 * LeetCode page: [2404. Most Frequent Even Element](https://leetcode.com/problems/most-frequent-even-element/);
 */
class Solution {
    private val evenNumberFrequency = hashMapOf<Int, Int>()

    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun mostFrequentEven(nums: IntArray): Int {
        restStates()
        updateEvenNumberFrequency(nums)
        return getMostFrequentEven()
    }

    private fun restStates() {
        evenNumberFrequency.clear()
    }

    private fun updateEvenNumberFrequency(numbers: IntArray) {
        for (num in numbers) {
            if (num and 1 == 0) {
                evenNumberFrequency[num] = evenNumberFrequency.getOrDefault(num, 0) + 1
            }
        }
    }

    private fun getMostFrequentEven(): Int {
        var mostFrequentEven = -1
        var mostFrequency = 0
        for ((even, frequency) in evenNumberFrequency) {
            when {
                frequency > mostFrequency -> {
                    mostFrequency = frequency
                    mostFrequentEven = even
                }
                frequency == mostFrequency -> {
                    mostFrequentEven = minOf(mostFrequentEven, even)
                }
            }
        }
        return mostFrequentEven
    }
}