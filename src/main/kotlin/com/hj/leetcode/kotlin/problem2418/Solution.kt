package com.hj.leetcode.kotlin.problem2418

/**
 * LeetCode page: [2418. Sort the People](https://leetcode.com/problems/sort-the-people/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of names and heights;
     */
    fun sortPeople(names: Array<String>, heights: IntArray): Array<String> {
        val sortedIndices = names
            .indices
            .sortedByDescending { heights[it] }
        return Array(names.size) { names[sortedIndices[it]] }
    }
}