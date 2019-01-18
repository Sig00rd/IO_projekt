package com.example.demo.utils;

import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class EarthDist {

	private static String apiKey = "AIzaSyDTcMb9tsqsJSfWymNME76U1Zze1bORsSg";

	// returns distance in kilometers
	public static double distance(double lat1, double lat2, double lon1,
			double lon2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1))
						* Math.cos(Math.toRadians(lat2))
						* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c;

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance);
	}
	public static Double[] lookupCoord(String establishment)
			throws ApiException, InterruptedException, IOException {

		// set up key
		GeoApiContext lookupDoodad = new GeoApiContext().setApiKey(apiKey);
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(lookupDoodad, establishment).await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LatLng coords = null;
		if (results != null) {
			coords = (results[0].geometry.location);
		}
		Double[] result = null;
		if (coords != null) {
			result = new Double[2];
			result[0] = coords.lat;
			result[1] = coords.lng;
		}
		return result;
	}
}