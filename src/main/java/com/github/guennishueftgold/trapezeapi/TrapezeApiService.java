package com.github.guennishueftgold.trapezeapi;


import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TrapezeApiService {
    String PATH_STATION_LOCATIONS = "geoserviceDispatcher/services/stopinfo/stops";
    String PATH_PATH_INFO_BY_TRIP_ID = "geoserviceDispatcher/services/pathinfo/trip";
    String PATH_PATH_INFO_BY_ROUTE_ID = "geoserviceDispatcher/services/pathinfo/route";

    /**
     * Gets the stop
     *
     * @param stop the stop
     * @param mode the mode
     * @return the stop information
     * @since 1.0.0
     */
    @FormUrlEncoded
    @POST("services/passageInfo/stopPassages/stop")
    Call<Station> getStation(@Field("stop") String stop,
                             @Field("mode") String mode);

    /**
     * Gets the stop with route
     *
     * @param stop      the stop to look up
     * @param mode      the mode
     * @param routeId   the route id
     * @param authority the authority
     * @param direction the direction
     * @return get stop passages
     * @since 1.0.0
     */
    @FormUrlEncoded
    @POST("services/passageInfo/stopPassages/stop")
    Call<Station> getStationWithRoute(@Field("stop") String stop,
                                      @Field("mode") String mode,
                                      @Field("routeId") String routeId,
                                      @Field("authority") String authority,
                                      @Field("direction") String direction);

    /**
     * Looks up stops for the query
     *
     * @param query autocomplete query
     * @return the lookup result
     * @since 1.0.0
     */
    @FormUrlEncoded
    @POST("services/lookup/autocomplete/json")
    Call<List<AutocompleteSearchResult>> getAutocomplete(@Field("query") String query);

    /**
     * Retrieves the trip passages
     *
     * @param tripId    the trip id
     * @param vehicleId the vehicle id
     * @param mode      the mode
     * @return the passages of the trip
     * @since 1.0.0
     */
    @FormUrlEncoded
    @POST("services/tripInfo/tripPassages")
    Call<TripPassages> getTripPassages(@Field("tripId") String tripId, @Field("vehicleId") String vehicleId, @Field("mode") String mode);

    /**
     * Retrieves the trip passages
     *
     * @param tripId the trip id
     * @param mode   the mode
     * @return the passages of the trip
     * @since 1.0.0
     */
    @FormUrlEncoded
    @POST("services/tripInfo/tripPassages")
    Call<TripPassages> getTripPassages(@Field("tripId") String tripId, @Field("mode") String mode);

    /**
     * get all stops
     *
     * @return the stops
     * @since 1.0.0
     */
    @FormUrlEncoded
    @POST("services/lookup/stopsByCharacter?character=")
    Call<StopsByCharacterResult> getAllStops();

    /**
     * Get the stops for the provided route id
     *
     * @param routeId the route id
     * @return the route stops
     * @since 1.0.0
     */
    @FormUrlEncoded
    @POST("services/routeInfo/routeStops")
    Call<StopsByCharacterResult> getRouteStops(@Field("routeId") String routeId);

    /**
     * Searches for stops by first character
     *
     * @param character the character to lookup
     * @return the search result
     * @since 1.0.0
     */
    @FormUrlEncoded
    @POST("services/lookup/stopsByCharacter")
    Call<StopsByCharacterResult> getStopsByCharacter(@Query("character") String character);

    /**
     * Searches for stops by full name
     *
     * @param search the search term to look up
     * @return the search result
     * @since 1.0.0
     */
    @FormUrlEncoded
    @POST("services/lookup/fulltext")
    Call<FulltextSearch> getStopsByName(@Field("search") String search);

    @GET("geoserviceDispatcher/services/vehicleinfo/vehicles?positionType=CORRECTED&colorType=ROUTE")
    Call<VehicleLocations> getVehicleLocations();

    @GET(PATH_STATION_LOCATIONS + "?left=-648000000&bottom=-324000000&right=648000000&top=324000000")
    Call<StationLocations> getStationLocations();

    @GET("geoserviceDispatcher/services/stopinfo/stopPoints?left=-648000000&bottom=-324000000&right=648000000&top=324000000")
    Call<StopPoints> getStopPoints();

    @GET("geoserviceDispatcher/services/pathinfo/vehicle")
    Call<VehiclePathInfo> getPathInfoByVehicleId(@Query("id") final String vehicleId);

    @GET(PATH_PATH_INFO_BY_TRIP_ID)
    Call<VehiclePathInfo> getPathInfoByTripId(@Query("id") final String tripId);

    @GET(PATH_PATH_INFO_BY_ROUTE_ID)
    Call<VehiclePathInfo> getPathInfoByRouteId(@Query("id") final String routeId);

    /**
     * Gets the server provided settings
     *
     * @return A call providing the settings
     * @since 1.2.0
     */
    @GET("settings")
    Call<Settings> getSettings();
}