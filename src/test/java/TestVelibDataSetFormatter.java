import com.velib.VelibDataSetFormatter;
import com.velib.model.exercice2.Station;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestVelibDataSetFormatter {

    @Test
    public void formatDataSetShouldReturnNonEmptyMap(){

        VelibDataSetFormatter velibDataSetFormatter = new VelibDataSetFormatter("src/test/resources/velib_dataset_test.txt");

        Map<Integer, List<Station>> formattedDataSet = velibDataSetFormatter.formatDataSet();

        Station station = Station.builder()
                .StationName("Favart - Italiens")
                .numberOfBikesAvailable(2)
                .numberOfDocksAvailable(2)
                .build();

        assertThat(formattedDataSet.size(), is(1));
        assertThat(formattedDataSet.get(5).get(0), is(station));

    }

    @Test
    public void formatDataSetShouldReturnEmptyMapWhenBadJson(){
        VelibDataSetFormatter velibDataSetFormatter = new VelibDataSetFormatter("src/test/resources/velib_dataset_test_bad_format.txt");

        Map<Integer, List<Station>> formattedDataSet = velibDataSetFormatter.formatDataSet();
        assertThat(formattedDataSet.size(), is(0));

    }
}
