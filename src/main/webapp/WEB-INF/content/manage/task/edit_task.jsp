<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加任务</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="../../script.html" %>
  </head>
  
  <body style="width:100%;max-width:600px;padding:30px 60px;">
  	<section class="info-section">
    	<form id="form" method="post">
    		<table>
				<tr>
					<td class="text-title">创建者名：</td>
					<td class="text-content">
						<input type="hidden" name="taskVo.taskid" value="${taskVo.taskid }"> 
						<input type="text" name="taskVo.userByCreatoruserName" value="${taskVo.userByCreatoruserName }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[2,10]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">负责人：</td>
					<td class="text-content">
						<input type="text" name="taskVo.ownerUsername" value="${taskVo.ownerUsername }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[2,10]'">
					</td>
				</tr>
				<tr style="display:${empty taskVo ? 'table-row' : 'none'};">
					<td class="text-title">删除人：</td>
					<td class="text-content">
						<input type="text" name="taskVo.userByDeleteuserName" value="${taskVo.userByDeleteuserName }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[2,10]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">相关人：</td>
					<td class="text-content">
						<input type="text" name="taskVo.aboutUserId" value="${taskVo.aboutUserId }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">主题：</td>
					<td class="text-content">
						<input type="text" name="taskVo.subject" value="${taskVo.subject }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="text-title">优先级：</td>
					<td class="text-content">
						<input type="text" name="taskVo.priority" value="${taskVo.priority }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="text-title">邮件通知：</td>
					<td class="text-content">
						<input type="text" name="taskVo.sendemail" value="${taskVo.sendemail }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'email'"></td>
				</tr>
				
				<tr>
					<td class="text-title">截止日期：</td>
					<td class="text-content">
						<input type="date" name="taskVo.duedate" value="${taskVo.duedate }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">更新日期：</td>
					<td class="text-content">
						<input type="date" name="taskVo.updtadate" value="${taskVo.updtadate }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				
				<tr>
					<td class="text-title">任务状态：</td>
					<td class="text-content">
						<select name="taskVo.status" class="easyui-combobox theme-textbox-radius">
							<option value="1" ${taskVo.status==1?"selected":"" }>正常</option>
							<option value="0" ${taskVo.status==0?"selected":"" }>禁用</option>
							<option value="2" ${taskVo.status==2?"selected":"" }>已删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">描述信息：</td>
					<td class="text-content">
						<textarea class="theme-textbox-radius" rows="5"  cols="20" name="taskVo.description">${taskVo.description }</textarea></td>
				</tr>
				<!-- 因为用户关联信息不需要修改，但是如果没有把这些信息传递给服务器端，hibernate对数据进行更新时就会把对应条目设置为null值 -->

				<tr style="display: none;">
					<td colspan="2">
						<input type="hidden" name="taskVo.createdate" value="${taskVo.createdate}">  
						<input type="hidden" name="taskVo.isclose" value="${taskVo.isclose}">
						<input type="hidden" name="taskVo.isdeleted" value="${taskVo.isdeleted}">
						<input type="hidden" name="taskVo.deletetime" value="${taskVo.deletetime}">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger">重置</a>
					</td>
				</tr>
			</table>	
    	</form>
    </section>	
  </body>
  	<script type="text/javascript">
  		$(function(){
            //为保存按钮添加事件
            $("#saveBtn").bind("click",function () {
                $.post("manage/task/TaskAction_saveOrUpdate.action",$("#form").serialize(),function (result) {
                    if (result.success) {
                        $.messager.alert('提示信息', '保存成功',"info",function () {
                            parent.closeTopWindow();
                        });
                    } else {
                        $.messager.alert('提示信息', '保存失败');
                    }
                },"json")
            })


  			$("#resetBtn").on("click",function(){
  				$("#form").form("reset")
  			});	
  		})
  	
  	</script>
</html>
