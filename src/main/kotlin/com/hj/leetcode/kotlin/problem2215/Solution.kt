package com.hj.leetcode.kotlin.problem2215

/**
 * LeetCode page: [2215. Find the Difference of Two Arrays](https://leetcode.com/problems/find-the-difference-of-two-arrays/);
 */
class Solution {
    /* Complexity:
     * Time (M+N) and Space O(M+N) where M and N are the size of nums1 and nums2;
     */
    fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
        val set1 = nums1.toSet()
        val set2 = nums2.toSet()
        return listOf(
            set1.filter { it !in set2 },
            set2.filter { it !in set1 }
        )
    }
}