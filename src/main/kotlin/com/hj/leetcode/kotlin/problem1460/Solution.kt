package com.hj.leetcode.kotlin.problem1460

/**
 * LeetCode page: [1460. Make Two Arrays Equal by Reversing Subarrays](https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of target and arr;
     */
    fun canBeEqual(target: IntArray, arr: IntArray): Boolean {
        /* Since swap is a valid operation, arr can be made equal to target
         * as long as and iff they consist of the same elements.
         */

        val counts = target
            .asIterable()
            .groupingBy { it }
            .eachCountTo(mutableMapOf())

        for (num in arr) {
            if (num !in counts) {
                return false
            }
            counts.computeIfPresent(num) { _, count ->
                if (count == 1) null else count - 1
            }
        }
        return counts.isEmpty()
    }
}