class CreateArchiveScreen(private val archives: MutableList<Archive>) : Screen() {

    companion object {
        private var archiveCount = 1

        fun incrementArchiveCount() {
            archiveCount++
        }
    }

    override fun show() {
        val archiveName = WorkWithUserInput.getUserInput("Введите название нового архива:")

        val archiveNumber = archiveCount
        val newArchive = Archive(uniqueNumber = archiveNumber, name = archiveName)

        archives.add(newArchive)

        println("Архив '$archiveName' создан с номером $archiveNumber!")

        incrementArchiveCount()

        SelectArchiveScreen(archives).show()
    }
}
