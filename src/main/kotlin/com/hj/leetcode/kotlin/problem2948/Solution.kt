package com.hj.leetcode.kotlin.problem2948

/**
 * LeetCode page: [2948. Make Lexicographically Smallest Array by Swapping Elements](https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of nums.
     */
    fun lexicographicallySmallestArray(
        nums: IntArray,
        limit: Int,
    ): IntArray {
        val sortedNums = nums.withIndex().sortedBy { it.value }

        // Partition the sorted numbers into the minimum number of groups where the
        // difference between adjacent numbers in each group doesn't exceed the limit.
        // Swap the numbers in `nums` group by group according to the sorted order.
        val result = IntArray(nums.size)
        val groupIndices = mutableListOf<Int>()
        var prevValue = sortedNums[0].value

        for (i in sortedNums.indices) {
            if (prevValue + limit < sortedNums[i].value) {
                groupIndices.sortDescending()
                repeat(groupIndices.size) {
                    result[groupIndices[it]] = sortedNums[i - 1 - it].value
                }
                groupIndices.clear()
            }
            prevValue = sortedNums[i].value
            groupIndices.add(sortedNums[i].index)
        }

        groupIndices.sortDescending()
        repeat(groupIndices.size) {
            result[groupIndices[it]] = sortedNums[sortedNums.lastIndex - it].value
        }
        groupIndices.clear()
        return result
    }
}
