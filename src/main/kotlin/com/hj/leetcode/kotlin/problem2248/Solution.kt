package com.hj.leetcode.kotlin.problem2248

/**
 * LeetCode page: [2248. Intersection of Multiple Arrays](https://leetcode.com/problems/intersection-of-multiple-arrays/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Aux_Space O(M) where N is the flat size of nums and M is the size of value range;
     */
    fun intersection(nums: Array<IntArray>): List<Int> {
        val numFrequency = getNumberFrequency(nums)
        return getSortedNumbersOfSpecificFrequency(numFrequency, nums.size)
    }

    private fun getNumberFrequency(numbers: Array<IntArray>): IntArray {
        val numFrequency = IntArray(1001) // size determined by constraint of value range;
        for (array in numbers) {
            for (num in array) {
                numFrequency[num]++
            }
        }
        return numFrequency
    }

    private fun getSortedNumbersOfSpecificFrequency(numFrequency: IntArray, frequency: Int): List<Int> {
        val matchedNumbers = mutableListOf<Int>()
        for (index in numFrequency.indices) {
            if (numFrequency[index] == frequency) matchedNumbers.add(index)
        }
        return matchedNumbers
    }
}