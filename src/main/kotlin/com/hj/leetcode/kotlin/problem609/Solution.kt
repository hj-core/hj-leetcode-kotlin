package com.hj.leetcode.kotlin.problem609

/**
 * LeetCode page: [609. Find Duplicate File in System](https://leetcode.com/problems/find-duplicate-file-in-system/);
 */
class Solution {
    /* Complexity:
     * Time O(NM) and Aux_Space O(NM) where N is size of paths and M is the average length of path;
     */
    fun findDuplicate(paths: Array<String>): List<List<String>> {
        val directoryTable = createPathIndexToDirectory(paths)
        val contentTable = createContentToFiles(paths, directoryTable)
        return getFilePathsPerDuplicateContent(contentTable, directoryTable)
    }

    private fun createPathIndexToDirectory(paths: Array<String>): Map<Int, String> {
        val table = hashMapOf<Int, String>()
        for ((index, path) in paths.withIndex()) {
            val directory = getDirectoryOfPath(path)
            table[index] = directory
        }
        return table
    }

    private fun getDirectoryOfPath(path: String): String {
        val directory = StringBuilder()
        var index = 0
        while (index < path.length) {
            val char = path[index]
            if (char == ' ') break

            directory.append(char)
            index++
        }
        return directory.toString()
    }

    private fun createContentToFiles(
        paths: Array<String>,
        pathIndexToDirectory: Map<Int, String>
    ): Map<String, List<File>> {
        val table = hashMapOf<String, MutableList<File>>()
        for ((index, path) in paths.withIndex()) {
            val directory = pathIndexToDirectory[index]
            checkNotNull(directory)
            updateContentToFiles(path, index, directory.length, table)
        }
        return table
    }

    private data class File(val name: String, val pathIndex: Int)

    private fun updateContentToFiles(
        path: String,
        pathIndex: Int,
        directoryLength: Int,
        contentToFiles: MutableMap<String, MutableList<File>>
    ) {
        var index = getStartIndexOfFirstFile(directoryLength)
        val builder = StringBuilder()
        var fileName = ""

        while (index < path.length) {
            when (val char = path[index]) {
                ' ' -> builder.clear()
                '(' -> {
                    fileName = builder.toString()
                    builder.clear()
                }
                ')' -> {
                    val content = builder.toString()
                    val file = File(fileName, pathIndex)
                    contentToFiles
                        .getOrPut(content) { mutableListOf() }
                        .add(file)
                    builder.clear()
                }
                else -> builder.append(char)
            }
            index++
        }
    }

    private fun getStartIndexOfFirstFile(directoryLength: Int) = directoryLength + 1

    private fun getFilePathsPerDuplicateContent(
        contentToFiles: Map<String, List<File>>,
        pathIndexToDirectory: Map<Int, String>
    ): List<List<String>> {
        val container = mutableListOf<List<String>>()
        for ((_, files) in contentToFiles) {
            val hasDuplicate = files.size > 1
            if (hasDuplicate) {
                val paths = getPathsOfFiles(files, pathIndexToDirectory)
                container.add(paths)
            }
        }
        return container
    }

    private fun getPathsOfFiles(
        files: List<File>,
        pathIndexToDirectory: Map<Int, String>
    ): List<String> {
        val paths = files.map {  file ->
            val directory = pathIndexToDirectory[file.pathIndex]
            checkNotNull(directory)
            getPathOfFile(file, directory)
        }
        return paths
    }

    private fun getPathOfFile(file: File, directory: String): String {
        val pathLength = directory.length + 1 + file.name.length
        val path = StringBuilder(pathLength)
            .append(directory)
            .append("/")
            .append(file.name)
        return path.toString()
    }
}