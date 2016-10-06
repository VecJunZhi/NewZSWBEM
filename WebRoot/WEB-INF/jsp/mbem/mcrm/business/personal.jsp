<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <title>�ͻ�����</title>
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
  			if(sex=="Ů") {
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
                    <div class="bas-pct">��������<span><!--36% --></span></div>
                </div>
            </div>
            
        </div></c:forEach>
        <!-- start ���������Ϣ -->
        <div class="basic-min">
            <a href="#" class="bas-edit" style="" data-ac="bas-edit-active">
                <img src="/mbem/mcrm/business/images/icon_edit.png">
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
        </div></li>
        
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
            �״��������ڣ�${cusTomFirstDate}&nbsp;&nbsp;
            </div>
            

            <div class="rec-p-r1">
                 �´θ������ڣ�${cusTomNextFollowDate}&nbsp;&nbsp;
                <!--<dl class=" ">
                    <dt><span></span></dt>
                    <dd>��ѯ</dd>
                </dl>
                
                <dl class=" ">
                    <dt><span></span></dt>
                    <dd>����</dd>
                </dl>
                
                <dl class=" ">
                    <dt><span></span></dt>
                    <dd>�ϳ�</dd>
                </dl>
                
                <dl class=" ">
                    <dt><span></span></dt>
                    <dd>�Ϲ�</dd>
                </dl>
                
                <dl class=" ">
                    <dt><span></span></dt>
                    <dd>ǩԼ</dd>
                </dl> -->
                
            </div>
        </div>
        
        
 
        <div class="rec-line border-1px">
          <dl>
                <dt>����${cusTomComeView}��<span class="rec-line-gjfs"></span><span class="rec-line-new"><img src="/mbem/mcrm/business/images/time_label_new.png" alt=""></span></dt>
                <!--<dd>
                    <div class="rec-line-msg">
                        <p>ϵͳ�Զ��������Ϲ�</p>
                        
                    </div>
                </dd> -->
            </dl>   
             <c:forEach items="${followByCusIdlist }" var="Record" varStatus="status">
            <dl>
                <dt>${Record.followDate}<span class="rec-line-gjfs">${Record.followWay}</span></dt>
                <dd>
                    <div class="rec-line-msg">
                        <p>${Record.followInfo}</p>
                        
                        <p class="rec-line-msg-t">�����ˣ�${Record.employeeName}<!--<span style="float:right;">${Record.nextFollowDay}�����</span> --></p>
                        
                    </div>
                </dd>
            </dl>  </c:forEach>    
        </div>
   </div> 
</li>
    
<li id="tab_con_2">

<c:forEach items="${cusBasicByCusIdlist}" var="cus" varStatus="status">            
    <div class="detail-wrap" style="display:block">
       
        <!--<a href="#" class="cst-lib border-1px" data-ac="data-ac">���Ͽ�</a> -->
        
        <dl class="info border-1px">
 				<dt class="border-1px">
                <div class="info-c1 default-tel">�ֻ�</div>
                <div class="info-c2">${cus.tel}
                    <a href="tel:${cus.tel}" class="info-tel btn-go-addrec border-1px"></a>
                     <a href="sms:${cus.tel}" class="info-sms"></a>
                    </div>
            </dt>
        </dl>
        <dl class="info border-1px">
             <dt class="border-1px">
                <div class="info-c1">�ͻ���Դ</div>
                <div class="info-c2 ">${cus.followWay}</div>
            </dt>
            <dt class="border-1px">
                <div class="info-c1">���򼶱�</div>
                <div class="info-c2 ">${cus.intentionType}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">�������</div>
                <div class="info-c2 ">${cus.intentionArea}</div>
            </dt>
            
            
       <!--     
            <dt class="border-1px">
                <div class="info-c1">����¥��</div>
                <div class="info-c2 unknown">δ֪</div>
            </dt> -->
            
            
            
            <dt class="border-1px">
                <div class="info-c1">������;</div>
                <div class="info-c2">${cus.houseUse}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">��ע����</div>
                <div class="info-c2 ">${cus.factor}</div>
            </dt>
            <dt class="border-1px">
                <div class="info-c1">��֪;��</div>
                <div class="info-c2 ">${cus.firstVisWay}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">�´θ���ʱ��</div>
                <div class="info-c2 unknown">${cus.nextFollowDate}</div>
            </dt>
        </dl>
        <dl class="info border-1px">
            
            
            <!--<dt class="border-1px">
                <div class="info-c1">���ػ���</div>
                <div class="info-c2">${cus.residenceAddr}</div>
            </dt> -->
            
            
            
        <!--    <dt class="border-1px">
                <div class="info-c1">�����籣</div>
                <div class="info-c2 unknown">δ֪</div>
            </dt>
             -->
            <dt class="border-1px">
                <div class="info-c1">��ҵ����</div>
                <div class="info-c2 ">${cus.buyTimes}</div>
            </dt>
           <!--  
            <dt class="border-1px">
                <div class="info-c1">�����ʸ�</div>
                <div class="info-c2 unknown">δ֪</div>
            </dt>
             -->
            <dt class="border-1px">
                <div class="info-c1">�����</div>
                <div class="info-c2 unknown">${cus.age}</div>
             </dt>
             <dt class="border-1px">
                <div class="info-c1">��ס����</div>
                <div class="info-c2 ">${cus.addrArea}</div>
            </dt>
             <dt class="border-1px">
                <div class="info-c1">��������</div>
                <div class="info-c2 unknown">${cus.workComp}</div>
            </dt>
            
            
            
         <!--   <dt class="border-1px">
                <div class="info-c1">����״��</div>
                <div class="info-c2 unknown">δ֪</div>
            </dt> -->
            
            
            
            <dt class="border-1px">
                <div class="info-c1">��ͥ�ṹ</div>
                <div class="info-c2 unknown">${cus.familyInfo}</div>
            </dt>
            
            
            
            <dt class="border-1px">
                <div class="info-c1">���֤</div>
                <div class="info-c2 ">${cus.idNo}</div>
            </dt>
            
            
            
         <!--   <dt class="border-1px">
                <div class="info-c1">���ҵ��׶�ʱ��</div>
                <div class="info-c2 ">2015-05-31 00:00</div>
            </dt>
             -->
            
            
            <dt class="border-1px">
                <div class="info-c1">����ҵ̬</div>
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
       	<a onclick="popClose()" class="btn-close" data-ac="active">����</a>
        <a href="sms:${cus.tel}" class="btn-sms" data-ac="active" onclick="callCustom('1')">����</a>
        <a href="tel:${cus.tel}" class="btn-contact btn-go-addrec" onclick="callCustom('1')">��ϵ�ͻ�</a>
        <a href="/mbem/mcrm/business/follow.action?type=1" class="btn-addrec" data-ac="active">��������</a>
        </c:forEach>
        
    </div>  
</body></html>