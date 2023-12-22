package com.hj.leetcode.kotlin.problem215

import java.util.*

/**
 * LeetCode page: [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/);
 */
class Solution {
    /* Complexity:
     * Time O(Nlogk) and Space O(k) where N is the size of nums;
     */
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val minPq = PriorityQueue<Int>()
        for (num in nums) {
            minPq.offer(num)
            if (minPq.size > k) {
                minPq.poll()
            }
        }
        return minPq.peek()
    }
}