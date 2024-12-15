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
        val classPq = PriorityQueue<IntArray> { a, b -> -comparingMarginalGain(a, b) }
        for (theClass in classes) {
            classPq.offer(theClass)
        }

        repeat(extraStudents) {
            val (pass, total) = classPq.poll()
            val withExtra = intArrayOf(pass + 1, total + 1)
            classPq.offer(withExtra)
        }
        return averagePassRatio(classPq)
    }

    private fun comparingMarginalGain(
        classA: IntArray,
        classB: IntArray,
    ): Int {
        val (passA, totalA) = classA
        val (passB, totalB) = classB
        val diff =
            (totalA - passA).toLong() * totalB * (totalB + 1) -
                (totalB - passB).toLong() * totalA * (totalA + 1)

        return when {
            diff > 0 -> 1
            diff < 0 -> -1
            else -> 0
        }
    }

    private fun averagePassRatio(results: Collection<IntArray>): Double =
        results.sumOf { (pass, total) -> pass.toDouble() / total } / results.size
}
