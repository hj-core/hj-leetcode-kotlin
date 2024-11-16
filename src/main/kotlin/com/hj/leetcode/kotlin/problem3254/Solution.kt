package com.hj.leetcode.kotlin.problem3254

/**
 * LeetCode page: [3254. Find the Power of K-Size Subarrays I](https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the size of nums.
     */
    fun resultsArray(
        nums: IntArray,
        k: Int,
    ): IntArray {
        if (k == 1) {
            return nums.clone()
        }
        // The longest consecutive ascending length ended at iNums=k-1
        var maxLength = 1
        for (i in k - 1 downTo 1) {
            if (canExtendPrev(nums, i)) maxLength += 1 else break
        }

        val result = IntArray(nums.size - k + 1)
        result[0] = if (maxLength < k) -1 else nums[k - 1]
        for (iResult in 1..<result.size) {
            val iNums = iResult + k - 1
            maxLength = if (canExtendPrev(nums, iNums)) maxLength + 1 else 1
            result[iResult] = if (maxLength < k) -1 else nums[iNums]
        }
        return result
    }

    private fun canExtendPrev(
        nums: IntArray,
        i: Int,
    ): Boolean = nums[i] == nums[i - 1] + 1
}
