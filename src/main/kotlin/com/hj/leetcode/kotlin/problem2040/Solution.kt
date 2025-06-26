package com.hj.leetcode.kotlin.problem2040

/**
 * LeetCode page: [2040. Kth Smallest Product of Two Sorted Arrays](https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays/);
 */
class Solution {
    // Complexity:
    // Time O((N+M)LogR) and Space O(1) where N is the length of nums1, M
    // is the length of nums2, and R is the range of products produced by
    // nums1 and nums2.
    fun kthSmallestProduct(
        nums1: IntArray,
        nums2: IntArray,
        k: Long,
    ): Long {
        val (pos1, neg1) = countSigned(nums1)
        val (pos2, neg2) = countSigned(nums2)

        val allProducts = 1L * nums1.size * nums2.size
        val posProducts = 1L * pos1 * pos2 + 1L * neg1 * neg2
        val negProducts = 1L * pos1 * neg2 + 1L * neg1 * pos2

        if (negProducts < k && k <= allProducts - posProducts) {
            return 0
        }

        val extremes =
            longArrayOf(
                1L * nums1[0] * nums2[0],
                1L * nums1[nums1.size - 1] * nums2[nums2.size - 1],
                1L * nums1[0] * nums2[nums2.size - 1],
                1L * nums1[nums1.size - 1] * nums2[0],
            )

        // Binary search the kth smallest product, which is in the
        // range [left, right+1].
        var left = extremes.min()
        var right = extremes.max()

        while (left <= right) {
            val mid = left + (right - left) / 2
            if (countProductNotLargerThanP(nums1, nums2, mid, pos1, neg1, pos2, neg2) < k) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }

    // Returns the number of positives and negatives in the array.
    private fun countSigned(nums: IntArray): Pair<Int, Int> {
        var positives = 0
        var negatives = 0
        for (num in nums) {
            if (num > 0) {
                positives++
            } else if (num < 0) {
                negatives++
            }
        }
        return positives to negatives
    }

    // Returns the number of products that are not larger than p given
    // the nums1, nums2, and their metadata.
    private fun countProductNotLargerThanP(
        nums1: IntArray,
        nums2: IntArray,
        p: Long,
        pos1: Int,
        neg1: Int,
        pos2: Int,
        neg2: Int,
    ): Long {
        var result: Long
        val allProducts = 1L * nums1.size * nums2.size
        if (p < 0) {
            result = 0L

            // positives from nums1 and negatives from nums2
            if (pos1 > 0 && neg2 > 0) {
                var i2 = 0
                for (i1 in (nums1.size - pos1)..<nums1.size) {
                    while (i2 < neg2 && 1L * nums1[i1] * nums2[i2] <= p) {
                        i2++
                    }
                    result += i2
                }
            }

            // negatives from nums1 and positives from nums2
            if (neg1 > 0 && pos2 > 0) {
                var i1 = 0
                for (i2 in (nums2.size - pos2)..<nums2.size) {
                    while (i1 < neg1 && 1L * nums1[i1] * nums2[i2] <= p) {
                        i1++
                    }
                    result += i1
                }
            }
        } else if (p > 0) {
            result = allProducts - 1L * neg1 * neg2 - 1L * pos1 * pos2

            // negatives from nums1 and negatives from nums2
            if (neg1 > 0 && neg2 > 0) {
                var i1 = 0
                for (i2 in (neg2 - 1) downTo 0) {
                    while (i1 < neg1 && 1L * nums1[i1] * nums2[i2] > p) {
                        i1++
                    }
                    result += neg1 - i1
                }
            }

            // positives from nums1 and positives from nums2
            if (pos1 > 0 && pos2 > 0) {
                var i1 = nums1.size - 1
                for (i2 in (nums2.size - pos2)..<nums2.size) {
                    while (i1 >= nums1.size - pos1 && 1L * nums1[i1] * nums2[i2] > p) {
                        i1--
                    }
                    result += i1 - nums1.size + pos1 + 1
                }
            }
        } else {
            result = allProducts - 1L * neg1 * neg2 - 1L * pos1 * pos2
        }
        return result
    }
}
