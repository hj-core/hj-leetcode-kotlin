package com.hj.leetcode.kotlin.problem2779

/**
 * LeetCode page: [2779. Maximum Beauty of an Array After Applying Operation](https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of nums.
     */
    fun maximumBeauty(
        nums: IntArray,
        k: Int,
    ): Int {
        val sortedNums = nums.sorted()
        var left = 0
        val threshold = k * 2
        for (num in sortedNums) {
            if (threshold < num - sortedNums[left]) {
                left++
            }
        }
        return sortedNums.size - left
    }
}
