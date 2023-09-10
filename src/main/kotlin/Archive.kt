class Archive(val uniqueNumber: Int, val name: String, val notes: MutableList<Note> = mutableListOf()) {

    private var noteCount = 1

    fun createNote(noteName: String, noteContent: String) {
        val newNote = Note(noteCount, noteName, noteContent)
        notes.add(newNote)

        println("Заметка '$noteName' создана в архиве '$name' с номером $noteCount!")

        incrementNoteCount()
    }

    private fun incrementNoteCount() {
        noteCount++
    }

    override fun toString(): String {
        return "${uniqueNumber}.${name}"
    }
}

class Note(val uniqueNumber: Int, val name: String, var content: String) {
    override fun toString(): String {
        return "Номер заметки: ${uniqueNumber}. Название заметки: $name\nСодержание заметки: $content"
    }
}

