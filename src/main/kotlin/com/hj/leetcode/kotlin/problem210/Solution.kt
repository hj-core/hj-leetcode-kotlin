package com.hj.leetcode.kotlin.problem210

/**
 * LeetCode page: [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(V+E) and Space O(V+E) where V is numCourses and E is size of prerequisites;
     */
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val coursesByPrerequisite = groupCoursesByPrerequisite(numCourses, prerequisites)
        val pendingCount = getNumPendingPrerequisitePerCourse(numCourses, prerequisites)
        return findPathToFinishAllCourses(numCourses, pendingCount, coursesByPrerequisite)
    }

    private fun groupCoursesByPrerequisite(
        numCourses: Int,
        prerequisites: Array<IntArray>
    ): List<List<Int>> {
        val graph = List(numCourses) { mutableListOf<Int>() }
        for (dependency in prerequisites) {
            val course = dependency[0]
            val prerequisite = dependency[1]
            graph[prerequisite].add(course)
        }
        return graph
    }

    private fun getNumPendingPrerequisitePerCourse(
        numCourses: Int,
        prerequisites: Array<IntArray>
    ): IntArray {
        val count = IntArray(numCourses)
        for (dependency in prerequisites) {
            val course = dependency[0]
            count[course]++
        }
        return count
    }

    private fun findPathToFinishAllCourses(
        numCourses: Int,
        numPendingPrerequisitePerCourse: IntArray,
        coursesByPrerequisite: List<List<Int>>
    ): IntArray {
        val pendingCount = numPendingPrerequisitePerCourse
        val path = IntArray(numCourses)

        var indexNextCourse = 0
        for (course in pendingCount.indices) {
            val noPrerequisite = pendingCount[course] == 0
            if (noPrerequisite) {
                path[indexNextCourse] = course
                indexNextCourse++
            }
        }

        var indexPrevCourse = 0
        while (indexPrevCourse < indexNextCourse) {
            val completedCourse = path[indexPrevCourse]
            val coursesRequiringIt = coursesByPrerequisite[completedCourse]

            for (course in coursesRequiringIt) {
                pendingCount[course]--
                val canBeCompleted = pendingCount[course] == 0
                if (canBeCompleted) {
                    path[indexNextCourse] = course
                    indexNextCourse++
                }
            }
            indexPrevCourse++
        }

        val pathFound = indexNextCourse == path.size
        return if (pathFound) path else intArrayOf()
    }
}