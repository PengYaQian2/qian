<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
      <table>
      
      <tr>
        <td>
          <a href="${ctx}/queryDept">添加</a>
        </td>
      </tr>
        <tr>
          <th>员工姓名</th>
          <th>部门</th>
          <th>年龄</th>
          <th>性别</th>
          <th>入职时间</th>
          <th>操作</th>
        </tr>
        
       <c:forEach items="${emp}" var="li">
       <tr>
          <td>${li.ename}</td>
          <td>${li.dname}</td>
          <td>${li.age}</td>
           <td>${li.gender}</td>
          <td><fmt:formatDate value="${li.workDate}"/></td>
          
          <td>
              <a href="${ctx}/selectId?eid=${li.eid}">修改</a>
              <a href="javaScript:void(0);" onclick="return shan(${li.eid})">删除</a>
          </td>
       </tr>
       </c:forEach>
       
       <tr>
         <td colspan="6">
           <a href="${ctx}/query?num=1">首页</a>
            <a href="${ctx}/query?num=${page.currNo-1}">上一页</a>
            <a href="${ctx}/query?num=${page.currNo+1}">下一页</a>
            <a href="${ctx}/query?num=${page.countCurrNo}">末页</a>
            <select name="xia">
           <%--  <c:forEach begin="1" items="${page.currNo}" var="${num}">
              <option value="${page.currNo}">${num}</option>
            </c:forEach> --%>
            </select>
         </td>
       
       </tr>
       
        
      </table>
  </body>
   <script type="text/javascript" src="jquery-1.4.2.min.js"></script>
   <script type="text/javascript">
     function shan(eid){
     if(confirm("确认是否删除?")){
       $.post("${ctx}/delete","eid="+eid,function(data){
             if(data=="true"){
               alert("删除成功");
               location="${ctx}/query";
             }else if(data=="false"){
               alert("删除失败");
               location="${ctx}/query";
             }
         
         });
     }
         
     }
     
     $(function(){
       $("[name=xia]").change(function(){
         var num = $(this).val();
         location="${ctx}/query?num="+num;
       })
     
     })
     
     
   </script>
  
  
  
</html>
