package com.hj.leetcode.kotlin.problem703

import java.util.*

/**
 * LeetCode page: [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/);
 */
class KthLargest(private val k: Int, nums: IntArray) {

    private val minPq = PriorityQueue<Int>()

    init {
        addAll(nums)
    }

    /* Complexity:
     * Time O(NLogk) and Space O(k) where N is the size of numbers;
     */
    private fun addAll(numbers: IntArray) {
        for (number in numbers) {
            minPq.offer(number)
            maintainQueueSize()
        }
    }

    private fun maintainQueueSize() {
        while (minPq.size > k) {
            minPq.poll()
        }
    }

    /* Complexity For N calls:
     * Time O(NLogk) and Space O(k);
     */
    fun add(`val`: Int): Int {
        minPq.offer(`val`)
        maintainQueueSize()
        return minPq.peek()
    }
}