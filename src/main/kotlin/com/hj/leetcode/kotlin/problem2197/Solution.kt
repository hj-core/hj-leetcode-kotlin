package com.hj.leetcode.kotlin.problem2197

/**
 * LeetCode page: [2197. Replace Non-Coprime Numbers in Array](https://leetcode.com/problems/replace-non-coprime-numbers-in-array/);
 */
class Solution {
    // Complexity:
    // Time O(NLogM) and Space O(N) where N is the length of
    // nums and M is the maximum value in nums.
    fun replaceNonCoprimes(nums: IntArray): List<Int> =
        buildList {
            for (num in nums) {
                var a = num
                while (isNotEmpty()) {
                    val b = last()
                    val gcd = gcd(a, b)
                    if (gcd == 1) {
                        break
                    }
                    removeLast()
                    a *= b / gcd
                }
                add(a)
            }
        }

    private tailrec fun gcd(
        a: Int,
        b: Int,
    ): Int = if (b == 0) a else gcd(b, a % b)
}
