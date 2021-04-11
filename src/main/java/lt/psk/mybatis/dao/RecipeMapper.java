package lt.psk.mybatis.dao;

import java.util.List;
import lt.psk.mybatis.model.Recipe;
import org.mybatis.cdi.Mapper;

@Mapper
public interface RecipeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Recipe record);

    Recipe selectByPrimaryKey(Integer id);

    List<Recipe> selectAll();

    int updateByPrimaryKey(Recipe record);
}