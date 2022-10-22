package Content;
import Content.Styles.Electronic;
import Content.Styles.HipHop;
import Content.Styles.Pop;
import Content.Styles.Rock;
import Interface.EmailMessage;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Logger;
public class Music {
    protected String title = "Untitled", author = "Unknown", genre = "Unknown", link = null;
    protected MusicDuration duration = new MusicDuration();
    protected MusicDuration durationSum = new MusicDuration();
    /* Список музичних композицій, які додані на даний момент */
    public ArrayList<Music> list = new ArrayList<>();
    /* Список композицій, які вилучили зі списку, зберігаю їх, для можливості їх відновити */
    public ArrayList<Music> dropped = new ArrayList<>();
    public enum MusicStyle{
        ROCK,
        HIPHOP,
        POP,
        ELECTRO
    }
    /**
     * Метод додавання нової композиції із введенням даних безпосередньо з клавіатури
     * @param style стиль, необхідно для визначення об'єкт якого класу створювати
     * @param title назва композиції
     * @param author автор композиції
     * @param len довжина у формаиі hh:mm:ss
     * @param log змінна для логування
     * @param test чи піддається даний метод тестуванню: true - так, false - ні
     */
    public void addNewComposition (MusicStyle style, String title, String author, String len, Logger log, boolean test){
        Music composition;
        if(test) {
            title = "Двері відкривайте";
            author = "Mc Petya";
            len = "00:05:10";
        }
        switch (style){
            case ROCK -> composition = new Rock(title, author, len, log);
            case POP -> composition = new Pop(title, author, len, log);
            case HIPHOP -> composition = new HipHop(title, author, len, log);
            case ELECTRO -> composition = new Electronic(title, author, len, log);
            default -> composition = new Music();
        }
        this.list.add(composition);
        this.durationSum.setSec(getPlaylistDuration(log), log);
    }
    /**
     * Вилучити пісню із основного списку, так, щоб вона попала у список вилучених
     * @param numb порядковий номер композиції (починаючине з 0, а з 1)
     */
    public void dropFromList (int numb){
        dropped.add(list.get(numb - 1));
        list.remove(numb - 1);
    }
    /**
     * Відновити пісню, та забрати її зі списку видалених назад в основний
     * @param numb номер композиції у списку вилучених (починаючи з 1)
     */
    public void restoreSong (int numb){
        list.add(dropped.get(numb - 1));
        dropped.remove(numb - 1);
    }
    /**
     * Видалити пісню із списку вилучених
     * @param numb номер пісні зі списку вилучених (починаючи з 1)
     */
    public void deleteSong(int numb){
        dropped.remove(numb - 1);
    }
    /**
     * Гетер жанру
     * @return стрінг із назвою жанру, до якого належить пісня
     */
    public String getGenre() {
        return genre;
    }
    /**
     * Гетер лінку на програвання пісні
     * @return строку з посиланням на джерело, де можна програти пісню
     */
    public String getLink() { return link; }
    /**
     * Втановленння посилання на пісню
     * @param link строка з посиланням
     */
    public void setLink(String link) { this.link = link; }
    /**
     * Визначення сумарної довжини усіх треків плейлиста
     * @param log змінна для логування
     * @return тривалість (в секундах) повного плейлиста
     */
    long getPlaylistDuration (Logger log){
        log.info(" Було визначено сумарну довжину пісень у плейлисті. ");
        return list.stream().mapToLong(x -> x.duration.Sec()).sum();
    }
    /**
     * Об'єкт класу триваліст пісні, з яким можна робити необхідні дії
     * @return сумарну довжину плейлиста
     */
    public MusicDuration getSum (){
        return durationSum;
    }
    /**
     * Вивелення на екран усього плейлиста
     * @param log логер для записів повідомлень у лог файл
     */
    public void printMusicList (Logger log){
        if (!this.IsAnySong(log)){ return; }
        for (int n = 0; n < list.size(); n++){
            System.out.print("\n" + (n + 1) + "." + list.get(n));
        }
        log.fine(" Список музики успішно виведено. ");
        System.out.print("\n");
    }
    /**
     * Виводить на екран список вилучених
     * @param log логер для запису повідомлень
     */
    public void printRemoved (Logger log) {
        if(!this.IsAnyRemoved(log)) { return; }
        for (int n = 0; n < dropped.size(); n++){
            System.out.print("\n" + (n + 1) + "." + dropped.get(n));
        }
        log.fine(" Список видалених композицій успішно виведено. ");
        System.out.print("\n");
    }
    /**
     * Перевірка чи є у плейлисті хоча б одна пісня
     * @param log змінна для логування
     * @return true, якщо список не є порожнім
     */
    public boolean IsAnySong(Logger log){
        if (list.size() == 0){
            System.out.print("\n На даний момент список пісень є порожнім.\n");
            return false;
        }
        log.fine(" Було здійснено перевірку наявності композицій в головному списку. ");
        return true;
    }
    /**
     * Перевірка наявності пісень у списку вилучених
     * @param log змінна для логування
     * @return true, якщо список не є порожнім
     */
    public boolean IsAnyRemoved(Logger log){
        if (dropped.size() == 0){
            System.out.print("\n На даний момент немає видалених пісень.\n");
            return false;
        }
        log.fine(" Було здійснено перевірку наявності композицій в списку вилучених. ");
        return true;
    }
    /**
     * Функція отримання інформації із файла типу .txt
     * @param filename ім'я файла
     * @param log змінна для логування
     * @return true, якщо вдалося відкрити файл
     * @throws Exception для надсилання типу помилки на електронну пошту
     */
    public boolean GetFromFile (String filename, Logger log) throws Exception {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            while (true){
                String style = bufferedReader.readLine();
                // Символ # у моєму випадку є кінцем плейлиста
                if (style.equals("#")) {
                    return true;
                }
                Music composition;
                // Визначення композицію якого саме жанру додаємо до плейлиста
                switch (style){
                    case "R":
                        composition = new Rock();
                        composition.genre = "Rock";
                        log.info(" З файла було успішно зчитано композицію стиля \"ROCK\". ");
                        break;
                    case "H":
                        composition = new HipHop();
                        composition.genre = "HipHop";
                        log.info(" З файла було успішно зчитано композицію стиля \"HIPHOP\". ");
                        break;
                    case "P":
                        composition = new Pop();
                        composition.genre = "Pop";
                        log.info(" З файла було успішно зчитано композицію стиля \"POP\". ");
                        break;
                    case "E":
                        composition = new Electronic();
                        composition.genre = "Electronic";
                        log.info(" З файла було успішно зчитано композицію стиля \"ELECTRONIC\". ");
                        break;
                    default:
                        composition = new Music();
                        log.warning(" Виникла критична помилка, у пісні невизначено стиль. ");
                }
                composition.title = bufferedReader.readLine();
                composition.author = bufferedReader.readLine();
                composition.duration.setDuration(LocalTime.parse(bufferedReader.readLine()), log);
                // Якщо до пісні було прив'язано посилання, то при записі у файл це буде позначено
                if (bufferedReader.readLine().equals("1")){
                    composition.link = bufferedReader.readLine();
                }
                this.list.add(composition);
                // Визначаємо нове значення повної довжини плейлиста
                this.durationSum.setSec(getPlaylistDuration(log), log);
            }
        } catch (FileNotFoundException e) {
            System.out.print(" Файл не знайдено.\n");
            log.fine(" Виникла помилка: " + filename + " не знайдено. ");
            return false;
        } catch (IOException e) {
            log.warning(" Критична помилка не вдалося зчитати строку з файлу. ");
            EmailMessage message = new EmailMessage();
            message.SendError(e.toString());
            throw new RuntimeException(e);
        }
    }
    /**
     * Функція збереження поточного плейлиста у фалй
     * @param filename ім'я файла
     * @param log логер для занесення усіх даних
     * @throws Exception для надсилання типу помилки на електронну пошту
     */
    public void SaveToFile (String filename, Logger log) throws Exception {
        if (!filename.contains(".txt")){
            filename += ".txt";
        }
        try {
            FileWriter writer = new FileWriter(filename);
            log.info(" Буде створено або редаговано файл під назвою \"" + filename + "\". ");
            for (Music obj: this.list){
                switch (obj.genre){
                    // Позначаю якого жанру трек буде записано (для зручного зчитування)
                    case "Rock" -> writer.write("R");
                    case "HipHop" -> writer.write("H");
                    case "Pop" -> writer.write("P");
                    case "Electronic" -> writer.write("E");
                }
                // Записую всю інформацію, включно з лінком, якщо він є
                writer.write("\n" + obj.title + "\n" + obj.author + "\n" + obj.duration.getDuration() + "\n");
                if (obj.link != null){
                    writer.write("1\n" + obj.link + "\n");
                }
                else {
                    writer.write("0\n");
                }
            }
            //Завершення запису
            writer.write("#");
            writer.close();
            log.info(" Успішно занесено усі дані у файл \"" + filename +"\". ");
        } catch (IOException e) {
            log.warning(" Критична помилка у записі в файл. ");
            EmailMessage message = new EmailMessage();
            message.SendError(e.toString());
            throw new RuntimeException(e);
        }
    }
    /**
     * Гетер для довжини безпосередньо даного треку зі списку
     * @return Клас довжини, з яким можна працювати
     */
    public MusicDuration getDuration() {return duration;}
    /**
     * Виводить список пісень, які попадають в межі довжини
     * @param min мінімальна довжина
     * @param max максимальна довжина
     * @param log логування
     */
    public void ByLen(long min, long max, Logger log){
        Music Result = new Music();
        for (Music ptr: this.list){
            if (ptr.getDuration().Sec() > min && ptr.getDuration().Sec() < max){
                Result.list.add(ptr);
            }
        }
        System.out.print("\n Отримано даний список пісень в межах даної тривалості:\n");
        Result.printMusicList(log);
    }
    @Override
    public String toString() {
        if (link != null){
            return " Song: " + author + " - \"" + title + "\""   + " (" + genre + "): " + duration.getDuration() + "\n   Link:" + link + "\n";
        }
        return " Song: " + author + " - \"" + title + "\""   + " (" + genre + "): " + duration.getDuration() + "\n";
    }
}