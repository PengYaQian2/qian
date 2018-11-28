package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.Tb_dept;
import service.Tb_deptService;

@Controller
public class Tb_deptController {
	@Autowired
	   private Tb_deptService tb_deptService;
	
	@RequestMapping("/queryDept")
	public String queryDept(Model model){
		  try {
			List<Tb_dept> dept = tb_deptService.queryDept();
			model.addAttribute("dept",dept);
			if(dept!=null){
				return "add";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
