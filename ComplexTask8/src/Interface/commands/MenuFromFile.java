package Interface.commands;
import Content.Music;
import Interface.MenuItems;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuFromFile implements MenuItems {
    Scanner scanner = new Scanner(System.in);
    /**
     * Виконання команди зчитування з файлу
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     * @throws Exception помилка у надсиланні повідомлення на електронну пошту
     */
    @Override
    public void executeCommand(Music music, Logger log) throws Exception {
        System.out.print("\n Введіть назву файла, з якого буде взято плейлист: ");
        String filename = scanner.nextLine();
        if(music.GetFromFile(filename, log)) {
            log.info(" Список було успішно отримано з файла: \"" + filename + "\". ");
            System.out.print("\n Інформацію успішно отримано з файла.\n");
        }
    }
}
