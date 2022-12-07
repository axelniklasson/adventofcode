package day7

data class Command(
    val type: Type,
    val arg: String? = null
) {
    enum class Type {
        CHANGE_DIRECTORY,
        LIST
    }
}
