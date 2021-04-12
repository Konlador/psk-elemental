package lt.psk.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Recipe {

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private Integer resultId;

    @Getter @Setter
    private Elem result;

    @Getter @Setter
    private List<Elem> ingredients;
}