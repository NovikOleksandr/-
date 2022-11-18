package Tests;
import Content.Music;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

class MusicTest {
    private Music subj = new Music();
    public static final Logger log = Logger.getGlobal();
    private final PrintStream OUT = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @org.junit.jupiter.api.Test
    void TestAddNewComposition() {
        log.setUseParentHandlers(false);
        subj.addNewComposition(Music.MusicStyle.ROCK,"", "", "", log, true);
        Assert.assertEquals(subj.list.get(0).toString(), " Song: Mc Petya - \"Двері відкривайте\" (Rock): 00:05:10\n");
        subj.dropFromList(1);
        subj.deleteSong(1);
        subj.addNewComposition(Music.MusicStyle.POP,"", "", "", log, true);
        Assert.assertEquals(subj.list.get(0).toString(), " Song: Mc Petya - \"Двері відкривайте\" (Pop): 00:05:10\n");
        subj.dropFromList(1);
        subj.deleteSong(1);
        subj.addNewComposition(Music.MusicStyle.HIPHOP,"", "", "", log,true);
        Assert.assertEquals(subj.list.get(0).toString(), " Song: Mc Petya - \"Двері відкривайте\" (HipHop): 00:05:10\n");
        subj.dropFromList(1);
        subj.deleteSong(1);
        subj.addNewComposition(Music.MusicStyle.ELECTRO,"", "", "", log, true);
        Assert.assertEquals(subj.list.get(0).toString(), " Song: Mc Petya - \"Двері відкривайте\" (Electronic): 00:05:10\n");
    }
    @org.junit.jupiter.api.Test
    void TestDropFromList() {
        log.setUseParentHandlers(false);
        subj.addNewComposition(Music.MusicStyle.ELECTRO,"", "", "", log, true);
        subj.dropFromList(1);
        Assert.assertEquals(subj.list.size(), 0);
        Assert.assertEquals(subj.dropped.get(0).toString(), " Song: Mc Petya - \"Двері відкривайте\" (Electronic): 00:05:10\n");
    }
    @org.junit.jupiter.api.Test
    void TestRestoreSong() {
        log.setUseParentHandlers(false);
        subj.addNewComposition(Music.MusicStyle.ELECTRO,"", "", "", log, true);
        subj.dropFromList(1);
        subj.restoreSong(1);
        Assert.assertEquals(subj.dropped.size(), 0);
        Assert.assertEquals(subj.list.get(0).toString(), " Song: Mc Petya - \"Двері відкривайте\" (Electronic): 00:05:10\n");
        Assert.assertEquals(subj.list.size(), 1);
    }
    @org.junit.jupiter.api.Test
    void TestDeleteSong() {
        log.setUseParentHandlers(false);
        subj.addNewComposition(Music.MusicStyle.ELECTRO,"", "", "", log, true);
        subj.dropFromList(1);
        subj.deleteSong(1);
        Assert.assertEquals(subj.dropped.size(), 0);
        Assert.assertEquals(subj.list.size(), 0);
    }
    @org.junit.jupiter.api.Test
    void TestPrintMusicList() {
        log.setUseParentHandlers(false);
        System.setOut(new PrintStream(outputStreamCaptor));
        subj.printMusicList(log);
        Assert.assertEquals(outputStreamCaptor.toString(), "\n На даний момент список пісень є порожнім.\n");
        subj.addNewComposition(Music.MusicStyle.ELECTRO,"", "", "", log, true);
        subj.printMusicList(log);
        Assert.assertEquals(outputStreamCaptor.toString(), "\n На даний момент список пісень є порожнім.\n\n" +
                "1. Song: Mc Petya - \"Двері відкривайте\" (Electronic): 00:05:10\n\n");
        System.setOut(new PrintStream(OUT));
    }
    @org.junit.jupiter.api.Test
    void TestPrintRemoved() {
        log.setUseParentHandlers(false);
        System.setOut(new PrintStream(outputStreamCaptor));
        subj.printRemoved(log);
        Assert.assertEquals(outputStreamCaptor.toString(), "\n На даний момент немає видалених пісень.\n");
        subj.addNewComposition(Music.MusicStyle.ELECTRO,"", "", "", log, true);
        subj.dropFromList(1);
        subj.printRemoved(log);
        Assert.assertEquals(outputStreamCaptor.toString(), "\n На даний момент немає видалених пісень.\n\n" +
                "1. Song: Mc Petya - \"Двері відкривайте\" (Electronic): 00:05:10\n\n");
        System.setOut(new PrintStream(OUT));
    }
    @org.junit.jupiter.api.Test
    void TestIsAnySong() {
        log.setUseParentHandlers(false);
        System.setOut(new PrintStream(outputStreamCaptor));
        subj.IsAnySong(log);
        Assert.assertEquals(outputStreamCaptor.toString(), "\n На даний момент список пісень є порожнім.\n");
        subj.addNewComposition(Music.MusicStyle.ELECTRO,"", "", "", log, true);
        subj.IsAnySong(log);
        Assert.assertEquals(outputStreamCaptor.toString(), "\n На даний момент список пісень є порожнім.\n");
        System.setOut(new PrintStream(OUT));
    }
    @org.junit.jupiter.api.Test
    void TestIsAnyRemoved() {
        log.setUseParentHandlers(false);
        System.setOut(new PrintStream(outputStreamCaptor));
        subj.IsAnyRemoved(log);
        Assert.assertEquals(outputStreamCaptor.toString(), "\n На даний момент немає видалених пісень.\n");
        subj.addNewComposition(Music.MusicStyle.ELECTRO,"", "", "", log, true);
        subj.dropFromList(1);
        subj.IsAnyRemoved(log);
        Assert.assertEquals(outputStreamCaptor.toString(), "\n На даний момент немає видалених пісень.\n");
        System.setOut(new PrintStream(OUT));
    }
    @org.junit.jupiter.api.Test
    void TestSaveToFile() throws Exception {
        log.setUseParentHandlers(false);
        subj.addNewComposition(Music.MusicStyle.ELECTRO,"", "", "", log, true);
        subj.SaveToFile("D:\\Files\\TESTS.txt", log);
        subj.dropFromList(1);
        subj.deleteSong(1);
        subj.GetFromFile("D:\\Files\\TESTS.txt", log);
        Assert.assertEquals(subj.list.get(0).toString(), " Song: Mc Petya - \"Двері відкривайте\" (Electronic): 00:05:10\n");
    }
    @org.junit.jupiter.api.Test
    void TestGetFromFile() throws Exception {
        log.setUseParentHandlers(false);
        subj.GetFromFile("D:\\Files\\TESTS.txt", log);
        Assert.assertEquals(subj.list.get(0).toString(), " Song: Mc Petya - \"Двері відкривайте\" (Electronic): 00:05:10\n");
    }

}