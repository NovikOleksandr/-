package Interface.commands;
import Content.Music;
import Interface.MenuItems;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuDelete implements MenuItems {
    Scanner scanner = new Scanner(System.in);
    int numb;
    /**
     * Виконання команди видаення пісні зі списку вилучених
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     */
    @Override
    public void executeCommand(Music music, Logger log) {
        if (!music.IsAnyRemoved(log)){ return; }
        System.out.print("\n Список доступних для видалення пісень: ");
        music.printRemoved(log);
        while (true) {
            System.out.print("\n Введіть номер пісні (із списку вилучених), яку хочете видалити: ");
            numb = scanner.nextInt();
            if (numb > 0 && numb <= music.dropped.size()) {
                break;
            }
            log.fine(" Було виконано спробу вийти за межі списку видалених (індекс неіснуючого елемента). ");
            System.out.print("\n Введіть номер існуючого елемента.\n");
        }
        music.deleteSong(numb);
        log.info(" Композицію за номером " + numb + " було вилучено. ");
        System.out.print("\n Композицію було видалено.\n");

    }
}
