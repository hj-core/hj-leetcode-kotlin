package com.hj.leetcode.kotlin.problem1233

/**
 * LeetCode page: [1233. Remove Sub-Folders from the Filesystem](https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/);
 */
class Solution2 {
    /* Complexity:
     * Time O(MNLogN) and Space O(NM) where N is the length of folder and
     * M is the longest folder length in folder.
     */
    fun removeSubfolders(folder: Array<String>): List<String> {
        val sortedFolder = folder.sortedArray()
        val result = mutableListOf(sortedFolder[0])

        for (i in 1..<sortedFolder.size) {
            if (!isSubFolder(sortedFolder[i], result.last())) {
                result.add(sortedFolder[i])
            }
        }
        return result
    }

    // Returns whether fa is a subfolder of fb. Note that A folder is not
    // considered a subfolder of itself.
    private fun isSubFolder(
        fa: String,
        fb: String,
    ): Boolean = fa.length > fb.length && fa[fb.length] == '/' && fa.startsWith(fb)
}
