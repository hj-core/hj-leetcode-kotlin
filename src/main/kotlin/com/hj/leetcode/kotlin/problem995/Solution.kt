package com.hj.leetcode.kotlin.problem995

/**
 * LeetCode page: [995. Minimum Number of K Consecutive Bit Flips](https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun minKBitFlips(nums: IntArray, k: Int): Int {
        var result = 0
        var i = 0
        val copyNums = nums.clone()
        while (i < copyNums.size) {
            while (i < copyNums.size && copyNums[i] == 1) {
                i++
            }
            when {
                i == copyNums.size -> return result
                i + k > copyNums.size -> return -1
            }

            result++ // Apply k-bit flips at i
            var j = i
            while (j < i + k && copyNums[j] == 0) {
                j++
            }
            when {
                j == i + k -> i = j
                j + k > copyNums.size -> return -1
                else -> {
                    result++ // Apply k-bit flips at j
                    // numbers in indices [j+1, i+k-1] are unchanged due to double flipped
                    for (p in (i + k)..<(j + k)) {
                        copyNums[p] = copyNums[p] xor 1
                    }
                    i = j + 1
                }
            }
        }
        return result
    }
}