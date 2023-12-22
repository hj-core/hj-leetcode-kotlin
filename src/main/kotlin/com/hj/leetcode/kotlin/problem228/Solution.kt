package com.hj.leetcode.kotlin.problem228

/**
 * LeetCode page: [228. Summary Ranges](https://leetcode.com/problems/summary-ranges/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the size of nums;
     */
    fun summaryRanges(nums: IntArray): List<String> {
        if (nums.isEmpty()) {
            return emptyList()
        }

        val result = mutableListOf<String>()
        var rangeStart = nums[0]

        for (index in 1 until nums.size) {
            val isNewRange = nums[index] != nums[index - 1] + 1
            if (isNewRange) {
                result.add(rangeString(rangeStart, nums[index - 1]))
                rangeStart = nums[index]
            }
        }
        result.add(rangeString(rangeStart, nums.last()))

        return result
    }

    private fun rangeString(start: Int, end: Int): String {
        return if (start == end) "$start" else "$start->$end"
    }
}