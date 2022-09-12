package com.hj.leetcode.kotlin.problem2404

/**
 * LeetCode page: [2404. Most Frequent Even Element](https://leetcode.com/problems/most-frequent-even-element/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun mostFrequentEven(nums: IntArray): Int {
        val evenNumberFrequency = getEvenNumberFrequency(nums)
        return getMostFrequentEven(evenNumberFrequency)
    }

    private fun getEvenNumberFrequency(numbers: IntArray): Map<Int, Int> {
        val evenNumberFrequency = hashMapOf<Int, Int>()
        for (num in numbers) {
            if (num and 1 == 0) {
                evenNumberFrequency[num] = evenNumberFrequency.getOrDefault(num, 0) + 1
            }
        }
        return evenNumberFrequency
    }

    private fun getMostFrequentEven(evenNumberFrequency: Map<Int, Int>): Int {
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