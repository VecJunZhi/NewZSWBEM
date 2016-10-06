<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>
<input id="userName" value="${userName}" type="hidden"/>
 <div id="addReplyContent" class="container-fluid"  hidden="hidden" style="margin-top:23px">
	<form action="#" method="get" class="form-horizontal">
	    <div class="control-group" style="margin-left: 0px" >
	      <textarea type="text" id="replyFeedCons" class="span3" value="" style="background-color: #fff;width:340px;height:120px;margin-left:3px" ></textarea> 
	    </div>
	</form>
</div>	
<div class="container-fluid" id="queryReplyContent" hidden="hidden" style="background-color: #fff">
  <div class="row-fluid" >
      <div class="span12">
        <div class="widget-box" style="margin-top:1px">
          <!-- <div class="widget-title"> 
            <h5>意见与反馈</h5>
          </div> -->
          <div class="widget-content nopadding" >
            <form class="form-horizontal" method="post" action="#" name="basic_validate" id="basic_validate" novalidate="novalidate">
              <div class="control-group" >
                <div class="controls" style="margin-left:30px;">
                	  主题：<input readonly id="logSubjectr" value="" type="text" name="required" id="required" class="span9"
                	  style="BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-TOP-STYLE: none;background-color: #fff;"
                	  />
                </div>
              </div>
              <div class="control-group">
                <div class="controls" style="margin-left:30px"> 
                	  内容：<textarea readonly id="logContentr" value="" type="text" name="url" id="url" class="span9" rows="7"
                	  style="BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-TOP-STYLE: none;background-color: #fff;"></textarea>
                </div>
              </div>
              <div class="control-group">
              
                <div class="controls" style="margin-left:30px" >
                 	<div id="reList" class="" style="margin-left:35px;background-color: #fff;width:430px;">
                 	 		
                 	</div>
                 	  <!-- <textarea id="reList" value="" type="text" name="url" id="url" class="span9" rows="7"
                 	 style="BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-TOP-STYLE: none;background-color: #fff;"></textarea>  -->
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
 </div>
</div>       
<script>
function queryReplyContent(logSubject,logContent,logTime){
	$.ajax({
			 	url : '/wbem/index/queryReplyContents.action',
				data : {logTime:logTime},
				type : 'post',
				dataType : 'json',
				success:function(data){
					var str="";
					var zsReplyFeedEntityList=data.zsReplyFeedEntityList;
					for(var i=0; i<zsReplyFeedEntityList.length;i++){
						str+= zsReplyFeedEntityList[i].replyName+":"+zsReplyFeedEntityList[i].replyContent+"<br/>";
					}
					 $("#reList").append(str); 
				},
	 });  
	 $("#logSubjectr").attr("value",logSubject);
	 $("#logContentr").attr("value",logContent);
	 layer.open({
		    type: 1,
       		title: '详细信息', 
        	maxmin: false,       
        	shadeClose: false, 
        	area : ['650px' , '550px'],
        	cancel: function(index){
        	  
        	   $.ajax({
			 	 url : '/wbem/index/changeUnReadType.action',
				 data : {logTime:logTime},
				 type : 'post',
				 dataType : 'json',
				 success:function(data){
				 },
			  });  
        	  
        	  window.location.reload();
        	},
        	content: $("#queryReplyContent"),
	  });	
}
function addReplyContent(logTime){
	  var userName = $("#userName").val();
	  userName = encodeURI(userName);
      layer.open({
		    type: 1,
       		title: '回复信息', 
        	maxmin: false,       
        	shadeClose: false, 
        	area : ['400px' , '255px'],
        	btn: ['回复', '关闭'],
        	yes: function(index,layero){
        	   var replyContent = encodeURI($("#replyFeedCons").val());
        	   if(replyContent==""){
        		 	layer.alert("回复内容不可为空");
        		 	return;
        	    }
        	  	$.ajax({
			 	      url : '/wbem/index/addReplyContent.action',//这个就是请求地址对应sAjaxSource
				      data :{logTime:logTime,userName:userName,replyContent:replyContent},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				      type : 'post',
				      dataType : 'json',
				      success : function(result) {          
				          if(result !=0){
							layer.alert("回复成功");
							$("#replyFeedCons").attr("value","");
							setTimeout(function(){
								window.location.reload();
							},2000);
						  }else{
				            layer.alert("回复失败");
				            window.location.reload();
				          }
				      },
				      error : function(msg){
				         layer.alert("系统异常，联系管理员");
				         window.location.reload();
				       }
				 });
        	  
        	},
        	cancel: function(index){
        	  $("#replyFeedCons").val("");
        	},
        	content: $("#addReplyContent"),
	  });	
}
function delFeedBack(logTime){
	layer.open({
		    type: 1,
       		title: '确定删除', 
        	maxmin: false,       
        	shadeClose: false, 
        	area : ['300px' , '180px'],
        	btn: ['确定', '取消'],
        	yes: function(index,layero){
        		data={logTime:logTime};
	 			$.ajax({
			 	      url : '/wbem/index/delAllFeedBack.action',//这个就是请求地址对应sAjaxSource
				      data : {"data":JSON.stringify(data)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				      type : 'post',
				      dataType : 'json',
				      success : function(result) {          
				          if(result !=0){
				            window.location.reload();
						  }else{
				            layer.alert("删除失败");
				            window.location.reload();
				          }
				      },
				      error : function(msg){
				         layer.alert("系统异常，联系管理员");
				         window.location.reload();
				       }
				}); 
        	},
	})

}
</script>