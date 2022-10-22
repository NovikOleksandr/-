package Interface.commands;
import Content.Music;
import Interface.MenuItems;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MenuByStyle implements MenuItems {
    Map<String, Integer> map = new LinkedHashMap<>();
    ArrayList<String> priority = new ArrayList<>();
    /**
     * Виконання команди додавання перестановки за стилем
     * @param music поточний плейлист з музикою
     * @param log змінна для логування
     */
    @Override
    public void executeCommand(Music music, Logger log) {
        if (!music.IsAnySong(log)){ return;}
        map.put("Rock", 0);
        map.put("HipHop", 0);
        map.put("Pop", 0);
        map.put("Electronic", 0);
        for (Music obj:music.list){
            map.replace(obj.getGenre(), map.get(obj.getGenre()) + 1);
        }
        PriorList();
        log.info(" Встановлено пріоритети для жанрів пісень (за їх кількістю у списку). ");
        NewList(music, log);
        log.info(" Список успішно відсортовано за стилями пісень. ");
        System.out.print("\n Список відсортовано за стилем по пріоритетності (за кількістю пісень певного стилю).\n");
    }
    /**
     * Створення нового списку пісень, порядок композицій переставлений на
     * основі пріоритетності їх стилю
     * @param music поточний плейлист
     * @param log змінна для логування
     */
    private void NewList (Music music, Logger log) {
        Music Sorted = new Music();
        while (music.list.size() > 0) {
            for (int n = 0; n < music.list.size(); n++){
                if (music.list.get(n).getGenre().equals(priority.get(0))){
                    Sorted.list.add(music.list.get(n));
                    music.list.remove(n);
                    n--;
                }
            }
            priority.remove(0);
        }
        log.info(" Список успішно був відсортований за музичним стилем. ");
        music.list = Sorted.list;
    }
    /**
     * Залежно від частоти попадання стилю у плейлисті ставить його вище чи нижче по пріоритетності
     */
    private void PriorList (){
        int max = 0, index = 0;
        ArrayList<String> temp = new ArrayList<>();
        temp.add("Electronic");
        temp.add("HipHop");
        temp.add("Pop");
        temp.add("Rock");
        while (temp.size() > 0){
            for (int n = 0; n < map.size(); n++) {
                if (map.get(temp.get(n)) >= max) {
                    max = map.get(temp.get(n));
                    index = n;
                }
            }
            max = 0;
            map.remove(temp.get(index));
            priority.add(temp.get(index));
            temp.remove(index);
        }
    }
}
