package com.hj.leetcode.kotlin.problem373

import java.util.*

/**
 * LeetCode page: [373. Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/);
 */
class Solution {
    /* Complexity:
     * Time O(kLogk) and Space O(k);
     */
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        // pq of pair(index1, index2) where the values are compared by nums1[index1] + nums2[index2]
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { nums1[it.first] + nums2[it.second] })
        val result = mutableListOf<List<Int>>()
        pq.offer(Pair(0, 0))
        while (result.size < k && pq.isNotEmpty()) {
            val (index1, index2) = pq.poll()
            result.add(listOf(nums1[index1], nums2[index2]))
            if (index2 + 1 < nums2.size) {
                pq.offer(Pair(index1, index2 + 1))
            }
            if (index2 == 0 && index1 + 1 < nums1.size) {
                pq.offer(Pair(index1 + 1, 0))
            }
        }
        return result
    }
}