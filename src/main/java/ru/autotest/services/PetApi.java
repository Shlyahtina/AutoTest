package ru.autotest.services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.autotest.dto.AddPetDTO;

import static io.restassured.RestAssured.given;

public class PetApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET = "/pet";
    private static final String GET_PET = "/pet/{petId}";

    private final RequestSpecification requestSpecification;

    public PetApi() {
        requestSpecification =
                given()
                        .baseUri(BASE_URL)
                        .contentType(ContentType.JSON);
    }

    public ValidatableResponse addPet(AddPetDTO addPetDTO) {

        return given(requestSpecification)
                .log().all()
                .body(addPetDTO)
                .when()
                .post(PET)
                .then()
                .log().all();
    }

    public ValidatableResponse getPetById(Long petId) {
        return given(requestSpecification)
                .log().all()
                .pathParam("petId", petId)
                .when()
                .get(GET_PET)
                .then()
                .log().all();
    }
}
