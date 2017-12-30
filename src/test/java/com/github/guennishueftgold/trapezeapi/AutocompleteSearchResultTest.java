package com.github.guennishueftgold.trapezeapi;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
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
    public void AutoCompleteSearchResult_should_be_equal() {
        final AutocompleteSearchResult autocompleteSearchResult1 = createSample(0).build();
        final AutocompleteSearchResult autocompleteSearchResult2 = createSample(0).build();
        assertEquals(autocompleteSearchResult1, autocompleteSearchResult1);
        assertEquals(autocompleteSearchResult1, autocompleteSearchResult2);
    }

    @Test
    public void AutoCompleteSearchResult_should_not_be_equal() {
        final AutocompleteSearchResult autocompleteSearchResult1 = createSample(0).build();
        assertNotEquals(autocompleteSearchResult1, null);
        assertNotEquals(autocompleteSearchResult1, new Object());
        AutocompleteSearchResult autocompleteSearchResult2 = createSample(0)
                .setName("other_name")
                .build();
        assertNotEquals(autocompleteSearchResult1, autocompleteSearchResult2);
        autocompleteSearchResult2 = createSample(0)
                .setShortName("other_short_name")
                .build();
        assertNotEquals(autocompleteSearchResult1, autocompleteSearchResult2);
        autocompleteSearchResult2 = createSample(0)
                .setType(-20)
                .build();
        assertNotEquals(autocompleteSearchResult1, autocompleteSearchResult2);
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
    public void AutoCompleteSearchResult_hashCode_should_be_equal() {
        final AutocompleteSearchResult autocompleteSearchResult1 = createSample(1).build();
        final AutocompleteSearchResult autocompleteSearchResult2 = createSample(1).build();
        assertEquals(autocompleteSearchResult1.hashCode(), autocompleteSearchResult2.hashCode());
    }

    @Test
    public void AutoCompleteSearchResult_hashCode_should_not_be_equal() {
        final AutocompleteSearchResult autocompleteSearchResult1 = createSample(1).build();
        final AutocompleteSearchResult autocompleteSearchResult2 = createSample(2).build();
        assertNotEquals(autocompleteSearchResult1.hashCode(), autocompleteSearchResult2.hashCode());
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

    @Test
    public void TypeAdapter_convertTypeFromJson() throws IOException {
        JsonReader jsonReader = mock(JsonReader.class);
        when(jsonReader.peek()).thenReturn(JsonToken.NULL);
        assertEquals(adapter.convertTypeFromJson(jsonReader), AutocompleteSearchResult.TYPE_UNKNOWN);
        reset(jsonReader);
        when(jsonReader.peek()).thenReturn(JsonToken.STRING);
        when(jsonReader.nextString()).thenReturn(AutocompleteSearchResult.Converter.NAME_TYPE_DIVIDER);
        assertEquals(adapter.convertTypeFromJson(jsonReader), AutocompleteSearchResult.TYPE_DIVIDER);
        when(jsonReader.nextString()).thenReturn(AutocompleteSearchResult.Converter.NAME_TYPE_STOP);
        assertEquals(adapter.convertTypeFromJson(jsonReader), AutocompleteSearchResult.TYPE_STOP);
        when(jsonReader.nextString()).thenReturn("UNKNOWN");
        assertEquals(adapter.convertTypeFromJson(jsonReader), AutocompleteSearchResult.TYPE_UNKNOWN);
    }

    @Test
    public void TypeAdapter_convertTypeToJson() throws IOException {
        JsonWriter jsonWriter = mock(JsonWriter.class);
        adapter.convertTypeToJson(jsonWriter, AutocompleteSearchResult.TYPE_UNKNOWN);
        adapter.convertTypeToJson(jsonWriter, AutocompleteSearchResult.TYPE_STOP);
        adapter.convertTypeToJson(jsonWriter, AutocompleteSearchResult.TYPE_DIVIDER);
        verify(jsonWriter, times(1)).nullValue();
        verify(jsonWriter, times(1)).value(AutocompleteSearchResult.Converter.NAME_TYPE_STOP);
        verify(jsonWriter, times(1)).value(AutocompleteSearchResult.Converter.NAME_TYPE_DIVIDER);
    }
}
