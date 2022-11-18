package Tests;
import Content.Music;
import Interface.commands.MenuByStyle;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

class MenuByStyleTest {
    private MenuByStyle test = new MenuByStyle();
    public static final Logger log = Logger.getGlobal();
    private Music subj = new Music();
    private final PrintStream OUT = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Test
    void TestExecuteCommand() throws Exception {
        log.setUseParentHandlers(false);
        subj.GetFromFile("D:\\Files\\MyList.txt", log);
        test.executeCommand(subj, log);
        System.setOut(new PrintStream(outputStreamCaptor));
        subj.printMusicList(log);
        String expect = "\n1. Song: Avenged Sevenfold - \"Burn It Down\" (Rock): 00:04:58\n" +
               "   Link:https://music.youtube.com/watch?v=uRxD-93_fwA&feature=share\n" +
               "\n" +
               "2. Song: Pantera - \"Walk\" (Rock): 00:05:15\n" +
               "\n" +
               "3. Song: The Animals - \"House of the Rising Sun\" (Rock): 00:04:30\n" +
               "   Link:https://music.youtube.com/watch?v=VixdIglCZXk&feature=share\n" +
               "\n" +
               "4. Song: Nirvana - \"You Know You're Right\" (Rock): 00:03:41\n" +
               "\n" +
               "5. Song: Black Sabbath - \"Paranoid\" (Rock): 00:02:48\n" +
               "   Link:https://music.youtube.com/watch?v=m7nwbJLO9qo&feature=share\n" +
               "\n" +
               "6. Song: Megadeth - \"In My Darkest Hour\" (Rock): 00:06:26\n" +
               "\n" +
               "7. Song: Eminem - \"Lose Yourself\" (HipHop): 00:05:23\n" +
               "   Link:https://music.youtube.com/watch?v=4wOLVrGHiIU&feature=share\n" +
               "\n" +
               "8. Song: 2Pac - \"Chill Money\" (HipHop): 00:04:52\n" +
               "\n" +
               "9. Song: Dr. Dre (feat. Snoop Dogg) - \"Still D.R.E.\" (HipHop): 00:04:31\n" +
               "\n" +
               "10. Song: Jay-Z - \"Dirt off Your Shoulder\" (HipHop): 00:04:04\n" +
               "   Link:https://music.youtube.com/watch?v=z7rOS9rd4sY&feature=share\n" +
               "\n" +
               "11. Song: Gorillaz - \"Dare\" (Electronic): 00:03:33\n" +
               "\n" +
               "12. Song: Tony Igy - \"Astronomia\" (Electronic): 00:04:18\n" +
               "\n" +
               "13. Song: Royksopp - \"Here She Comes Again\" (Electronic): 00:05:05\n" +
               "\n" +
               "14. Song: Post Malone - \"Better Now\" (Pop): 00:03:52\n" +
               "\n" +
               "15. Song: Imagine Dragons x J.I.D - \"Enemy\" (Pop): 00:03:33\n" +
               "   Link:https://music.youtube.com/watch?v=D9G1VOjN_84&feature=share\n" +
               "\n";
        Assert.assertEquals(outputStreamCaptor.toString(), expect);
        System.setOut(new PrintStream(OUT));
    }
}