package Interface.commands;
import Content.Music;
import Interface.EmailMessage;
import Interface.MenuItems;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;
public class MenuPlay implements MenuItems {
    public Scanner scanner = new Scanner(System.in);
    /**
     * Виконання команди відтворення пісні за посиланням
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     * @throws Exception помилка у надсиланні повідомлення на пошту
     */
    @Override
    public void executeCommand(Music music, Logger log) throws Exception {
        if (!music.IsAnySong(log)){
            return;
        }
        int numb;
        while (true){
            System.out.print("\n Введіть номер пісні, до якої буде прив'язано посилання (якщо його ще немає): ");
            numb = scanner.nextInt();
            if (numb > 0 && numb <= music.list.size()) { break; }
            log.fine(" Було виконано спробу вийти за межі списку пісень (індекс неіснуючого елемента). ");
            System.out.print("\n Значення повинне бути більшим нуля або та номером елемента, який існує.");
        }
        if (music.list.get(numb - 1).getLink() == null){
            log.info(" Посилання на дану пісню поки не існує в системі, було виконано запит на його введення. " + numb + ".");
            System.out.print("\n Задайте посилання для даної пісні: ");
            music.list.get(numb - 1).setLink(scanner.next());
        }
        log.info(" Посилання встановлено для композиції номер " + numb + ".");
        Desktop mydt = Desktop.getDesktop();
        try {
            mydt.browse(new URL(music.list.get(numb - 1).getLink()).toURI());
        } catch (IOException e) {
            music.list.get(numb - 1).setLink(null);
            System.out.print(" Посилання не є дійсним.\n");
            log.fine(" Виникла помилка (посилання не є дійсним). ");
        } catch (URISyntaxException e) {
            music.list.get(numb - 1).setLink(null);
            System.out.print("\n Виникла критична помилка при створенні посилання. ");
            log.warning(" Виникла критична помилка при створенні посилання. ");
            EmailMessage message = new EmailMessage();
            message.SendError(e.toString());
            throw new RuntimeException(e);
        }
    }
}
