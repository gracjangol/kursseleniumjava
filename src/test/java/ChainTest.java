import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ChainTest {
    @Test
    public void testAddInChain() {
        Chain chain = new Chain();
        assertEquals(chain.lines.size(), 0);

        chain.add("pierwsza")
             .add("druga")
             .add("trzecia");

        assertEquals(chain.lines.size(), 3);

    }
}