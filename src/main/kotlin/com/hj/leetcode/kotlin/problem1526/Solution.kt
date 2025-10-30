package com.hj.leetcode.kotlin.problem1526

/**
 * LeetCode page: [1526. Minimum Number of Increments on Subarrays to Form a Target Array](https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of target.
    fun minNumberOperations(target: IntArray): Int =
        target[0] +
            target
                .asSequence()
                .windowed(2)
                .filter { (a, b) -> b > a }
                .sumOf { (a, b) -> b - a }
}
