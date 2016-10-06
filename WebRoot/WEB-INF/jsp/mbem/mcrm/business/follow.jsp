<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <title>新增跟进</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">  
	<link rel="Stylesheet" href="/mbem/mcrm/business/css/base.min.css">
	<link rel="Stylesheet" href="/mbem/mcrm/business/css/user.css">
    <link rel="stylesheet" href="/mbem/mcrm/business/css/date.css"  >
	<script src="/common/js/jquery-1.9.1.min.js" ></script>
	<script src="/common/js/bootstrap/bootstrap.min.js"></script> 
<script  src="/mbem/mcrm/business/js/date.js" ></script>
<script  src="/mbem/mcrm/business/js/iscroll.js" ></script>
<script>
$(function(){
	$('#beginTime').date();
	$('#endTime').date({theme:"datetime"});
});


$(document).ready(function(){
			$("#submit").click(function(){
				$("div[id^=btgroup]").each(function(){
					$("label",this).each(function(){
						if($(this).hasClass("active")){
							//alert($("input",this).val());
							var val = $("input",this).val();
							$(this).parent().prev().val(val);
						}
					});
				});
				var val = $("textarea").val();
				if(val==null || val=="" || typeof(val)=='undefined'){
					alert("请将必填项填写完整(用*标注)");
					return false;
				}
				return true;
			});
			
			$("#cancle").click(function(){
				var type = $(this).attr("closeType");
				if(type ==1 ) {
					window.location.href = "/mbem/mcrm/business/cancleNewFollow.action";
				}
				if(type == 2) {
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
		
		 var flag = 0;
   		/* $(function ($) {
	    	if (window.history && window.history.pushState) {
	        	$(window).on('popstate', function () {
	                var hash = window.location.hash;
	                //alert("hash=="+hash);
	                //alert(flag+"flag");
	                if (hash != '' && flag == 1 || (hash ==='' && flag == 0)) {
	                    window.location.href="/mbem/mcrm/business/personal.action";
	                }
	        	});
			        window.history.pushState('forward', null, "/mbem/mcrm/business/follow.action");//'./#forward');
			        location.hash="#1"; 
		       
		    }
		});    */
</script>
    
            <style>
#wrapper{height:100%;-webkit-overflow-scrolling:touch;overflow:auto; min-height:320px;}
</style>
    
    
<script type="text/javascript">
    $ (function ()
    {
        $ ("shijian").click (function ()
        {
            //alert("fj");
            //alert ($ (this).attr ("id"));
        })
          
    });
</script> 
</head>
<body>
<div id="warpBox">
<div class="container" id="container">


    <form class="form-content" id="form-content" action="${pageContext.request.contextPath}/mbem/mcrm/business/insertZsOldCusFollowInfoDao.action" method="post">
    
    
        <%--  <div class="form-group border-1px other" data-id="2" style="display:none">
        
        <ul class="border-1px">
    
    
    
     <li class="border-1px" data-ac="active" data-tag="s_customer@ages" data-type="singleSelect">
    
        <div class="li-inner border-1px" onclick='showhidediv("followWay")'>
            <span class="k">跟进方式</span>
            <span class="v">                  
           <input id="a" type="text" placeholder="请输入" value="${followWay}" maxlength="100" name="followWay">
          </span>
            
        </div>   
</li> 

<li class="border-1px" data-require="1" data-ac="active" data-tag="s_opportunity@known_id" data-type="levelSelect">
    
        <div class="li-inner border-1px" onclick='showhidediv("intentionType")'>
            <span class="k">意向级别</span>
            <span class="v">
            <input id="i" type="text" placeholder="请输入" value="${intentionType}" maxlength="100" name="intentionType">
            </span>
            
        </div>
        
    
</li>
</ul></div> --%>
    
    

    
 <div class="switchSinglePage-wrap">
 <div class="switchSinglePage panel" style="-webkit-transform: translate3d(0%, 0px, 0px);">
 <div class="scroll">
<div class="form-group border-1px first">
<ul class="border-1px">
<li class="border-1px">
 <div class="li-inner border-1px">
<div class="select-head">跟进方式</div>
    <input type="hidden" id="followWay" name="followWay">
    <div id="btgroup1" stype="single"  data-toggle="buttons">
       <c:forEach items="${followTypeList}" var="follow" varStatus="status">
   <label class="btn" >
      <input type="radio" value="${follow.selectName}">${follow.selectName}
   </label>
   </c:forEach>
    </div>
    </div></li>
    

    
    <li class="border-1px" data-require="1" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
    
        
            <div class="li-inner border-1px">
            
                <span class="k">跟进内容</span>
                <span class="v">
                 
                <textarea type="text" placeholder="请输入" value="${followInfo}" maxlength="100"  style="height: 26px;" name="followInfo"></textarea>
                </span>
           </div>
</li>
   
    <li class="border-1px">
 <div class="li-inner border-1px">
<div class="select-head">意向级别</div>
    <%-- <c:forEach items="${intentionLevelList }" var="intent" varStatus="status">
    <div class="c" id="c${intent.property}"><a class="xz" onClick="if(this.style.backgroundColor=='green'){this.style.backgroundColor='transparent'}else{this.style.backgroundColor='green'}">${intent.selectName}</a></div>
    </c:forEach> --%> 
    <input type="hidden" id="intentLevel" name="intentionType">
    <div id="btgroup2" stype="single"  data-toggle="buttons">
      <c:forEach items="${intentionLevelList}" var="intent" varStatus="status">
   <label class="btn" >
      <input type="radio" value="${intent.selectName}"> ${intent.selectName}
   </label>
   </c:forEach>
    </div>
    
    </div></li>
    
    <li class="border-1px">
     <div class="li-inner border-1px">
                    <span class="k">下次跟进</span>
                    <span class="v">
                     <!--    <input type="text" value="${nextFollowDate}" name="nextFollowDate"  onFocus="CalendarWebControl.show(this,true,this.value);">  -->
        <div class="clearfix dome3_box">
        <input  id="beginTime" class="kbtn" name="nextFollowDate" value="${autoGenNextFollowDate}"/>
		
                    </span>
            
       
            </div>            
</li>

  </ul>
  
</div></div></div></div>
  

         <div id="action-mask"></div>
        <div class="form-group submit-group noswitchSinglePage">
        <a class="btns btns-default" closeType="${closeType}" href="#" style="text-align:center; color:#3c6" id="cancle">取消</a>
        <button class="btns btns-primary" id="submit" data-ac="active">保存</button>
    </div>
     </form>

 

</div>
</div><div id="datePlugin"></div>
<script>
    var UA = navigator.userAgent;
    var forIOS = function(){
        if(!UA.match(/iPad/) && !UA.match(/iPhone/) && !UA.match(/iPod/)){return;}
        if($('#wrapper').length){return;}
        $('body').children().not('script').wrapAll('<div id="wrapper"></div>');
    }();
</script>
</body></html>