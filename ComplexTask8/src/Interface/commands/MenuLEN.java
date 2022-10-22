package Interface.commands;
import Content.Music;
import Content.MusicDuration;
import Interface.MenuItems;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuLEN implements MenuItems {
    Scanner scanner = new Scanner(System.in);
    /**
     * Виконання команди виводу пісень, які попадають в діапазон довжини
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     */
    @Override
    public void executeCommand(Music music, Logger log) {
        if (!music.IsAnySong(log)){ return;}
        MusicDuration[] range = new MusicDuration[2];
        range[0] = new MusicDuration();
        range[1] = new MusicDuration();
        System.out.print("\n Введіть мінімальну тривалість пісні: ");
        range[0].setDuration(LocalTime.parse(scanner.nextLine()), log);
        while (true) {
            System.out.print("\n Введіть максимальну тривалість пісні: ");
            range[1].setDuration(LocalTime.parse(scanner.nextLine()), log);
            if (range[1].Sec() > range[0].Sec()){ break; }
            System.out.print(" Максимальна тривалість має бути більшою ніж мінімальна.\n");
            log.fine(" Було виконано спробу задати максимальну тривалість меншою ніж мінімальна. ");
        }
        music.ByLen(range[0].Sec(), range[1].Sec(), log);
        log.info(" Успішно створено та виведено список пісень відсортований за тривалістю. ");
    }
}
