package com.hj.leetcode.kotlin.problem2542

import java.util.*

/**
 * LeetCode page: [2542. Maximum Subsequence Score](https://leetcode.com/problems/maximum-subsequence-score/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums1;
     */
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        val sortedIndices = nums2.indices.sortedBy { -nums2[it] }

        val kLargest = PriorityQueue<Int>()
        var sum = 0L

        for (i in 0 until k) {
            val value = nums1[sortedIndices[i]]
            kLargest.offer(value)
            sum += value
        }

        var maxScore = sum * nums2[sortedIndices[k - 1]]

        for (i in k until nums2.size) {
            val newValue = nums1[sortedIndices[i]]
            if (newValue <= kLargest.peek()) {
                continue
            }

            kLargest.offer(newValue)
            val dropValue = kLargest.poll()
            sum += newValue - dropValue

            val factor = nums2[sortedIndices[i]]
            val newScore = sum * factor
            if (newScore > maxScore) {
                maxScore = newScore
            }
        }
        return maxScore
    }
}