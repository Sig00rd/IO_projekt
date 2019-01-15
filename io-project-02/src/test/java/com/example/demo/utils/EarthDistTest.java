package com.example.demo.utils;


import com.example.demo.test_utils.TestUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EarthDistTest
{
    @Test
    public void whenDistance_givenTheSamePointTwice_thenReturnZero() {
        // given
        double point_latitude = 17.325;
        double point_longitude = 0.63;

        // when
        double distance = EarthDist.distance(
                point_latitude, point_latitude,
                point_longitude, point_longitude);

        // then
        assertThat(distance).isEqualTo(0.0);

    }

    @Test
    public void whenDistance_givenDistantPoints_thenReturnCorrectDistance() {
        // given
        double lat1 = 54.3520500;
        double lat2 = 49.683333;
        double lon1 = 18.6463700;
        double lon2 = 21.75;

        // when
        double distance = EarthDist.distance(
                lat1, lat2,
                lon1, lon2);

        double distance_from_other_source = 560.74;
        double absolute_margin = 0.05;
        double difference = Math.abs(distance - distance_from_other_source);

        // then
        assertThat(difference).isLessThanOrEqualTo(absolute_margin);
    }

    @Test
    public void whenDistance_givenClosePoints_thenReturnCorrectDistance() {
        // given
        double lat1 = 50.0683;
        double lon1 = 19.9045;
        double lat2 = 50.068024;
        double lon2 = 19.908254;

        double distance = EarthDist.distance(
                lat1, lat2,
                lon1, lon2);

        double distance_from_other_source = 0.27;
        double absolute_margin = 0.01;
        double difference = Math.abs(distance - distance_from_other_source);

        // then
        assertThat(difference).isLessThanOrEqualTo(absolute_margin);
    }
}
