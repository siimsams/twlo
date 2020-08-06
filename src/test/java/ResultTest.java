import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class ResultTest {
    @Test
    public void exampleTest() throws IOException {
        Assert.assertEquals(Arrays.asList("UK votes to leave EU", "F.C.C. Repeals Net Neutrality Rules"),
                Result.topArticles(2));
    }
}