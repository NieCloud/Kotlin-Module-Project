class SelectNoteScreen(private val archive: Archive) : Screen() {

    private val MENU_HEADER = "Введите номер:\n"

    override fun show() {
        val input = WorkWithUserInput.getUserInput(
            emptyMenu = getMenuWhenEmpty(),
            filledMenu = getMenuWithNotes(),
            isEmptyDataSet = archive.notes.isEmpty()
        )

        val userInput = input.toIntOrNull()
        when (userInput) {
            1 -> goToNotesCreationMenu()
            in 2 until 2 + archive.notes.size -> chooseNote(userInput!! - 1)
            2 + archive.notes.size -> previousScreen?.show()
            else -> checkNotesForInput(input)
        }
    }

    private fun getMenuWhenEmpty() = "$MENU_HEADER 1. Создать новую заметку\n2. Вернуться в меню Архивов"

    private fun getMenuWithNotes(): String {
        val menuBuilder = StringBuilder(MENU_HEADER)
        menuBuilder.append("1. Создать новую заметку\n")
        archive.notes.forEachIndexed { index, note ->
            menuBuilder.append("${index + 2}. Это моя уже созданная заметка: ${note.name}\n")
        }
        menuBuilder.append("${archive.notes.size + 2}. Вернуться в меню Архивов")
        return menuBuilder.toString()
    }


    private fun checkNotesForInput(input: String) {
        WorkWithUserInput.checkForErrorIfNotCorrectInteger(input)
        show()
    }

    private fun goToNotesCreationMenu() {
        val nextScreen = CreateNoteScreen(archive)
        nextScreen.previousScreen = this
        nextScreen.show()
    }

    private fun chooseNote(noteIndex: Int) {
        val selectedNote = archive.notes.getOrNull(noteIndex - 1)
        if (selectedNote != null) {
            println("Заметка ${selectedNote.name} выбрана!")
            val nextScreen = NoteScreen(selectedNote)
            nextScreen.previousScreen = this
            nextScreen.show()
        } else {
            WorkWithUserInput.showError("Заметки с номером '$noteIndex' не существует")
            show()
        }
    }
}
