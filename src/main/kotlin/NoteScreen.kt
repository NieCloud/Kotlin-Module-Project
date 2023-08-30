class NoteScreen(private val note: Note) : Screen() {
    override fun show() {
        println("Заметка:")
        println("Название заметки: ${note.name}")
        println("Содержание заметки: ${note.content}")
        val input = WorkWithUserInput.getUserInput("\nВведите\n1. Вернуться назад\n2. Отредактировать содержимое заметки")
        when (input.toIntOrNull()) {
            1 -> previousScreen?.show() ?: exit()
            2 -> {
                val newContent = WorkWithUserInput.getUserInput("Введите новое содержание заметки:")

                if (newContent.isEmpty()) {
                    WorkWithUserInput.showError("Ввод не может быть пустым!")
                    show()
                } else {
                    note.content = newContent
                    println("Содержание заметки обновлено!")
                    show()
                }
            }
            else -> {
                WorkWithUserInput.showError("Неверный ввод! Пожалуйста, введите корректную команду!")
                show()
            }
        }
    }
}
