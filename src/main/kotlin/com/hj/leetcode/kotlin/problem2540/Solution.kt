package com.hj.leetcode.kotlin.problem2540

/**
 * LeetCode page: [2540. Minimum Common Value](https://leetcode.com/problems/minimum-common-value/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(1) where M is the size of nums1 and N is the
     * size of nums2;
     */
    fun getCommon(nums1: IntArray, nums2: IntArray): Int {
        var index1 = 0
        var index2 = 0
        while (index1 < nums1.size && index2 < nums2.size) {
            when {
                nums1[index1] < nums2[index2] -> index1++
                nums1[index1] > nums2[index2] -> index2++
                else -> return nums1[index1]
            }
        }
        return -1
    }
}