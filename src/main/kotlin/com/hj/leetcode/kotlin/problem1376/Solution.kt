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
        traverseOrganization(
            headId = headID,
            organization = organization(n, headID, manager),
            informTime = informTime,
            informedTime = 0
        ) { informedTime ->

            if (result < informedTime) {
                result = informedTime
            }
        }
        return result
    }

    /**
     * Return a list that its value at index i is the employee ids which are the subordinates
     * of employee id i.
     */
    private fun organization(n: Int, headID: Int, manager: IntArray): List<List<Int>> {
        val result = List(n) { mutableListOf<Int>() }
        for ((id, managerId) in manager.withIndex()) {
            if (id == headID) {
                continue
            }
            result[managerId].add(id)
        }
        return result
    }

    private fun traverseOrganization(
        headId: Int,
        organization: List<List<Int>>,
        informTime: IntArray,
        informedTime: Int,
        onEachEmployee: (informedTime: Int) -> Unit
    ) {
        onEachEmployee(informedTime)

        val subordinates = organization[headId]
        val subordinatesInformedTime = informedTime + informTime[headId]
        for (subordinate in subordinates) {
            traverseOrganization(
                headId = subordinate,
                organization = organization,
                informTime = informTime,
                informedTime = subordinatesInformedTime,
                onEachEmployee = onEachEmployee
            )
        }
    }
}