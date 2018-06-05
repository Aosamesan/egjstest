import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUnit {

    @Test
    public void shouldGetName() {
        Pattern numberPattern = Pattern.compile("^([0-9]+)([^0-9]*)$");
        String test1 = "123abc";

        Matcher matcher = numberPattern.matcher(test1);

        Assert.assertTrue(matcher.find());
        String extracted = matcher.group(1);

        Assert.assertEquals(extracted, "123");
    }

}
