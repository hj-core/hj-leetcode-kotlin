package com.hj.leetcode.kotlin.problem703

import java.util.*

/**
 * LeetCode page: [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/);
 */
class KthLargest(private val k: Int, nums: IntArray) {

    private val kLargest = PriorityQueue<Int>()

    init {
        addAll(nums)
    }

    /* Complexity:
     * Time O(NLogk) and Space O(k) where N is the size of numbers;
     */
    private fun addAll(numbers: IntArray) {
        for (number in numbers) {
            kLargest.offer(number)
            removeExtra()
        }
    }

    private fun removeExtra() {
        while (kLargest.size > k) {
            kLargest.poll()
        }
    }

    /* Complexity For N calls:
     * Time O(NLogk) and Space O(k);
     */
    fun add(`val`: Int): Int {
        kLargest.offer(`val`)
        removeExtra()
        return kLargest.peek()
    }
}