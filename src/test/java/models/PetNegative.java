package models;

import enums.PetStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Category;
import models.Tag;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PetNegative {
    private Integer id;
    private Integer name;
    private Category category;
    private List<String> photoUrls;
    private List<Tag> tags;
    private PetStatus status;
}
