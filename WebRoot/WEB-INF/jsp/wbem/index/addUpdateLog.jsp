<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<div id="addLog" class="container-fluid"  hidden="hidden" style="margin-top:30px;margin-left:10px">
	 <form action="#" method="get" class="form-horizontal">
	 	 <!-- <div class="control-group" id="logSubjectS"> -->
	       <label class="control-label">日志主题 :</label>
	       <div class="controls">
	         <input type="text" id="logSubject" class="span3" value="" />
	       </div>
	     <!-- </div> -->
	     <!-- <div class="control-group" id="logContentS" > -->
	       <label class="control-label">详细内容 :</label>
	       <div class="controls">
	       	 <textarea type="text" id="logContent" class="span3" value=""  rows="5"></textarea>
	       </div>
	     <!-- </div> -->
	     <!-- <div class="control-group" id="logContentS" > -->
	       <label class="control-label">所属类别:</label>
	       <div class="controls">
	         <input  id="logClasses"   value=""  type="hidden"/>
	         <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="logClasses" id="logClassesQ" style="margin-left: 0;" value=""/>前台</label>
	         <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="logClasses" id="logClassesH" style="margin-left: 0;" value="" checked/>后台</label>
	       </div>
	     <!-- </div> -->
	     <!-- <div class="control-group" id="logUserNameS" > -->
	       <label class="control-label">更新人:</label>
	       <div class="controls"> 
	       	 <input type="text" id="logUserName" class="span3" value="${userName}" style="background-color: #fff;BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-TOP-STYLE: none" readonly/><br/>
	        </div> 
	     <!-- </div> -->
	 </form>
</div>
 <div id="queryContent" class="container-fluid"  hidden="hidden" style="margin-top:20px">
	<form action="#" method="get" class="form-horizontal">
	    <div class="control-group" style="margin-left: 0px" >
	      <textarea type="text" id="logContentQuery" class="span3" value="" style="background-color: #fff;width:355px;height:120px;BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-TOP-STYLE: none" readonly></textarea> 
	      
	    </div>
	</form>
</div> 
<div id="editLog" class="container-fluid"  hidden="hidden" style="margin-top:32px;margin-left:8px">
	 <form action="#" method="get" class="form-horizontal">
	 	 <!-- <div class="control-group" id="editLogSubjectS"> -->
	       <label class="control-label">日志主题 :</label>
	       <div class="controls">
	         <input type="text" id="editLogSubject" class="span3" value="" />
	       </div>
	     <!-- </div> -->
	     <!-- <div class="control-group" id="editLogContentS" > -->
	       <label class="control-label">详细内容 :</label>
	       <div class="controls">
	       	<!--  <input type="text" id="editLogContent" class="span3" value="" /> -->
	       	<textarea type="text" id="editLogContent" class="span3" value=""  rows="5"></textarea>
	       </div>
	     <!-- </div> -->
	 </form>
