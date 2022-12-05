package day1

import util.FileUtils.readLines

private const val inputName = "day1_input.txt"

// https://adventofcode.com/2022/day/1
fun partOne() {
    val lines = readLines(inputName)
    var maxCalories = 0

    var running = 0
    lines.forEach {
        if (it == "") {
            maxCalories = maxOf(maxCalories, running)
            running = 0
        } else {
            running += it.toInt()
        }
    }

    println("max calories carried by one elf: $maxCalories")
}

// https://adventofcode.com/2022/day/1#part2
fun partTwo() {
    val lines = readLines(inputName)
    var maxCalories = listOf(0, 0, 0)

    var running = 0
    lines.forEach {
        if (it == "") {
            var idxToReplace = -1;
            maxCalories.forEachIndexed { idx, element ->
                if (running > element) {
                    if (idxToReplace < 0) idxToReplace = idx
                    if (element < maxCalories[idxToReplace]) idxToReplace = idx
                }
            }

            if (idxToReplace > -1) {
                maxCalories = maxCalories.mapIndexed { index, element ->
                    if (index == idxToReplace) {
                        running
                    } else {
                        element
                    }

                }
            }
            running = 0
        } else {
            running += it.toInt()
        }
    }

    println("max calories carried by three elves: ${maxCalories.sum()}")
}

fun main() {
    partOne()
    partTwo()
}