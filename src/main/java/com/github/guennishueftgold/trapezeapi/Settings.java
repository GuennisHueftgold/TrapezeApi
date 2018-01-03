package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

/**
 * Server provided Settings
 */
public final class Settings {
    private final int mDefaultTimePreview;
    private final boolean mGeolocationEnabled;
    private final long mInitialLatitude;
    private final long mInitialLongitude;
    private final int mInitialZoom;
    private final boolean mMapEnabled;
    private final boolean mMapShowControls;
    private final boolean mMapShowStops;
    private final boolean mMapShowPatterns;
    private final boolean mMapShowVehicles;
    private final int mMaxZoom;
    private final int mMinZoom;
    private final boolean mMobileEnabled;
    private final boolean mSearchByRoutesEnabled;
    private final boolean mSearchByStoppointsEnabled;
    private final boolean mShowAboutDepartureText;
    private final boolean mShowActualColumn;
    private final boolean mShowDepartingText;
    private final boolean mShowDepArrText;
    private final boolean mShowMixedColumn;
    private final boolean mShowPassageTypeColumn;
    private final boolean mShowScheduleColumn;
    private final boolean mSuppressCountdownTimeIncrement;
    private final boolean mTimesliderEnabled;

    private Settings(Builder builder) {
        this.mDefaultTimePreview = builder.getDefaultTimePreview();
        this.mMapEnabled = builder.isMapEnabled();
        this.mMinZoom = builder.getMinZoom();
        this.mMaxZoom = builder.getMaxZoom();
        this.mTimesliderEnabled = builder.isTimesliderEnabled();
        this.mSuppressCountdownTimeIncrement = builder.isSuppressCountdownTimeIncrement();
        this.mShowScheduleColumn = builder.isShowScheduleColumn();
        this.mShowPassageTypeColumn = builder.isShowPassageTypeColumn();
        this.mShowMixedColumn = builder.isShowMixedColumn();
        this.mShowDepArrText = builder.isShowDepArrText();
        this.mShowDepartingText = builder.isShowDepartingText();
        this.mShowActualColumn = builder.isShowActualColumn();
        this.mShowAboutDepartureText = builder.isShowAboutDepartureText();
        this.mSearchByRoutesEnabled = builder.isSearchByRoutesEnabled();
        this.mSearchByStoppointsEnabled = builder.isSearchByStoppointsEnabled();
        this.mMobileEnabled = builder.isMobileEnabled();
        this.mInitialLatitude = builder.getInitialLatitude();
        this.mInitialLongitude = builder.getInitialLongitude();
        this.mInitialZoom = builder.getInitialZoom();
        this.mGeolocationEnabled = builder.isGeolocationEnabled();
        this.mMapShowControls = builder.isMapShowControls();
        this.mMapShowPatterns = builder.isMapShowPatterns();
        this.mMapShowStops = builder.isMapShowStops();
        this.mMapShowVehicles = builder.isMapShowVehicles();
    }

    public int getDefaultTimePreview() {
        return mDefaultTimePreview;
    }

    public boolean isGeolocationEnabled() {
        return mGeolocationEnabled;
    }

    public long getInitialLatitude() {
        return mInitialLatitude;
    }

    public long getInitialLongitude() {
        return mInitialLongitude;
    }

    /**
     * Get the initial zoom to be used
     *
     * @return initial zoom value
     */
    public int getInitialZoom() {
        return mInitialZoom;
    }

    /**
     *
     * @return
     */
    public boolean isMapEnabled() {
        return mMapEnabled;
    }

    /**
     *
     * @return
     */
    public boolean isMapShowControls() {
        return mMapShowControls;
    }

    public boolean isMapShowStops() {
        return mMapShowStops;
    }

    public boolean isMapShowPatterns() {
        return mMapShowPatterns;
    }

    public boolean isMapShowVehicles() {
        return mMapShowVehicles;
    }

    public int getMaxZoom() {
        return mMaxZoom;
    }

    public int getMinZoom() {
        return mMinZoom;
    }

    public boolean isMobileEnabled() {
        return mMobileEnabled;
    }

    public boolean isSearchByRoutesEnabled() {
        return mSearchByRoutesEnabled;
    }

    public boolean isSearchByStoppointsEnabled() {
        return mSearchByStoppointsEnabled;
    }

    public boolean isShowAboutDepartureText() {
        return mShowAboutDepartureText;
    }

    public boolean isShowActualColumn() {
        return mShowActualColumn;
    }

