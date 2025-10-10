package com.hj.leetcode.kotlin.problem2221

/**
 * LeetCode page: [2221. Find Triangular Sum of an Array](https://leetcode.com/problems/find-triangular-sum-of-an-array/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(1) where N is the length of nums.
    fun triangularSum(nums: IntArray): Int {
        val n = nums.size
        val coeffIter = newMod10Iter(n)
        var result = 0

        val mid = n shr 1
        for (i in 0..<mid) {
            result = (result + (nums[i] + nums[n - 1 - i]) * coeffIter.next()) % 10
        }
        if (n and 1 == 1) {
            result = (result + nums[mid] * coeffIter.next()) % 10
        }
        return result
    }

    // Return an iterator of C(n-1, i) % 10, starting with i = 0.
    private fun newMod10Iter(n: Int): Iterator<Int> =
        object : Iterator<Int> {
            private var i = -1
            private val mod5Iter = newMod5Iter(n)

            val n1 = 2
            val n2 = 5
            val m1 = -2
            val m2 = 1

            override fun next(): Int {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }

                i++
                val a2 = mod5Iter.next()

                if (i == 0) {
                    return 1
                }

                // C(n-1, i) % 2 derived from the property of Lucas's
                // theorem.
                val a1 = if (i and (n - 1) == i) 1 else 0

                // Construction method based on the Chinese Remainder
                // Theorem.
                return (a1 * m2 * n2 + a2 * m1 * n1).mod(10)
            }

            override fun hasNext(): Boolean = i < n - 1
        }

    // Return an iterator of C(n-1, i) % 5, starting with i = 0.
    private fun newMod5Iter(n: Int): Iterator<Int> =
        object : Iterator<Int> {
            private var i = -1
            private var mod5 = 1 // The part not divisible by 5
            private var exp5 = 0
            private var inv5 = intArrayOf(-1, 1, 3, 2, 9) // inv5[i]*i % 5 = 1 except i=0

            override fun next(): Int {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }

                i++
                if (i == 0) {
                    return 1
                }

                var a = n - i
                while (a % 5 == 0) {
                    a /= 5
                    exp5++
                }

                var b = i
                while (b % 5 == 0) {
                    b /= 5
                    exp5--
                }

                // inv5[b%5]=inv5[b] is derived from the Extended Euclidean
                // algorithm.
                mod5 = (mod5 * a * inv5[b % 5]) % 5
                return if (exp5 > 0) 0 else mod5
            }

            override fun hasNext(): Boolean = i < n - 1
        }
}
