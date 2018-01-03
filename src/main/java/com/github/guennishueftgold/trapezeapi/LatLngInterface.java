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
     * @since 1.0.0
     */
    long getLongitude();

    /**
     * Gets the latitude
     *
     * @return latitude in arc-milisecond
     * @since 1.0.0
     */
    long getLatitude();
}
