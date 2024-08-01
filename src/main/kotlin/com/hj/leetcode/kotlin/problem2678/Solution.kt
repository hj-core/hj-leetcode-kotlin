package com.hj.leetcode.kotlin.problem2678

/**
 * LeetCode page: [2678. Number of Senior Citizens](https://leetcode.com/problems/number-of-senior-citizens/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of details;
     */
    fun countSeniors(details: Array<String>): Int {
        return details.count { it.substring(11..12).toInt() > 60 }
    }
}