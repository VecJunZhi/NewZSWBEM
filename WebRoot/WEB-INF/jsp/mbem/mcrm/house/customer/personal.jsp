<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/house/pub/head.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<title>�ͻ�����</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<link  rel="Stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link  rel="Stylesheet" href="/mbem/mcrm/house/css/personal.css">
<script  src="/mbem/mcrm/house/js/base_new.min.js"></script>
<script  src="/mbem/mcrm/house/js/addCustom.js"></script>
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
			//var json={time:new Date().getTime()};
			//window.history.pushState(json,"","/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action");
		}
		
		function popClose(type){
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
		}
  	
  		$(document).ready(function(){
  			/* var json={time:new Date().getTime()};
			window.history.pushState(json,"","/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action");
    		window.onpopstate = function(e){
    			if(history.state ||(history.state==null && flag == 0)) {
    				window.location.href = "/mbem/mcrm/house/customer/customPage.action";
    			}
			}; */ //���˴���
  		
  			

  			var status="";
  			$("dd",".rec-p-r2").each(function(){
  				status = $(this).text();
  			});
  			//alert(status);
  			if(status == "�Ϲ�") {
  				$(".rec-p-r2").append("<dl class=''><dt><span></span></dt><dd>ǩԼ</dd></dl>");
  			}
  			else if(status == "ǩԼ") {
  			}
  			else {
  				$(".rec-p-r2").append("<dl class=''><dt><span></span></dt><dd>�Ϲ�</dd></dl>");
  				$(".rec-p-r2").append("<dl class=''><dt><span></span></dt><dd>ǩԼ</dd></dl>");
  			}
  		});
  	</script>
<style>
#wrapper{height:100%;-webkit-overflow-scrolling:touch;overflow:auto;}
</style>
    </head>


<body>
  

 <div class="head">
  <c:forEach items="${cstInfoList}" var="cus" varStatus="status">    
        <div class="basic">
            <div class="bas-r1">
                <div class="bas-r1-c1">
                    <div class="bas-gender">
                    	<c:if test="${cus.xsCst.gender == '��'}">
                    		 <div class="gender-img-man">
                    		 	 <c:if test="${isInvalid == '1'}">
                        	 		<img src="/mbem/mcrm/house/images/invalid.png" style=" margin-top:10px;">
                       			</c:if> 
                        	</div>
                    	</c:if>
                    	<c:if test="${cus.xsCst.gender == 'Ů'}">
                    		 <div class="gender-img-woman">  
                        		<c:if test="${isInvalid == '1'}">
                        	 		<img src="/mbem/mcrm/house/images/invalid.png" style=" margin-top:10px;">
                       			</c:if> 
                        	</div>
                    	</c:if>
                        <div class="bas-intent">${cus.xsOpp.gfyx}</div>

                    </div>
                </div>
                <div class="bas-r1-c2">
                    <div class="bas-name" style="max-width:100%">${cus.xsCst.cstName}</div>
                    <div class="bas-tel">${cus.xsCst.mobileTel}</div>
                    <input id="sex" type="hidden" value="${cus.xsCst.gender}">
                </div>
                <div class="bas-r1-c3" style="padding:5px 20px 0 0;">
                	<div class="bas-pct">��������<!-- <span>36%</span> --></div>
                    <a href="/mbem/mcrm/house/customer/updateCustom.action?cstGuid=${cstGuid}&oppGuid=${oppGuid}" class="bas-edit" style="" data-ac="bas-edit-active">
                        <img src="/mbem/mcrm/house/images/icon_edit.png" style="margin-top:5px;">
                    </a>
                    <div style="color:#fff;margin-top:5px;font-size:12px;">
                    	<a href="/mbem/mcrm/house/find/selectProject.action?flag=2&cstGuid=${cstGuid}&oppGuid=${oppGuid}">
                    	<img src="/mbem/mcrm/house/images/cst_copy.png" style="width:20px;height:20px;">
                    	�ͻ�����</a>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach> 
        <!-- start ���������Ϣ -->
        <div class="basic-min">
            <a href="#" class="bas-edit" style="" data-ac="bas-edit-active">
                <img src="/mbem/mcrm/house/images/icon_edit.png">
            </a>
            <div class="bas-min-name"><!--Τ�� --></div>
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
            <div><a href="#">������¼</a></div>
        </div>
        </li>  
        <li class="on tab" id="tab_2" onclick="switchTab(2)">
        <div data-ac="tab-active" data-show="detail-wrap">
            <div><a href="#">��ϸ����</a></div>
        </div></li>
    </ul>
