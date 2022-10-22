package Interface.commands;
import Content.Music;
import Interface.MenuItems;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuRemove implements MenuItems {
    Scanner scanner = new Scanner(System.in);
    int numb;
    /**
     * Виконання команди вилучення пісні у список вилучених (з якого її ще можна відновити)
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     */
    @Override
    public void executeCommand(Music music, Logger log) {
        if (!music.IsAnySong(log)) {return;}
        System.out.print("\n Ваш поточний плейлист: ");
        music.printMusicList(log);
        while (true) {
            System.out.print("\n Введіть номер пісні, яку хочете вилучити: ");
            numb = scanner.nextInt();
            if (numb > 0 && numb <= music.list.size()){ break; }
            log.fine(" Було виконано спробу вийти за межі списку пісень (індекс неіснуючого елемента). ");
            System.out.print("\n Введіть номер існуючого елемента.\n");
        }
        music.dropFromList(numb);
        log.info(" Композицію за номером " + numb + " було успішно видалено з основного списку. ");
        System.out.print("\n Композицію буде вилучено у список видалених.\n");
    }
}
