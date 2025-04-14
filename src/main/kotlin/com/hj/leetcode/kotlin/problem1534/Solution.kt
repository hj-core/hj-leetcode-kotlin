package com.hj.leetcode.kotlin.problem1534

/**
 * LeetCode page: [1534. Count Good Triplets](https://leetcode.com/problems/count-good-triplets/);
 */
class Solution {
    // Let's define sortedArr at state j as the array that consists of three
    // parts: sorted(arr[0..<j]), arr[j], and sorted(arr[j+1..<len(arr)]).

    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of arr.
    fun countGoodTriplets(
        arr: IntArray,
        a: Int,
        b: Int,
        c: Int,
    ): Int {
        val sortedArr = computeSortedArrAtLastIndex(arr)
        var result = 0
        for (j in sortedArr.lastIndex - 1 downTo 1) {
            transitionSorted(sortedArr, arr, j)
            result += countGoodTripletsAtJ(sortedArr, j, a, b, c)
        }
        return result
    }

    private fun computeSortedArrAtLastIndex(arr: IntArray): IntArray {
        val result = arr.clone()
        result[result.lastIndex] = Int.MAX_VALUE

        result.sort()
        result[result.lastIndex] = arr[arr.lastIndex]
        return result
    }

    // transitionSorted transitions the sortedArr from state j+1 to state j.
    private fun transitionSorted(
        sortedArr: IntArray,
        arr: IntArray,
        j: Int,
    ) {
        // Move arr[j+1] to the correct position.
        var p = j + 1
        while (p < sortedArr.lastIndex && sortedArr[p + 1] < sortedArr[p]) {
            sortedArr[p] = sortedArr[p + 1].also { sortedArr[p + 1] = sortedArr[p] }
            p++
        }

        // Find out arr[j] and move it to the index j
        var q = 0
        while (sortedArr[q] != arr[j]) {
            q++
        }
        while (q < j) {
            sortedArr[q] = sortedArr[q + 1].also { sortedArr[q + 1] = sortedArr[q] }
            q++
        }
    }

    // countGoodTripletsAtJ returns the number of good triplets with mid at j,
    // given the sortedArr at state j.
    private fun countGoodTripletsAtJ(
        sortedArr: IntArray,
        j: Int,
        a: Int,
        b: Int,
        c: Int,
    ): Int {
        var i = j - 1
        while (0 <= i && sortedArr[j] + a < sortedArr[i]) {
            i--
        }

        // The valid ks for the current i is in the range (minK, maxK].
        var maxK = sortedArr.lastIndex
        var minK = sortedArr.lastIndex
        var result = 0

        // Hint: The range of k limited by j is fixed, while the range limited
        // by i is sliding.
        while (0 <= i && sortedArr[j] - a <= sortedArr[i]) {
            val kValueHigh = minOf(sortedArr[i] + c, sortedArr[j] + b)
            while (j < maxK && kValueHigh < sortedArr[maxK]) {
                maxK--
            }
            if (maxK == j) {
                break
            }

            minK = minOf(minK, maxK)
            val kValueLow = maxOf(sortedArr[i] - c, sortedArr[j] - b)
            while (j < minK && kValueLow <= sortedArr[minK]) {
                minK--
            }

            result += maxK - minK
            i--
        }
        return result
    }
}
