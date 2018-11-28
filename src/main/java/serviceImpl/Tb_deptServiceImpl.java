package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.Tb_deptMapper;
import pojo.Tb_dept;
import service.Tb_deptService;

@Service
public class Tb_deptServiceImpl implements Tb_deptService{
	@Autowired
	private Tb_deptMapper tb_deptMapper;

	@Override
	public List<Tb_dept> queryDept() {
		try {
			List<Tb_dept> list = tb_deptMapper.queryDept();
			if(list!=null){
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
