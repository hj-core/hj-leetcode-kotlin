package com.hj.leetcode.kotlin.problem440

import kotlin.math.min

/**
 * LeetCode page: [440. K-th Smallest in Lexicographical Order](https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/);
 */
class Solution {
    /* Complexity:
     * Time O((Log n)^2) and Space O(1).
     */
    fun findKthNumber(
        n: Int,
        k: Int,
    ): Int {
        /* Search the 'digit tree' where the root has child nodes 1 to 9,
         * and all child nodes recursively have child nodes 0 to 9.
         */
        return search(1, 1, n, k)
    }

    private tailrec fun search(
        root: Int,
        rank: Int,
        n: Int,
        k: Int,
    ): Int {
        if (rank == k) {
            return root
        }

        val size = size(root, n)
        return if (rank + size - 1 < k) {
            search(root + 1, rank + size, n, k) // Move to the next sibling
        } else {
            search(root * 10, rank + 1, n, k) // Move to the first child
        }
    }

    private fun size(
        root: Int,
        n: Int,
    ): Int {
        var result = 0
        var startValue = root // The first value in current level
        var breadth = 1
        while (startValue <= n) {
            val endValue = min(n, startValue + breadth - 1)
            val trueBreadth = endValue - startValue + 1
            result += trueBreadth

            if (n / 10 < startValue) {
                break
            }
            startValue *= 10
            breadth *= 10
        }
        return result
    }
}
