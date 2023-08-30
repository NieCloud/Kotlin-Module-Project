class SelectNoteScreen(private val archive: Archive) : Screen() {

    private val MENU_WHEN_NOTES_ARE_EMPTY = "Введите\n2. Создать новую заметку\n4. Вернуться в меню Архивов"
    private val MENU_WHEN_NOTES_ARE_FILLED = "Введите\n1. Выбрать заметку\n2. Cоздать новую заметку\n3. Посмотреть все заметки\n4. Вернуться в меню Архивов"

    override fun show() {

        val input = WorkWithUserInput.getUserInput(emptyMenu = MENU_WHEN_NOTES_ARE_EMPTY, filledMenu = MENU_WHEN_NOTES_ARE_FILLED, isEmptyDataSet = archive.notes.isEmpty())

        when (input.toIntOrNull()) {
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
                val notesString = archive.notes.joinToString("\n", prefix = "[", postfix = "]")
                println("Заметки:\n$notesString")
                show()
            }
            4 ->
                previousScreen?.show()
            else -> {
                WorkWithUserInput.checkForErrorIfNotCorrectInteger(input)
                show()
            }
        }
    }
}
