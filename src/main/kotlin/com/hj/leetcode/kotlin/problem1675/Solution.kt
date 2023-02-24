package com.hj.leetcode.kotlin.problem1675

import java.util.*

/**
 * LeetCode page: [1675. Minimize Deviation in Array](https://leetcode.com/problems/minimize-deviation-in-array/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N);
     */
    fun minimumDeviation(nums: IntArray): Int {
        /* We build the maxPq from nums, using the original even numbers but doubling the odd numbers.
         * Doing so will not affect the result, since we can divide the number back if necessary.
         * However, we now reduce these two possible operations to one, i.e. division only.
         */
        val numMaxPq = buildMaxPqWithAllOddNumbersDoubled(nums)
        return findMinimumDeviation(numMaxPq)
    }

    private fun buildMaxPqWithAllOddNumbersDoubled(numbers: IntArray): PriorityQueue<Int> {
        val result = PriorityQueue<Int>(reverseOrder())
        for (number in numbers) {
            if (number.isOdd()) {
                result.offer(number shl 1)
            } else {
                result.offer(number)
            }
        }
        return result
    }

    private fun Int.isOdd() = this and 1 == 1

    private fun findMinimumDeviation(numMaxPq: PriorityQueue<Int>): Int {
        require(numMaxPq.size >= 2)

        var currMin = numMaxPq.min()!!
        var minDeviation = numMaxPq.peek() - currMin

        // If the max number is odd, we can be sure we found the min deviation.
        while (!numMaxPq.peek().isOdd()) {
            val currMax = numMaxPq.poll()
            val halfCurrMax = currMax shr 1
            numMaxPq.offer(halfCurrMax)

            if (halfCurrMax < currMin) currMin = halfCurrMax
            val newDeviation = numMaxPq.peek() - currMin
            minDeviation = minOf(minDeviation, newDeviation)
        }
        return minDeviation
    }
}