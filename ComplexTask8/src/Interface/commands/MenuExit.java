package Interface.commands;
import Content.Music;
import Interface.MenuItems;
import java.util.Scanner;
import java.util.logging.Logger;
public class MenuExit implements MenuItems {
    int answ;
    Scanner scanner = new Scanner(System.in);
    /**
     * Виконання команди вихід з меню і завершення роботи
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     * @throws Exception помилка у надсиланні електронного повідомлення
     */
    @Override
    public void executeCommand(Music music, Logger log) throws Exception {
        System.out.print("\n Перед завершенням роботи чи не хотіли б ви зберегти поточний плейлист? (1 - так, 0 - ні): ");
        while(true){
            answ = scanner.nextInt();
            if(answ >= 0 && answ < 2) { break;}
            System.out.print(" Будь ласка виберіть відповідь (1 - так, 0 - ні): ");
        }
        if(answ == 1){
            MenuToFile action = new MenuToFile();
            log.info(" Викликано метод занесення у файл при завершенні роботи програми. ");
            action.executeCommand(music, log);
        }
        log.info(" Програма успішно завершила своє виконання. ");
        System.out.print("\n Завершення роботи...\n");
        System.exit(0);
    }
}
