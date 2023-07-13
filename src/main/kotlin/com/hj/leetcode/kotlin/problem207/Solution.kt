package com.hj.leetcode.kotlin.problem207

/**
 * LeetCode page: [207. Course Schedule](https://leetcode.com/problems/course-schedule/);
 */
class Solution {
    /* Complexity:
     * Time O(V+E) and Space O(V+E) where V equals numCourses and E is the size of prerequisites;
     */
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val visited = BooleanArray(numCourses)
        val adjacencyList = adjacencyList(prerequisites) // entry = (course, its prerequisites)

        fun hasCyclicPrerequisites(course: Int, currentPath: MutableSet<Int>): Boolean {
            if (visited[course]) {
                return currentPath.contains(course)
            }

            visited[course] = true
            currentPath.add(course)
            for (prerequisite in (adjacencyList[course] ?: emptyList())) {
                if (hasCyclicPrerequisites(prerequisite, currentPath)) {
                    return true
                }
            }
            currentPath.remove(course)
            return false
        }

        val courses = 0 until numCourses
        return !courses.any { hasCyclicPrerequisites(it, hashSetOf()) }
    }

    private fun adjacencyList(prerequisites: Array<IntArray>): Map<Int, List<Int>> {
        val result = hashMapOf<Int, MutableList<Int>>()
        for ((course, prerequisite) in prerequisites) {
            result
                .computeIfAbsent(course) { mutableListOf() }
                .add(prerequisite)
        }
        return result
    }
}