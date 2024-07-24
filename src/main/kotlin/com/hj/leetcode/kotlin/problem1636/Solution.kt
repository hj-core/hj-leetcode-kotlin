package com.hj.leetcode.kotlin.problem1636

/**
 * LeetCode page: [1636. Sort Array by Increasing Frequency](https://leetcode.com/problems/sort-array-by-increasing-frequency/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun frequencySort(nums: IntArray): IntArray {
        return buildResult(nums.size, sortedCounts(nums))
    }

    private fun sortedCounts(nums: IntArray): List<Map.Entry<Int, Int>> {
        return nums
            .asIterable()
            .groupingBy { it }
            .eachCount()
            .entries
            .sortedWith { o1, o2 ->
                if (o1.value == o2.value) {
                    o2.key.compareTo(o1.key)
                } else {
                    o1.value.compareTo(o2.value)
                }
            }
    }

    private fun buildResult(
        size: Int,
        sortedCounts: List<Map.Entry<Int, Int>>,
    ): IntArray {
        val result = IntArray(size)
        var i = 0
        for ((num, count) in sortedCounts) {
            repeat(count) {
                result[i] = num
                i++
            }
        }
        return result
    }
}