package com.hj.leetcode.kotlin.problem2948

/**
 * LeetCode page: [2948. Make Lexicographically Smallest Array by Swapping Elements](https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun lexicographicallySmallestArray(
        nums: IntArray,
        limit: Int,
    ): IntArray {
        val valueIndices = LongArray(nums.size) { valueIndex(nums[it], it) }
        valueIndices.sort()

        val result = IntArray(nums.size)
        var start = 0
        for (end in 1..<nums.size) {
            val isNewGroup = getValue(valueIndices[end]) - getValue(valueIndices[end - 1]) > limit
            if (isNewGroup) {
                assignResult(valueIndices, start, end, result)
                start = end
            }
        }
        assignResult(valueIndices, start, nums.size, result)

        return result
    }

    private fun valueIndex(
        value: Int,
        index: Int,
    ): Long = value.toLong() shl 32 or index.toLong()

    private fun getValue(valueIndex: Long): Int = (valueIndex ushr 32).toInt()

    private fun getIndex(valueIndex: Long): Int = (valueIndex and 0xFFFF_FFFF).toInt()

    private fun assignResult(
        valueIndices: LongArray,
        start: Int,
        end: Int,
        result: IntArray,
    ) {
        val idxAssignOrder = IntArray(end - start) { getIndex(valueIndices[start + it]) }
        idxAssignOrder.sort()
        for (i in start..<end) {
            result[idxAssignOrder[i - start]] = getValue(valueIndices[i])
        }
    }
}
