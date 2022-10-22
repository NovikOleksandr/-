package Interface;
import Content.Music;
import Interface.commands.*;
import java.util.logging.Logger;
import java.util.Map;
import java.util.LinkedHashMap;
public class CommandMenu {
    private final Map <String, MenuItems> menu;
    public CommandMenu(){
        menu = new LinkedHashMap<>();
        menu.put("menu", new MenuHelp());
        menu.put("exit", new MenuExit());
        menu.put("add", new MenuAdd());
        menu.put("remove", new MenuRemove());
        menu.put("restore", new MenuRestor());
        menu.put("byStyle", new MenuByStyle());
        menu.put("get", new MenuFromFile());
        menu.put("save", new MenuToFile());
        menu.put("byLen", new MenuLEN());
        menu.put("play", new MenuPlay());
        menu.put("removed", new MenuRemoved());
        menu.put("show", new MenuPrintList());
        menu.put("delete", new MenuDelete());
        menu.put("sum", new MenuSum());
    }
    /**
     * Виконання вибраної користувачем команди
     * @param music поточний плейлист
     * @param command команда
     * @param log змінна для логування
     * @throws Exception помилка у надсиланні листа на пошту
     */
    public void exeCommand (Music music, String command, Logger log) throws Exception {
        if (menu.get(command) != null){
            menu.get(command).executeCommand(music, log);
            log.info(" Виконання команди \"" + command + "\" завершено. ");
        }
        else {
            System.out.print(" Будь ласка повторіть введення.\n");
            log.info(" Команди \"" + command + "\" не була виконана, так як її не існує. ");
        }
    }
}
