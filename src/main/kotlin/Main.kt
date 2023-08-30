fun main() {
    println("Добро пожаловать в приложение по хранению заметок!")
    val archives = mutableListOf<Archive>()
    SelectArchiveScreen(archives).show()
}
