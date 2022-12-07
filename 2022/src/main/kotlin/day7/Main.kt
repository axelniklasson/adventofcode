package day7

import util.FileUtils

private const val inputName = "day7_input.txt"

private fun parseCommand(line: String): Command {
    val parts = line.split(" ")
    if (parts[1] == "ls") return Command(Command.Type.LIST)
    return Command(Command.Type.CHANGE_DIRECTORY, parts[2])
}

private fun getPath(currentPath: String, name: String): String {
    return if (currentPath == "/") {
        "/$name"
    } else {
        "$currentPath/$name"
    }
}

// https://adventofcode.com/2022/day/7
fun partOne() {
    val lines = FileUtils.readLines(inputName)
    var currentDirectory: Directory? = null
    val dirSize = 100000L
    var totalSizeOfSmallDirs = 0L

    var i = 0
    while (i <= lines.size - 1) {
        val line = lines[i]
        if (line.first() == '$') {
            val command = parseCommand(line)
            if (command.type == Command.Type.CHANGE_DIRECTORY) {
                when (command.arg) {
                    "/" -> {
                        currentDirectory = Directory("/", null)
                    }
                    ".." -> {
                        val size = currentDirectory?.size() ?: throw IllegalStateException("Size could not be calculated for dir ${currentDirectory?.path}")
                        if (size <= dirSize) totalSizeOfSmallDirs += size
                        currentDirectory = currentDirectory.parent
                    }
                    else -> {
                        currentDirectory = currentDirectory?.subDirectories?.first { it.path.endsWith("/${command.arg}") }
                            ?: Directory(getPath(currentDirectory?.path ?: "", command.arg ?: ""), currentDirectory)
                    }
                }
            } else {
                // parse all output lines
                if (currentDirectory == null) throw IllegalStateException("Current directory is null")

                while (i < lines.size - 1 && lines[i+1].first() != '$') {
                    val nextLine = lines[i+1]
                    val parts = nextLine.split(" ")
                    val path = getPath(currentDirectory.path, parts[1])
                    if (nextLine.startsWith("dir")) {
                        currentDirectory.subDirectories.add(Directory(path, currentDirectory))
                    } else {
                        currentDirectory.files.add(File(path, parts[0].toLong()))
                    }
                    i++
                }
            }
        }
        i++
    }

    println("Total size of dirs with size < 100000 is: $totalSizeOfSmallDirs")
}

// https://adventofcode.com/2022/day/7#part2
fun partTwo() {
    val totalSpace = 70000000L
    val unusedSpaceNeeded = 30000000L

    val lines = FileUtils.readLines(inputName)
    var currentDirectory: Directory? = null
    var dirSizes = mutableListOf<Long>()
    var root: Directory? = null

    var i = 0
    while (i <= lines.size - 1) {
        val line = lines[i]
        if (line.first() == '$') {
            val command = parseCommand(line)
            if (command.type == Command.Type.CHANGE_DIRECTORY) {
                when (command.arg) {
                    "/" -> {
                        currentDirectory = Directory("/", null)
                        root = currentDirectory
                    }
                    ".." -> {
                        dirSizes.add(currentDirectory?.size() ?: throw IllegalStateException("Size could not be calculated for dir ${currentDirectory?.path}"))
                        currentDirectory = currentDirectory.parent
                    }
                    else -> {
                        currentDirectory = currentDirectory?.subDirectories?.first { it.path.endsWith("/${command.arg}") }
                            ?: Directory(getPath(currentDirectory?.path ?: "", command.arg ?: ""), currentDirectory)
                    }
                }
            } else {
                // parse all output lines
                if (currentDirectory == null) throw IllegalStateException("Current directory is null")

                while (i < lines.size - 1 && lines[i+1].first() != '$') {
                    val nextLine = lines[i+1]
                    val parts = nextLine.split(" ")
                    val path = getPath(currentDirectory.path, parts[1])
                    if (nextLine.startsWith("dir")) {
                        currentDirectory.subDirectories.add(Directory(path, currentDirectory))
                    } else {
                        currentDirectory.files.add(File(path, parts[0].toLong()))
                    }
                    i++
                }
            }
        }
        i++
    }

    val freeSpace = totalSpace - (root?.size() ?: throw IllegalStateException("Root is undefined"))
    val spaceRequired = unusedSpaceNeeded - freeSpace
    dirSizes.sort()
    val sizeOfDirToDelete = dirSizes.first { it >= spaceRequired }

    println("Total size of dir to delete: $sizeOfDirToDelete")
}

fun main() {
    partOne()
    partTwo()
}