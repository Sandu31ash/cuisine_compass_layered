package lk.ijse.cuisineCompass.view.tdm;

import lombok.*;


@Data
@AllArgsConstructor

public class CategoryTM implements Comparable<CategoryTM>{

    private String code;
    private String type;
    private String des;

    @Override
    public int compareTo(CategoryTM o) {
        return code.compareTo(o.getCode());
    }
}
