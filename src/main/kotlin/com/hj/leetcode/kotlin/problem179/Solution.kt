package com.hj.leetcode.kotlin.problem179

/**
 * LeetCode page: [179. Largest Number](https://leetcode.com/problems/largest-number/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun largestNumber(nums: IntArray): String {
        val sortedBuckets = getSortedDigitBuckets(nums)
        return getLargestNumber(sortedBuckets)
    }

    private fun getSortedDigitBuckets(nums: IntArray): List<List<String>> {
        val sortedBucket = List(10) { mutableListOf<String>() }
        for (num in nums) {
            val strForm = num.toString()
            sortedBucket[strForm[0] - '0'].add(strForm)
        }

        val comparator = getDesiredComparator()
        for (bucket in sortedBucket) {
            bucket.sortWith(comparator)
        }
        return sortedBucket
    }

    private fun getDesiredComparator(): Comparator<String> {
        val comparator = Comparator<String> { strNum1, strNum2 ->
            val strNum1Num2 = strNum1 + strNum2
            val strNum2Num1 = strNum2 + strNum1
            for (index in 0 until (strNum1.length + strNum2.length)) {
                if (strNum1Num2[index] != strNum2Num1[index]) {
                    return@Comparator strNum2Num1[index] - strNum1Num2[index]
                }
            }
            return@Comparator 0
        }
        return comparator
    }

    private fun getLargestNumber(sortedDigitBuckets: List<List<String>>): String {
        if (containsZerosOnly(sortedDigitBuckets)) return "0"

        val largestNumber = StringBuilder()
        for (digit in sortedDigitBuckets.indices.reversed()) {
            for (strNum in sortedDigitBuckets[digit]) {
                largestNumber.append(strNum)
            }
        }
        return largestNumber.toString()
    }

    private fun containsZerosOnly(sortedDigitBuckets: List<List<String>>): Boolean {
        for (digit in 1..9) {
            if (sortedDigitBuckets[digit].isNotEmpty()) return false
        }
        return true
    }
}