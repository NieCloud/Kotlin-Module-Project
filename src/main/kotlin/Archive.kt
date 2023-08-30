class Archive(val name: String, val notes: MutableList<Note> = mutableListOf()) {

    override fun toString(): String {
        return name
    }
}

class Note(val name: String, var content: String) {
    override fun toString(): String {
        return "Название заметки: $name\nСодержание заметки: $content"
    }
}

