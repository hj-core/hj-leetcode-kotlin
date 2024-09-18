package com.hj.leetcode.kotlin.problem179

/**
 * LeetCode page: [179. Largest Number](https://leetcode.com/problems/largest-number/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of nums.
     */
    fun largestNumber(nums: IntArray): String {
        val strNums = nums.mapTo(mutableListOf()) { it.toString() }
        strNums.sortWith { str1, str2 ->
            val order1 = str2 + str1
            val order2 = str1 + str2
            order1.compareTo(order2)
        }

        if (strNums.first() == "0") {
            return "0"
        }
        return strNums.joinToString(separator = "")
    }
}
