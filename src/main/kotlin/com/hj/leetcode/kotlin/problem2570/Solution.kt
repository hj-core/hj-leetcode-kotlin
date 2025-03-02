package com.hj.leetcode.kotlin.problem2570

/**
 * LeetCode page: [2570. Merge Two 2D Arrays by Summing Values](https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/);
 */
class Solution {
    // Complexity:
    // Time O(M+N) and Space O(M+N)
    // where M and N are the length of `nums` and `nums2`, respectively.
    fun mergeArrays(
        nums1: Array<IntArray>,
        nums2: Array<IntArray>,
    ): Array<IntArray> {
        var i = 0
        var j = 0
        val result = mutableListOf<IntArray>()
        while (i < nums1.size && j < nums2.size) {
            when {
                nums1[i][0] < nums2[j][0] -> {
                    result.add(nums1[i].clone())
                    i++
                }

                nums1[i][0] > nums2[j][0] -> {
                    result.add(nums2[j].clone())
                    j++
                }

                else -> {
                    result.add(intArrayOf(nums1[i][0], nums1[i][1] + nums2[j][1]))
                    i++
                    j++
                }
            }
        }
        while (i < nums1.size) {
            result.add(nums1[i].clone())
            i++
        }
        while (j < nums2.size) {
            result.add(nums2[j].clone())
            j++
        }
        return result.toTypedArray()
    }
}
