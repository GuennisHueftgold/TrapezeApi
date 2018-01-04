package com.github.guennishueftgold.trapezeapi;

/**
 * A interface implementing common coordinate methods
 *
 * @since 1.0.0
 */
public interface LatLngInterface {

    /**
     * Gets the longitude
     *
     * @return longitude in arc-milisecond
     * @apiNote To convert the value to an value that can be used in e.g. Google Maps
     * divide the value with {@link TrapezeApiClient#COORDINATES_CONVERTION_CONSTANT}
     * @since 1.0.0
     */
    long getLongitude();

    /**
     * Gets the latitude
     *
     * @return latitude in arc-milisecond
     * @apiNote To convert the value to an value that can be used in e.g. Google Maps
     * divide the value with {@link TrapezeApiClient#COORDINATES_CONVERTION_CONSTANT}
     * @since 1.0.0
     */
    long getLatitude();
}
