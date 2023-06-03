package com.hj.leetcode.kotlin.problem1376

/**
 * LeetCode page: [1376. Time Needed to Inform All Employees](https://leetcode.com/problems/time-needed-to-inform-all-employees/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun numOfMinutes(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {
        var result = 0
        val cachedInformedTime = hashMapOf<Int, Int>() // entry = (employeeId, informedTime)

        for (employeeId in 0 until n) {
            if (isLeafEmployee(employeeId, informTime)) {
                val informedTime =
                    informedTime(employeeId, headID, manager, informTime, cachedInformedTime)
                if (result < informedTime) {
                    result = informedTime
                }
            }
        }
        return result
    }

    private fun isLeafEmployee(employeeId: Int, informTime: IntArray): Boolean {
        return informTime[employeeId] == 0
    }

    private fun informedTime(
        employeeId: Int,
        headID: Int,
        manager: IntArray,
        informTime: IntArray,
        cachedResult: MutableMap<Int, Int>
    ): Int {

        if (employeeId in cachedResult) {
            return checkNotNull(cachedResult[employeeId])
        }

        if (employeeId == headID) {
            return 0
        }

        val managerId = manager[employeeId]
        val result =
            informTime[managerId] + informedTime(managerId, headID, manager, informTime, cachedResult)
        cachedResult[employeeId] = result
        return result
    }
}