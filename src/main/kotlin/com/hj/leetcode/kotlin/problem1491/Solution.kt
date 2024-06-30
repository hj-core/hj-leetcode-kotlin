package com.hj.leetcode.kotlin.problem1491

/**
 * LeetCode page: [1491. Average Salary Excluding the Minimum and Maximum Salary](https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of salary;
     */
    fun average(salary: IntArray): Double {
        val minSalary = salary.min()!!
        val maxSalary = salary.max()!!
        val sumSalary = salary.sum().toDouble()
        // Return average salary excluding the minimum and maximum salary
        return (sumSalary - minSalary - maxSalary) / (salary.size - 2)
    }
}