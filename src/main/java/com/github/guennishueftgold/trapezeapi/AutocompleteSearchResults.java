package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class AutocompleteSearchResults {
    private final List<AutocompleteSearchResult> mSearchResults;

    private AutocompleteSearchResults(Builder builder) {
        this.mSearchResults = Collections.unmodifiableList(builder.getSearchResults());
    }

    @Override
    public String toString() {
        return "AutocompleteSearchResults{" +
                "mSearchResults=" + mSearchResults +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutocompleteSearchResults that = (AutocompleteSearchResults) o;
        return Objects.equals(mSearchResults, that.mSearchResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mSearchResults);
    }

    public List<AutocompleteSearchResult> getSearchResults() {
        return mSearchResults;
    }

    public static final class Builder{

        private List<AutocompleteSearchResult> mSearchResults=new ArrayList<>();
        public AutocompleteSearchResults build(){
            return new AutocompleteSearchResults(this);
        }

        public List<AutocompleteSearchResult> getSearchResults() {
            return mSearchResults;
        }

        public Builder setSearchResults(List<AutocompleteSearchResult> searchResults) {
            this.mSearchResults.clear();
            if (searchResults != null)
                this.mSearchResults.addAll(searchResults);
            return this;
        }

        public Builder add(AutocompleteSearchResult result) {
            this.mSearchResults.add(result);
            return this;
        }
    }

    static final class Converter extends TypeAdapter<AutocompleteSearchResults> {

        private final TypeAdapter<AutocompleteSearchResult> mTypeAdapter;

        public Converter(Gson gson){
            this(gson.getAdapter(AutocompleteSearchResult.class));
        }

        public Converter(TypeAdapter<AutocompleteSearchResult> typeAdapter) {
            this.mTypeAdapter = typeAdapter;
        }

        @Override
        public void write(JsonWriter out, AutocompleteSearchResults value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginArray();
            for (AutocompleteSearchResult autocompleteSearchResult : value.getSearchResults()) {
                this.mTypeAdapter.write(out, autocompleteSearchResult);
            }
            out.endArray();
        }

        @Override
        public AutocompleteSearchResults read(JsonReader in) throws IOException {
            if(in.peek()== JsonToken.NULL){
                in.skipValue();
                return null;
            }
            Builder builder=new Builder();
            in.beginArray();
            while(in.hasNext()){
                final AutocompleteSearchResult result= this.mTypeAdapter.read(in);
                if(result==null||result.getType()==AutocompleteSearchResult.TYPE_DIVIDER)
                    continue;
                builder.add(result);
            }
            in.endArray();
            return builder.build();
        }
    }
}
