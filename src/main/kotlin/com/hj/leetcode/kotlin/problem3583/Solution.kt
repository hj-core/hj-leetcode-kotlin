package com.hj.leetcode.kotlin.problem3583

/**
 * LeetCode page: [3583. Count Special Triplets](https://leetcode.com/problems/count-special-triplets/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun specialTriplets(nums: IntArray): Int {
        // countPair[num*2]:= the number of (num*2, num) pairs in
        // nums[0..<k]
        val countPair = hashMapOf<Int, Long>()
        // countNum:= the frequency of num in nums[0..<k]
        val countNum = hashMapOf<Int, Int>()

        var result = 0L
        for (num in nums) {
            result += countPair[num] ?: 0

            val twoNum = num * 2
            countPair.compute(twoNum) { _, count ->
                (count ?: 0L) + (countNum[twoNum] ?: 0)
            }
            countNum.compute(num) { _, count ->
                (count ?: 0) + 1
            }
        }
        return (result % 1_000_000_007).toInt()
    }
}
