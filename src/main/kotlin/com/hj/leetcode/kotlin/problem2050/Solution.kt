package com.hj.leetcode.kotlin.problem2050

/**
 * LeetCode page: [2050. Parallel Courses III](https://leetcode.com/problems/parallel-courses-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(n+E) and Space O(n+E);
     */
    fun minimumTime(n: Int, relations: Array<IntArray>, time: IntArray): Int {
        // prerequisites[i]::= the prerequisites of the course i
        val prerequisites = coursePrerequisites(n, relations)
        // memoization[i]::= the earliest finish time of the course i
        val memoization = hashMapOf<Int, Int>()

        return (1..n).maxOf { course ->
            earliestFinishTime(course, time, prerequisites, memoization)
        }
    }

    private fun coursePrerequisites(n: Int, relations: Array<IntArray>): List<List<Int>> {
        val result = List(n + 1) { mutableListOf<Int>() }
        for ((prerequisite, course) in relations) {
            result[course].add(prerequisite)
        }
        return result
    }

    private fun earliestFinishTime(
        course: Int,
        time: IntArray,
        prerequisites: List<List<Int>>,
        memoization: MutableMap<Int, Int>,
    ): Int {
        if (course in memoization) {
            return checkNotNull(memoization[course])
        }

        val earliestStartTime = prerequisites[course].maxOfOrNull {
            earliestFinishTime(it, time, prerequisites, memoization)
        } ?: 0

        val result = earliestStartTime + time[course - 1]
        memoization[course] = result
        return result
    }
}