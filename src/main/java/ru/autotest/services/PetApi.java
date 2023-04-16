package ru.autotest.services;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import ru.autotest.dto.AddPetDTO;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class PetApi {

    private static final String BASE_URL = System.getProperty("BASE_URL", "https://petstore.swagger.io/v2").trim().toLowerCase(Locale.ROOT);
    private static final String BASE_PATH = "/pet";

    private final RequestSpecification requestSpecification;
    private final ResponseSpecification responseSpecification;

    private ValidatableResponse validatableResponse;

    public PetApi() {
        requestSpecification =
                given()
                        .baseUri(BASE_URL + BASE_PATH)
                        .contentType(ContentType.JSON);

        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
    }

    public ValidatableResponse addPet(AddPetDTO addPetDTO) {

        return validatableResponse = given(requestSpecification)
                .log().all()
                .body(addPetDTO)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .log().all();
    }

    public ValidatableResponse getPetById(Long petId) {
        return validatableResponse = given(requestSpecification)
                .log().all()
                .pathParam("petId", petId)
                .when()
                .get("/{petId}")
                .then()
                .spec(responseSpecification)
                .log().all();
    }

    public void verifyPetSchema() {
        validatableResponse.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/petSchema.json"));
    }

}
