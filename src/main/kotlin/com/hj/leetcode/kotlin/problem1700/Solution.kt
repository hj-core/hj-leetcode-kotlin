package com.hj.leetcode.kotlin.problem1700

/**
 * LeetCode page: [1700. Number of Students Unable to Eat Lunch](https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(1) where N is the size of students
     * and M is the size of sandwiches;
     */
    fun countStudents(students: IntArray, sandwiches: IntArray): Int {
        val demand = intArrayOf(0, 0) // demand[i] is the number of students prefers i
        for (preference in students) {
            demand[preference]++
        }

        var top = 0 // Top index of remaining sandwiches
        while (top < sandwiches.size && demand[sandwiches[top]] > 0) {
            demand[sandwiches[top]]--
            top++
        }
        return students.size - top
    }
}