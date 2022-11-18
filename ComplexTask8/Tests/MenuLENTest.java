package Tests;
import Content.Music;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

class MenuLENTest {
    public static final Logger log = Logger.getGlobal();
    private Music subj = new Music();
    private final PrintStream OUT = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Test
    void TestExecuteCommand() throws Exception {
        log.setUseParentHandlers(false);
        System.setOut(new PrintStream(outputStreamCaptor));
        subj.GetFromFile("D:\\Files\\MyList.txt", log);
        subj.ByLen(360, 420, log);
        String expect = "\n Отримано даний список пісень в межах даної тривалості:\n" +
                "\n" +
                "1. Song: Megadeth - \"In My Darkest Hour\" (Rock): 00:06:26\n" +
                "\n";
        Assert.assertEquals(outputStreamCaptor.toString(), expect);
        System.setOut(new PrintStream(OUT));
    }
}