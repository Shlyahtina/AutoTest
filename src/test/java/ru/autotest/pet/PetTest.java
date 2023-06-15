package ru.autotest.pet;

import org.junit.jupiter.api.Test;
import ru.autotest.data.StatusPetEnum;
import ru.autotest.dto.AddPetDTO;
import ru.autotest.dto.Category;
import ru.autotest.dto.Tag;
import ru.autotest.services.PetApi;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static ru.autotest.dto.Tag.builder;


public class PetTest {
    PetApi petApi = new PetApi();

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
                .status(StatusPetEnum.AVAILABLE)
                .tags(Collections.singletonList(tag))
                .build();


        petApi.addPet(addPetDTO)
                .statusCode(200)
                .body("id", equalTo(11))
                .body("name", equalTo("Milya"))
                .body("category.name", equalTo("Cats"));


        petApi.getPetById(11L)
                .statusCode(200)
                .body("name", equalTo("Milya"));
        petApi.verifyPetSchema();
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
                .statusCode(200)
                .body("name", equalTo("Anfisa"));
    }


    /**
     * TC#4: Получить животное по айди GET: /pet/{petId}
     */

    @Test
    public void getPetById() {
        petApi.getPetById(11L)
                .statusCode(200)
                .body("name", equalTo("Milya"));
        petApi.verifyPetSchema();
    }

}
