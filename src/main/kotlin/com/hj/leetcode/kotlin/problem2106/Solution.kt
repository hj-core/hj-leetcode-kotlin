package com.hj.leetcode.kotlin.problem2106

/**
 * LeetCode page: [2106. Maximum Fruits Harvested After at Most K Steps](https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of fruits.
    fun maxTotalFruits(
        fruits: Array<IntArray>,
        startPos: Int,
        k: Int,
    ): Int {
        val (startIndex, found) =
            fruits
                .binarySearch(intArrayOf(startPos), { o1, o2 -> o1[0] - o2[0] })
                .let { if (it < 0) -it - 1 to false else it to true }
        var result = 0

        // Strategy 1: startPos -> indexL -> indexR
        var indexL = startIndex
        var indexR = indexL
        var count = 0

        while (0 < indexL && (startPos - fruits[indexL - 1][0]) * 2 <= k) {
            indexL--
            count += fruits[indexL][1]
        }
        while (indexR < fruits.size && fruits[indexR][0] - startPos <= k) {
            count += fruits[indexR][1]
            while (fruits[indexR][0] + startPos - fruits[indexL][0] * 2 > k) {
                count -= fruits[indexL][1]
                indexL++
            }
            result = maxOf(result, count)
            indexR++
        }

        // Strategy 2: startPos -> indexR -> indexL
        indexL = if (found) startIndex else startIndex - 1
        indexR = indexL
        count = 0

        while (indexR < fruits.size - 1 && (fruits[indexR + 1][0] - startPos) * 2 <= k) {
            indexR++
            count += fruits[indexR][1]
        }
        while (0 <= indexL && startPos - fruits[indexL][0] <= k) {
            count += fruits[indexL][1]
            while (fruits[indexR][0] * 2 - startPos - fruits[indexL][0] > k) {
                count -= fruits[indexR][1]
                indexR--
            }
            result = maxOf(result, count)
            indexL--
        }

        return result
    }
}
