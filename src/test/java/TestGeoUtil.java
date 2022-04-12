import com.velib.GeoUtil;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
public class TestGeoUtil {

    @Test
    public void testComputeDistanceInKilometers(){

        // Coordinates for the address : 27 Boulevard des Italiens, 75002 Paris
        Double lat = 48.87110230451192;
        Double lon = 2.33535951179537;


        // Coordinates for the address : Charonne - Robert et Sonia Delauney
        Double lat2 = 48.85590755596891;
        Double lon2 = 2.3925706744194035;


        Double distanceInKilometers = GeoUtil.computeDistanceInKilometers(lat, lon, lat2, lon2);

        assertThat(distanceInKilometers, is(4.512979459794036));
    }

    @Test
    public void testComputeDistanceInKilometersWhenCoordinatesAreTheSame(){

        // Coordinates for the address : 27 Boulevard des Italiens, 75002 Paris
        Double lat = 48.87110230451192;
        Double lon = 2.33535951179537;

        Double distanceInKilometers = GeoUtil.computeDistanceInKilometers(lat, lon, lat, lon);

        assertThat(distanceInKilometers, is(0.00));
    }
}
