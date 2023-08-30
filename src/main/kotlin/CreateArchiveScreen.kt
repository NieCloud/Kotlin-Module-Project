class CreateArchiveScreen(private val archives: MutableList<Archive>) : Screen() {
    override fun show() {
        val archiveName = WorkWithUserInput.getUserInput("Введите название нового архива:")

        val newArchive = Archive(archiveName)

        archives.add(newArchive)

        println("Архив '$archiveName' создан!")

        SelectArchiveScreen(archives).show()
    }
}
