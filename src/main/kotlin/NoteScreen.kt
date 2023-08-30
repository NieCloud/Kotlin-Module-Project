class NoteScreen(private val note: Note) : Screen() {
    override fun show() {
        println("Заметка:")
        println("Название заметки: ${note.name}")
        println("Содержание заметки: ${note.content}")
        val input = WorkWithUserInput.getUserInput("\nВведите 1 чтобы вернуться или 2 чтобы отредактировать содержимое заметки")
        when (input.toInt()) {
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
