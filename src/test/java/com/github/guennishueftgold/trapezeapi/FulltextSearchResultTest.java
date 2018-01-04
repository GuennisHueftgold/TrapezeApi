package com.github.guennishueftgold.trapezeapi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FulltextSearchResultTest {
    static FulltextSearchResult.Converter adapter = new FulltextSearchResult.Converter();

    static FulltextSearchResult.Builder createSample(int idx) {
        return new FulltextSearchResult.Builder()
                .setShortName("short_name_" + (idx / 2))
                .setName("name_" + idx);
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        final FulltextSearchResult input = createSample(0).build();
        final FulltextSearchResult output = adapter.fromJson(adapter.toJson(input));
        assertEquals(input, output);
    }

    @Test
    public void equal_should_be_true() {
        final FulltextSearchResult input1 = createSample(0).build();
        final FulltextSearchResult input2 = createSample(0).build();
        assertEquals(input1, input2);
    }

    @Test
    public void equal_should_be_false() {
        final FulltextSearchResult input1 = createSample(0).build();
        for (int i = 1; i < 3; i++) {
            final FulltextSearchResult input2 = createSample(i).build();
            assertNotEquals(input1, input2);
        }
    }
}