<ul id="tab_con">
<li id="tab_con_1">
    <div class="rec-wrap"> 
        <div class="rec-p">
            <div class="rec-p-r1">
            	�״��������ڣ�${firstVisitTime} &nbsp;&nbsp;
            </div>
            <div class="rec-p-r1">
                                 �´θ������ڣ�${nextFollowDate}&nbsp;&nbsp;
            </div>
            <div class="rec-p-r2">
                <input id="way" type="hidden" value="${statusList}">
                <c:forEach items="${statusList}" var="cstStatus" varStatus="status"> 
               <dl class="active">
                    <dt><span></span></dt>
                    <dd>${cstStatus}</dd>
                </dl>
                </c:forEach>
            </div>
        </div> 
        <div class="rec-line border-1px">
          <dl>
                <dt>����${visitTimes}��<span class="rec-line-gjfs"></span><span class="rec-line-new"><img src="/mbem/mcrm/house/images/time_label_new.png" alt=""></span></dt>
                <!--<dd>
                    <div class="rec-line-msg">
                        <p>ϵͳ�Զ��������Ϲ�</p>
                        
                    </div>
                </dd> -->
            </dl>   
             <c:forEach items="${followInfoList }" var="Record" varStatus="status">
            <dl>
                <dt>${Record.xsOpp2Gjjl.gjDate}<span class="rec-line-gjfs">${Record.xsOpp2Gjjl.gjfs}</span></dt>
                <dd>
                    <div class="rec-line-msg">
                        <p>${Record.xsOpp2Gjjl.gjContent}</p>
                        
                        <p class="rec-line-msg-t">�����ˣ�${Record.employee.userName}</p>
                        
                    </div>
                </dd>
            </dl>  </c:forEach>    
        </div>
   </div> 
   
</li>
    
<li id="tab_con_2">

