<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE html><html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
     <link rel="stylesheet" href="/mbem/mcrm/business/css/bmin.css">
       <link rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
        <link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/selectroom_srs.css">
        <link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/saleRoom.css">
 
    <script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
    <script type="text/javascript" src="/common/js/jquery-1.9.1.min.js"></script>
<!--    <script type="text/javascript" src="/mbem/mcrm/business/js/swiper.min.js"></script>
    <script type="text/javascript" src="/mbem/mcrm/business/js/iscroll-zoom.js"></script>
    <script type="text/javascript" src="/mbem/mcrm/business/js/room_common.js"></script>-->
    <title>销控管理</title>
    
     <script type="text/javascript">
	    function new_form(){  
	        var f = document.createElement("form");  
	        document.body.appendChild(f);  
	        f.method = "post";  
	        return f;  
	    }  
	      
	    function create_elements(eForm,eName,eValue){  
	        var e=document.createElement("input");  
	        eForm.appendChild(e);  
	        e.type='text';  
	        e.name=eName;  
	        if(!document.all){  
	            e.style.display='none';  
	        }else{  
	            e.style.display='block';  
	            e.style.width='0px';  
	            e.style.height='0px';  
	        }  
	        e.value=eValue;  
	        return e;  
	    }  
    </script>
    <script type="text/javascript">
    	$(function ($) {
			if (window.history && window.history.pushState) {
			$(window).on('popstate', function () {
		        var hash = window.location.hash;
		        if (hash === '') {
		            //window.location.href="/mbem/mcrm/house/saleManager/index.action";
		            window.location.href="/mbem/mcrm/house/find/findSearch.action";//2016.4.10 置业顾问使用，返回发现页的房源信息
		        }
		    });
		    var url = window.location.url;
		    window.history.pushState('forward', null, url);
		    location.hash="#1";
			}
		});    	 
    
    	$(document).ready(function(){
    		$(".unit-short span:first").addClass("checked");
    		$("div.roomlisttable-zoom :first").attr("style","display:block");
    	
    		$(".unit-short").children().click(function(){
    			$(".unit-short").children().removeClass("checked");
    			$(this).addClass("checked");
    			var unit = $(this).children().children().html();
    			$(".roomlisttable-zoom").each(function(){
    				var id=$(this).attr("id");
    				if(id.indexOf(unit) != -1)
    				{
    					$(".roomlisttable-zoom").attr("style","display:none");
    					$(this).attr("style","display:block");
    				}
    			});
    		});
    		
    		$("#searchRoom").click(function(){
    			
    			var bldNo = $("#bldGuid").val();
    			var _f = new_form();
    			//alert(bldNo);
				create_elements(_f,"bldGuid",bldNo);
				_f.action = "/mbem/mcrm/house/room/searchRoom.action";
				_f.submit(); 
    		});
    		
    		$("#chooseBuilding").click(function(){
    			window.location.replace("/mbem/mcrm/house/room/selectRoomBulid.action");
    		});
    		
    		/* $(".room_floor li").each(function(){
    			$(this).click(function(){
    			if($(this).hasClass("wfp") == true) {
    				alert("你没有该权限");
    				return false;	
    			}
    			var _f = new_form();
				create_elements(_f,"bldGuid",$("input",this).attr("bldNo"));
				create_elements(_f,"unit",$("input",this).attr("unitNo"));
				create_elements(_f,"roomNo",$("input",this).attr("roomNo"));
				_f.action = "/mbem/mcrm/house/room/roomInfo.action";
				_f.submit(); 
    			});
    		}); */
    	});
    	
    	function viewRoomInfo(tag){
   			if($(tag).hasClass("wfp") == true) {
   				alert("你没有该权限");
   				return false;	
   			}
   			var _f = new_form();
			create_elements(_f,"bldGuid",$("input",tag).attr("bldNo"));
			create_elements(_f,"unit",$("input",tag).attr("unitNo"));
			create_elements(_f,"roomNo",$("input",tag).attr("roomNo"));
			_f.action = "/mbem/mcrm/house/room/roomInfo.action";
			_f.submit(); 
    	}
    </script>
    <style type="text/css">
        body{padding-top:48px;background-color:#f0f0f0}
        .powered{
            position: fixed;
            margin-bottom: 0px;
            bottom: 6px;
            font-size: 11px;
            color: #aaa;
            text-align: center;
            width: 100%;
        }
		 .room_floor li{box-sizing:border-box;}
        .room_floor li.yus{color:#fff;background: #FC3;}
        .room_floor li.wfp{color:#fff;background: #BEBEBE;}
       
        .room_floor li.li_done,.room_floor li.li_ready{background-size:25px 25px;background-position:right bottom;-webkit-background-size:25px 25px;background-repeat:no-repeat;}
        .room_floor li.li_done{background-image:url(/Public/myui_v1/css/images/hook_yellow_focus.png);}
        .room_floor li.li_ready{background-image:url(/Public/myui_v1/css/images/hook_default.png);}
        .room_floor li.li_done.yus{background-color:#fff;color:#9a9a9a}
    </style> 
	
</head>
<body class="bg-gray">
<div class="menu-panel">
        <ul>
            <li class="change">
                <i></i>
                <span id="chooseBuilding">${bldFullName} <input id="bldGuid" type="hidden" value="${bldGuid}"></span>
                <span class="choose-building-next">&gt;</span>
            </li>
            <li class="search"><i></i><span id="searchRoom">筛选</span></li>
        </ul>
    </div>    
    <div id="ajaxloadimg" style="display:none;  width:32px; height:32px; position:absolute; z-index:1999; top:50px; left:45%;">

    </div>

    <div class="clearfix unit-warp">
        <div class="unit-short">
        	<c:forEach items="${unitNameList}" var="unitName" varStatus="status">
            	<span class="unit-btn">
                     <span class="unit-hidden">
                     <span class="unit-num">${unitName}</span></span>
                 </span>
             </c:forEach>     
                    <span class="unit-btn more" style="display: none;">
                <span class="unit-hidden" style="width: 173.25px;">
                    更多...
                </span>
            </span>
        </div>

        <div class="more-popup">
            <div class="fag"></div>
                    </div>
    </div>
    <div class="roomlistchart">
        <!--<div class="swiper-container" id="swiperRoomName">-->
        <!--<div class="swiper-wrapper bld-header" id="bldHeader">-->
        <!---->
        <!--<div class="swiper-slide"><div>史家庄城中村改造项目-御泽嘉园-A区-1号楼</div></div>-->
        <!---->
        <!--</div>-->
        <!--<div class="button-prev"></div><div class="button-next"></div>-->
        <!--</div>-->

        <div class="swiper-container" id="swiperRoom">
            <div class="swiper-wrapper">
                                    <div class="swiper-slide" >
                            <div class="scrollpanel clearfix">
                                <div class="roomtemplt bg-gray">
                                <ul>
                                    <li class="fl sold_yes">
                                        <i></i>
                                        <span class="text">已售</span>
                                <span class="YiS">
                                    <span class="num">
                                        <span class="build-tao" id="1">${roomCountInfo.sold}</span>套
                                    </span>
                                    <span class="lit">占${roomPercentInfo.sold}</span>
                                </span>
                                    </li>
                                    <li class="fl sold_no">
                                        <i></i>
                                        <span class="text">未售</span>
                                <span class="WeiS">
                                    <span class="num">
                                        <span class="build-tao">${roomCountInfo.unSold}</span>套
                                    </span>
                                    <span class="lit">占${roomPercentInfo.unSold}</span>
                                </span>
                                    </li>
                                    <li class="fl sold_wfp">
                                        <i></i>
                                        <span class="text">销控</span>
                                <span class="WeiS">
                                    <span class="num">
                                        <span class="build-tao">${roomCountInfo.preControl}</span>套
                                    </span>
                                    <span class="lit">占${roomPercentInfo.preControl}</span>
                                </span>
                                    </li>
                                </ul>
                            </div>
							
							

							
                            <div class="floor-warp">
                                <div class="floor"><span></span></div>
                            </div>
           <!--                    <div class="building-floor-wrap">
                                <div class="building-floor-num" id="building-floor-num_一单元" style="height: 370px; display: block;">
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">20</div><div class="floor-head" style="height: 107px; padding-top: 37px;">19</div><div class="floor-head" style="height: 107px; padding-top: 37px;">18</div><div class="floor-head" style="height: 107px; padding-top: 37px;">17</div><div class="floor-head" style="height: 107px; padding-top: 37px;">16</div><div class="floor-head" style="height: 107px; padding-top: 37px;">15</div><div class="floor-head" style="height: 107px; padding-top: 37px;">14</div><div class="floor-head" style="height: 107px; padding-top: 37px;">13</div><div class="floor-head" style="height: 107px; padding-top: 37px;">12</div><div class="floor-head" style="height: 107px; padding-top: 37px;">11</div><div class="floor-head" style="height: 107px; padding-top: 37px;">10</div><div class="floor-head" style="height: 107px; padding-top: 37px;">9</div><div class="floor-head" style="height: 107px; padding-top: 37px;">8</div><div class="floor-head" style="height: 107px; padding-top: 37px;">7</div><div class="floor-head" style="height: 107px; padding-top: 37px;">6</div><div class="floor-head" style="height: 107px; padding-top: 37px;">5</div><div class="floor-head" style="height: 107px; padding-top: 37px;">4</div><div class="floor-head" style="height: 107px; padding-top: 37px;">3</div>                                    
                                </div>
                                <div class="building-floor-num" id="building-floor-num_二单元" style="display: none; height: 370px;">
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">20</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">19</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">18</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">17</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">16</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">15</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">14</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">13</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">12</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">11</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">10</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">9</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">8</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">7</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">6</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">5</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">4</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">3</div>                                   
                               </div>
                               <div class="building-floor-num" id="building-floor-num_三单元" style="display: none; height: 370px;">
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">20</div>
                                        <div class="floor-head" style="height: 107px; padding-top: 37px;">19</div><div class="floor-head" style="height: 107px; padding-top: 37px;">18</div><div class="floor-head" style="height: 107px; padding-top: 37px;">17</div><div class="floor-head" style="height: 107px; padding-top: 37px;">16</div><div class="floor-head" style="height: 107px; padding-top: 37px;">15</div><div class="floor-head" style="height: 107px; padding-top: 37px;">14</div><div class="floor-head" style="height: 107px; padding-top: 37px;">13</div><div class="floor-head" style="height: 107px; padding-top: 37px;">12</div><div class="floor-head" style="height: 107px; padding-top: 37px;">11</div><div class="floor-head" style="height: 107px; padding-top: 37px;">10</div><div class="floor-head" style="height: 107px; padding-top: 37px;">9</div><div class="floor-head" style="height: 107px; padding-top: 37px;">8</div><div class="floor-head" style="height: 107px; padding-top: 37px;">7</div><div class="floor-head" style="height: 107px; padding-top: 37px;">6</div><div class="floor-head" style="height: 107px; padding-top: 37px;">5</div><div class="floor-head" style="height: 107px; padding-top: 37px;">4</div><div class="floor-head" style="height: 107px; padding-top: 37px;">3</div>                                    
                              </div>                            
                            </div>  --> 

                                    <c:forEach items="${unitInfoList}" var="unit" varStatus="status"> 
                                    <div class="roomlisttable-zoom" id="roomtable_${unit.unitName}" style="display: none;height:auto;overflow:hidden;">
                                        <div class="roomlisttable" id="roomlisttable" style=" height:auto;overflow:hidden;">
                                        <!-- 单元 -->
                                        <div class="roomtable" style="width: 236px; transition: 0ms cubic-bezier(0.1, 0.57, 0.1, 1); -webkit-transition: 0ms cubic-bezier(0.1, 0.57, 0.1, 1); transform: translate(0px, 0px) scale(1) translateZ(0px);">
											<c:forEach items="${unit.unitInfo}" var="floorInfo" varStatus="status"> 
                                            <div class="building-floor">
                                                    <div class="">
                                                        <div class="floor-head">${floorInfo.floorNo}</div>
                                                    </div>

                                                    <div class="floor-ul">
                                                        <ul class="room_floor">
                                                            <c:forEach items="${floorInfo.roomList}" var="room" varStatus="status"> 
                                                            	<shiro:hasRole name="销售系统">  
																    <li url="/mbem/mcrm/house/room/roomInfo.action" rgid="bd3feba3-6bea-e411-baaf-fcaa145c42f2" state="ys" class="${room.statusClass}" style="width: 65px;">
                                                                		${room.roomNo}
                                                               			<input type="hidden" bldNo="${room.bldNo}" unitNo="${room.unitNo}" roomNo="${room.roomNo}">                                                      
                                                               		</li>
																</shiro:hasRole>
																<shiro:hasAnyRoles name="系统管理员,销售系统管理员">
																	<li url="/mbem/mcrm/house/room/roomInfo.action" rgid="bd3feba3-6bea-e411-baaf-fcaa145c42f2" state="ys" class="${room.statusClass}" style="width: 65px;" onclick="viewRoomInfo(this)">
                                                                		${room.roomNo}
                                                                		<input type="hidden" bldNo="${room.bldNo}" unitNo="${room.unitNo}" roomNo="${room.roomNo}">                                                      
                                                                	</li>
																</shiro:hasAnyRoles>                                                                                                                     
                                                                
                                                            </c:forEach> 
                                                         </ul>
                                                    </div>
                                                </div>
                                                </c:forEach>                                     
                                        </div>
                                     </div>
                                        </div>
                                       </c:forEach> 
                                                 </div>
                                            </div>
                                        </div>
                                </div>
                            </div>
    
    <!-- <style>
    .powered{
        margin-bottom: 6px;
        font-size: 11px;
        color: #aaa;
        text-align: center;
        width: 100%;
    }
    .powered a{
        color: #aaa;
        text-decoration: none;
    }
    .powered a:hover,.powered a:visited{
        color: #aaa;
    }
    .powered a:active{
        color: #2caf56;
    }
</style>
<div class="powered" id="powered">
    Powered&nbsp;by&nbsp;&nbsp;<a href="http://m.myunke.com/">明源云客</a>
</div> -->
    <script>
        var bld_guid_arr = ["b93feba3-6bea-e411-baaf-fcaa145c42f2"];
        var timer;
        $(function(){
            var interval=setInterval(function(){
                var wh=$(window).height();
                if(wh!=0){
                    clearInterval(interval);
                    $('.swiper-slide').css('height',wh-88);
                }
            },100);
            /* $("#chooseBuilding").on("click", function () {
                var url   = "/Sales/RoomStatus/selectBulid?token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&projid=c5091504-41ae-4c2e-ae3b-69a008a5762f";
                window.sessionStorage.removeItem('goCustomer_selectBulid');
                window.location.href=url;
            });

            //进入高级搜索
            $("#searchRoom").on("click", function () {
                var url   = "/Sales/RoomStatus/search?token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&projid=c5091504-41ae-4c2e-ae3b-69a008a5762f";
                window.sessionStorage.removeItem('goSaleManage_selectBulid');
                window.location.href=url;
            }); */

            if(document.referrer.indexOf("/Sales/Find/findmain")<0){
                if(document.referrer.indexOf("/Sales/RoomStatus/selectroominfo")>=0){
                    window.sessionStorage.setItem('goCustomer_selectroominfo',1);
                    window.sessionStorage.removeItem('goCustomer_selectBulid');
                }
                else if(document.referrer.indexOf("/Sales/Customer/selectBulid")>0){
                    window.sessionStorage.setItem('goCustomer_selectBulid',1);
                    window.sessionStorage.removeItem('goCustomer_selectroominfo');
                }
            }
//                loadSwiper();
            checkArrowShow();
            // var myscroll=new iScroll("swiperRoom");
            $.each(bld_guid_arr,function(i,item){
                getErpRoom(item,i);
            });

            // 置业顾问和经理公用逻辑
            saleCommonExecute();

//                document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
        });


        function loadSwiper(){
            // Initialize Swiper
            var currentSlide = getRecord() || 0;
            var room = new Swiper('#swiperRoom',{
                initialSlide: currentSlide
            });
            var roomName = new Swiper('#swiperRoomName', {
                centeredSlides: true,
                slidesPerView: 'auto',
                touchRatio: 0.2,
                slideToClickedSlide: true,
                initialSlide: currentSlide,
                nextButton: '.button-next',
                prevButton: '.button-prev'
            });
            room.params.control = roomName;
            roomName.params.control = room;
            // end  Swiper
            // roomName.on('slideChangeEnd', function () {
            //     $(document).scrollTop(0)
            // });
        }
        // 检查是否只有一项楼栋
        function checkArrowShow(){
            var len = $("#bldHeader .swiper-slide").length;
            if(len <= 1){
                $("#button-next,#button-prev").hide();
            }
        }

        //ajax加载ERP数据
        function getErpRoom(bld_guid,index){
            var url = "/Sales/RoomStatus/getErpRooms?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557";
            //var bld_guid  = '';
            $.ajax({
                type: "GET",
                url: url,
                data: {bld_guid:bld_guid,searchData:null},
        dataType: "json",
                success: function(result){
            var YiS = $('.YiS').eq(index);
            var WeiS = $('.WeiS').eq(index);

            YiS.find('.build-tao').html(result.data.yis_count);
            WeiS.find('.build-tao').html(result.data.weis_count);

            YiS.find('.lit').html('占'+result.data.yis_count_bi);
            WeiS.find('.lit').html('占'+result.data.weis_count_bi);

            if(result.status==0){
                var usertype = '';
                var data  =  result.data.erpRooms;
                $('.room_floor').eq(index).find("li").each(function(i,o){
                    var rgid = $(o).attr('rgid');
                    if(!data.hasOwnProperty(rgid))return;
                    var is_sale = data[rgid].is_sale;

                    var is_fp = data[rgid].is_fp;

                    if(is_fp == 1){
                        if(is_sale==1){
                            roomState = 'ys';
                        }else{
                            if($(o).attr('state') =='yus'){
                                roomState = 'ys';
                            }else{
                                roomState = 'ws';
                            }
                        }
                    }else{
                        roomState = 'ys';
                    }

                    $(o).removeClass();
                    $(o).addClass(roomState);

                })
            }
        }
        });
        }
        //查看房间详情
        function goUrl(obj,url){
            //var showTipsdiv;
            if($(obj).attr('state') == 'wfp'){
                showErrMsg("您无查看该房间信息权限");
            }else if($(obj).hasClass('ys')){
                var param = url.substr(url.indexOf("?")+1);
                var new_url = 'checkRoomTrade';
                $.ajax({
                    type: "GET",
                    url: new_url,
                    data: param,
                    success: function(result){
                        var json =  JSON.parse(result);
                        if(json.status === 0){
                            if(json.data === 1){
                                setRecord();
                                window.location.href = url;
                            }else{
                                showErrMsg("您无查看该房间信息权限");
                            }
                        }
                    }
                });
            }else{
                if(url.indexOf("/Sales/RoomStatus/selectroominfo")>=0){
                    window.sessionStorage.removeItem('goCustomer_selectroominfo');
                }
                setRecord();
                window.location.href=url;
            }
        }
        function showErrMsg(msg){
            $("#show_tips_black").fadeIn();
            $("#show_tips_black_content").html(msg);
            clearTimeout(timer);
            timer = setTimeout("$('#show_tips_black').fadeOut();", 2000);
            return;
        }
        function setRecord(){
            var index = $("#bldHeader").find(".swiper-slide-active").index();
            window.sessionStorage.setItem("currentSlide",index);
        }
        function getRecord(){
            var index = window.sessionStorage.currentSlide;
            if(index){
                window.sessionStorage.removeItem("currentSlide");
                return index;
            }
        }
    </script>
    <div id="show_tips_black" style="position:fixed;display:none; z-index:9898989; text-align:center;bottom:90px;width:100%; height:30px;">
        <div id="show_tips_black_content" style="width:270px;background:#333; height:40px; color:#FFF;  line-height:40px; font-size:16px; margin:auto; border-radius:10px; "></div>
    </div>

</body></html>