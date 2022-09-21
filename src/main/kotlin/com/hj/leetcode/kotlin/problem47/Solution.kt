package com.hj.leetcode.kotlin.problem47

/**
 * LeetCode page: [47. Permutations II](https://leetcode.com/problems/permutations-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N * N!) and Aux_Space O(N) where N is the size of nums;
     */
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val frequencyPerNum = getFrequencyPerNum(nums)
        val uniquePermutations = mutableListOf<List<Int>>()

        addUniquePermutations(frequencyPerNum, nums.size, uniquePermutations)
        return uniquePermutations
    }

    private fun getFrequencyPerNum(nums: IntArray): MutableMap<Int, Int> {
        val frequency = hashMapOf<Int, Int>()
        for (num in nums) {
            frequency[num] = frequency.getOrDefault(num, 0) + 1
        }
        return frequency
    }

    private fun addUniquePermutations(
        frequencyPerNum: MutableMap<Int, Int>,
        length: Int,
        container: MutableList<List<Int>>,
        accList: MutableList<Int> = mutableListOf(),
    ) {
        val hasCompleted = accList.size == length
        if (hasCompleted) {
            val copy = accList.toList()
            container.add(copy)
            return
        }

        for ((num, freq) in frequencyPerNum) {
            if (freq > 0) {
                frequencyPerNum[num] = freq - 1
                accList.add(num)
                addUniquePermutations(frequencyPerNum, length, container, accList)
                accList.removeAt(accList.lastIndex)
                frequencyPerNum[num] = freq
            }
        }
    }
}