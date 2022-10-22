package Interface.commands;
import Content.Music;
import Interface.MenuItems;
import java.util.logging.Logger;

public class MenuPrintList implements MenuItems {
    /**
     * Виконання команди виведення плейлиста
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     */
    @Override
    public void executeCommand(Music music, Logger log) {
        music.printMusicList(log);
    }
}
