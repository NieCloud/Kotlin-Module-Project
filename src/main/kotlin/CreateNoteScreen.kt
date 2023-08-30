class CreateNoteScreen(private val archive: Archive) : Screen() {
    override fun show() {
        val noteName = getUserInput("Введите название новой заметки:")
        val noteContent = getUserInput("Введите содержание новой заметки:")
        val newNote = Note(noteName.toString(), noteContent.toString())
        archive.notes.add(newNote)
        println("Заметка создана!")
        previousScreen?.show()
    }
}