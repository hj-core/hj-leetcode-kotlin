package com.hj.leetcode.kotlin.problem624

import kotlin.math.max

/**
 * LeetCode page: [624. Maximum Distance in Arrays](https://leetcode.com/problems/maximum-distance-in-arrays/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of arrays;
     */
    fun maxDistance(arrays: List<List<Int>>): Int {
        val indexMin = arrays.indices.minBy { arrays[it].first() }
        val indexMax = arrays.indices.maxBy { arrays[it].last() }
        if (indexMin != indexMax) {
            return arrays[indexMax].last() - arrays[indexMin].first()
        }

        val indexMin2 = arrays
            .indices
            .asSequence()
            .filter { it != indexMin }
            .minBy { arrays[it].first() }
        val indexMax2 = arrays
            .indices
            .asSequence()
            .filter { it != indexMax }
            .maxBy { arrays[it].last() }

        return max(
            arrays[indexMax].last() - arrays[indexMin2].first(),
            arrays[indexMax2].last() - arrays[indexMin].first()
        )
    }
}