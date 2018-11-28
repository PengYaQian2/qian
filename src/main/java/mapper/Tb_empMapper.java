package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Tb_emp;

public interface Tb_empMapper {
   List<Tb_emp> query(@Param("currNo") int currNo,@Param("currSize") int currSize);
   int count();
   int add(Tb_emp emp);
   Tb_emp selectId(@Param("eid") int eid);
   int modify(Tb_emp emp);
   int delete(@Param("eid") int eid);
}
