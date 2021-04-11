package lt.psk.mybatis.dao;

import java.util.List;
import lt.psk.mybatis.model.Elem;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ElemMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Elem record);

    Elem selectByPrimaryKey(Integer id);

    List<Elem> selectAll();

    int updateByPrimaryKey(Elem record);
}