import api.Vet.Vet;
import api.Vet.VetClient;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VetApiTest<apiUrl> {

    static String apiUrl;
    private String vetId;

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }


    @Test
    public void get_Create_Delete () throws InvalidResponseException{
        getVetInfo();
        createVetInfo();
        deleteVetInfo();
    }


    public void getVetInfo() throws InvalidResponseException {
        VetClient client = new VetClient(apiUrl);
        Vet[] vets = client.getVet();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(vets[1].getFirstName()).isEqualTo("Helen");
        softly.assertThat(vets[1].getLastName()).isEqualTo("Leary");

        softly.assertAll();
    }




    public void createVetInfo() throws InvalidResponseException {

        VetClient client = new VetClient(apiUrl);
        Vet createdVet = client.createVet(Vet.builder().firstName("prishika").lastName("peter").build());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createdVet.getFirstName()).isEqualTo("prishika");
        softly.assertThat(createdVet.getLastName()).isEqualTo("peter");

        vetId=createdVet.getId();
    }



    public void deleteVetInfo() throws InvalidResponseException{

        VetClient client = new VetClient(apiUrl,vetId);
        ApiResponse<Vet[]> deleteVet =client.deleteId();

        SoftAssertions softly= new SoftAssertions();
        softly.assertThat(deleteVet.getHttpStatusCode()).isEqualTo(204);


        softly.assertAll();

    }





}
