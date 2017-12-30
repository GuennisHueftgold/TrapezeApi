package com.github.guennishueftgold.trapezeapi;

import com.google.gson.stream.JsonToken;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AutocompleteSearchResultTest {

    private final static AutocompleteSearchResult.Converter adapter = new AutocompleteSearchResult.Converter();

    public final static AutocompleteSearchResult.Builder createSample(int idx) {
        AutocompleteSearchResult.Builder builder = new AutocompleteSearchResult.Builder()
                .setName("name_" + idx)
                .setShortName("short_name_" + idx);
        switch (idx % 3) {
            case 0:
                builder.setType(AutocompleteSearchResult.TYPE_DIVIDER);
                break;
            case 1:
                builder.setType(AutocompleteSearchResult.TYPE_STOP);
                break;
            case 2:
                builder.setType(AutocompleteSearchResult.TYPE_UNKNOWN);
                break;
        }
        return builder;
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        final AutocompleteSearchResult autocompleteSearchResult = createSample(0).build();
        final AutocompleteSearchResult output = adapter.fromJson(adapter.toJson(autocompleteSearchResult));
        assertEquals(autocompleteSearchResult, output);
    }

    @Test
    public void typeadapter_check_read_null() throws Exception {
        assertNull(adapter.fromJson("null"));
    }

    @Test
    public void typeadapter_check_write_null() {
        assertEquals(adapter.toJson(null), "null");
    }

    @Test
    public void TypeAdapter_skip_unknown_name() throws IOException {
        Logger logger = mock(Logger.class);
        Logger.setInstance(logger);
        final AutocompleteSearchResult autocompleteSearchResult = adapter.fromJson("{\"unknown_tag\":null}");
        assertNotNull(autocompleteSearchResult);
        verify(logger, times(1)).unknownName(adapter, "unknown_tag", JsonToken.NULL);
    }

}