</div>
<script>
	$("#logClasses").attr("value","后台");
    $("#logClassesH").click(function(){
	  $("#logClasses").attr("value","后台");
	});
	$("#logClassesQ").click(function(){
	  $("#logClasses").attr("value","前台");
	});
	function addUpdateLog(){
	  layer.open({
			type: 1,
       		title: '增加日志',
        	maxmin: false,       
        	shadeClose: false, 
        	area : ['450px' , '420px'],
        	btn: ['保存', '关闭'],
        	yes: function(index,layero){ 
        		 data={logSubject:encodeURI($("#logSubject").val()),logContent:encodeURI($("#logContent").val()),logClasses:encodeURI($("#logClasses").val()),userName:encodeURI($("#logUserName").val())};	
        		 if(encodeURI($("#logSubject").val())==""||encodeURI($("#logContent").val())==""){
        		 	layer.alert("主题和内容不可为空");
        		 	return;
        		 }
        		 $.ajax({
			 	      url : '/wbem/index/addUpdateLog.action',//这个就是请求地址对应sAjaxSource
				      data : {"data":JSON.stringify(data)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				      type : 'post',
				      dataType : 'json',
				      success : function(result) {          
				          if(result !=0){
							$('.data-table').DataTable().ajax.reload();
							layer.alert("保存成功");
							//layer.close(index);
							setTimeout(function(){
								window.location.reload();
							},2000); 
						  }else{
				            layer.alert("编辑失败");
				          }
				      },
				      error : function(msg){
				         layer.alert("系统异常，联系管理员");
				       }
				 });
        	},
        	cancel: function(index){
        		$("#logSubject").attr("value","");
        		$("#logContent").attr("value","");
        		$('.data-table').DataTable().ajax.reload();
        	}, 
             content: $("#addLog"),
	  });
	}
	function queryContent(logContent,logSubject){
 	 $("#logContentQuery").attr("value",logContent);  
      layer.open({
		    type: 1,
       		 title: '详细内容',
        	maxmin: false,       
        	shadeClose: false, 
        	area : ['400px' , '250px'],
        	cancel: function(index){
        	 $('.data-table').DataTable().ajax.reload();
        	},
        	content: $("#queryContent"),
	  });	
	}
	function editLog(logContent,logSubject,logTime){
	  $("#editLogSubject").attr("value",logSubject); 
	  $("#editLogContent").attr("value",logContent); 
      layer.open({
		    type: 1,
       		title: "编辑", 
        	maxmin: false,       
        	shadeClose: false, 
        	area : ['450px' , '330px'],
        	btn: ['保存', '取消'],
        	yes: function(index,layero){
        	     var newLogContent = $("#editLogContent").val();
        		 var newLogSubject = $("#editLogSubject").val();
        		 if(logContent==newLogContent&&logSubject==newLogSubject){
        		 	layer.alert("没有修改信息");
        		 	return;
        		 }
        		 data={logSubject:encodeURI(newLogSubject),logContent:encodeURI(newLogContent),logTime:logTime};	
        		 $.ajax({
			 	      url : '/wbem/index/editUpdateLog.action',//这个就是请求地址对应sAjaxSource
				      data : {"data":JSON.stringify(data)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				      type : 'post',
				      dataType : 'json',
				      success : function(result) {          
				          if(result !=0){
							$('.data-table').DataTable().ajax.reload();
							  layer.alert("保存成功"); 
							 /*  setTimeout(function(){
								layer.close(index);
							},2000) */;  
						  }else{
				            layer.alert("编辑失败");
				          }
				      },
				      error : function(msg){
				         layer.alert("系统异常，联系管理员");
				       }
				 }); 
				 
        	},
        	cancel: function(index){
        	 $('.data-table').DataTable().ajax.reload();
        	},
        	content: $("#editLog"),
	  });	
	}
	function delLog(logTime){
		 layer.open({
		    type: 1,
       		title: "确定删除", 
        	maxmin: false,       
        	shadeClose: false, 
        	area : ['300px' , '180px'],
        	btn: ['确定', '取消'],
			yes: function(index,layero){
				data={logTime:logTime};
	 			$.ajax({
			 	      url : '/wbem/index/delLog.action',//这个就是请求地址对应sAjaxSource
				      data : {"data":JSON.stringify(data)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				      type : 'post',
				      dataType : 'json',
				      success : function(result) {          
				          if(result !=0){
							$('.data-table').DataTable().ajax.reload();
							layer.close(index);
						  }else{
				            layer.alert("删除失败");
				          }
				      },
				      error : function(msg){
				         layer.alert("系统异常，联系管理员");
				       }
				}); 
			},
		});
	
	
	
	
	/*  	  if(layer.alert("确定删除此条日志？")){
	 		data={logTime:logTime};
	 		$.ajax({
			 	      url : '/wbem/index/delLog.action',//这个就是请求地址对应sAjaxSource
				      data : {"data":JSON.stringify(data)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				      type : 'post',
				      dataType : 'json',
				      success : function(result) {          
				          if(result !=0){
							$('.data-table').DataTable().ajax.reload();
						  }else{
				            layer.alert("删除失败");
				          }
				      },
				      error : function(msg){
				         layer.alert("系统异常，联系管理员");
				       }
			}); 
	 	}else{
	 		
	 	} */
	 	
	 	
	}	
</script>
