package com.hj.leetcode.kotlin.problem3069

/**
 * LeetCode page: [3069. Distribute Elements Into Two Arrays I](https://leetcode.com/problems/distribute-elements-into-two-arrays-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun resultArray(nums: IntArray): IntArray {
        val arr1 = IntArray(nums.size)
        val arr2 = IntArray(nums.size)

        arr1[0] = nums[0]
        arr2[0] = nums[1]
        var i1 = 0
        var i2 = 0
        for (j in 2..<nums.size) {
            if (arr1[i1] > arr2[i2]) {
                i1++
                arr1[i1] = nums[j]
            } else {
                i2++
                arr2[i2] = nums[j]
            }
        }

        arr2.copyInto(arr1, i1 + 1, 0, i2 + 1)
        return arr1
    }
}
