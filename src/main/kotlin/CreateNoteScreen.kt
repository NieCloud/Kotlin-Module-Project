class CreateNoteScreen(private val archive: Archive) : Screen() {
    override fun show() {
        val noteName = WorkWithUserInput.getUserInput("Введите название новой заметки:")

        val noteContent = WorkWithUserInput.getUserInput("Введите содержание новой заметки:")

        val newNote = Note(noteName, noteContent)
        archive.notes.add(newNote)
        println("Заметка создана!")
        previousScreen?.show()
    }
}