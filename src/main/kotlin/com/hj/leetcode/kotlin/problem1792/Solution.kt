package com.hj.leetcode.kotlin.problem1792

import java.util.*

/**
 * LeetCode page: [1792. Maximum Average Pass Ratio](https://leetcode.com/problems/maximum-average-pass-ratio/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+KLogN) and Space O(N)
     * where N is the length of classes and K is extraStudents.
     */
    fun maxAverageRatio(
        classes: Array<IntArray>,
        extraStudents: Int,
    ): Double {
        val resultPq = PriorityQueue(this::comparingExamResult)
        for (examResult in classes) {
            resultPq.offer(examResult)
        }

        repeat(extraStudents) {
            val (pass, total) = resultPq.poll()
            resultPq.offer(intArrayOf(pass + 1, total + 1))
        }
        return averagePassRatio(resultPq)
    }

    private fun comparingExamResult(
        classA: IntArray,
        classB: IntArray,
    ): Int {
        val (passA, totalA) = classA
        val (passB, totalB) = classB
        // Comparing the marginal improvement in pass ratio of classB to classA
        val diff =
            (totalB - passB).toLong() * totalA * (totalA + 1) -
                (totalA - passA).toLong() * totalB * (totalB + 1)

        return when {
            diff > 0 -> 1
            diff < 0 -> -1
            else -> 0
        }
    }

    private fun averagePassRatio(results: Collection<IntArray>): Double =
        results.sumOf { (pass, total) -> pass.toDouble() / total } / results.size
}
