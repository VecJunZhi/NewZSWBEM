<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <title>客户详情</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link rel="Stylesheet" href="/mbem/mcrm/business/css/base.min.css">
    <link rel="Stylesheet" href="/mbem/mcrm/business/css/personal.css">
    <script  src="/mbem/mcrm/business/js/base_new.min.js"></script>
    <script  src="/common/js/jquery-1.9.1.min.js"></script>
    <script src="/mbem/mcrm/business/js/addCustom.js"></script>
  	<script>
  	var flag = 0;
  	function switchTab(n){
		for(var i = 1; i <= 2; i++){
			document.getElementById("tab_" + i).className = "tab";
			document.getElementById("tab_con_" + i).style.display = "none";
			}
			document.getElementById("tab_" + n).className = "on tab active";
			document.getElementById("tab_con_" + n).style.display = "block";
			flag = 1;
		}
		
		function popClose(){
		if(window.name==''){
			window.location.href="/mbem/mcrm/business/customPage.action";
		}else{
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			}
		}
  		$(document).ready(function(){
  			var sex = $("#sex").val();
  			if(sex=="女") {
  				$(".gender-img img").attr("src","/mbem/mcrm/business/images/woman.png");
  			}
  			
  		});
  	</script>
  <!--  <script>
            $(function(){
                var H = $(window).height();
                var W = $(window).width();
                $("body").width(W).height(H);a
                 
            });
        </script>-->
        
        <style>
#wrapper{height:100%;-webkit-overflow-scrolling:touch;overflow:auto;}
</style>
  
    </head>
<body>

 <div class="head">
 <c:forEach items="${cusBasicByCusIdlist}" var="cus" varStatus="status">    
        <div class="basic">
            <div class="bas-r1">
                <div class="bas-r1-c1">
                    <div class="bas-gender">
                        <div class="gender-img">  
                        <img src="/mbem/mcrm/business/images/xq_adnan.png">
                        
                        </div>

                        <div class="bas-intent">${cus.intentionType}</div>

                    </div>
                </div>
                <div class="bas-r1-c2">
                    <div class="bas-name">${cus.cusName}</div>
                    <div class="bas-tel">${cus.tel}</div>
                     <input id="sex" type="hidden" value="${cus.sex}">
                </div>
                <div class="bas-r1-c3">
                    <a href="/mbem/mcrm/business/updateCustom.action" class="bas-edit" style="" data-ac="bas-edit-active">
                        <img src="/mbem/mcrm/business/images/icon_edit.png">
                    </a>
                    <div class="bas-pct">资料完善<span><!--36% --></span></div>
                </div>
            </div>
            
        </div></c:forEach>
        <!-- start 简洁版个人信息 -->
        <div class="basic-min">
            <a href="#" class="bas-edit" style="" data-ac="bas-edit-active">
                <img src="/mbem/mcrm/business/images/icon_edit.png">
            </a>
            <div class="bas-min-name"><!--韦东 --></div>
        </div>
    </div>

    
    <div class="invalid-cst" style="display:none">
        <div class="invalid-reason">
            
        </div>
    </div>
    
 <div id="box">
  <ul id="tab" class="tabs">
       <li class="on tab active" id="tab_1" onclick="switchTab(1)"> 
       <div data-ac="tab-active" data-show="rec-wrap">
            <div><a href="#">跟进记录</a></div>
        </div></li>
        
        <li class="on tab" id="tab_2" onclick="switchTab(2)">
        <div data-ac="tab-active" data-show="detail-wrap">
            <div><a href="#">详细资料</a></div>
        </div></li>
    </ul>
<ul id="tab_con">
<li id="tab_con_1">
    <div class="rec-wrap"> 
        <div class="rec-p">
            <div class="rec-p-r1">
            首次来访日期：${cusTomFirstDate}&nbsp;&nbsp;
            </div>
            

            <div class="rec-p-r1">
                 下次跟进日期：${cusTomNextFollowDate}&nbsp;&nbsp;
                <!--<dl class=" ">
                    <dt><span></span></dt>
                    <dd>问询</dd>
                </dl>
                
                <dl class=" ">
                    <dt><span></span></dt>
                    <dd>看房</dd>
                </dl>
                
                <dl class=" ">
                    <dt><span></span></dt>
                    <dd>认筹</dd>
                </dl>
                
                <dl class=" ">
                    <dt><span></span></dt>
                    <dd>认购</dd>
                </dl>
                
                <dl class=" ">
                    <dt><span></span></dt>
                    <dd>签约</dd>
                </dl> -->
                
            </div>
        </div>
        
        
 
        <div class="rec-line border-1px">
          <dl>
                <dt>来访${cusTomComeView}次<span class="rec-line-gjfs"></span><span class="rec-line-new"><img src="/mbem/mcrm/business/images/time_label_new.png" alt=""></span></dt>
                <!--<dd>
                    <div class="rec-line-msg">
                        <p>系统自动跟进－认购</p>
                        
                    </div>
                </dd> -->
            </dl>   
             <c:forEach items="${followByCusIdlist }" var="Record" varStatus="status">
            <dl>
                <dt>${Record.followDate}<span class="rec-line-gjfs">${Record.followWay}</span></dt>
                <dd>
                    <div class="rec-line-msg">
                        <p>${Record.followInfo}</p>
                        
                        <p class="rec-line-msg-t">跟进人：${Record.employeeName}<!--<span style="float:right;">${Record.nextFollowDay}需跟进</span> --></p>
                        
                    </div>
                </dd>
            </dl>  </c:forEach>    
        </div>
   </div> 
