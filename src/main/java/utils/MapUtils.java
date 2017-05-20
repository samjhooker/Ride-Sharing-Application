package utils;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import models.Stop;

import java.io.IOException;

/**
 * Created by samuelhooker on 20/05/17.
 */
public class MapUtils {

    private static final String GOOGLE_API_KEY = "AIzaSyB524c8FV_g_2afEJM0NYgHnhHEzp8bstE";
    private static final double UC_LATITUDE = -43.5235;
    private static final double UC_LONGITUDE = 172.5839;



    public static Stop getStopFromAddress (String address){
        GeoApiContext context = new GeoApiContext().setApiKey(GOOGLE_API_KEY);
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context,
                    address).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(results.length >= 1 && results[0] != null){
            Stop stop = new Stop(results[0].formattedAddress, results[0].geometry.location.lat, results[0].geometry.location.lng);
            return stop;
        }else {
            return null;
        }

    }

    public static double distanceToUni(double lat1, double lng1) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(UC_LATITUDE-lat1);
        double dLng = Math.toRadians(UC_LONGITUDE-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(UC_LATITUDE)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (double) (earthRadius * c);

        return dist/1000;
    }

    public static double calculatePrice(double distance, double litresPer100km){
        return 2.0 + (0.15 * litresPer100km * distance);
    }
}

