class SelectArchiveScreen(private val archives: MutableList<Archive>) : Screen() {
    override fun show() {
        val input = getUserInput("Введите 1, чтобы выбрать архив, 2 чтобы создать новый архив и 3 чтобы выйти из программы")
        when (input?.toIntOrNull()) {
            1 -> {
                val archiveName = getUserInput("Введите название архива:")
                val selectedArchive = archives.find { it.name == archiveName }
                if (selectedArchive != null) {
                    println("Архив выбран!")
                    nextScreen = SelectNoteScreen(selectedArchive)
                    nextScreen?.previousScreen = this
                    nextScreen?.show()
                } else {
                    showError("Архив '$archiveName' не существует.")
                    show()
                }
            }
            2 -> {
                nextScreen = CreateArchiveScreen(archives)
                nextScreen?.show()
            }
            3 -> exit()
            else -> {
                showError("Неверный ввод! Пожалуйста, введите корректную команду!")
                show()
            }
        }
    }
}