    public boolean isShowDepartingText() {
        return mShowDepartingText;
    }

    public boolean isShowDepArrText() {
        return mShowDepArrText;
    }

    public boolean isShowMixedColumn() {
        return mShowMixedColumn;
    }

    public boolean isShowPassageTypeColumn() {
        return mShowPassageTypeColumn;
    }

    public boolean isShowScheduleColumn() {
        return mShowScheduleColumn;
    }

    public boolean isSuppressCountdownTimeIncrement() {
        return mSuppressCountdownTimeIncrement;
    }

    public boolean isTimesliderEnabled() {
        return mTimesliderEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settings)) return false;

        Settings settings = (Settings) o;

        if (mDefaultTimePreview != settings.mDefaultTimePreview) return false;
        if (mGeolocationEnabled != settings.mGeolocationEnabled) return false;
        if (mInitialLatitude != settings.mInitialLatitude) return false;
        if (mInitialLongitude != settings.mInitialLongitude) return false;
        if (mInitialZoom != settings.mInitialZoom) return false;
        if (mMapEnabled != settings.mMapEnabled) return false;
        if (mMapShowControls != settings.mMapShowControls) return false;
        if (mMapShowStops != settings.mMapShowStops) return false;
        if (mMapShowPatterns != settings.mMapShowPatterns) return false;
        if (mMapShowVehicles != settings.mMapShowVehicles) return false;
        if (mMaxZoom != settings.mMaxZoom) return false;
        if (mMinZoom != settings.mMinZoom) return false;
        if (mMobileEnabled != settings.mMobileEnabled) return false;
        if (mSearchByRoutesEnabled != settings.mSearchByRoutesEnabled) return false;
        if (mSearchByStoppointsEnabled != settings.mSearchByStoppointsEnabled) return false;
        if (mShowAboutDepartureText != settings.mShowAboutDepartureText) return false;
        if (mShowActualColumn != settings.mShowActualColumn) return false;
        if (mShowDepartingText != settings.mShowDepartingText) return false;
        if (mShowDepArrText != settings.mShowDepArrText) return false;
        if (mShowMixedColumn != settings.mShowMixedColumn) return false;
        if (mShowPassageTypeColumn != settings.mShowPassageTypeColumn) return false;
        if (mShowScheduleColumn != settings.mShowScheduleColumn) return false;
        if (mSuppressCountdownTimeIncrement != settings.mSuppressCountdownTimeIncrement) return false;
        return mTimesliderEnabled == settings.mTimesliderEnabled;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDefaultTimePreview,
                mGeolocationEnabled,
                mInitialLatitude,
                mInitialLongitude,
                mInitialZoom,
                mMapEnabled,
                mMapShowControls,
                mMapShowStops,
                mMapShowPatterns,
                mMapShowVehicles,
                mMaxZoom,
                mMinZoom,
                mMobileEnabled,
                mSearchByRoutesEnabled,
                mSearchByStoppointsEnabled,
                mShowAboutDepartureText,
                mShowActualColumn,
                mShowDepartingText,
                mShowDepArrText,
                mShowMixedColumn,
                mShowPassageTypeColumn,
                mShowScheduleColumn,
                mSuppressCountdownTimeIncrement,
                mTimesliderEnabled);
    }

    @Override
    public String toString() {
        return "Settings{" +
                "mDefaultTimePreview=" + mDefaultTimePreview +
                ", mGeolocationEnabled=" + mGeolocationEnabled +
                ", mInitialLatitude=" + mInitialLatitude +
                ", mInitialLongitude=" + mInitialLongitude +
                ", mInitialZoom=" + mInitialZoom +
                ", mMapEnabled=" + mMapEnabled +
                ", mMapShowControls=" + mMapShowControls +
                ", mMapShowStops=" + mMapShowStops +
                ", mMapShowPatterns=" + mMapShowPatterns +
                ", mMapShowVehicles=" + mMapShowVehicles +
                ", mMaxZoom=" + mMaxZoom +
                ", mMinZoom=" + mMinZoom +
                ", mMobileEnabled=" + mMobileEnabled +
                ", mSearchByRoutesEnabled=" + mSearchByRoutesEnabled +
                ", mSearchByStoppointsEnabled=" + mSearchByStoppointsEnabled +
                ", mShowAboutDepartureText=" + mShowAboutDepartureText +
                ", mShowActualColumn=" + mShowActualColumn +
                ", mShowDepartingText=" + mShowDepartingText +
                ", mShowDepArrText=" + mShowDepArrText +
                ", mShowMixedColumn=" + mShowMixedColumn +
                ", mShowPassageTypeColumn=" + mShowPassageTypeColumn +
                ", mShowScheduleColumn=" + mShowScheduleColumn +
                ", mSuppressCountdownTimeIncrement=" + mSuppressCountdownTimeIncrement +
                ", mTimesliderEnabled=" + mTimesliderEnabled +
                '}';
    }

    /**
     * Builder used to construct {@link Settings}
     */
    public final static class Builder {

        private int mDefaultTimePreview;
        private boolean mGeolocationEnabled;
        private long mInitialLatitude;
        private long mInitialLongitude;
        private int mInitialZoom;
        private boolean mMapEnabled;
        private boolean mMapShowControls;
        private boolean mMapShowStops;
        private boolean mMapShowPatterns;
        private boolean mMapShowVehicles;
        private int mMaxZoom;
        private int mMinZoom;
        private boolean mMobileEnabled;
        private boolean mSearchByRoutesEnabled;
        private boolean mSearchByStoppointsEnabled;
        private boolean mShowAboutDepartureText;
        private boolean mShowActualColumn;
        private boolean mShowDepartingText;
        private boolean mShowDepArrText;
        private boolean mShowMixedColumn;
        private boolean mShowPassageTypeColumn;
        private boolean mShowScheduleColumn;
        private boolean mSuppressCountdownTimeIncrement;
        private boolean mTimesliderEnabled;

        public int getDefaultTimePreview() {
            return mDefaultTimePreview;
        }

        public Builder setDefaultTimePreview(int defaultTimePreview) {
            mDefaultTimePreview = defaultTimePreview;
            return this;
        }

        public boolean isGeolocationEnabled() {
            return mGeolocationEnabled;
        }

        public Builder setGeolocationEnabled(boolean geolocationEnabled) {
            mGeolocationEnabled = geolocationEnabled;
            return this;
        }

        public long getInitialLatitude() {
            return mInitialLatitude;
        }

        public Builder setInitialLatitude(long initialLatitude) {
            mInitialLatitude = initialLatitude;
            return this;
        }

        public long getInitialLongitude() {
            return mInitialLongitude;
        }

        public Builder setInitialLongitude(long initialLongitude) {
            mInitialLongitude = initialLongitude;
            return this;
        }

        public int getInitialZoom() {
            return mInitialZoom;
        }

        public Builder setInitialZoom(int initialZoom) {
            mInitialZoom = initialZoom;
            return this;
        }

        public boolean isMapEnabled() {
            return mMapEnabled;
        }

        public Builder setMapEnabled(boolean mapEnabled) {
            mMapEnabled = mapEnabled;
            return this;
        }

        public boolean isMapShowControls() {
            return mMapShowControls;
        }

        public Builder setMapShowControls(boolean mapShowControls) {
            mMapShowControls = mapShowControls;
            return this;
        }

        public boolean isMapShowStops() {
            return mMapShowStops;
        }

        public Builder setMapShowStops(boolean mapShowStops) {
            mMapShowStops = mapShowStops;
            return this;
        }

        public boolean isMapShowPatterns() {
            return mMapShowPatterns;
        }

        public Builder setMapShowPatterns(boolean mapShowPatterns) {
            mMapShowPatterns = mapShowPatterns;
            return this;
        }

        public boolean isMapShowVehicles() {
            return mMapShowVehicles;
        }

        public Builder setMapShowVehicles(boolean mapShowVehicles) {
            mMapShowVehicles = mapShowVehicles;
            return this;
        }

        public int getMaxZoom() {
            return mMaxZoom;
        }

        public Builder setMaxZoom(int maxZoom) {
            mMaxZoom = maxZoom;
            return this;
        }

        public int getMinZoom() {
            return mMinZoom;
        }

        public Builder setMinZoom(int minZoom) {
            mMinZoom = minZoom;
            return this;
        }

        public boolean isMobileEnabled() {
            return mMobileEnabled;
        }

        public Builder setMobileEnabled(boolean mobileEnabled) {
            mMobileEnabled = mobileEnabled;
            return this;
        }

        public boolean isSearchByRoutesEnabled() {
            return mSearchByRoutesEnabled;
        }

        public Builder setSearchByRoutesEnabled(boolean searchByRoutesEnabled) {
            mSearchByRoutesEnabled = searchByRoutesEnabled;
            return this;
        }

        public boolean isSearchByStoppointsEnabled() {
            return mSearchByStoppointsEnabled;
        }

        public Builder setSearchByStoppointsEnabled(boolean searchByStoppointsEnabled) {
            mSearchByStoppointsEnabled = searchByStoppointsEnabled;
            return this;
        }

        public boolean isShowAboutDepartureText() {
            return mShowAboutDepartureText;
        }

        public Builder setShowAboutDepartureText(boolean showAboutDepartureText) {
            mShowAboutDepartureText = showAboutDepartureText;
            return this;
        }

        public boolean isShowActualColumn() {
            return mShowActualColumn;
        }

        public Builder setShowActualColumn(boolean showActualColumn) {
            mShowActualColumn = showActualColumn;
            return this;
        }

        public boolean isShowDepartingText() {
            return mShowDepartingText;
        }

        public Builder setShowDepartingText(boolean showDepartingText) {
            mShowDepartingText = showDepartingText;
            return this;
        }

        public boolean isShowDepArrText() {
            return mShowDepArrText;
        }

        public Builder setShowDepArrText(boolean showDepArrText) {
            mShowDepArrText = showDepArrText;
            return this;
        }

        public boolean isShowMixedColumn() {
            return mShowMixedColumn;
        }

        public Builder setShowMixedColumn(boolean showMixedColumn) {
            mShowMixedColumn = showMixedColumn;
            return this;
        }

        public boolean isShowPassageTypeColumn() {
            return mShowPassageTypeColumn;
        }

        public Builder setShowPassageTypeColumn(boolean showPassageTypeColumn) {
            mShowPassageTypeColumn = showPassageTypeColumn;
            return this;
        }

        public boolean isShowScheduleColumn() {
            return mShowScheduleColumn;
        }

        public Builder setShowScheduleColumn(boolean showScheduleColumn) {
            mShowScheduleColumn = showScheduleColumn;
            return this;
        }

        public boolean isSuppressCountdownTimeIncrement() {
            return mSuppressCountdownTimeIncrement;
        }

        public Builder setSuppressCountdownTimeIncrement(boolean suppressCountdownTimeIncrement) {
            mSuppressCountdownTimeIncrement = suppressCountdownTimeIncrement;
            return this;
        }

        public boolean isTimesliderEnabled() {
            return mTimesliderEnabled;
        }

        public Builder setTimesliderEnabled(boolean timesliderEnabled) {
            mTimesliderEnabled = timesliderEnabled;
            return this;
        }

        public Settings build() {
            return new Settings(this);
        }
    }

    final static class Converter extends TypeAdapter<Settings> {
        /*
  "DEFAULT_TIME_PREVIEW": 30,
          "GEOLOCATION_ENABLED": false,
          "INITIAL_LAT": 195530731,
          "INITIAL_LON": 36473446,
          "INITIAL_ZOOM": 14,
          "MAP_ENABLED": false,
          "MAP_SHOW_CONTROLS": true,
          "MAP_SHOW_PATTERNS": true,
          "MAP_SHOW_STOPS": true,
          "MAP_SHOW_VEHICLES": true,
          "MAX_ZOOM": 19,
          "MIN_ZOOM": 13,
          "MOBILE_ENABLED": true,
          "SEARCH_BY_ROUTES_ENABLED": false,
          "SEARCH_BY_STOPPOINTS_ENABLED": false,
          "SHOW_ABOUT_DEPARTURE_TEXT": true,
          "SHOW_ACTUAL_COLUMN": false,
          "SHOW_DEPARTING_TEXT": true,
          "SHOW_DEP_ARR_TEXT": false,
          "SHOW_MIXED_COLUMN": true,
          "SHOW_PASSAGETYPE_COLUMN": false,
          "SHOW_SCHEDULE_COLUMN": false,
          "SUPPRESS_COUNTDOWN_TIME_INCREMENT": false,
          "TIMESLIDER_ENABLED": false
          */
        final static String NAME_DEFAULT_TIME_PREVIEW = "DEFAULT_TIME_PREVIEW",
                NAME_GEOLOCATION_ENABLED = "GEOLOCATION_ENABLED",
                NAME_INITIAL_LATITUDE = "INITIAL_LAT",
                NAME_INITIAL_LONGITUDE = "INITIAL_LON",
                NAME_INITIAL_ZOOM = "INITIAL_ZOOM",
                NAME_MAP_ENABLED = "MAP_ENABLED",
                NAME_MAP_SHOW_CONTROLS = "MAP_SHOW_CONTROLS",
                NAME_MAP_SHOW_PATTERNS = "MAP_SHOW_PATTERNS",
                NAME_MAP_SHOW_STOPS = "MAP_SHOW_STOPS",
                NAME_MAP_SHOW_VEHICLES = "MAP_SHOW_VEHICLES",
                NAME_MAX_ZOOM = "MAX_ZOOM",
                NAME_MIN_ZOOM = "MIN_ZOOM",
                NAME_MOBILE_ENABLED = "MOBILE_ENABLED",
                NAME_SEARCH_BY_ROUTES_ENABLED = "SEARCH_BY_ROUTES_ENABLED",
                NAME_SEARCH_BY_STOPPOINTS_ENABLED = "SEARCH_BY_STOPPOINTS_ENABLED",
                NAME_SHOW_ABOUT_DEPARTURE_TEXT = "SHOW_ABOUT_DEPARTURE_TEXT",
                NAME_SHOW_ACTUAL_COLUMN = "SHOW_ACTUAL_COLUMN",
                NAME_SHOW_DEPARTING_TEXT = "SHOW_DEPARTING_TEXT",
                NAME_SHOW_DEP_ARR_TEXT = "SHOW_DEP_ARR_TEXT",
                NAME_SHOW_MIXED_COLUMN = "SHOW_MIXED_COLUMN",
                NAME_SHOW_PASSAGETYPE_COLUMN = "SHOW_PASSAGETYPE_COLUMN",
                NAME_SHOW_SCHEDULE_COLUMN = "SHOW_SCHEDULE_COLUMN",
                NAME_SUPPRESS_COUNTDOWN_TIME_INCREMENT = "SUPPRESS_COUNTDOWN_TIME_INCREMENT",
                NAME_TIMESLIDER_ENABLED = "TIMESLIDER_ENABLED";

        @Override
        public void write(JsonWriter jsonWriter, Settings settings) throws IOException {
            if (settings == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(NAME_TIMESLIDER_ENABLED)
                    .value(settings.isTimesliderEnabled())
                    .name(NAME_DEFAULT_TIME_PREVIEW)
                    .value(settings.getDefaultTimePreview())
                    .name(NAME_INITIAL_LATITUDE)
                    .value(settings.getInitialLatitude())
                    .name(NAME_INITIAL_LONGITUDE)
                    .value(settings.getInitialLongitude())
                    .name(NAME_INITIAL_ZOOM)
                    .value(settings.getInitialZoom())
                    .name(NAME_GEOLOCATION_ENABLED)
                    .value(settings.isGeolocationEnabled())
                    .name(NAME_MAP_ENABLED)
                    .value(settings.isMapEnabled())
                    .name(NAME_MAP_SHOW_CONTROLS)
                    .value(settings.isMapShowControls())
                    .name(NAME_MAP_SHOW_PATTERNS)
                    .value(settings.isMapShowPatterns())
                    .name(NAME_MAP_SHOW_STOPS)
                    .value(settings.isMapShowStops())
                    .name(NAME_MAP_SHOW_VEHICLES)
                    .value(settings.isMapShowVehicles())
                    .name(NAME_MOBILE_ENABLED)
                    .value(settings.isMobileEnabled())
                    .name(NAME_SEARCH_BY_ROUTES_ENABLED)
                    .value(settings.isSearchByRoutesEnabled())
                    .name(NAME_SEARCH_BY_STOPPOINTS_ENABLED)
                    .value(settings.isSearchByStoppointsEnabled())
                    .name(NAME_SHOW_ABOUT_DEPARTURE_TEXT)
                    .value(settings.isShowAboutDepartureText())
                    .name(NAME_SHOW_DEPARTING_TEXT)
                    .value(settings.isShowDepartingText())
                    .name(NAME_SHOW_DEP_ARR_TEXT)
                    .value(settings.isShowDepArrText())
                    .name(NAME_SHOW_MIXED_COLUMN)
                    .value(settings.isShowMixedColumn())
                    .name(NAME_SHOW_ACTUAL_COLUMN)
                    .value(settings.isShowActualColumn())
                    .name(NAME_SHOW_PASSAGETYPE_COLUMN)
                    .value(settings.isShowPassageTypeColumn())
                    .name(NAME_SUPPRESS_COUNTDOWN_TIME_INCREMENT)
                    .value(settings.isSuppressCountdownTimeIncrement())
                    .name(NAME_SHOW_SCHEDULE_COLUMN)
                    .value(settings.isShowScheduleColumn())
                    .name(NAME_MIN_ZOOM)
                    .value(settings.getMinZoom())
                    .name(NAME_MAX_ZOOM)
                    .value(settings.getMaxZoom());
            jsonWriter.endObject();
        }

        @Override
        public Settings read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                return null;
            }
            jsonReader.beginObject();
            Builder builder = new Builder();
            String name;
            while (jsonReader.hasNext()) {
                name = jsonReader.nextName();
                switch (name) {
                    case NAME_TIMESLIDER_ENABLED:
                        builder.setTimesliderEnabled(jsonReader.nextBoolean());
                        break;
                    case NAME_SUPPRESS_COUNTDOWN_TIME_INCREMENT:
                        builder.setSuppressCountdownTimeIncrement(jsonReader.nextBoolean());
                        break;
                    case NAME_SHOW_SCHEDULE_COLUMN:
                        builder.setShowScheduleColumn(jsonReader.nextBoolean());
                        break;
                    case NAME_SHOW_PASSAGETYPE_COLUMN:
                        builder.setShowPassageTypeColumn(jsonReader.nextBoolean());
                        break;
                    case NAME_DEFAULT_TIME_PREVIEW:
                        builder.setDefaultTimePreview(jsonReader.nextInt());
                        break;
                    case NAME_GEOLOCATION_ENABLED:
                        builder.setGeolocationEnabled(jsonReader.nextBoolean());
                        break;
                    case NAME_INITIAL_LATITUDE:
                        builder.setInitialLatitude(jsonReader.nextLong());
                        break;
                    case NAME_INITIAL_LONGITUDE:
                        builder.setInitialLongitude(jsonReader.nextLong());
                        break;
                    case NAME_INITIAL_ZOOM:
                        builder.setInitialZoom(jsonReader.nextInt());
                        break;
                    case NAME_MAP_ENABLED:
                        builder.setMapEnabled(jsonReader.nextBoolean());
                        break;
                    case NAME_MAP_SHOW_CONTROLS:
                        builder.setMapShowControls(jsonReader.nextBoolean());
                        break;
                    case NAME_MAP_SHOW_PATTERNS:
                        builder.setMapShowPatterns(jsonReader.nextBoolean());
                        break;
                    case NAME_MAP_SHOW_STOPS:
                        builder.setMapShowStops(jsonReader.nextBoolean());
                        break;
                    case NAME_MAP_SHOW_VEHICLES:
                        builder.setMapShowVehicles(jsonReader.nextBoolean());
                        break;
                    case NAME_MAX_ZOOM:
                        builder.setMaxZoom(jsonReader.nextInt());
                        break;
                    case NAME_MIN_ZOOM:
                        builder.setMinZoom(jsonReader.nextInt());
                        break;
                    case NAME_MOBILE_ENABLED:
                        builder.setMobileEnabled(jsonReader.nextBoolean());
                        break;
                    case NAME_SEARCH_BY_ROUTES_ENABLED:
                        builder.setSearchByRoutesEnabled(jsonReader.nextBoolean());
                        break;
                    case NAME_SEARCH_BY_STOPPOINTS_ENABLED:
                        builder.setSearchByStoppointsEnabled(jsonReader.nextBoolean());
                        break;
                    case NAME_SHOW_ABOUT_DEPARTURE_TEXT:
                        builder.setShowAboutDepartureText(jsonReader.nextBoolean());
                        break;
                    case NAME_SHOW_ACTUAL_COLUMN:
                        builder.setShowActualColumn(jsonReader.nextBoolean());
                        break;
                    case NAME_SHOW_DEPARTING_TEXT:
                        builder.setShowDepartingText(jsonReader.nextBoolean());
                        break;
                    case NAME_SHOW_DEP_ARR_TEXT:
                        builder.setShowDepArrText(jsonReader.nextBoolean());
                        break;
                    case NAME_SHOW_MIXED_COLUMN:
                        builder.setShowMixedColumn(jsonReader.nextBoolean());
                        break;
                    default:
                        Logger.reportUnknownName(this, name, jsonReader.peek());
                        jsonReader.skipValue();
                        break;
                }
            }
            jsonReader.endObject();
            return builder.build();
        }
    }
}
