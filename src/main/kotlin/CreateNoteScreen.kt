class CreateNoteScreen(private val archive: Archive) : Screen() {
    override fun show() {
        val noteName = WorkWithUserInput.getUserInput("Введите название новой заметки:")
        val noteContent = WorkWithUserInput.getUserInput("Введите содержание новой заметки:")

        archive.createNote(noteName, noteContent)

        //println("Заметка '$noteName' создана в архиве '${archive.name}' с номером ${archive.getNoteCount()}!")

        previousScreen?.show()
    }
}