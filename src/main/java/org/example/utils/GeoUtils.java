package org.example.utils;

public final class GeoUtils {


    private GeoUtils() {
    }

    public static double haversineDistance(final double sourceLat, final double sourceLon,
                                           final double destinationLat, final double destinationLon) {
        double differenceLat = Math.toRadians(destinationLat - sourceLat);
        double differenceLng = Math.toRadians(destinationLon - sourceLon);
        double sourceLatInRadians = Math.toRadians(sourceLat);
        double destinationLatInRadians = Math.toRadians(destinationLat);

        double a = Math.pow(Math.sin(differenceLat / 2),2) + Math.pow(Math.sin(differenceLng / 2),2) * Math.cos(sourceLatInRadians) * Math.cos(destinationLatInRadians);
        double c = 2 * Math.asin(Math.sqrt(a));
        return Constants.RADIUS_OF_GLOBE_IN_MILES * c ;
    }

}