<c:forEach items="${cstInfoList}" var="cus" varStatus="status">            
    <div class="detail-wrap" style="display:block">
       
        <!--<a href="#" class="cst-lib border-1px" data-ac="data-ac">���Ͽ�</a> -->
        
        <dl class="info border-1px">
 				<dt class="border-1px">
                <div class="info-c1 default-tel">�ֻ�</div>
                <input id="cstStatus" type="hidden" value="${cus.xsOpp.status}"/>
                <div class="info-c2">${cus.xsCst.mobileTel}
                    <a href="tel:${cus.xsCst.mobileTel}" class="info-tel btn-go-addrec border-1px" onclick="callCustom('1','${cstGuid}','${oppGuid}')"></a>
                     <a href="sms:${cus.xsCst.mobileTel}" class="info-sms" onclick="callCustom('1','${cstGuid}','${oppGuid}')"></a>
                 </div>
            </dt>
        </dl>
        <dl class="info border-1px">
             <dt class="border-1px">  
                <div class="info-c1">�ͻ���Դ</div>
                <div class="info-c2 ">${cus.xsOpp.oppSource}</div>
            </dt>
            <dt class="border-1px">
                <div class="info-c1">���򼶱�</div>
                <div class="info-c2 ">${cus.xsOpp.gfyx}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">�������</div>
                <div class="info-c2 ">${cus.xsOpp2Room.yxArea}</div>
            </dt>
       <!--     
            <dt class="border-1px">
                <div class="info-c1">����¥��</div>
                <div class="info-c2 unknown">δ֪</div>
            </dt> -->
            <dt class="border-1px">
                <div class="info-c1">������;</div>
                <div class="info-c2">${cus.xsOpp.gfyt}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">��ע����</div>
                <div class="info-c2 ">${cus.xsOpp.gzfm1}</div>
            </dt>
            <dt class="border-1px">
                <div class="info-c1">��֪;��</div>
                <div class="info-c2 ">${cus.xsOpp.subMediaName} </div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">�´θ���ʱ��</div>
                <div class="info-c2">${cus.xsOpp.nextDate}</div>
            </dt>
        </dl>
        <dl class="info border-1px">
            <!--<dt class="border-1px">
                <div class="info-c1">���ػ���</div>
                <div class="info-c2"></div>
            </dt> 
            -->
        <!--    <dt class="border-1px">
                <div class="info-c1">�����籣</div>
                <div class="info-c2 unknown">δ֪</div>
            </dt>
             -->
            <dt class="border-1px">
                <div class="info-c1">��ҵ����</div>
                <div class="info-c2 ">${cus.xsCstAttr.zyNum}</div>
            </dt>
           <!--  
            <dt class="border-1px">
                <div class="info-c1">�����ʸ�</div>
                <div class="info-c2 unknown">δ֪</div>
            </dt>
             -->
            <dt class="border-1px">
                <div class="info-c1">�����</div>
                <div class="info-c2">${cus.xsCstAttr.ageGroup}</div>
             </dt>
             <dt class="border-1px">
                <div class="info-c1">��ס����</div>
                <div class="info-c2 ">${cus.xsCstAttr.homeArea}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">��������</div>
                <div class="info-c2">${cus.xsCstAttr.workArea}</div>
            </dt>
         <!--   <dt class="border-1px">
                <div class="info-c1">����״��</div>
                <div class="info-c2 unknown">δ֪</div>
            </dt> -->
            <dt class="border-1px">
                <div class="info-c1">��ͥ�ṹ</div>
                <div class="info-c2">${cus.xsCstAttr.family}</div>
            </dt>
            <dt class="border-1px">
                <div class="info-c1">���֤</div>
                <div class="info-c2 ">${cus.xsCst.cardId}</div>
            </dt>   
        </dl>
      
    </div> </c:forEach>
    
 </li> </ul> </div>

   
   
       
    <script>
    var UA = navigator.userAgent;
    var forIOS = function(){
        if(!UA.match(/iPad/) && !UA.match(/iPhone/) && !UA.match(/iPod/)){return;}
        if($('#wrapper').length){return;}
        $('body').children().not('script').wrapAll('<div id="wrapper"></div>');
    }();
</script> 
    
<div class="btn-group">    
       <c:forEach items="${cstInfoList}" var="cus" varStatus="status">
       	<a onclick="popClose()" class="btn-close" data-ac="active" >����</a>
        <a href="sms:${cus.xsCst.mobileTel}" class="btn-sms" data-ac="active" onclick="callCustom('1','${cstGuid}','${oppGuid}')">����</a>
        <a href="tel:${cus.xsCst.mobileTel}" class="btn-contact btn-go-addrec" onclick="callCustom('1','${cstGuid}','${oppGuid}')">��ϵ�ͻ�</a>
        <a href="/mbem/mcrm/house/customer/follow.action?type=1&cstGuid=${cstGuid}&oppGuid=${oppGuid}" class="btn-addrec" data-ac="active">��������</a>
        </c:forEach>
   
    </div>

    <div class="form-group submit-group noswitchSinglePage">
        <button class="btn btn-default" id="cancel" data-ac="active">ȡ��</button>
        <button class="btn btn-primary" id="submit" data-ac="active">����</button>

    </div>




</body>
</html>