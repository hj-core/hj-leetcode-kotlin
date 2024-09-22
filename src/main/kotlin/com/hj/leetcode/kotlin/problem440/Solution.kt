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
        return search(1, 0, n, k)
    }

    private tailrec fun search(
        root: Int,
        rank: Int,
        n: Int,
        k: Int,
    ): Int {
        if (rank + 1 == k) {
            return root
        }

        val size = size(root, n)
        return if (rank + size < k) {
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
        var levelStart = root
        var breadth = 1
        while (levelStart <= n) {
            val levelEnd = min(n, levelStart + breadth - 1)
            result += levelEnd - levelStart + 1

            if (n / 10 < levelStart) {
                break
            }
            levelStart *= 10
            breadth *= 10
        }
        return result
    }
}
