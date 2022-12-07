package day7

data class Directory(
    val path: String,
    val parent: Directory?,
    val subDirectories: MutableList<Directory> = mutableListOf(),
    val files: MutableList<File> = mutableListOf(),
) {
    override fun toString(): String = this.path
}

fun Directory.size(): Long {
    return files.sumOf { it.size } + subDirectories.sumOf { it.size() }
}