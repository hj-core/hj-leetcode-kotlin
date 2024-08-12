package com.hj.leetcode.kotlin.problem703

import java.util.*

/**
 * LeetCode page: [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/);
 */
class KthLargest(private val k: Int, nums: IntArray) {

    private val kSmallest = PriorityQueue<Int>()

    init {
        for (num in nums) {
            add(num)
        }
    }

    /* Complexity For N calls:
     * Time O(NLogk) and Space O(k);
     */
    fun add(`val`: Int): Int {
        kSmallest.offer(`val`)
        if (kSmallest.size > k) {
            kSmallest.poll()
        }
        return kSmallest.peek()
    }
}