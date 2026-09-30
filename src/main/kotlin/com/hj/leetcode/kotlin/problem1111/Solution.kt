package com.hj.leetcode.kotlin.problem1111

/**
 * LeetCode page: [1111. Maximum Nesting Depth of Two Valid Parentheses Strings](https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Auxiliary Space O(1) where N is the length of seq.
    fun maxDepthAfterSplit(seq: String): IntArray {
        // Let L be the number of '(' in seq[..=i]. The nesting depth at index i
        // is 2L-i-1. Therefore, the parities of the nesting depth and the index
        // are always different.
        return IntArray(seq.length) { i -> i and 1 xor (seq[i] - '(') }
    }

    //  fun maxDepthAfterSplit(seq: String): IntArray {
    //      var depth = 0
    //      return IntArray(seq.length) { i ->
    //          val c = seq[i] - '('
    //          depth += 1 - (c shl 1)
    //          depth and 1 xor c
    //      }
    //  }
}
