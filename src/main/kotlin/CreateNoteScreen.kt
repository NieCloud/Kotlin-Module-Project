class CreateNoteScreen(private val archive: Archive) : Screen() {
    override fun show() {
        val noteName = WorkWithUserInput.getUserInput("Введите название новой заметки:")

        if (noteName?.isEmpty() == true) {
            showError("Ввод не может быть пустым!")
            show()
        }

        val noteContent = WorkWithUserInput.getUserInput("Введите содержание новой заметки:")

        if (noteContent?.isEmpty() == true) {
            showError("Ввод не может быть пустым!")
            show()
        }

        val newNote = Note(noteName.toString(), noteContent.toString())
        archive.notes.add(newNote)
        println("Заметка создана!")
        previousScreen?.show()
    }
}