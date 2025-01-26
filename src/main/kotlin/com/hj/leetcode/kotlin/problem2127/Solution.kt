package com.hj.leetcode.kotlin.problem2127

/**
 * LeetCode page: [2127. Maximum Employees to Be Invited to a Meeting](https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of favorite.
     */
    fun maximumInvitations(favorite: IntArray): Int {
        val totalEmployees = favorite.size

        // revFavorite[e]::= the employees who like e
        val revFavorite = List(totalEmployees) { mutableListOf<Int>() }
        val inDegrees = IntArray(totalEmployees) { 0 } // inDegrees[e]

        for ((employee, likeEmployee) in favorite.withIndex()) {
            revFavorite[likeEmployee].add(employee)
            inDegrees[likeEmployee]++
        }

        // Use Khan's algorithm to filter out the employees not in a like-cycle
        val stack = mutableListOf<Int>()
        for (employee in 0..<totalEmployees) {
            if (inDegrees[employee] == 0) {
                stack.add(employee)
            }
        }

        while (stack.isNotEmpty()) {
            val employee = stack.removeLast()
            val likeEmployee = favorite[employee]
            inDegrees[likeEmployee]--
            if (inDegrees[likeEmployee] == 0) {
                stack.add(likeEmployee)
            }
        }

        // Handle the like-cycles based on their length
        var sumLengthTwoExtended = 0
        var longestCycle = 0

        for (employee in 0..<totalEmployees) {
            if (inDegrees[employee] == 0) {
                continue
            }
            val likeEmployee = favorite[employee]
            val isLengthTwo = favorite[likeEmployee] == employee

            if (isLengthTwo) {
                inDegrees[employee] = 0
                inDegrees[likeEmployee] = 0

                sumLengthTwoExtended +=
                    longestPath(employee, likeEmployee, revFavorite) +
                    longestPath(likeEmployee, employee, revFavorite)
            } else {
                longestCycle = maxOf(longestCycle, removeCycle(employee, favorite, inDegrees))
            }
        }
        return maxOf(longestCycle, sumLengthTwoExtended)
    }

    // Find the longest like-path that ends at employee and doesn't include skipEmployee.
    private fun longestPath(
        employee: Int,
        skipEmployee: Int,
        revFavorite: List<List<Int>>,
    ): Int {
        var longestExtension = 0
        for (likingEmployee in revFavorite[employee]) {
            if (likingEmployee == skipEmployee) {
                continue
            }
            longestExtension = maxOf(longestExtension, longestPath(likingEmployee, skipEmployee, revFavorite))
        }
        return 1 + longestExtension
    }

    // Clear the in-degree of like-cycle members and return the length of the cycle.
    private fun removeCycle(
        start: Int,
        favorites: IntArray,
        inDegrees: IntArray,
    ): Int {
        var result = 0
        // Since each employee has an out-degree of one, they can only participate in one
        // like-cycle, so a simple like-traversal is sufficient.
        var curr = start
        while (inDegrees[curr] > 0) {
            result++
            inDegrees[curr] = 0
            curr = favorites[curr]
        }
        return result
    }
}
