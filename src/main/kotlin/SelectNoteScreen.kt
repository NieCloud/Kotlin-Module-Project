class SelectNoteScreen(private val archive: Archive) : Screen() {
    override fun show() {
        val input = getUserInput("Введите 1 чтобы выбрать заметку, 2 чтобы создать новую заметку или 3 чтобы вернуться назад")

        when (input?.toIntOrNull()) {
            1 -> {
                val noteName = getUserInput("Введите название заметки:")
                val selectedNote = archive.notes.find { it.name == noteName }
                if (selectedNote != null) {
                    println("Заметка выбрана!")
                    nextScreen = NoteScreen(selectedNote)
                    nextScreen?.previousScreen = this
                    nextScreen?.show()
                } else {
                    showError("Заметка '$noteName' не существует")
                    show()
                }
            }
            2 -> {
                nextScreen = CreateNoteScreen(archive)
                nextScreen?.previousScreen = this
                nextScreen?.show()
            }
            3 -> previousScreen?.show()
            else -> {
                showError("Неверный ввод! Пожалуйста, введите корректную команду!")
                show()
            }
        }
    }
}
