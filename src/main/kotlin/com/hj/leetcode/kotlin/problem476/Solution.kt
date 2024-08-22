package com.hj.leetcode.kotlin.problem476

/**
 * LeetCode page: [476. Number Complement](https://leetcode.com/problems/number-complement/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun findComplement(num: Int): Int {
        var mask = num // For example, mask for b11010 is b11111
        mask = mask or (mask shr 1)
        mask = mask or (mask shr 2)
        mask = mask or (mask shr 4)
        mask = mask or (mask shr 8)
        mask = mask or (mask shr 16)
        return num xor mask
    }
}
