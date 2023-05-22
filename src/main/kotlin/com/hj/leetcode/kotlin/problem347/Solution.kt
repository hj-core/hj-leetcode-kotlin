package com.hj.leetcode.kotlin.problem347

/**
 * LeetCode page: [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val result = IntArray(k)
        val numbersByFrequency = numbersGroupByFrequency(nums)
        var numCollected = 0

        // Collect the k elements by searching in a decreasing frequency order
        search@ for (frequency in nums.size downTo 1) {
            val numbers = numbersByFrequency[frequency] ?: continue
            for (number in numbers) {
                result[numCollected] = number
                numCollected++

                if (numCollected == k) {
                    break@search
                }
            }
        }
        return result
    }

    private fun numbersGroupByFrequency(numbers: IntArray): Map<Int, List<Int>> {
        val result = hashMapOf<Int, MutableList<Int>>()
        for ((number, frequency) in numberFrequency(numbers)) {
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
}