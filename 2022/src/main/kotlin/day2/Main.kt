package day2

import util.FileUtils.readLines

private const val inputName = "day2_input.txt"

private fun getSelection(s: String): Selection {
    return when {
        (s == "A" || s == "X") -> Selection.ROCK
        (s == "B" || s == "Y") -> Selection.PAPER
        (s == "C" || s == "Z") -> Selection.SCISSORS
        else -> throw IllegalStateException("Can't parse $s as selection")
    }
}

private fun getScoreForSelection(selection: Selection): Int {
    return when (selection) {
        Selection.ROCK -> 1
        Selection.PAPER -> 2
        Selection.SCISSORS -> 3
    }
}

private fun getScoreForRound(opponentSelection: Selection, ownSelection: Selection): Int {
    if (opponentSelection == ownSelection) return 3
    if (ownSelection == Selection.ROCK && opponentSelection == Selection.SCISSORS) return 6
    if (ownSelection == Selection.SCISSORS && opponentSelection == Selection.PAPER) return 6
    if (ownSelection == Selection.PAPER && opponentSelection == Selection.ROCK) return 6
    return 0
}

private fun getOutcome(s: String): Outcome {
    return when (s) {
        "X" -> Outcome.LOSE
        "Y" -> Outcome.DRAW
        "Z" -> Outcome.WIN
        else -> throw IllegalStateException("Can't parse string as outcome $s")
    }
}

private fun calculateSelection(opponentSelection: Selection, outcome: Outcome): Selection {
    if (outcome == Outcome.DRAW) return opponentSelection

    val shouldWin = outcome == Outcome.WIN
    return when (opponentSelection) {
        Selection.ROCK -> if (shouldWin) Selection.PAPER else Selection.SCISSORS
        Selection.PAPER -> if (shouldWin) Selection.SCISSORS else Selection.ROCK
        Selection.SCISSORS -> if (shouldWin) Selection.ROCK else Selection.PAPER
    }
}

// https://adventofcode.com/2022/day/2
fun partOne() {
    val lines = readLines(inputName)
    var totalScore = 0

    lines.forEach {
        val parts = it.split(" ")
        val selections = parts.map { part -> getSelection(part) }
        totalScore += getScoreForRound(selections[0], selections[1]) + getScoreForSelection(selections[1])
    }

    println("Total score is $totalScore")
}

// https://adventofcode.com/2022/day/2#part2
fun partTwo() {
    val lines = readLines(inputName)
    var totalScore = 0

    lines.forEach {
        val parts = it.split(" ")
        val opponentSelection = getSelection(parts[0])
        val outcome = getOutcome(parts[1])
        val losingSelection = calculateSelection(opponentSelection, outcome)
        totalScore += getScoreForRound(opponentSelection, losingSelection) + getScoreForSelection(losingSelection)
    }

    println("Total score is $totalScore")
}

fun main() {
    partOne()
    partTwo()
}