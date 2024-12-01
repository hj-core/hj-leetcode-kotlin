package com.hj.leetcode.kotlin.problem1346

/**
 * LeetCode page: [1346. Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of arr.
     */
    fun checkIfExist(arr: IntArray): Boolean {
        val visited = mutableSetOf<Int>()
        for (num in arr) {
            if (num * 2 in visited) {
                return true
            }
            if (num % 2 == 0 && num / 2 in visited) {
                return true
            }
            visited.add(num)
        }
        return false
    }
}
