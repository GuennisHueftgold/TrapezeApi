package com.github.guennishueftgold.trapezeapi;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationLocation implements LatLngInterface {

    @Expose
    @SerializedName("category")
    private String mCategory;
    @Expose
    @SerializedName("id")
    private String mId;
    @Expose
    @SerializedName("latitude")
    private long mLatitude;
    @Expose
    @SerializedName("longitude")
    private long mLongitude;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("shortName")
    private String mShortName;

    /**
     * Gets the stops category
     *
     * @return the category
     */
    public String getCategory() {
        return mCategory;
    }

    /**
     * Gets the stops id
     *
     * @return the id
     */
    public String getId() {
        return mId;
    }

    public long getLatitude() {
        return mLatitude;
    }

    public long getLongitude() {
        return mLongitude;
    }

    /**
     * Gets the stops name
     *
     * @return stop name
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets the Stops short name
     *
     * @return short name
     */
    public String getShortName() {
        return mShortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationLocation)) return false;

        StationLocation that = (StationLocation) o;

        if (mLatitude != that.mLatitude) return false;
        if (mLongitude != that.mLongitude) return false;
        if (mCategory != null ? !mCategory.equals(that.mCategory) : that.mCategory != null) return false;
        if (mId != null ? !mId.equals(that.mId) : that.mId != null) return false;
        if (mName != null ? !mName.equals(that.mName) : that.mName != null) return false;
        return mShortName != null ? mShortName.equals(that.mShortName) : that.mShortName == null;
    }

    @Override
    public int hashCode() {
        int result = mCategory != null ? mCategory.hashCode() : 0;
        result = 31 * result + (mId != null ? mId.hashCode() : 0);
        result = 31 * result + (int) (mLatitude ^ (mLatitude >>> 32));
        result = 31 * result + (int) (mLongitude ^ (mLongitude >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mShortName != null ? mShortName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StationLocation{" +
                "mCategory='" + mCategory + '\'' +
                ", mId='" + mId + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                ", mName='" + mName + '\'' +
                ", mShortName='" + mShortName + '\'' +
                '}';
    }
}
