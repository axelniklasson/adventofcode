package day5

import util.FileUtils
import java.util.ArrayDeque

private const val inputName = "day5_input.txt"

private fun parseStacks(): List<ArrayDeque<Char>> {
    val stacks: MutableList<ArrayDeque<Char>> = mutableListOf()
    val lines = FileUtils.readLines(inputName)

    var stackCount = lines.mapIndexed{ idx, el -> Pair(idx, el)}.first { !it.second.contains('[') }.component1()
    for (i in 0..stackCount) stacks.add(ArrayDeque<Char>())

    for (i in 0..stackCount) {
        val line = lines[i]

        for (i in line.indices) {
            if (line[i] == '[') {
                val idx = i / 4
                stacks[idx].addLast(line[i+1])
            }
        }
    }
    return stacks
}

private fun parseMoves(): List<Move> {
    val lines = FileUtils.readLines(inputName)
    val moves: MutableList<Move> = mutableListOf()

    lines.forEach {
        if (it.isNotEmpty() && it.substring(0, 4) == "move") {
            val parts = it.split(" ")
            moves.add(Move(parts[1].toInt(), parts[3].toInt()-1, parts[5].toInt()-1))
        }
    }

    return moves
}

// https://adventofcode.com/2022/day/5
fun partOne() {
    val stacks = parseStacks()
    val moves = parseMoves()

    moves.forEach {
        for (i in 0 until it.count) {
            stacks[it.from].poll()?.let { el -> stacks[it.to].push(el) }
        }
    }

    println("Top crates are: ${stacks.map { it.peek() }.joinToString("")}")
}

// https://adventofcode.com/2022/day/5#part2
fun partTwo() {
    val stacks = parseStacks()
    val moves = parseMoves()

    moves.forEach {
        val elsToMove = stacks[it.from].take(it.count)
        elsToMove.reversed().forEach { el ->
            stacks[it.to].push(el)
            stacks[it.from].pop()
        }
    }

    println("Top crates are: ${stacks.map { it.peek() }.joinToString("")}")
}

fun main() {
    partOne()
    partTwo()
}