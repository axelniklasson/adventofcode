package day8

import util.FileUtils

private const val inputName = "day8_input.txt"

private fun buildMatrix(lines: List<String>): Pair<List<List<Int>>, List<List<Int>>> {
    val rows = mutableListOf<List<Int>>()
    val cols = mutableListOf<MutableList<Int>>()

    for (i in 0 until lines[0].length) {
        cols.add(mutableListOf<Int>())
    }

    lines.forEach { line ->
        val heights = line.map { it.toString().toInt() }
        rows.add(heights)
        heights.forEachIndexed { idx, height ->
            cols[idx].add(height)
        }
    }

    return Pair(rows, cols)
}

private fun isVisible(height: Int, coordinate: Pair<Int, Int>, row: List<Int>, col: List<Int>): Boolean {
    if (row.subList(0, coordinate.second).all { it < height }) return true
    if (row.subList(coordinate.second + 1, row.size).all { it < height }) return true
    if (col.subList(0, coordinate.first).all { it < height }) return true
    if (col.subList(coordinate.first + 1, col.size).all { it < height }) return true

    return false
}

private fun getScenicScore(height: Int, coordinate: Pair<Int, Int>, row: List<Int>, col: List<Int>): Int {
    // check up
    var score = 0
    for (i in coordinate.first-1 downTo 0) {
        score++
        if (col[i] >= height) break
    }

    // check right
    var running = 0
    for (i in coordinate.second+1 until row.size) {
        running++
        if (row[i] >= height) break
    }
    score *= running

    // check down
    running = 0
    for (i in coordinate.first+1 until row.size) {
        running++
        if (col[i] >= height) break
    }
    score *= running

    // check left
    running = 0
    for (i in coordinate.second-1 downTo 0) {
        running++
        if (row[i] >= height) break
    }

    return score * running
}

// https://adventofcode.com/2022/day/8
fun partOne() {
    val lines = FileUtils.readLines(inputName)
    val (rows, cols) = buildMatrix(lines)
    var visibleCount = 0

    rows.forEachIndexed { i, row ->
        row.forEachIndexed { j, height ->
            if (i == 0 || i == rows.size - 1 || j == 0 || j == row.size - 1) {
                visibleCount++
            } else {
                if (isVisible(height, Pair(i, j), row, cols[j])) {
                    visibleCount++
                }
            }
        }
    }

    println("$visibleCount trees are visible")
}

// https://adventofcode.com/2022/day/8#part2
fun partTwo() {
    val lines = FileUtils.readLines(inputName)
    val (rows, cols) = buildMatrix(lines)
    var highestScenicScore = 0

    rows.forEachIndexed { i, row ->
        row.forEachIndexed { j, height ->
            if (i == 0 || i == rows.size - 1 || j == 0 || j == row.size - 1) {
                return@forEachIndexed
            } else {
                highestScenicScore = maxOf(highestScenicScore, getScenicScore(height, Pair(i, j), row, cols[j]))
            }
        }
    }

    println("highest scenic score is: $highestScenicScore")
}

fun main() {
    partOne()
    partTwo()
}