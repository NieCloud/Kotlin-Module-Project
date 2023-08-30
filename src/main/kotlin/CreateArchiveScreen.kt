class CreateArchiveScreen(private val archives: MutableList<Archive>) : Screen() {
    override fun show() {
        val archiveName = WorkWithUserInput.getUserInput("Введите название нового архива:")

        if (archiveName?.isEmpty() == true) {
            showError("Ввод не может быть пустым!")
            show()
        }

        val newArchive = Archive(archiveName.toString())
        archives.add(newArchive)
        println("Архив '$archiveName' создан!")
        SelectArchiveScreen(archives).show()
    }
}
