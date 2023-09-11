package com.hj.leetcode.kotlin.problem1282

/**
 * LeetCode page: [1282. Group the People Given the Group Size They Belong To](https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n) where n is the size of groupSizes;
     */
    fun groupThePeople(groupSizes: IntArray): List<List<Int>> {
        val people = 0 until groupSizes.size
        return people
            .groupBy { groupSizes[it] }
            .flatMap { (groupSizes, people) -> people.chunked(groupSizes) }
    }
}