package ru.autotest.pet;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.autotest.dto.AddPetDTO;
import ru.autotest.dto.ResponseErrorDTO;
import ru.autotest.services.PetApi;

import java.util.Collections;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class NegativePetTest {
    PetApi petApi = new PetApi();

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
                .statusCode(200)
                .body("id", notNullValue());

        AddPetDTO addPetDTO2 = AddPetDTO.builder()
                .name(null)
                .photoUrls(Collections.singletonList(null))
                .build();

        petApi.addPet(addPetDTO2)
                .statusCode(200)
                .body("id", notNullValue());

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
