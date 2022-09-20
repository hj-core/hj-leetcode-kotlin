package com.hj.leetcode.kotlin.problem718

/**
 * LeetCode page: [718. Maximum Length of Repeated Subarray](https://leetcode.com/problems/maximum-length-of-repeated-subarray/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(1) where M and N are the size of nums1 and nums2;
     */
    fun findLength(nums1: IntArray, nums2: IntArray): Int {
        var maxLength = 0

        slideTargetByTailOverDestination(
            target = nums1,
            destination = nums2
        ) { overlapLength, startIndexByTarget, startIndexByDestination ->
            val currMaxLength = findMaxLengthHavingSameElements(
                first = nums1,
                second = nums2,
                lengthOfScope = overlapLength,
                startIndexOfFirst = startIndexByTarget,
                startIndexOfSecond = startIndexByDestination
            )
            maxLength = maxOf(maxLength, currMaxLength)
        }

        slideTargetByTailOverDestination(
            target = nums2,
            destination = nums1
        ) { overlapLength, startIndexByTarget, startIndexByDestination ->
            val currMaxLength = findMaxLengthHavingSameElements(
                first = nums2,
                second = nums1,
                lengthOfScope = overlapLength,
                startIndexOfFirst = startIndexByTarget,
                startIndexOfSecond = startIndexByDestination
            )
            maxLength = maxOf(maxLength, currMaxLength)
        }

        return maxLength
    }

    private fun slideTargetByTailOverDestination(
        target: IntArray,
        destination: IntArray,
        sideEffectPerOverlap: (
            overlapLength: Int,
            startIndexByTarget: Int,
            startIndexByDestination: Int
        ) -> Unit
    ) {
        for (index in destination.indices) {
            val overlapLength = minOf(index + 1, target.size)
            val startIndexByTarget = target.size - overlapLength
            val startIndexByDestination = index - overlapLength + 1
            sideEffectPerOverlap(overlapLength, startIndexByTarget, startIndexByDestination)
        }
    }

    private fun findMaxLengthHavingSameElements(
        first: IntArray,
        second: IntArray,
        lengthOfScope: Int,
        startIndexOfFirst: Int,
        startIndexOfSecond: Int
    ): Int {
        var maxLength = 0
        var currMaxLength = 0
        for (index in 0 until lengthOfScope) {
            val num1 = first[startIndexOfFirst + index]
            val num2 = second[startIndexOfSecond + index]
            if (num1 == num2) {
                currMaxLength++
                maxLength = maxOf(maxLength, currMaxLength)
            } else {
                currMaxLength = 0
            }
        }
        return maxLength
    }
}