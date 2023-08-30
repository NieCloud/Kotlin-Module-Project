class SelectNoteScreen(private val archive: Archive) : Screen() {

    private val MENU_WHEN_NOTES_ARE_EMPTY = "Введите\n2. Создать новый архив\n4. Выйти из программы"
    private val MENU_WHEN_NOTES_ARE_FILLED = "Введите\n1. Выбрать архив\n2. Создать новый архив\n3. Посмотреть список всех архивов\n4. Выйти из программы"

    override fun show() {

        val input = WorkWithUserInput.getUserInput(emptyMenu = MENU_WHEN_NOTES_ARE_EMPTY, filledMenu = MENU_WHEN_NOTES_ARE_FILLED, isEmptyDataSet = archive.notes.isEmpty())

        when (input.toInt()) {
            1 -> {
                val noteName = WorkWithUserInput.getUserInput("Введите название заметки:")
                val selectedNote = archive.notes.find { it.name == noteName }
                if (selectedNote != null) {
                    println("Заметка выбрана!")
                    nextScreen = NoteScreen(selectedNote)
                    nextScreen?.previousScreen = this
                    nextScreen?.show()
                } else {
                    WorkWithUserInput.showError("Заметка '$noteName' не существует")
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
                WorkWithUserInput.showError("Неверный ввод! Пожалуйста, введите корректную команду!")
                show()
            }
        }
    }
}
