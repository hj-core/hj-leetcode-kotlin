package com.hj.leetcode.kotlin.problem881

/**
 * LeetCode page: [881. Boats to Save People](https://leetcode.com/problems/boats-to-save-people/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of people;
     */
    fun numRescueBoats(people: IntArray, limit: Int): Int {
        val sortedPeople = people.sorted()
        var minBoats = 0
        var front = 0
        var back = sortedPeople.lastIndex
        while (front <= back) {
            val canCarryBoth = sortedPeople[front] + sortedPeople[back] <= limit
            if (canCarryBoth) {
                front++
            }
            back--
            minBoats++
        }
        return minBoats
    }
}