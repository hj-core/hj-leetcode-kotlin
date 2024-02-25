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

        val sieve = IntArray(nums.max() + 1)
        for (i in 2..<sieve.size) {
            if (sieve[i] == 0) {
                for (j in i..<sieve.size step i) {
                    sieve[j] = i
                }
            }
        }

        val primes = hashSetOf<Int>()
        for (num in nums) {
            var remaining = num
            while (remaining > 1) {
                val prime = sieve[remaining]
                primes.add(prime)
                uf.make(prime)
                uf.union(num, prime)

                while (remaining % prime == 0) {
                    remaining /= prime
                }
            }
        }

        val refRoot = uf.find(primes.first())
        return primes.all { uf.find(it) == refRoot }
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