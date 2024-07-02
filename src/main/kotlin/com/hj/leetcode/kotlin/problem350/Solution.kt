package com.hj.leetcode.kotlin.problem350

/**
 * LeetCode page: [350. Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(min(M, N)) where M is the size of nums1
     * and N is the size of nums2;
     */
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val (shorter, longer) = if (nums1.size < nums2.size) {
            nums1 to nums2
        } else {
            nums2 to nums1
        }

        val countShorter = shorter
            .asIterable()
            .groupingBy { it }
            .eachCountTo(mutableMapOf())

        val intersect = mutableListOf<Int>()
        for (num in longer) {
            if (num in countShorter) {
                countShorter.compute(num) { _, v ->
                    if (v == 1) null else checkNotNull(v) - 1
                }
                intersect.add(num)
            }
        }
        return intersect.toIntArray()
    }
}