package ru.autotest.pet;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.autotest.data.StatusPetEnum;
import ru.autotest.dto.AddPetDTO;
import ru.autotest.dto.Category;
import ru.autotest.dto.ResponseErrorDTO;
import ru.autotest.dto.Tag;
import ru.autotest.services.PetApi;

import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static ru.autotest.dto.Tag.builder;


public class PetTest {
    PetApi petApi = new PetApi();
    static ResponseSpecification responseSpecification = null;

    @BeforeAll
    public static void setupResponseSpecification() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
    }

    /**
     * TC#1: Проверяем добавление животного POST: /pet
     * <p>
     * 1) созадть кошку по имени Milya
     * 2) проверить код 200
     * 3) проверить ответ содержит имя кошки Milya
     * 4) проверить что кошка создалась успешно - выполнив запрос 'получить кошку по id'
     * 5) проверить что имя вернулось Milya
     */

    @Test
    public void addNewPet() {
        Category category = Category.builder()
                .id(1L)
                .name("Cats")
                .build();

        Tag tag = builder()
                .id(1L)
                .name("Tags")
                .build();

        AddPetDTO addPetDTO = AddPetDTO.builder()
                .category(category)
                .id(11L)
                .name("Milya")
                .photoUrls(Collections.singletonList("url/Milya"))
                .status(StatusPetEnum.available)
                .tags(Collections.singletonList(tag))
                .build();


        petApi.addPet(addPetDTO)
                .spec(responseSpecification)
                .body("id", equalTo(11))
                .body("name", equalTo("Milya"))
                .body("category.name", equalTo("Cats"));


        petApi.getPetById(11L)
                .spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/petSchema.json"))
                .body("name", equalTo("Milya"));
    }

    /**
     * TC#2: Проверяем только обязательные поля POST: /pet
     */

    @Test
    public void mandatoryFieldsPet() {

        AddPetDTO addPetDTO = AddPetDTO.builder()
                .name("Anfisa")
                .photoUrls(Collections.singletonList("url/Anfisa"))
                .build();
        petApi.addPet(addPetDTO)
                .spec(responseSpecification)
                .body("name", equalTo("Anfisa"));
    }

    /**
     * TC#3: null и Empty POST: /pet
     * 1) отправить пустую json
     * 2) ответ должен быть обработан что отправили пустую json и код ошибки
     * 3) отправить null
     * 4) ответ должен быть обработан что отправили пустую json и код ошибки
     * <p>
     * !НО тут не верно возвращает!
     */

    @Test
    public void verifyNullEmptyPet() {

        AddPetDTO addPetDTO = AddPetDTO.builder().build();

        petApi.addPet(addPetDTO)
                .spec(responseSpecification)
                .body("id", notNullValue());

        AddPetDTO addPetDTO2 = AddPetDTO.builder()
                .name(null)
                .photoUrls(Collections.singletonList(null))
                .build();

        petApi.addPet(addPetDTO2)
                .spec(responseSpecification)
                .body("id", notNullValue());

    }

    /**
     * TC#4: Получить животное по айди GET: /pet/{petId}
     */

    @Test
    public void getPetById() {
        petApi.getPetById(11L)
                .spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/petSchema.json"))
                .body("name", equalTo("Milya"));
    }

    /**
     * TC#5: не найден GET: /pet/{petId}
     */

    @Test
    public void petNotFound() {

        ValidatableResponse validatableResponse = petApi.getPetById(0L)
                .statusCode(404)
                .time(lessThan(1159L));

        ResponseErrorDTO responseErrorDTO = validatableResponse.extract().as(ResponseErrorDTO.class);

        Assertions.assertAll("Verify response Error",
                () -> Assertions.assertEquals(1, responseErrorDTO.getCode(), "Incorrect Code"),
                () -> Assertions.assertEquals("error", responseErrorDTO.getType(), "Incorrect Type"),
                () -> Assertions.assertEquals("Pet not found", responseErrorDTO.getMessage(), "Incorrect Message"));
    }

}
