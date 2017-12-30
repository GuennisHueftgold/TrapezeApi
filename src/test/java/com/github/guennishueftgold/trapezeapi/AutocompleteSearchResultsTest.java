package com.github.guennishueftgold.trapezeapi;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AutocompleteSearchResultsTest {

    private final static AutocompleteSearchResults.Converter adapter = new AutocompleteSearchResults
            .Converter(new AutocompleteSearchResult.Converter());

    public final static AutocompleteSearchResults.Builder createSample(int entries) {
        AutocompleteSearchResults.Builder builder = new AutocompleteSearchResults.Builder();
        for (int i = 0; i < entries; i++) {
            builder.add(AutocompleteSearchResultTest.createSample(i).build());
        }
        return builder;
    }

    @Test
    public void AutoCompleteSearchResult_should_be_equal() {
        final AutocompleteSearchResults autocompleteSearchResults1 = createSample(0).build();
        final AutocompleteSearchResults autocompleteSearchResults2 = createSample(0).build();
        assertEquals(autocompleteSearchResults1, autocompleteSearchResults1);
        assertEquals(autocompleteSearchResults1, autocompleteSearchResults2);
    }

    @Test
    public void AutoCompleteSearchResult_should_not_be_equal() {
        final AutocompleteSearchResults autocompleteSearchResults1 = createSample(4).build();
        assertNotEquals(autocompleteSearchResults1, null);
        assertNotEquals(autocompleteSearchResults1, new Object());
        AutocompleteSearchResults autocompleteSearchResults2 = createSample(5)
                .build();
        assertNotEquals(autocompleteSearchResults1, autocompleteSearchResults2);
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        final AutocompleteSearchResults autocompleteSearchResults = createSample(0)
                .setSearchResults(null)
                .add(new AutocompleteSearchResult.Builder()
                        .setType(AutocompleteSearchResult.TYPE_STOP)
                        .setName("random_name")
                        .setShortName("short_name")
                        .build())
                .build();
        final AutocompleteSearchResults output = adapter.fromJson(adapter.toJson(autocompleteSearchResults));
        assertEquals(autocompleteSearchResults, output);
    }

    @Test
    public void TypeAdapter_skip_divider() throws IOException {
        final AutocompleteSearchResults autocompleteSearchResults1 = createSample(3).build();
        final AutocompleteSearchResults.Builder builder = createSample(3);
        builder.getSearchResults().remove(0);
        final AutocompleteSearchResults output = adapter.fromJson(adapter.toJson(autocompleteSearchResults1));
        assertEquals(output, builder.build());
    }

    @Test
    public void typeadapter_check_read_null() throws Exception {
        assertNull(adapter.fromJson("null"));
    }

    @Test
    public void AutoCompleteSearchResult_hashCode_should_be_equal() {
        final AutocompleteSearchResults autocompleteSearchResults1 = createSample(7).build();
        final AutocompleteSearchResults autocompleteSearchResults2 = createSample(7).build();
        assertEquals(autocompleteSearchResults1.hashCode(), autocompleteSearchResults2.hashCode());
    }

    @Test
    public void AutoCompleteSearchResult_hashCode_should_not_be_equal() {
        final AutocompleteSearchResults autocompleteSearchResults1 = createSample(1).build();
        final AutocompleteSearchResults autocompleteSearchResults2 = createSample(2).build();
        assertNotEquals(autocompleteSearchResults1.hashCode(), autocompleteSearchResults2.hashCode());
    }

    @Test
    public void typeadapter_check_write_null() {
        assertEquals(adapter.toJson(null), "null");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void AutocompleteSearchResultsTest_check_list_immutablity() {
        final AutocompleteSearchResults results = createSample(20).build();
        results.getSearchResults().remove(0);
    }
}
