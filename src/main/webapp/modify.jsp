<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <form action="${ctx}/modify" method="post" enctype="multipart/form-data">
       <table>
         <tr>
           <td colspan="2"><h3>修改员工信息</h3></td>
         </tr>
         <tr>
           <td>员工姓名:</td>
           <td><input type="text" name="ename" value="${emp.ename}"></td>
         </tr>
         
         <tr>
           <td>部门:</td>
           <td>
             <select name="dept_id">
               <option value="0">请选择</option>
               <c:forEach items="${dept}" var="depts">
               
                 <option value="${depts.dids}" <c:if test="${emp.eid==depts.dids}">selected</c:if>>${depts.dname}</option>
               </c:forEach>
             </select>
           </td>
         </tr>
         
         <tr>
           <td>年龄:</td>
           <td><input type="text" name="age" value="${emp.age}"></td>
         </tr>
         
         <tr>
           <td>性别:</td>
           <td>
             <input type="radio" name="gender" value="男" <c:if test="${emp.gender=='男'}">checked</c:if>> 男
             <input type="radio" name="gender" value="女" <c:if test="${emp.gender=='女'}">checked</c:if>>女
           </td>
         </tr>
         
         <tr>
           <td>时间:</td>
           <td><input type="text" name="workDate" value='<fmt:formatDate value="${emp.workDate}"/>'></td>
         </tr>
         
         <tr>
           <td>文件:</td>
           <td>
           <img alt="" src="${ctx}/upload/${emp.path}">
           <input type="file" name="paths">
           <input type="hidden" name="path">
           </td>
           ${pathError}
         </tr>
         
          <tr>
           <td colspan="2">
              <input type="submit" value="修改" id="ti">
              <input type="button" value="返回">
           </td>
         </tr>
       </table>
     </form>
  </body>
  
   <script type="text/javascript" src="jquery-1.4.2.min.js"></script>
   <script type="text/javascript">
     $(function(){
       $("#ti").click(function(){
         var ename = $("[name=ename]").val();
         var dept_id = $("[name=dept_id]").val();
         var age = $("[name=age]").val();
         var gender = $("[name=gender]:checked").val();
         var workDate = $("[name=workDate]").val();
         var paths = $("[name=paths]").val();
       
         if(ename==""){
           alert("用户名不能为空");
            return false;
         }
         
         if(dept_id=="0"){
           alert("部门不能为空");
            return false;
         }
         
         var num = /^\d*$/;
         if(age==""){
           alert("年龄不能为空");
            return false;
         }else if(!num.test(age)){
           alert("年龄只能是数字");
         }
         
         if(gender==""){
           alert("性别不能为空");
            return false;
         }
         
         var dates=/^\d{4}-\d{1,2}-\d{1,2}$/;
         if(workDate==""){
           alert("时间不能为空");
            return false;
         }else if(!dates.test(workDate)){
           alert("时间格式不正确");
         }
         return true;
       
       })
     
     })
   
    </script>
</html>
