package lt.psk.mybatis.dao;

import java.util.List;
import lt.psk.mybatis.model.RecipeElem;
import org.mybatis.cdi.Mapper;

@Mapper
public interface RecipeElemMapper {

    int insert(RecipeElem record);

    List<RecipeElem> selectAll();
}