package com.hj.leetcode.kotlin.problem2106

/**
 * LeetCode page: [2106. Maximum Fruits Harvested After at Most K Steps](https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of fruits.
    fun maxTotalFruits(
        fruits: Array<IntArray>,
        startPos: Int,
        k: Int,
    ): Int {
        val minIndexL =
            fruits
                .binarySearch(intArrayOf(startPos - k), { o1, o2 -> o1[0] - o2[0] })
                .let { if (it < 0) -it - 1 else it }
        val maxIndexR =
            fruits
                .binarySearch(intArrayOf(startPos + k + 1), { o1, o2 -> o1[0] - o2[0] })
                .let { if (it < 0) -it - 1 else it } // Exclusive

        // Returns the minimum steps to collect all the fruits between
        // indexL and indexR.
        fun computeMinSteps(
            indexL: Int,
            indexR: Int,
        ): Int =
            when {
                startPos <= fruits[indexL][0] -> fruits[indexR][0] - startPos
                startPos >= fruits[indexR][0] -> startPos - fruits[indexL][0]
                else ->
                    minOf(
                        startPos - fruits[indexL][0],
                        fruits[indexR][0] - startPos,
                    ) + fruits[indexR][0] - fruits[indexL][0]
            }

        var result = 0
        var indexL = minIndexL
        var wndCount = 0
        for (indexR in indexL..<maxIndexR) {
            wndCount += fruits[indexR][1]
            while (k < computeMinSteps(indexL, indexR)) {
                wndCount -= fruits[indexL][1]
                indexL++
            }
            result = maxOf(result, wndCount)
        }
        return result
    }
}
