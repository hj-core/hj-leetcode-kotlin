package com.hj.leetcode.kotlin.problem1472

/**
 * LeetCode page: [1472. Design Browser History](https://leetcode.com/problems/design-browser-history/);
 */
class BrowserHistory2(homepage: String) {

    private var currPage = homepage
    private val prevPages = ArrayDeque<String>()
    private var nextPages = ArrayDeque<String>()

    /* Complexity:
     * Time O(1) and Space O(L) where L is the length of url;
     */
    fun visit(url: String) {
        prevPages.addLast(currPage)
        currPage = url
        nextPages = ArrayDeque()
    }

    /* Complexity:
     * Time O(steps) and Space O(1);
     */
    fun back(steps: Int): String {
        var remainingSteps = steps
        while (remainingSteps > 0 && prevPages.isNotEmpty()) {
            remainingSteps--
            nextPages.addLast(currPage)
            currPage = prevPages.removeLast()
        }
        return currPage
    }

    /* Complexity:
     * Time O(steps) and Space O(1);
     */
    fun forward(steps: Int): String {
        var remainingSteps = steps
        while (remainingSteps > 0 && nextPages.isNotEmpty()) {
            remainingSteps--
            prevPages.addLast(currPage)
            currPage = nextPages.removeLast()
        }
        return currPage
    }
}