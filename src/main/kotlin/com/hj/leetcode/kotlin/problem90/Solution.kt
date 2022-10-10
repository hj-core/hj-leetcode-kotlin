package com.hj.leetcode.kotlin.problem90

/**
 * LeetCode page: [90. Subsets II](https://leetcode.com/problems/subsets-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N * 2^N) and Space O(N * 2^N) where N is the size of nums;
     */
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        val countPerNum = getCountPerNum(nums)
        val subsets = mutableListOf(emptyList<Int>())

        for (num in countPerNum.keys) {
            val countOfNum = checkNotNull(countPerNum[num])
            updateSubsetsByNum(subsets, num, countOfNum)
        }

        return subsets
    }

    private fun getCountPerNum(nums: IntArray): Map<Int, Int> {
        val counts = hashMapOf<Int, Int>()

        for (num in nums) {
            counts[num] = counts.getOrDefault(num, 0) + 1
        }
        return counts
    }

    private fun updateSubsetsByNum(subsets: MutableList<List<Int>>, num: Int, countOfNum: Int) {
        repeat(subsets.size) { index ->
            for (count in 1..countOfNum) {
                val newSubset = subsets[index].toMutableList()
                repeat(count) { newSubset.add(num) }
                subsets.add(newSubset)
            }
        }
    }
}