package com.hj.leetcode.kotlin.problem2433

/**
 * LeetCode page: [2433. Find The Original Array of Prefix Xor](https://leetcode.com/problems/find-the-original-array-of-prefix-xor/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of pref;
     */
    fun findArray(pref: IntArray): IntArray {
        return IntArray(pref.size) { index ->
            pref[index] xor pref.getOrElse(index - 1) { 0 }
        }
    }
}