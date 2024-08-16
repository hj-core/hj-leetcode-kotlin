package com.hj.leetcode.kotlin.problem624

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [624. Maximum Distance in Arrays](https://leetcode.com/problems/maximum-distance-in-arrays/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of arrays;
     */
    fun maxDistance(arrays: List<List<Int>>): Int {
        /* Pile up the arrays and connect the pairs that lead to the result.
         * The line must be either tilted to the left or tilted to the right.
         */
        var result = 0
        var minValue = arrays[0].first()
        var maxValue = arrays[0].last()

        for (i in 1..<arrays.size) {
            result = maxOf(
                result,
                arrays[i].last() - minValue, // Tilt to the right
                maxValue - arrays[i].first() // Tilt to the left
            )
            minValue = min(minValue, arrays[i].first())
            maxValue = max(maxValue, arrays[i].last())
        }
        return result
    }
}