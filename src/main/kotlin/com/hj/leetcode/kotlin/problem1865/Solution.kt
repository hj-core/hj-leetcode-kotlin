package com.hj.leetcode.kotlin.problem1865

class Solution

/**
 * LeetCode page: [1865. Finding Pairs With a Certain Sum](https://leetcode.com/problems/finding-pairs-with-a-certain-sum/);
 */
class FindSumPairs(
    nums1: IntArray,
    private val nums2: IntArray,
) {
    private val freq1 = hashMapOf<Int, Int>()
    private val freq2 = hashMapOf<Int, Int>()

    // Complexity:
    // Time O(N+M) and Space O(N+M) where N is the length of nums1 and
    // M is the length of nums2.
    init {
        for (num in nums1) {
            freq1[num] = (freq1[num] ?: 0) + 1
        }
        for (num in nums2) {
            freq2[num] = (freq2[num] ?: 0) + 1
        }
    }

    // Complexity:
    // Time O(1) and Space O(1).
    fun add(
        index: Int,
        value: Int,
    ) {
        val old = nums2[index]
        val new = old + value
        nums2[index] = new
        freq2.compute(new) { _, count -> 1 + (count ?: 0) }
        freq2.compute(old) { _, count -> if (count == 1) null else checkNotNull(count) - 1 }
    }

    // Complexity:
    // Time O(min(N,M)) and Space O(1) where N is the length of nums1
    // and M is the length of nums2.
    fun count(tot: Int): Int = if (freq1.size < freq2.size) countPairs(freq1, freq2, tot) else countPairs(freq2, freq1, tot)

    // Return the number of (num1, num2) pairs that add up to sum.
    fun countPairs(
        freq1: Map<Int, Int>,
        freq2: Map<Int, Int>,
        sum: Int,
    ): Int {
        var result = 0
        for ((num1, count1) in freq1) {
            val num2 = sum - num1
            val count2 = freq2[num2] ?: continue
            result += count1 * count2
        }
        return result
    }
}
