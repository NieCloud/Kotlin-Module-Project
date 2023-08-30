class SelectNoteScreen(private val archive: Archive) : Screen() {
    override fun show() {

        var input: String?

        if (archive.notes.isEmpty()) {
            input = WorkWithUserInput.getUserInput("Введите\n2. Создать новую заметку\n4.Вернуться назад")
        } else {
            input = WorkWithUserInput.getUserInput("Введите\n1. Выбрать заметку\n2. Создать новую заметку\n3.Посмотреть все заметки\n4.Вернуться назад")
        }

        if (input?.isEmpty() == true) {
            showError("Ввод не может быть пустым!")
            show()
        }

        when (input?.toIntOrNull()) {
            1 -> {
                val noteName = WorkWithUserInput.getUserInput("Введите название заметки:")
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
            3 -> {
                println(archive.notes.joinToString(prefix = "[", postfix = "]"))
                show()
            }

            4 -> previousScreen?.show()
            else -> {
                showError("Неверный ввод! Пожалуйста, введите корректную команду!")
                show()
            }
        }
    }
}
