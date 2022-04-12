package com.velib;


public final class GeoUtil {

    public static Double computeDistanceInKilometers(Double lat1, Double lon1, Double lat2, Double lon2){


        if (lat1.equals(lat2) && (lon1.equals(lon2))) {
            return 0.00;
        }

        else {
            double theta = lon1 - lon2;
            double distance = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            distance = Math.acos(distance);
            distance = Math.toDegrees(distance);
            distance = distance * 60 * 1.1515;
            distance = distance * 1.609344;

            return distance;
        }
    }
}
