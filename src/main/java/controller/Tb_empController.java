package controller;

import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pojo.Page;
import pojo.Tb_dept;
import pojo.Tb_emp;
import service.Tb_deptService;
import service.Tb_empService;

@Controller
public class Tb_empController {
	@Autowired
   private Tb_empService tb_empService;
	@Autowired
   private Tb_deptService tb_deptService;
	
	@RequestMapping("/query")
	public String query(Model model,@RequestParam(value="num",required=false,defaultValue="1") int num){
		try {
			int count = tb_empService.count();
			Page page = new Page();
			page.setCountCurrSize(count);
			page.setCurrNo(num);
			
			int pageNum = (page.getCurrNo()-1)*page.getCurrSize();
			
			List<Tb_emp> emp = tb_empService.query(pageNum, page.getCurrSize());
			
			model.addAttribute("emp",emp);
			model.addAttribute("page",page);
			return "index";
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpSession session,Tb_emp emp,Model model,
			         @RequestParam(value="paths",required=false) MultipartFile multipartFile
			){
		try {
			if(!multipartFile.isEmpty()){
				String name = multipartFile.getOriginalFilename();
				
				String path = session.getServletContext().getRealPath("/upload");
				
				String num = name.substring(name.lastIndexOf("."),name.length());
				
				name = System.currentTimeMillis()+new Random().nextInt(100000)+"ss"+num;
				
				String prefix = FilenameUtils.getExtension(name);
				
				if(multipartFile.getSize()>500000){
					 model.addAttribute("pathError","文件大小不超过50KB");
					return "add";
				}
				
				if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("png")		 
						){
					String fileName = System.currentTimeMillis()+new Random().nextInt(100000)+"ss"+num;
					
					File file = new File(path,fileName);
					
					if(!file.exists()){
						file.mkdirs();
					}

					name = path+File.separator+name;
					
					try {
						multipartFile.transferTo(file);
						emp.setPath(fileName);
						
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("pathError","文件上传失败");
						return "add";
					}
				}else{
					model.addAttribute("pathError","文件只能是图片");
					return "add";
				}
			}
			
			try {
				int count = tb_empService.add(emp);
				if(count>0){
					model.addAttribute("pathError","添加成功");
					return "redirect:/query";
					
				}else{
					model.addAttribute("pathError","添加失败");
					return "add";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(HttpSession session,Tb_emp emp,Model model,
			         @RequestParam(value="paths",required=false) MultipartFile multipartFile,
			         @RequestParam(value="path",required=false) String path
			){
		try {
			if(!multipartFile.isEmpty()){
				String name = multipartFile.getOriginalFilename();
				
				String path2 = session.getServletContext().getRealPath("/upload");
				
				String num = name.substring(name.lastIndexOf("."),name.length());
				
				name = System.currentTimeMillis()+new Random().nextInt(100000)+"ss"+num;
				
				String prefix = FilenameUtils.getExtension(name);
				
				if(multipartFile.getSize()>500000){
					 model.addAttribute("pathError","文件大小不超过50KB");
					return "modify";
				}
				
				if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("png")		 
						){
					String fileName = System.currentTimeMillis()+new Random().nextInt(100000)+"ss"+num;
					
					File file = new File(path2,fileName);
					
					if(!file.exists()){
						file.mkdirs();
					}

					name = path2+File.separator+name;
					
					try {
						multipartFile.transferTo(file);
						emp.setPath(fileName);
						
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("pathError","文件上传失败");
						return "modify";
					}
				}else{
					model.addAttribute("pathError","文件只能是图片");
					return "modify";
				}
			}else{
				emp.setPath(path);
			}
			
			try {
				int count = tb_empService.modify(emp);
				if(count>0){
					model.addAttribute("pathError","修改成功");
					return "redirect:/query";
					
				}else{
					model.addAttribute("pathError","修改失败");
					return "modify";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="eid",required=false) int eid){
		try {
			int count = tb_empService.delete(eid);
			if(count>0){
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
		
	}
	
	@RequestMapping("/selectId")
	public String selectId(@RequestParam(value="eid",required=false) int eid,Model model){
		try {
			Tb_emp emp = tb_empService.selectId(eid);
			List<Tb_dept> dept = tb_deptService.queryDept();
			model.addAttribute("emp",emp);
			model.addAttribute("dept",dept);
			return "modify";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
}
