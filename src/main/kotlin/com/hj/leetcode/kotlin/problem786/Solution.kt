package com.hj.leetcode.kotlin.problem786

import java.util.*

/**
 * LeetCode page: [786. K-th Smallest Prime Fraction](https://leetcode.com/problems/k-th-smallest-prime-fraction/);
 */
class Solution {
    /* Complexity:
     * Time O(kLogk) and Space O(k);
     */
    fun kthSmallestPrimeFraction(arr: IntArray, k: Int): IntArray {
        val sortedPairs = TreeSet<Pair<Int, Int>>(
            compareBy { (i, j) -> arr[i].toDouble() / arr[j] })

        sortedPairs.add(Pair(0, arr.lastIndex))
        repeat(k - 1) {
            val (i, j) = checkNotNull(sortedPairs.pollFirst())
            if (i + 1 != j) {
                sortedPairs.add(Pair(i + 1, j))
            }
            if (i != j - 1) {
                sortedPairs.add(Pair(i, j - 1))
            }
        }

        val kthSmallestPair = checkNotNull(sortedPairs.pollFirst())
        return intArrayOf(arr[kthSmallestPair.first], arr[kthSmallestPair.second])
    }
}