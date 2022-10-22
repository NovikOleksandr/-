package Interface.commands;
import Content.Music;
import Interface.MenuItems;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuAdd implements MenuItems {
    /**
     * Виконання команди додавання пісні в плейлист
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     */
    @Override
    public void executeCommand(Music music, Logger log) {
        int style;
        String len, title, author;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n Виберіть стиль пісні, яку ви хочете додати (1 - Rock, 2 - HipHop, 3 - Pop, 4 - Electronic): ");
            style = scanner.nextInt();
            if (style > 0 && style < 5){ break; }
            log.fine(" Було виконано спробу вказати неіснуючий стиль. ");
            System.out.print("\n Будь ласка повторіть введення.\n");
        }
        scanner.nextLine();
        System.out.print("\n Введіть назву композиції: ");
        title = scanner.nextLine();
        System.out.print("\n Введіть ім'я автора: ");
        author = scanner.nextLine();
        System.out.print("\n Введіть довжину треку (формат hh:mm:ss): ");
        len = scanner.next();
        log.info(" Успішно зчитано усю необхідну інформацію про композицію. ");
        switch (style){
            case 1 -> {
                music.addNewComposition(Music.MusicStyle.ROCK, title, author, len, log, false);
                log.info(" Було успішно додано композицію стилю \"ROCK\".");
            }
            case 2 ->{
                music.addNewComposition(Music.MusicStyle.HIPHOP, title, author, len, log, false);
                log.info(" Було успішно додано композицію стилю \"HIPHOP\".");
            }
            case 3 -> {
                music.addNewComposition(Music.MusicStyle.POP, title, author, len, log, false);
                log.info(" Було успішно додано композицію стилю \"POP\".");
            }
            case 4 -> {
                music.addNewComposition(Music.MusicStyle.ELECTRO, title, author, len, log, false);
                log.info(" Було успішно додано композицію стилю \"ELECTRONIC\".");
            }
            default -> {
                System.out.print("\n Виникла помилка.\n");
                log.warning(" Виникла критична помилка: у список не було додано композицію.");
            }
        }
        System.out.print("\n Композицію було успішно додано до плейлиста.\n");
    }
}
