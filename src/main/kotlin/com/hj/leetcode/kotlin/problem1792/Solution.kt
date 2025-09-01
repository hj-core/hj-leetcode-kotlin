package com.hj.leetcode.kotlin.problem1792

import java.util.*

/**
 * LeetCode page: [1792. Maximum Average Pass Ratio](https://leetcode.com/problems/maximum-average-pass-ratio/);
 */
private typealias Class = DoubleArray // (pass, total, oneExtraGain * -1.0)

class Solution {
    // Complexity:
    // Time O(NLogN+KLogN) and Space O(N) where N is the
    // length of classes and K is extraStudents.
    fun maxAverageRatio(
        classes: Array<IntArray>,
        extraStudents: Int,
    ): Double {
        val pq = PriorityQueue<Class>(classes.size, compareBy { it[2] })
        for (c in classes) {
            pq.offer(newClass(c[0], c[1]))
        }

        repeat(extraStudents) {
            val c = pq.poll()
            c[0]++
            c[1]++
            c[2] = -1.0 * calcOneExtraGain(c[0], c[1])
            pq.offer(c)
        }

        return pq.sumOf { it[0] / it[1] } / pq.size
    }

    private fun newClass(
        pass: Int,
        total: Int,
    ): Class {
        val pass = pass.toDouble()
        val total = total.toDouble()
        return doubleArrayOf(pass, total, -1.0 * calcOneExtraGain(pass, total))
    }

    private fun calcOneExtraGain(
        pass: Double,
        total: Double,
    ): Double = (pass + 1.0) / (total + 1.0) - pass / total
}
