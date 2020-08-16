package org.example.utils;

import org.junit.Test;

public class GeoUtilsTest {

    @Test
    // Coords of Manchester City Centre
    public void shouldReturnOver50(){
        double x = GeoUtils.haversineDistance(Constants.LONDON_CENTER_LATITUDE, Constants.LONDON_CENTER_LONGITUDE,53.483959, -2.244644);
        assert x >= 50;
    }

    @Test
    // Coords of Buckingham Palace
    public void shouldReturnLessThan50(){
        double x = GeoUtils.haversineDistance(Constants.LONDON_CENTER_LATITUDE, Constants.LONDON_CENTER_LONGITUDE,51.501476, -0.140634);
        assert x <= 50;
    }
}
