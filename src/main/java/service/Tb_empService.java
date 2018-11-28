package service;

import java.util.List;
import pojo.Tb_emp;

public interface Tb_empService {
	 List<Tb_emp> query(int currNo,int currSize);
	   int count();
	   int add(Tb_emp emp);
	   Tb_emp selectId(int eid);
	   int modify(Tb_emp emp);
	   int delete(int eid);
}
