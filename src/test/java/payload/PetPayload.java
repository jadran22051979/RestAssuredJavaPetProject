package payload;

import enums.PetStatus;
import models.Category;
import models.Pet;
import models.Tag;
import net.datafaker.Faker;

import java.util.Arrays;

public class PetPayload {

    public static Pet petDto(PetStatus petStatus) {
        Faker faker = new Faker();
        return Pet.builder()
                .id(faker.number().positive())
                .name(faker.animal().name())
                .photoUrls(Arrays.asList(faker.internet().url(), faker.internet().url()))
                .category(
                        Category.builder()
                                .id(faker.number().positive())
                                .name(faker.text().text())
                                .build())
                .tags(Arrays.asList(
                        Tag.builder()
                                .id(faker.number().positive())
                                .name(faker.text().text())
                                .build(),
                        Tag.builder()
                                .id(faker.number().positive())
                                .name(faker.text().text())
                                .build()))
                .status(petStatus)
                .build();


    }

 
}
