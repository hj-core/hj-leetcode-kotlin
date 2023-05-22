package com.hj.leetcode.kotlin.problem347

/**
 * LeetCode page: [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val numbersByFrequency = numbersGroupByFrequency(nums)
        return topKFrequentNumbers(k, numbersByFrequency)
    }

    private fun numbersGroupByFrequency(numbers: IntArray): Map<Int, List<Int>> {
        val result = hashMapOf<Int, MutableList<Int>>()
        val numberFrequency = numberFrequency(numbers)

        for ((number, frequency) in numberFrequency) {
            result
                .computeIfAbsent(frequency) { mutableListOf() }
                .add(number)
        }
        return result
    }

    private fun numberFrequency(numbers: IntArray): Map<Int, Int> {
        val result = hashMapOf<Int, Int>()
        for (number in numbers) {
            result[number] = (result[number] ?: 0) + 1
        }
        return result
    }

    private fun topKFrequentNumbers(
        k: Int,
        numbersByFrequency: Map<Int, List<Int>>
    ): IntArray {

        val result = IntArray(k)
        var numPicked = 0
        val maxFrequency = numbersByFrequency.keys.max()!!

        for (frequency in maxFrequency downTo 1) {
            val numbers = numbersByFrequency[frequency] ?: continue
            for (number in numbers) {
                result[numPicked] = number
                numPicked++

                if (numPicked == k) {
                    return result
                }
            }
        }
        throw IllegalStateException()
    }
}