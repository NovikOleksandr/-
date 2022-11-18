package Tests;
import Content.Music;
import Interface.commands.MenuSum;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

class MenuSumTest {
    private Music subj = new Music();
    public static final Logger log = Logger.getGlobal();
    private final PrintStream OUT = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Test
    void TestExecuteCommand() throws Exception {
        MenuSum com = new MenuSum();
        log.setUseParentHandlers(false);
        System.setOut(new PrintStream(outputStreamCaptor));
        subj.addNewComposition(Music.MusicStyle.ROCK,"", "", "", log, true);
        subj.addNewComposition(Music.MusicStyle.ROCK,"", "", "", log, true);
        String expect = "\n Сумарна довжина плейлиста: 00:10:20\n";
        com.executeCommand(subj, log);
        Assert.assertEquals(outputStreamCaptor.toString(), expect);
        System.setOut(new PrintStream(OUT));
    }
}