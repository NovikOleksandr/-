package Tests;
import Content.MusicDuration;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import java.time.LocalTime;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

class MusicDurationTest {
    public static final Logger log = Logger.getGlobal();
    private MusicDuration subj = new MusicDuration();
    @Test
    void TestSetDuration() {
        log.setUseParentHandlers(false);
        subj.setDuration(LocalTime.parse("10:23:25"), log);
        Assert.assertEquals(LocalTime.parse("10:23:25"), subj.getDuration());
        Assert.assertEquals(5005, subj.Sec());
    }
    @Test
    void TestSetSec() {
        log.setUseParentHandlers(false);
        subj.setSec(5005, log);
        Assert.assertEquals(5005, subj.Sec());
        Assert.assertEquals(LocalTime.parse("01:23:25"), subj.getDuration());
    }
}