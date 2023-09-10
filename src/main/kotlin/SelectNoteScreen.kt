class SelectNoteScreen(private val archive: Archive) : Screen() {

    private val MENU_WHEN_NOTES_ARE_EMPTY = "Введите номер:\n1. Создать новую заметку\n2. Вернуться в меню Архивов"
    private val MENU_WHEN_NOTES_ARE_FILLED = "Введите номер:\n1. Выбрать заметку\n2. Cоздать новую заметку\n3. Посмотреть все заметки\n4. Вернуться в меню Архивов"

    override fun show() {

        val input = WorkWithUserInput.getUserInput(emptyMenu = MENU_WHEN_NOTES_ARE_EMPTY, filledMenu = MENU_WHEN_NOTES_ARE_FILLED, isEmptyDataSet = archive.notes.isEmpty())

        if (archive.notes.isEmpty()) {
            when (input.toIntOrNull()) {
                1 -> goToNotesCreationMenu()
                2 -> previousScreen?.show()
                else -> checkNotesForInput(input)
            }
        } else {
            when (input.toIntOrNull()) {
                1 -> {
                    showNotesNames()
                    chooseNote()
                }
                2 -> goToNotesCreationMenu()
                3 -> showAllNotes()
                4 -> previousScreen?.show()
                else -> checkNotesForInput(input)
            }
        }
    }

    fun checkNotesForInput(input: String) {
        WorkWithUserInput.checkForErrorIfNotCorrectInteger(input)
        show()
    }

    fun showAllNotes() {
        val notesString = archive.notes.joinToString("\n", prefix = "[", postfix = "]")
        println("Заметки:\n$notesString\n")
        show()
    }

    fun showNotesNames() {
        println("Названия заметок:")
        var startNumber = 1
        for (i in archive.notes) {
            println("${startNumber}. ${i.name}")
            startNumber++
        }
    }

    fun goToNotesCreationMenu() {
        nextScreen = CreateNoteScreen(archive)
        nextScreen?.previousScreen = this
        nextScreen?.show()
    }

    fun chooseNote() {
        var noteNumber: Int? = null

        while (noteNumber == null) {
            val input = WorkWithUserInput.getUserInput("Введите номер заметки:")
            noteNumber = input.toIntOrNull()

            if (noteNumber == null) {
                WorkWithUserInput.showError("Некорректный ввод. Введите целое число.")
            }
        }



        val selectedNote = archive.notes.find { it.uniqueNumber == noteNumber }
        if (selectedNote != null) {
            println("Заметка выбрана!")
            nextScreen = NoteScreen(selectedNote)
            nextScreen?.previousScreen = this
            nextScreen?.show()
        } else {
            WorkWithUserInput.showError("Заметки с номером '$noteNumber' не существует")
            show()
        }
    }
}
