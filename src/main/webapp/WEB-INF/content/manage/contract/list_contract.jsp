<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>合同列表</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<%@include file="../../script.html" %>

  </head>
  
  <body >
  	<div>
  		<select class="easyui-combobox theme-textbox-radius" style="width:120px" id="contractOne">
  			<option value="2">客户</option>
  			<option value="3">联系人</option>
  			<option value="4">负责人</option>
  		</select>
  		<select class="easyui-combobox theme-textbox-radius" style="width:100px" id="contractTwo">
  			<option value="1">包含字段</option>
  			<option value="2">精确查询</option>
  		</select>
  		<span>查询:</span>
		<input id="contract" style="line-height:26px;border:1px solid #ccc">
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		<a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload" style="background-color:#00aa00;color:white;margin-left:10px">重置</a>
  	</div>
   	<table id="contractList" class="easyui-datagrid"></table>
    <div id="toolbar">
    	<a href="javascript:void(0);" onclick="return add('manage/contract/ContractAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
    	<a href="javascript:void(0);" onclick="return del('manage/contract/ContractAction_delete.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    	<a href="javascript:void(0);" onclick="return edit('manage/contract/ContractAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
    </div>
    <script type="text/javascript">
    	//条件查询
		function doSearch() {
			$("#contractList").datagrid('load',{
                contractOne : $("#contractOne").val(),
                contractTwo : $("#contractTwo").val(),
                contract : $("#contract").val()
			})
        }

        //为重置按钮添加事件处理函数
        $("#setBtn").bind("click", function(){
            $("#contractOne").textbox("setValue", "");
            $("#contractTwo").textbox("setValue","");
            $("#contract").val("");
            $("#contractList").datagrid("load",{
                contractOne:$("#contractOne").val(),
				contractTwo:$("#contractTwo").val(),
				contract:$("#contract").val(),
            });
        })
		//添加合同
        var add = function (uri) {
            parent.openTopWindow({
                width: 800,
                height: 550,
                title: '添加',
                url: uri,
                close: function () {
                    $("#contractList").datagrid("reload");
                }
            })
        }
        //修改合同
		var edit = function (uri) {
		    var id = $("#contractList").datagrid("getSelected").contractid;
			parent.openTopWindow({
			    width:700,
				height:550,
				title:'修改',
				url:uri+ "?contractid=" + id,
				close:function () {
                    $("#contractList").datagrid("reload");
				}

			})
        }

        //查看合同
        var see = function (uri) {
            var id = $("#contractList").datagrid("getSelected").contractid;
            parent.openTopWindow({
                width:700,
                height:550,
                title:'查看',
                url:uri+ "?contractid=" + id,
                close:function () {
                    $("#contractList").datagrid("reload");
                }

            })
        }


     	//删除部门
		function del(url) {
		    //获取选中的一行数据
			var contractIds = new Array();
			var rows = $("#contractList").datagrid("getChecked");
            if(!rows.length){
                $.messager.alert("警告","请选择要删除的数据");
                return false;
            }

            $.messager.confirm("警告","数据删除后无法恢复，是否确认删除",function(b){
                if(b){
                    if(rows){
                        $.each(rows,function (index,obj) {
                            contractIds.push(obj.contractid);
                        })
                    }
                    contractIds.join(",");
                    $.post(url, {"contractIds" : contractIds.toString()}, function (result) {
                        if (result.success) {
                            $.messager.alert("信息",result.message);
                            $("#contractList").datagrid("reload");
                            $("#contractList").datagrid("clearChecked");
                        }
                    }, "json")
                }
            });
		}

    
    	//加载用户数据
    	$(function(){
    		$("#contractList").datagrid({
    			url : "manage/contract/ContractAction_findByPage.action",
    			//queryParams:{"positionName":$("#positionName").val()},
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "contractid",
    			rownumbers : true,
    			/*singleSelect:true,*/
    			showHeader:true,//定义是否显示头部
    			columns : [[
    				{field:"contractid",title:"选择合同",checkbox:true},
    				{field:"contractnumber",title:"合同编号"},
    				{field:"businessCustomerName",title:"客户"},
    				{field:"ownerUserName",title:"负责人"},//ownerUserName
    				{field:"businessConnectionName",title:"联系人"},
    				{field:"duetime",title:"签约日期"},
    				{field:"price",title:"合同金额"},
    				{field:"status",title:"状态"},
    				{field:"endTime",title:"距离到期时间"},
    				{field:"operation",title:"操作",formatter:function(value,row,index){ 
		  			return "<a href='javascript:void(0)' onclick=\"(see('manage/contract/ContractAction_see.action'))\">查看</a>"+
		  			"&nbsp;&nbsp<a href='javascript:void(0)' onclick='return modify("+index+")'>编辑</a>";
  				}}
    				
    				
    			]],
    			loadFilter:function(data){
    				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
    				return {"total":data.data.totalRows,"rows":data.data.list};
    			}
    		
    		});
    	});
    </script>
  </body>
</html>
