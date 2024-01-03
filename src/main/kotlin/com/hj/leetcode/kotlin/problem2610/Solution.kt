package com.hj.leetcode.kotlin.problem2610

/**
 * LeetCode page: [2610. Convert an Array Into a 2D Array With Conditions](https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun findMatrix(nums: IntArray): List<List<Int>> {
        val numsCount = nums.asIterable().groupingBy { it }.eachCount()
        val minRows = numsCount.values.max()
        val result = List(minRows) { mutableListOf<Int>() }
        for ((num, count) in numsCount) {
            for (index in 0..<count) {
                result[index].add(num)
            }
        }
        return result
    }
}