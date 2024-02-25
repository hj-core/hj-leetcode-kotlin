package com.hj.leetcode.kotlin.problem2709

/**
 * LeetCode page: [2709. Greatest Common Divisor Traversal](https://leetcode.com/problems/greatest-common-divisor-traversal/);
 */
class Solution {
    /* Complexity:
     * TODO()
     */
    fun canTraverseAllPairs(nums: IntArray): Boolean {
        if (nums.size == 1) {
            return true
        }
        if (1 in nums) {
            return false
        }

        val uf = UnionFind()
        for (num in nums) {
            uf.make(num)
        }

        val primeSieve = IntArray(nums.max() + 1)
        for (i in 2..<primeSieve.size) {
            if (primeSieve[i] == 0) {
                for (j in i..<primeSieve.size step i) {
                    primeSieve[j] = i
                }
            }
        }

        val primeFactors = hashSetOf<Int>()
        for (num in nums) {
            var remaining = num
            while (remaining > 1) {
                val primeFactor = primeSieve[remaining]
                primeFactors.add(primeFactor)
                uf.make(primeFactor)
                uf.union(num, primeFactor)

                while (remaining % primeFactor == 0) {
                    remaining /= primeFactor
                }
            }
        }

        val refRoot = uf.find(primeFactors.first())
        return primeFactors.all { uf.find(it) == refRoot }
    }

    private class UnionFind {

        private val parents = hashMapOf<Int, Int>()
        private val ranks = hashMapOf<Int, Int>()

        fun make(x: Int): Boolean {
            if (x in parents) {
                check(x in ranks)
                return false
            }
            parents[x] = x
            ranks[x] = 0
            return true
        }

        fun find(x: Int): Int {
            val xParent = checkNotNull(parents[x])
            if (x != xParent) {
                parents[x] = find(xParent)
            }
            return checkNotNull(parents[x])
        }

        fun union(x: Int, y: Int): Boolean {
            val xRoot = find(x)
            val yRoot = find(y)

            if (xRoot == yRoot) {
                return false
            }

            val xRank = checkNotNull(ranks[x])
            val yRank = checkNotNull(ranks[y])

            when {
                xRank < yRank -> parents[xRoot] = yRoot
                xRank > yRank -> parents[yRoot] = xRoot
                else -> {
                    parents[xRoot] = yRoot
                    ranks[y] = yRank + 1
                }
            }
            return true
        }
    }
}