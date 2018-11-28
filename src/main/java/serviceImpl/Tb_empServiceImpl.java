package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mapper.Tb_empMapper;
import pojo.Tb_emp;
import service.Tb_empService;

@Service
@Transactional
public class Tb_empServiceImpl implements Tb_empService{
	@Autowired
   private Tb_empMapper tb_empMapper;

	@Override
	public List<Tb_emp> query(int currNo, int currSize) {
		try {
			List<Tb_emp> list = tb_empMapper.query(currNo, currSize);
			if(list!=null){
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int count() {
		try {
			int count = tb_empMapper.count();
			if(count>0){
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int add(Tb_emp emp) {
		try {
			int count = tb_empMapper.add(emp);
			if(count>0){
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Tb_emp selectId(int eid) {
		try {
			Tb_emp emp = tb_empMapper.selectId(eid);
			if(emp!=null){
				return emp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int modify(Tb_emp emp) {
		try {
			int count = tb_empMapper.modify(emp);
			if(count>0){
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int eid) {
		try {
			int count = tb_empMapper.delete(eid);
			if(count>0){
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
