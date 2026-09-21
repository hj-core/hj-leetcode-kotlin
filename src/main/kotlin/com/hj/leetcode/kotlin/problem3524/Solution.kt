package com.hj.leetcode.kotlin.problem3524

/**
 * LeetCode page: [3524. Find X Value of Array I](https://leetcode.com/problems/find-x-value-of-array-i/);
 */
class Solution {
    // Complexity:
    // Time O(kN) and Space O(k+N) where N is the length of nums.
    fun resultArray(
        nums: IntArray,
        k: Int,
    ): LongArray {
        val result = LongArray(k)
        val remFreq = Array(2) { IntArray(k) }
        for (i in nums.indices) {
            val modK = nums[i] % k
            val prevFreq = remFreq[i and 1 xor 1]
            val currFreq = remFreq[i and 1]
            currFreq[modK]++
            for (j in 0..<k) {
                currFreq[(j * modK) % k] += prevFreq[j]
                prevFreq[j] = 0
            }

            for (j in 0..<k) {
                result[j] += currFreq[j]
            }
        }

        return result
    }
}
