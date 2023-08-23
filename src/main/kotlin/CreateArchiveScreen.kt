class CreateArchiveScreen(private val archives: MutableList<Archive>) : Screen() {
    override fun show() {
        val archiveName = getUserInput("Введите название нового архива:")
        val newArchive = Archive(archiveName.toString())
        archives.add(newArchive)
        println("Архив '$archiveName' создан!")
        SelectArchiveScreen(archives).show()
    }
}
