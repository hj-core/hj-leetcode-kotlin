package com.hj.leetcode.kotlin.problem349

/**
 * LeetCode page: [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(min(M,N)) where M is the size of nums1 and
     * N is the size of nums2;
     */
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val result = mutableListOf<Int>()
        val (short, long) =
            if (nums1.size < nums2.size) nums1 to nums2 else nums2 to nums1
        val shortSet = short.toHashSet()
        for (num in long) {
            if (num in shortSet) {
                result.add(num)
                shortSet.remove(num)
            }
        }
        return result.toIntArray()
    }
}