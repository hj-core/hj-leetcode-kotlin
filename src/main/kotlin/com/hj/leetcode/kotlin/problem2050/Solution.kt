package com.hj.leetcode.kotlin.problem2050

/**
 * LeetCode page: [2050. Parallel Courses III](https://leetcode.com/problems/parallel-courses-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(n+E) and Space O(n+E) where E is the size of relations;
     */
    fun minimumTime(n: Int, relations: Array<IntArray>, time: IntArray): Int {
        // prerequisites[i]::= the prerequisites of course i
        val prerequisites = prerequisites(n, relations)
        // nextCourses[i]::= the courses with course i as a prerequisite
        val nextCourses = nextCourses(n, relations)

        val sortedCourses = topologicallySortedCourses(n, prerequisites)
        return earliestFinishTimes(n, time, nextCourses, sortedCourses).max()
    }

    private fun prerequisites(n: Int, relations: Array<IntArray>): List<List<Int>> {
        val result = List(n + 1) { mutableListOf<Int>() }
        for ((prerequisite, course) in relations) {
            result[course].add(prerequisite)
        }
        return result
    }

    private fun nextCourses(n: Int, relations: Array<IntArray>): List<List<Int>> {
        val result = List(n + 1) { mutableListOf<Int>() }
        for ((prerequisite, course) in relations) {
            result[prerequisite].add(course)
        }
        return result
    }

    private fun topologicallySortedCourses(n: Int, prerequisites: List<List<Int>>): List<Int> {
        val result = ArrayDeque<Int>()
        val visited = BooleanArray(n + 1)
        for (course in 1..n) {
            if (visited[course]) {
                continue
            }
            visited[course] = true
            dfs(course, prerequisites, visited) {
                result.addFirst(it)
            }
        }
        return result.toList()
    }

    private fun dfs(
        course: Int,
        prerequisites: List<List<Int>>,
        visited: BooleanArray,
        onCompletion: (course: Int) -> Unit,
    ) {
        check(visited[course])

        for (prerequisite in prerequisites[course]) {
            if (visited[prerequisite]) {
                continue
            }
            visited[prerequisite] = true
            dfs(prerequisite, prerequisites, visited, onCompletion)
        }
        onCompletion(course)
    }

    private fun earliestFinishTimes(
        n: Int,
        time: IntArray,
        nextCourses: List<List<Int>>,
        sortedCourses: List<Int>, // In topological order
    ): IntArray {
        val result = IntArray(n + 1).apply {
            for (i in 1..<size) {
                this[i] = time[i - 1]
            }
        }

        for (course in sortedCourses.asReversed()) {
            for (nextCourse in nextCourses[course]) {
                val finishTime = result[course] + time[nextCourse - 1]
                if (result[nextCourse] < finishTime) {
                    result[nextCourse] = finishTime
                }
            }
        }
        return result
    }
}