</li>
    
<li id="tab_con_2">

<c:forEach items="${cusBasicByCusIdlist}" var="cus" varStatus="status">            
    <div class="detail-wrap" style="display:block">
       
        <!--<a href="#" class="cst-lib border-1px" data-ac="data-ac">资料库</a> -->
        
        <dl class="info border-1px">
 				<dt class="border-1px">
                <div class="info-c1 default-tel">手机</div>
                <div class="info-c2">${cus.tel}
                    <a href="tel:${cus.tel}" class="info-tel btn-go-addrec border-1px"></a>
                     <a href="sms:${cus.tel}" class="info-sms"></a>
                    </div>
            </dt>
        </dl>
        <dl class="info border-1px">
             <dt class="border-1px">
                <div class="info-c1">客户来源</div>
                <div class="info-c2 ">${cus.followWay}</div>
            </dt>
            <dt class="border-1px">
                <div class="info-c1">意向级别</div>
                <div class="info-c2 ">${cus.intentionType}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">意向面积</div>
                <div class="info-c2 ">${cus.intentionArea}</div>
            </dt>
            
            
       <!--     
            <dt class="border-1px">
                <div class="info-c1">意向楼层</div>
                <div class="info-c2 unknown">未知</div>
            </dt> -->
            
            
            
            <dt class="border-1px">
                <div class="info-c1">购房用途</div>
                <div class="info-c2">${cus.houseUse}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">关注因素</div>
                <div class="info-c2 ">${cus.factor}</div>
            </dt>
            <dt class="border-1px">
                <div class="info-c1">认知途径</div>
                <div class="info-c2 ">${cus.firstVisWay}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">下次跟进时间</div>
                <div class="info-c2 unknown">${cus.nextFollowDate}</div>
            </dt>
        </dl>
        <dl class="info border-1px">
            
            
            <!--<dt class="border-1px">
                <div class="info-c1">本地户籍</div>
                <div class="info-c2">${cus.residenceAddr}</div>
            </dt> -->
            
            
            
        <!--    <dt class="border-1px">
                <div class="info-c1">有无社保</div>
                <div class="info-c2 unknown">未知</div>
            </dt>
             -->
            <dt class="border-1px">
                <div class="info-c1">置业次数</div>
                <div class="info-c2 ">${cus.buyTimes}</div>
            </dt>
           <!--  
            <dt class="border-1px">
                <div class="info-c1">购房资格</div>
                <div class="info-c2 unknown">未知</div>
            </dt>
             -->
            <dt class="border-1px">
                <div class="info-c1">年龄段</div>
                <div class="info-c2 unknown">${cus.age}</div>
             </dt>
             <dt class="border-1px">
                <div class="info-c1">居住区域</div>
                <div class="info-c2 ">${cus.addrArea}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">工作区域</div>
                <div class="info-c2 unknown">${cus.workComp}</div>
            </dt>
            
            
            
         <!--   <dt class="border-1px">
                <div class="info-c1">婚姻状况</div>
                <div class="info-c2 unknown">未知</div>
            </dt> -->
            
            
            
            <dt class="border-1px">
                <div class="info-c1">家庭结构</div>
                <div class="info-c2 unknown">${cus.familyInfo}</div>
            </dt>
            
            
            
            <dt class="border-1px">
                <div class="info-c1">身份证</div>
                <div class="info-c2 ">${cus.idNo}</div>
            </dt>
            
            
            
         <!--   <dt class="border-1px">
                <div class="info-c1">最近业务阶段时间</div>
                <div class="info-c2 ">2015-05-31 00:00</div>
            </dt>
             -->
            
            
            <dt class="border-1px">
                <div class="info-c1">招商业态</div>
                <div class="info-c2 unknown">${cus.investmentInfo}</div>
            </dt>
            
            
        </dl>
      
    </div> </c:forEach>
    
 </li> 

 
 
 </ul> 
 
 
 
 
 </div>
    




    
    <script>
    var UA = navigator.userAgent;
    var forIOS = function(){
        if(!UA.match(/iPad/) && !UA.match(/iPhone/) && !UA.match(/iPod/)){return;}
        if($('#wrapper').length){return;}
        $('body').children().not('script').wrapAll('<div id="wrapper"></div>');
    }();
</script>
    
    
 <div class="btn-group">    
       <c:forEach items="${cusBasicByCusIdlist}" var="cus" varStatus="status">
       	<a onclick="popClose()" class="btn-close" data-ac="active">返回</a>
        <a href="sms:${cus.tel}" class="btn-sms" data-ac="active" onclick="callCustom('1')">短信</a>
        <a href="tel:${cus.tel}" class="btn-contact btn-go-addrec" onclick="callCustom('1')">联系客户</a>
        <a href="/mbem/mcrm/business/follow.action?type=1" class="btn-addrec" data-ac="active">新增跟进</a>
        </c:forEach>
        
    </div>  
</body></html>