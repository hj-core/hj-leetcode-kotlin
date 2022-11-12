package com.hj.leetcode.kotlin.problem295

import java.util.*

/**
 * LeetCode page: [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/);
 */
class MedianFinder() {
    private val leftHalf = PriorityQueue<Int>(reverseOrder())
    private val rightHalf = PriorityQueue<Int>()

    /* Complexity of N calls:
     * Time O(NLogN) and Space O(N);
     */
    fun addNum(num: Int) {
        val shouldInsertLeft = shouldInsertLeft(num)
        if (shouldInsertLeft) leftHalf.offer(num) else rightHalf.offer(num)
        balance()
    }

    private fun shouldInsertLeft(newNum: Int): Boolean {
        return leftHalf.peek()
            ?.let { it >= newNum }
            ?: true
    }

    private fun balance() {
        when {
            leftHalf.size > rightHalf.size + 1 -> {
                val pop = leftHalf.poll()
                rightHalf.offer(pop)
            }

            leftHalf.size < rightHalf.size -> {
                val pop = rightHalf.poll()
                leftHalf.offer(pop)
            }
        }
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun findMedian(): Double {
        val size = leftHalf.size + rightHalf.size
        val isEvenSize = size and 1 == 0

        return if (isEvenSize) findMedianWhenEvenSize() else findMedianWhenOddSize()
    }

    private fun findMedianWhenOddSize(): Double {
        val leftPeek = checkNotNull(leftHalf.peek())
        return leftPeek.toDouble()
    }

    private fun findMedianWhenEvenSize(): Double {
        val leftPeek = checkNotNull(leftHalf.peek())
        val rightPeek = checkNotNull(rightHalf.peek())
        return (leftPeek + rightPeek) / 2.0
    }
}