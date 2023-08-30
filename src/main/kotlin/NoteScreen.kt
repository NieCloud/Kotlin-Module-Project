class NoteScreen(private val note: Note) : Screen() {
    override fun show() {
        println("Заметка:")
        println("Название заметки: ${note.name}")
        println("Содержание заметки: ${note.content}")
        val input = getUserInput("\nВведите 1 чтобы вернуться или 2 чтобы отредактировать содержимое заметки")
        when (input?.toIntOrNull()) {
            1 -> previousScreen?.show() ?: exit()
            2 -> {
                val newContent = getUserInput("Введите новое содержание заметки:")
                note.content = newContent.toString()
                println("Содержание заметки обновлено!")
                show()
            }
            else -> {
                showError("Неверный ввод! Пожалуйста, введите корректную команду!")
                show()
            }
        }
    }
}
