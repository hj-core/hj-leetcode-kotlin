package com.hj.leetcode.kotlin.problem1840

import java.util.TreeSet

/**
 * LeetCode page: [1840. Maximum Building Height](https://leetcode.com/problems/maximum-building-height/);
 */
class Solution {
    // Complexity:
    // Time O(MLogM) and Space O(M) where M is the length of restrictions.
    fun maxBuilding(
        n: Int,
        restrictions: Array<IntArray>,
    ): Int {
        if (restrictions.isEmpty()) {
            return n - 1
        }

        val restrictions = cleanedRestrictions(restrictions)
        var maxHeight = 0
        var restriction = intArrayOf(1, 0)
        for (nextRestriction in restrictions) {
            val height = restriction[1] + nextRestriction[0] - restriction[0]
            maxHeight = maxOf(maxHeight, (height + nextRestriction[1]) / 2)
            restriction = nextRestriction
        }

        if (restriction[0] < n) {
            maxHeight = maxOf(maxHeight, restriction[1] + n - restriction[0])
        }

        return maxHeight
    }

    // Return the tightened restrictions ordered by increasing ID.
    private fun cleanedRestrictions(restrictions: Array<IntArray>): Array<out IntArray> {
        if (restrictions.isEmpty()) {
            return emptyArray()
        }

        val cleaned = restrictions.sortedArrayWith { r1, r2 -> r1[0] - r2[0] }
        if (cleaned[0][0] == 2) {
            cleaned[0][1] = minOf(cleaned[0][1], 1)
        }

        for (i in 1..<cleaned.size) {
            cleaned[i][1] =
                minOf(cleaned[i][1], cleaned[i - 1][1] + cleaned[i][0] - cleaned[i - 1][0])
        }
        for (i in cleaned.lastIndex downTo 1) {
            cleaned[i - 1][1] =
                minOf(cleaned[i - 1][1], cleaned[i][1] + cleaned[i][0] - cleaned[i - 1][0])
        }

        return cleaned
    }

//    private fun cleanedRestrictions(restrictions: Array<IntArray>): Array<out IntArray> {
//        if (restrictions.isEmpty()) {
//            return emptyArray()
//        }
//
//        val cleaned = restrictions.sortedArrayWith { r1, r2 -> r1[0] - r2[0] }
//        if (cleaned[0][0] == 2) {
//            cleaned[0][1] = minOf(cleaned[0][1], 1)
//        }
//
//        val indices =
//            TreeSet(
//                compareBy<Int> { cleaned[it][1] }.thenBy { cleaned[it][0] },
//            )
//        indices.addAll(cleaned.indices)
//
//        while (indices.isNotEmpty()) {
//            val index = checkNotNull(indices.pollFirst())
//            if (index > 0) {
//                val maxLeft = cleaned[index][1] + cleaned[index][0] - cleaned[index - 1][0]
//                if (cleaned[index - 1][1] > maxLeft) {
//                    indices.remove(index - 1)
//                    cleaned[index - 1][1] = maxLeft
//                    indices.add(index - 1)
//                }
//            }
//            if (index < cleaned.lastIndex) {
//                val maxRight = cleaned[index][1] + cleaned[index + 1][0] - cleaned[index][0]
//                if (cleaned[index + 1][1] > maxRight) {
//                    indices.remove(index + 1)
//                    cleaned[index + 1][1] = maxRight
//                    indices.add(index + 1)
//                }
//            }
//        }
//
//        return cleaned
//    }
}
