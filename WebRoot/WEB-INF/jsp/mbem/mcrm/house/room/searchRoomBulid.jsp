<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/mbem/mcrm/business/css/bmin.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/mobile/ui.css">
<title>当前项目搜索</title>
    <script>
    var statistics={}
    statistics.start=+new Date();
</script>
 <script >
        statistics.t1=+new Date()-statistics.start;
    </script>
      <script  src="/common/js/jquery-1.9.1.min.js"></script>
    <script >
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
	    $(function ($) {
			if (window.history && window.history.pushState) {
			$(window).on('popstate', function () {
		        var hash = window.location.hash;
		        if (hash === '') {
		            /* window.location.href="/mbem/mcrm/house/saleManager/index.action"; */
		        	window.location.href="/mbem/mcrm/house/find/findSearch.action";
		        }
		    });
		    var url = window.location.url;
		    window.history.pushState('forward', null, url);
		    location.hash="#1";
			}
		});    	 
	    $(document).ready(function(){
	    
	    	$("#search_input").click(function(){
	    		
	    		var status="";
	    		var huXing="";
	    		var roomStru="";
				var minTotalPrice="";
				var maxTotalPrice="";
				var minArea="";
				var maxArea="";
	    		$("div[id^=search]").each(function(){
	    			var type = $("label",this).text();
	    			switch(type) {
	    				case "搜索范围":
	    					break;
	    				case "总价范围":
	    					var tag = $("div",this);
	    					if($("input.begin",tag).val() != "")
	    						minTotalPrice = $("input.begin",tag).val()+"0000";
	    					if($("input.end",tag).val() != "")
	    						maxTotalPrice = $("input.end",tag).val()+"0000";
	    					//alert(minTotalPrice);
	    					//alert(maxTotalPrice);
	    					break;
	    				case "面积范围":
	    					var tag = $("div",this);
	    					minArea = $("input.begin",tag).val();
	    					maxArea = $("input.end",tag).val();
	    					//alert(minArea);
	    					//alert(maxArea);
	    					break;
	    				case "房间状态":
	    					var tag = $("div",this);
	    					$("li",tag).each(function(){
	    						if($(this).hasClass("select")){
	    							var val = $("div",this).text();
	    							switch(val){
	    							case "已售":
	    							  val="\'认购\',\'签约\'";
	    							  break;
	    							 case "未售":
	    							  val = "\'待售\'";
	    							  break;
	    							 case "预销控":
	    							 case "未放盘":
	    							 	val = "\'销控\'";
	    							  break;
	    							}
	    							status += val+",";
	    						}
	    					});
	    					if(status != "") {
	    						status = status.substring(0, status.length-1);
	    					}
	    					//alert(status);
	    					break;
	    				case "房间结构":
	    					var tag = $("div",this);
	    					$("li",tag).each(function(){
	    						if($(this).hasClass("select"))
	    							roomStru += "\'"+$("div",this).text()+"\'"+",";
	    					});
	    					if(roomStru != "") {
	    						roomStru = roomStru.substring(0, roomStru.length-1);
	    					}
	    					//alert(roomStru);
	    					break;
	    				case "户型":
	    					var tag = $("div",this);
	    					$("li",tag).each(function(){
	    						if($(this).hasClass("select"))
	    							huXing +=  "\'"+$("div",this).text()+"\'"+",";
	    					});
	    					if(huXing != "") {
	    						huXing = huXing.substring(0, huXing.length-1);
	    					}
	    					//alert(huXing);
	    					break;
	    			}
	    		});
	    		
	    		var _f = new_form();
				create_elements(_f,"huXing",huXing);
				create_elements(_f,"roomStru",roomStru);
				create_elements(_f,"status",status);
				create_elements(_f,"minTotalPrice",minTotalPrice);
				create_elements(_f,"maxTotalPrice",maxTotalPrice);
				create_elements(_f,"minArea",minArea);
				create_elements(_f,"maxArea",maxArea);		
				_f.action = "/mbem/mcrm/house/room/searchRoomInCurProject.action";
				_f.submit(); 
	    	});
	    }); 
    </script>
</head>
<body data-pageid="10" data-php="" data-ram="" data-token="cdycid1429859342" data-projid="c5091504-41ae-4c2e-ae3b-69a008a5762f">
<style type="text/css">
body{
    overflow: hidden;
}
.float-right{float: right;}
.container {
    margin: 10px 15px;
}

.center {
    margin-right: auto;
    margin-left: auto;
    text-align: center; }

.link {
    color: #5cb85c;
    font-size: 15px; }

.btn {
    display: inline-block;
    padding: 0 12px;
    margin: 5px 0px;
    outline: none;
    font-size: 16px;
    font-weight: normal;
    line-height: 38px;
    height: 38px;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    color: white;
    background-color: #33cc66;
    border: 0px;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    -o-user-select: none;
    user-select: none;
    -webkit-appearance: none;
}

.btn:hover,
.btn:focus {
    text-decoration: none;
    color: white;
    background-color: #33cc66; }

.btn:active,
.btn.active {
    color: white;
    background-color: #2caf56;
    background-image: none;
    outline: none; }

.calculatorBtn {
    display: inline-block;
    padding: 0 12px;
    margin: 5px 0px;
    font-size: 16px;
    font-weight: normal;
    line-height: 38px;
    height: 38px;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    color: white;
    background-color: #33cc66;
    border: 0px;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    -o-user-select: none;
    user-select: none; }

.btn.disabled,
.btn[disabled],
.btn.disabled:hover,
.btn[disabled]:hover,
.btn.disabled:focus,
.btn[disabled]:focus,
.btn.disabled.active,
.btn[disabled].active {
    background-color: #2caf56; }
.fl{float:left;}
.fr{float:right;}
.clearfix:before, .clearfix:after{content: "200B"; display: block; height: 0; overflow: hidden; }
.clearfix:after{clear: both; }

.btn-block {
    display: block;
    width: 100%;
    padding-right: 0;
    padding-left: 0; }

.btn.white_btn {
    background: #fff;
    border: 1px solid #d8d8d8;
    color: #33cc66; }

.btn.white_btn.select {
    background: #33cc66;
    color: #fff;
    border-color: #33cc66; }

.btn.cancel-btn {
    color: #333; }

.btn.cancel-btn:active,.btn.cancel-btn:hover{
    background-color: #33cc66; color: #fff;
}
.btn.borderbtn{
    border: 1px solid #33cc66;
    color:#33cc66;
}
.tow-Btn {
    height: 38px;
    clear: both;

}

.tow-Btn .btn {
    width: 100%;
}

.bottom {
    position: fixed;
    bottom: 0px;
    right: 10px;
    left: 10px;
    width: auto; }

form .form-group {
    margin: 5px 0px;
    padding: 7px 0px;
    display: table;
    content: " ";
    position: relative;
    width: 100%;
    border-style: solid;
    border-color: #f3f3f3;
    border-width: 0px 0px 1px 0px; }

form.new-form-group  .form-group{
    padding: 16px 0px;
}

form.new-form-group  .clearfix{
    padding: 0px 0 2px;
}

form .clearfix{border:0;}

form .form-group label {
    margin-bottom: 6px;
    color: #333333;
    /*font-weight: bold;*/
    font-size: 15px;
    width: 80px; }

form .form-group label.error {
    width: 200px; }

form .form-group .form-control {
    position: absolute;
    height: 100%;
    left: 80px;
    top: 3px;
    right: 0px;
    padding-right: 20px;
}
form .form-group #dkllToggle{
    padding-right:0;
}
form .form-group .hide{
    display:none;
}

form .form-group .prNone{
    padding-right:0;
}

form .form-group .new-form-control{
    top: 11px;
    left:90px;
    padding-right:0;
}

form .form-group .clearfix{
    position:relative;
    left:0;
    padding-right:0;
}

form .form-group{
    overflow:hidden;
}

.form-control input,
.form-control input:disabled,
.form-control textarea {
    border: 0px;
    color: #a5a5a5;
    width: 100%;
    padding: 5px; }

.new-form-group .form-control input,
.new-form-group .form-control input:disabled{
    height:20px;
    padding: 10px 5px 8px;
}

.new-form-group #dkllToggle input:disabled{
    padding:12px 5px 6px;
}

.new-form-group .needUnitSpan input{
    padding: 13px 5px 5px;
}

.form-control textarea {
    height:30px;max-height: 200px; }

.form-group .form-control.captcha {
    padding-right: 0px; }

.form-control.captcha input {
    width: 95%; }

.form-control.captcha input.captcha {
    width: 90px;
    border: 1px solid #efefef; }

.form-group span.error {
    position: absolute;
    left: 130px; }

.captchaBtn {
    position: absolute;
    right: 0;
    top: 2px;
    padding: 5px;
    border: 1px solid #EFEFEF;
    border-radius: 5px;
    background: #fff; }

.form-control.needUnitSpan {
    margin-right: 10px; }

.form-control.needUnitSpan span {
    position: absolute;
    right: -10px;
    top: 3px;
    text-align: left; }

.new-form-group .form-control.needUnitSpan span{
    top:13px;
}

.form-control .telSpan {
    position: absolute;
    right: 0;
    top: 3px;
    width: 20px;
    height: 20px;
    display: inline-block;
    z-index: 20; }

form .form-group .form-control.clearBoth {
    position: relative;
    left: 0; }

form .form-group .form-control .multi-input li {
    float: left;
    line-height: 30px;
    text-align: center; }

form .form-group .form-control .multi-input li input {
    text-align: center;
    width: 52px;
    display: inline-block; }

form .form-group .form-control .multi-input li input.min_input {
    width: 20px; }

form .form-group .form-control .multi-input li span {
    color: #a5a5a5;
    margin: 0 10px; }

.new-form-group .form-group .form-control .multi-input li span{
    margin:0;
    vertical-align:1px;
}

.form-control ul li {
    float: left; }

.form-control ul.checkbox {
    padding: 12px 0 0 5px; }

.form-control ul.checkbox li {
    color: #a5a5a5;
    padding-left: 26px;
    margin-right: 40px;
    height: 22px;
    cursor: pointer;
    position: relative; }

.new-form-control ul.checkbox li{
    width:52px;
    margin-right: 10px;
}

.form-control ul.checkbox li > span {
    position: absolute;
    left: 0;
    display: inline-block;
    height: 20x;
    width: 20px;
    line-height: 20px;
    -moz-border-radius: 11px 11px 11px 11px;
    -webkit-border-radius: 11px 11px 11px 11px;
    border-radius: 11px 11px 11px 11px;
    background: url("/Public/myui_v1/css/images/select_empty@2x.png") no-repeat center center;
    background-size: 20px; }

.form-control ul.checkbox li.select > span {
    -moz-border-radius: 11px 11px 11px 11px;
    -webkit-border-radius: 11px 11px 11px 11px;
    border-radius: 11px 11px 11px 11px;
    background-image: url("/Public/myui_v1/css/images/select_full@2x.png"); }

.form-control ul.checkbox li > div {
    position: absolute;
    left: 26px;
    top: 1px;
    bottom: 0px; }

.new-form-control ul.checkbox li > div{
    line-height:8px;
}

.form-control ul.multSelect,
.form-control ul.singleSelect,
.form-control .MultiLayerSelect {
    display: none; }

ul.multSelect, ul.singleSelect {
    height: 240px;
    overflow: hidden;
    position: absolute;
    width: 100%; }

ul.PPmultSelect, ul.PPsingleSelect {
    margin-top: -3px;}

ul.ppTap{
    overflow:hidden;border:1px solid #33cc66;border-radius:5px;border-right:0;
}

ul.multSelect li,
ul.singleSelect li {
    font-size: 16px;
    padding: 0 12px 0 40px;
    height: 43px;
    line-height: 43px;
    cursor: pointer;
    color: #333333;
    border-bottom: 1px solid #f0f0f0;
    background: url("images/select_empty@2x.png") no-repeat 11px center;
    background-size: 20px; }

ul.singleSelect li {
    background: none;
    padding-left: 12px; }

ul.multSelect li:active,
ul.singleSelect li:active,
li.panel_group_content:active,
.panel_group_content li:active {
    background-color: #dddddd; }

.new-form-group .form-group .form-control ul.multi-input .item{
    color:#333;
    background:#f0f0f0;
    padding-right:5px;
    margin-top:4px;
}

.new-form-group .form-group .form-control ul.multi-input p{
    line-height:18px;
    padding:0 0 0 4px;
    display:inline-block;
}

.new-form-group .form-group .form-control ul.multi-input .item input{
    color:#3c6;
    width:40px;
    text-align: right;
}

.new-form-group .form-group .form-control ul.multi-input .item input.tr{
    text-align:right;
    width:29px;
}

.new-form-group .form-group .form-control ul.multi-input input.white{
    background:#fff;
    color:#3c6;
    width:35px;
    padding-left:0;
    text-align:right;
}

ul.multi-input li i{
    color:#3c6;
    font-style:normal;
}
#advance_search_box ul.PPmultSelect,
#advance_search_box ul.PPsingleSelect{
    width: 100%;
}
ul.PPmultSelect li,
ul.PPsingleSelect li {
    width: 33.33%;

    color: #666;
    float: left;
    box-sizing: border-box;
}
ul.PPmultSelect li div,
ul.PPsingleSelect li div{
    text-align: center;
    border-radius: 3px;
    border: 1px solid #d7d7d7;
    width: 100%;
    height:39px;
    line-height: 39px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
ul.PPmultSelect li.select div,
ul.PPsingleSelect li.select div {
    color: #fff;
    border:1px solid #3c6;
    background-color: #33cc66;
}
ul.NewPPsingleSelect li{
    border:0;
}

ul.PPsingleSelect.equal li {
    width: 42px;
    padding: 4px; }

ul.PPsingleSelect.equal li{
    /*width: -webkit-calc( 100% / 3 - 1px );*/
    /*width: calc( 100% / 3 - 1px );*/
    padding:10px 0;
}

ul.ppTap li{
    margin:0;
    border-radius:0;
    -webkit-border-radius:0;
    border:0;
    border-right:1px solid #33cc66;
}

ul.PPsingleSelect li:last-child{
    border-right:0;
}

ul.multSelect li.select,
ul.singleSelect li.select {
    color: #33cc66;
    position: relative;
    background-image: url("images/select_full@2x.png"); }

ul.singleSelect li.select {
    background: #33CC66;
    color: #fff; }

ul.ppTap li.select {
    color: #3c6;
    border:0;
}

ul.NewPPsingleSelect li.select {
    color: #3c6;
    border:0;
}

ul.ppTap li.select {
    background:#3c6;
    border-color: #33cc66;
    color: #fff; }

.form-control .select-value {
    color: #a5a5a5;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    padding: 5px 5px 5px 5px; }

.new-form-group .form-control .select-value{
    color:#a5a5a5;
    padding: 14px 25px 5px 5px;
}

i.arrow_right {
    width: 40px;
    height: 20px;
    display: inline-block;
    position: absolute;
    right: 0;
    top: 6px;
    background: url("/Public/myui_v1/css/images/yhxx_08.png") no-repeat;
    background-size: 15px; }
#divStage i.arrow_right{background:none;top:0;}
.new-form-group i.arrow_right{
    top:16px;
}

.td_arrow_box {
    position: relative; }

.td_arrow_box i.arrow_right {
    top: 12px; }

.form-group .tel {
    margin-right: 20px; }

.form-group .tel span {
    display: block;
    position: absolute;
    top: 6px;
    right: -20px; }

form .disabled label {
    color: #a5a5a5; }

.MultiLayerSelect {
    padding: 0px 15px 0px 15px;
    position: relative;
    color: #000000; }

.MultiLayerSelect_left {
    width: 100px;
    position:absolute;
    overflow:hidden;
    border-right: 1px solid #cccccc;
    height: 305px;
    /*overflow: scroll;
    overflow-x: hidden;
    */ }

.MultiLayerSelect_left li {
    line-height: 33px; }

.MultiLayerSelect_left li.select {
    color: #33CC66;
    position: relative; }

.MultiLayerSelect_left li.select span {
    width: 0px;
    height: 0px;
    border-bottom: 5px solid transparent;
    /* left arrow slant */
    border-top: 5px solid transparent;
    /* right arrow slant */
    border-right: 5px solid #33CC66;
    /* bottom, add background color here */
    font-size: 0px;
    line-height: 0px;
    position: absolute;
    right: 0;
    top: 12px; }

.MultiLayerSelect_right {
    position: absolute;
    overflow:hidden;
    top: 0;
    left: 150px; }

.MultiLayerSelect_right li {
    line-height: 33px; }

.MultiLayerSelect_right ul {
    display: none; }

.MultiLayerSelect_right ul.select {
    display: block; }

.MultiLayerSelect_right ul.select li.select {
    color: #33CC66; }


ul.tab li {
    float: left;
    display: inline-block;
    width: 50%;
    height: 100%;
    vertical-align: middle;
    text-align: center;
    font-size: 14px;
    cursor: pointer; }

ul.tab li div {
    margin-top: 7px; }

ul.tab li.select {
    background: #33cc66;
    color: white; }
#mask .buttonBox {
    padding: 0 12px;
    margin: 10px 0; }

#mask .buttonBox .btn {
    height: 38px;
    line-height: 38px;
    padding: 0;
    margin: 0; }

.error {
    color: #ff5944;
    font-size: 13px;
    padding: 0 0 10px 0; }
.tab_new li {
    float: left;
    width: 50%;
    text-align: center;
    line-height: 30px; }

.tab_new li.select {
    background: #3C6;
    color: #fff; }

.tab_new.three li a {
    display: inline-block;
    border-right: 1px solid #3C6;
    width: 100%; }

.tab_new.three li {
    width: 33%; }

.tab_new.three li:last-child {
    width: 34%;
    float: right; }

.popWin_mask {
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    background: #333;
    filter: alpha(opacity=68);
    -moz-opacity: 0.68;
    -khtml-opacity: 0.68;
    opacity: 0.68;
    display: none;
    z-index: 10000; }

.popWin_box {
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 10002;
    background: #fff;
    display: none; }

.needClear form .form-group .form-control {
    position: relative;
    left: 0;
    top: 0;
    padding-right: 0; }

.needClear form .form-group.border-bottom {
    border-bottom: 1px solid #f0f0f0;
    margin: 0 0 10px 0;
    padding: 10px 0 5px 0; }

.needClear .border-bottom label {
    width: 60px; }

.needClear form .form-group.border-bottom .form-control {
    position: absolute;
    left: 60px; }

.needClear .border-bottom input {
    margin-top: 5px; }

.popMsgLayer {
    position: fixed;
    top: 20%;
    width: 100%;
    z-index: 10005;
    text-align: center; }

.popMsgLayer a {
    background: #333;
    border-radius: 5px;
    padding: 10px;
    color: #fff;
    max-width: 50%;
    display: inline-block; }

.singleHeight .form-group label {
    margin-top: 10px;
    margin-bottom: 0px; }

.singleHeight .form-group .form-control {
    position: relative;
    margin-left: 80px;
    left: 0;
    top: 6px; }

.singleHeight .form-group .form-control ul {
    clear: none; }

#advance_search_box {
    /*padding: 45px 0px 0 0px;*/
    display: block;
    position: static;
    overflow: auto;
}
/*#advan_form{
    padding:0 15px;
}*/

#advance_search_box .form-group {
    border-bottom: 0;
    clear: both;
    padding-bottom: 0;
    margin-top: 20px; }

#advance_search_box .form-group .form-control {
    position: relative;
    left: 0;
    top: 0; 
	margin-left:0;
    display: -webkit-box;
}
#advance_search_box .form-group .form-control>span{
    -webkit-box-flex: 1;
    display: block;
    position: relative;
}

#advance_search_box .form-group input.key_input {
    border-bottom: 1px solid #f0f0f0;
    -webkit-appearance: none;
    outline: none;
    height: 44px;
    padding: 10px 0px;
    border-radius:0;
    box-sizing:border-box;
    margin-bottom:0;
}

.button-box {
    position:relative;
    width:100%;
    z-index: 10;
    margin:0;
    padding:10px 7.5px;
    display: -webkit-box;
	background:#f0f0f0;
}
.button-box .btn-wrap{
    -webkit-box-flex: 1;
    box-sizing: border-box;
    padding: 0px 7.5px;
}
.button-box .btn-wrap:first-child{
}

.button-box .btn {
    width: 98%;
    height: 40px;
    line-height: 40px; }

#advance_search_box .form-group label {
    font-size: 15px;
    color: #333;
    margin-bottom: 0px;
    width: 70px; }
#advance_search_box label{
    font-size: 15px;
    color: #333;
}
#advance_search_box .form-group .form-control ul {
    clear: none; }

.advance-title {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 2;
    width: 100%;
    height: 44px;
    line-height: 44px;
    text-align: center;
    border-bottom: 1px solid #ddd;
    font-size: 16px; 
    background-color: #fff;

}

.advance-title span {
    position: absolute;
    right: 20px;
    color: #33cc66;
    font-size: 15px;
    z-index: 999;
}

.clearboth_form {
    width: 100%;
    overflow: hidden; }

.clearboth_form h3 {
    height: 40px;
    line-height: 40px;
    font-size: 16px;
    margin: 0;
    padding: 0;
    padding-left: 12px; }

.clearboth_form .panel_title {
    padding-left: 12px; }

.clearboth_form .form-group {
    padding: 0 12px;
    margin-bottom: 0; }

.clearboth_form .form-group label {
    width: 100%; }

.clearboth_form .panel_group_content {
    margin-bottom: -1px; }

.clearboth_form .form-group .serialNum {
    margin-right: 5px;
    font-family: Arial, sans-serif; }

.clearboth_form .form-group .form-control {
    position: relative;
    left: 0;
    top: 0;
    padding-right: 0; }

.clearboth_form .form-group.border-bottom {
    border-bottom: 1px solid #f0f0f0; }
.list_infolist {
    -webkit-padding-start: 0px;
    margin-top: 0px;
    margin-bottom: 0px; }
.list_selectlist {
    -webkit-padding-start: 0px;
    margin-top: 0px;
    margin-bottom: 0px;
    font-size: 15px; }
.list_card {
    -webkit-padding-start: 0px;
    margin-top: 0px;
    margin-bottom: 0px;
    padding-top: 15px;
    font-size: 15px; }

.list_card li {
    list-style-type: none;
    line-height: 30px;
    padding-left: 15px;
    padding-right: 15px; }

.list_card_title {
    position: relative; }

.list_card_title_left span {
    margin-left: 12px; }

.list_card_title_left span:first-child {
    margin-left: 0px;
    font-size: 22px;
    /*font-weight: bold;*/ }

.list_card_title_left span:last-child {
    /*color: $highColor*/ }

.list_card_title_right {
    position: absolute;
    right: 0px;
    top: 0;
    width: 20px;
    height: 30px;
    color: #33cc66;
    text-align: right;
    background: url("images/yhxx_03.png");
    background-size: 24px;
    background-repeat: no-repeat;
    background-position: left; }

.list_card_title_right img {
    height: 24px; }
.list_numlist li {
    list-style-type: none;
    border-bottom: 1px solid #f0f0f0;
    line-height: 44px;
    position: relative; }

.list_numlist li:last-child {
    border-bottom: 0px solid #f0f0f0; }

.search_advanced table {
    border-collapse: collapse;
    border-spacing: 0;
    width: 100%;
    height: 100%; }

.search_advanced table td:last-child {
    text-align: right;
    width: 40px; }

.search_advanced_icon_right img {
    margin-top: 5px;
    height: 15px; }

.search_advanced_icon img {
    position: absolute;
    height: 15px;
    margin-top: 3px; }

.search_advanced_icon span:last-child {
    margin-left: 20px; }
.form-group input{
    -webkit-appearance:none;
}

.dw.dwbg{
    bottom: 40px!important;
    top: auto!important;
}
#advance_search_box .form-group .textareaflex{margin-left:0;}
#phrasesParamBox{position:absolute;right:0;top:0;height:100%;text-align:right;}
#form_text_phrasesParams{font-size:0px;}
.textareaflex .textareaflex-div{
    padding: 10px 5px 5px 5px;height: 20px;position:absolute;opacity:0;z-index:-1;height:auto;min-height:20px;
    max-height: 200px;width: 88%;word-wrap: break-word;word-break: break-all;box-sizing:content-box;
}



.cls input{
	border: 1px solid #ddd;
	border-radius: 3px;
	height: 30px;
	width: 98%;
	color:#333;
    font-size: 13px;
}

.cls i{
    position: absolute;
    right: 8px;
    top: 10px;
    font-style: normal;
    color: #666;
    font-size: 11px;
}

.cls em{
    position: relative;
    top:-19px;
    display: block;
	width: 10px;
    margin:0 10px;
    border-bottom:1px solid #ddd;
}



#advance_search_box .form-group label{
	float:none;
	display: block;
	margin-bottom: 10px;
	margin-top:0;
	color:#333;
}

#fanwei a{ display: block; color: #666}
#fanwei .select a{ color: #fff}
    .reset-title{
        color: #33cc66;
        text-align: center;
    }
    .reset-title span{
        font-size: 16px;
        padding: 20px 0;
        display: inline-block;
    }
</style>

















<div id="advance_search_box" style="height: 698px;">
    <div class="clearfix">
        <div id="advan_form">
            <div id="search">
                <div id="search_1" class="form-group cls">
                    <label>搜索范围</label>
                    <div class="form-control">
                       <ul class="PPmultSelect" id="fanwei">
                            <li><div><a href="/mbem/mcrm/house/room/searchRoom.action">当前楼栋</a></div></li>
                            <li  class="select"><div><a href="javascript:void(0)">当前项目</a></div></li>
                       </ul>
                    </div>
                </div>
                  <div id="search_2" class="form-group cls">
                            <label>总价范围</label>
                            <div class="form-control">
                            <span>
                      		<input type="text" file_type="inputRange" class="begin" for="total" tablename="s_room" id="" name=""><i>万元</i>
                      		</span>
                      		<em></em>
                      		<span>
                      		<input type="text" file_type="inputRange" class="end" for="total" tablename="s_room" id="" name=""><i>万元</i>
	             			</span>
	             			</div>
	             </div>
	              <div id="search_3" class="form-group cls">
                            <label>面积范围</label>
                            <div class="form-control">
                            <span>
                      		<input type="text" file_type="inputRange" class="begin" for="bld_area" tablename="s_room" id="" name=""><i>m2  </i>
                      		</span>
                      		<em></em>
                      		<span>
                      		<input type="text" file_type="inputRange" class="end" for="bld_area" tablename="s_room" id="" name=""><i>m2</i>
	             			</span>
	             			</div>
	             </div>
	             	             <div id="search_4" class="form-group cls">
	             <label>房间结构</label>
	             <div  class="form-control">
                       <ul class="PPmultSelect form-ul" file_type="PPmultSelect" tablename="s_room" for="room_stru">
                    		<li value="三室两厅两卫"><div>三室两厅两卫</div></li><li value="两室一厅一卫"><div>两室一厅一卫</div></li><li value="四室两厅两卫"><div>四室两厅两卫</div></li><li value="三室一厅两卫"><div>三室一厅两卫</div></li><li value="三室一厅一卫"><div>三室一厅一卫</div></li>                       </ul>
            	</div>
            	</div>
            	            	            	 <div id="search_5" class="form-group cls">
	             <label>房间状态</label>
	             <div  class="form-control">
                       <ul class="PPmultSelect form-ul" file_type="PPmultSelect" tablename="s_room" for="room_status">
                    		<li value="0"><div>已售</div></li><li value="1"><div>未售</div></li><li value="2"><div>预销控</div></li><li value="3"><div>未放盘</div></li>                       </ul>
            	</div>
            	</div>
            	            	            	<div id="search_6" class="form-group cls">
	             <label>户型</label>
	             <div  class="form-control">
                       <ul class="PPmultSelect form-ul" file_type="PPmultSelect" tablename="s_room" for="huxing">
                    		<li value="A15F"><div>A15F</div></li><li value="A06A1"><div>A06A1</div></li><li value="A07C1"><div>A07C1</div></li><li value="商铺"><div>商铺</div></li><li value="A14C"><div>A14C</div></li><li value="A15C"><div>A15C</div></li><li value="A06C"><div>A06C</div></li><li value="A07B"><div>A07B</div></li><li value="A14K"><div>A14K</div></li><li value="A15K"><div>A15K</div></li><li value="A06I"><div>A06I</div></li><li value="A07G"><div>A07G</div></li><li value="A14H"><div>A14H</div></li><li value="A02E"><div>A02E</div></li><li value="A06F"><div>A06F</div></li><li value="A07D"><div>A07D</div></li><li value="A09G1"><div>A09G1</div></li><li value="A02B"><div>A02B</div></li><li value="A10G"><div>A10G</div></li><li value="A01B"><div>A01B</div></li><li value="A09H1"><div>A09H1</div></li><li value="A02H"><div>A02H</div></li><li value="A10D"><div>A10D</div></li><li value="A01H"><div>A01H</div></li><li value="A09A"><div>A09A</div></li><li value="A10A"><div>A10A</div></li><li value="A01E"><div>A01E</div></li><li value="A09D"><div>A09D</div></li><li value="A11F1"><div>A11F1</div></li><li value="A05D1"><div>A05D1</div></li><li value="A13E"><div>A13E</div></li><li value="A09G"><div>A09G</div></li><li value="A11J"><div>A11J</div></li><li value="A05F"><div>A05F</div></li><li value="A13B"><div>A13B</div></li><li value="A03E"><div>A03E</div></li><li value="A08C1"><div>A08C1</div></li><li value="A11G"><div>A11G</div></li><li value="A05C"><div>A05C</div></li><li value="A13G"><div>A13G</div></li><li value="A03B"><div>A03B</div></li><li value="A08E"><div>A08E</div></li><li value="A11B"><div>A11B</div></li><li value="A15H"><div>A15H</div></li><li value="A06D1"><div>A06D1</div></li><li value="A07F1"><div>A07F1</div></li><li value="A08B"><div>A08B</div></li><li value="A14E"><div>A14E</div></li><li value="A15E"><div>A15E</div></li><li value="A06H1"><div>A06H1</div></li><li value="A07A1"><div>A07A1</div></li><li value="A14B"><div>A14B</div></li><li value="A15B"><div>A15B</div></li><li value="A06B"><div>A06B</div></li><li value="A07A"><div>A07A</div></li><li value="A14J"><div>A14J</div></li><li value="A15J"><div>A15J</div></li><li value="A06H"><div>A06H</div></li><li value="A07F"><div>A07F</div></li><li value="A14G"><div>A14G</div></li><li value="A02D"><div>A02D</div></li><li value="A06E"><div>A06E</div></li><li value="A01D"><div>A01D</div></li><li value="A09F1"><div>A09F1</div></li><li value="A02A"><div>A02A</div></li><li value="A10F"><div>A10F</div></li><li value="A01A"><div>A01A</div></li><li value="A09C"><div>A09C</div></li><li value="A10C"><div>A10C</div></li><li value="A01G"><div>A01G</div></li><li value="A09F"><div>A09F</div></li><li value="A02G"><div>A02G</div></li><li value="A10D1"><div>A10D1</div></li><li value="A13D1"><div>A13D1</div></li><li value="A09I"><div>A09I</div></li><li value="A11F"><div>A11F</div></li><li value="A05C1"><div>A05C1</div></li><li value="A13D"><div>A13D</div></li><li value="A08F1"><div>A08F1</div></li><li value="A11I"><div>A11I</div></li><li value="A05E"><div>A05E</div></li><li value="A13A"><div>A13A</div></li><li value="A03D"><div>A03D</div></li><li value="A08C2"><div>A08C2</div></li><li value="A11D"><div>A11D</div></li><li value="A05B"><div>A05B</div></li><li value="A06G1"><div>A06G1</div></li><li value="A03A"><div>A03A</div></li><li value="A08D"><div>A08D</div></li><li value="A11A"><div>A11A</div></li><li value="A15G"><div>A15G</div></li><li value="A06C1"><div>A06C1</div></li><li value="A07E1"><div>A07E1</div></li><li value="A08A"><div>A08A</div></li><li value="A14D"><div>A14D</div></li><li value="A15D"><div>A15D</div></li><li value="A06D"><div>A06D</div></li><li value="A07C"><div>A07C</div></li><li value="A14A"><div>A14A</div></li><li value="A15A"><div>A15A</div></li><li value="A06A"><div>A06A</div></li><li value="A07H"><div>A07H</div></li><li value="A14I"><div>A14I</div></li><li value="A15I"><div>A15I</div></li><li value="A06G"><div>A06G</div></li><li value="A07E"><div>A07E</div></li><li value="车位"><div>车位</div></li><li value="A02C"><div>A02C</div></li><li value="A10H"><div>A10H</div></li><li value="A01C"><div>A01C</div></li><li value="A09D1"><div>A09D1</div></li><li value="A02I"><div>A02I</div></li><li value="A10E"><div>A10E</div></li><li value="A01I"><div>A01I</div></li><li value="A09B"><div>A09B</div></li><li value="A10B"><div>A10B</div></li><li value="A01F"><div>A01F</div></li><li value="A09E"><div>A09E</div></li><li value="A02F"><div>A02F</div></li><li value="A05F1"><div>A05F1</div></li><li value="A13F"><div>A13F</div></li><li value="A09H"><div>A09H</div></li><li value="A11E"><div>A11E</div></li><li value="A05C2"><div>A05C2</div></li><li value="A13C"><div>A13C</div></li><li value="A03F"><div>A03F</div></li><li value="A08D1"><div>A08D1</div></li><li value="A11H"><div>A11H</div></li><li value="A05D"><div>A05D</div></li><li value="A13H"><div>A13H</div></li><li value="A03C"><div>A03C</div></li><li value="A08F"><div>A08F</div></li><li value="A11C"><div>A11C</div></li><li value="A05A"><div>A05A</div></li><li value="A06F1"><div>A06F1</div></li><li value="A07H1"><div>A07H1</div></li><li value="A08C"><div>A08C</div></li><li value="A14F"><div>A14F</div></li>                       </ul>
            	</div>
            	</div>
            	            </div>
            
        </div>
    </div>
</div>
<div class="reset-title">
    <span id="resetAdvForm">重置条件</span>
</div>

                <div class="button-box">
                    <div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="确定"></div>
                </div>
<script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
<style>
    /*iScroll滚动条*/
    .myScrollbarV {
        position: absolute;
        z-index: 100;
        width: 4px;
        bottom: 7px;
        top: 2px;
        right: 1px;
    }
    .myScrollbarV > div {
        position: absolute;
        z-index: 100;
        width: 100%;
        background-color:#b3b3b3;
        -webkit-background-clip: padding-box;
        -webkit-box-sizing: border-box;
        -webkit-border-radius: 4px;
    }
</style>
<script>
    /**
     * @description MYSOFT 全局对象，负责前端的交互组织
     * @namespace 全局的命名空间
     */
    MYSOFT = window.MYSOFT || {};

    /**
     * @description MYSOFT界面对象，负责前端的公共控件
     * @namespace MYSOFT界面的命名空间
     */
    MYSOFT.Ui = window.MYSOFT.Ui || {};

    /**
     * @description MYSOFT全局配置
     * @namespace MYSOFT全局配置的命名空间
     */
    MYSOFT.Config = window.MYSOFT.Config || {};

    /**
     * @description MYSOFT工具
     * @namespace MYSOFT工具的命名空间
     */
    MYSOFT.Util = window.MYSOFT.Util || {};

    /**
     * @description MYSOFT Dom命名空间,负责Dom操作
     * @namespace MYSOFT Dom命名空间
     */
    MYSOFT.Dom = window.MYSOFT.Dom || {};
    /**
     * @namespace MYSOFT 滚动条组件
     * @param selector dom选择器
     * @param options 滚动条属性,属性说明请参考MYSOFT.Ui.Scroll.Defaults
     */
    MYSOFT.Ui.Scroll = function (selector, options) {
        var scroll, scrollId, $el = $(selector),
                scrollId = $el.attr('_jscroll_');
        //滚动组件使用频繁，缓存起来节省开销
        if (scrollId && MYSOFT.Ui.Scroll.ScrollCache[scrollId]) {
            scroll = MYSOFT.Ui.Scroll.ScrollCache[scrollId];
            scroll.scroller.refresh();
            return scroll;
        } else {
            scrollId = '_jscroll_' + MYSOFT.Ui.Scroll.Index++;
            $el.attr('_jscroll_', scrollId);
            var defaults = {
                hScroll: false,
                bounce: false,
                lockDirection: true,
                useTransform: true,
                useTransition: false,
                checkDOMChanges: false,
                scrollbarClass: "myScrollbar",
                onBeforeScrollStart: function (e) {
                    var target = e.target;
                    while (target.nodeType != 1) target = target.parentNode;
                    if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA')
                        e.preventDefault();
                }
            };
            var opts = $.extend(true, defaults, options);
            var scroller = new iScroll($el[0], opts);
            return MYSOFT.Ui.Scroll.ScrollCache[scrollId] = {
                scroller: scroller,
                destroy: function () {
                    scroller.destroy();
                    delete MYSOFT.Ui.Scroll.ScrollCache[scrollId];
                }
            };
        };
    };
    /**
     *滚动条缓存
     */
    MYSOFT.Ui.Scroll.ScrollCache = {};

    /**
     *滚动条索引
     */
    MYSOFT.Ui.Scroll.Index = 1;
</script>
<script>
// 根据url设置当前楼栋和当前项目的链接和样式
function getUrlInfo(){
    var url = window.location.href;
    var inProject = getUrlParam("inProject");
    if(inProject === "1"){
        // 当前项目
        alert("1");
        $("#fanwei li").eq(1).addClass("select");
        $("#fanwei li").eq(1).find("a").eq(0).attr("href","javascript:void(0)");
        var loudongUrl = url.substr(0,url.indexOf("inProject")-1);
        $("#fanwei li").eq(0).find("a").eq(0).attr("href",loudongUrl);
        $("#inProject").val(1);
    }else{
        // 当前楼栋
        //$("#fanwei li").eq(0).addClass("select");
        $("#fanwei li").eq(1).find("a").eq(0).attr("href","javascript:void(0)");
       // $("#fanwei li").eq(0).find("a").eq(0).attr("href",url+"&inProject=1");
        $("#inProject").val(0);
    }
}  
//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

$(function(){
    getUrlInfo();

    // var interval=setInterval(function(){
    //     var wh=$(window).height();
    //     if(wh!=0){
    //         clearInterval(interval);
    //         $('#advan_form').css('min-height',wh-44-50-20);
    //     }
    // },100);
    setTimeout(function(){
        var wh=$(window).height();
        $('#advance_search_box').height(wh-70-58);
    });
    var savedata={
            "PPmultSelect":[],
            "inputRange":[]
	}
    
    function verify(){
    	//必须数字
    	//end>start
    }
    
    $("#formpost").one("submit",function(e){
        e.preventDefault();
        verify();
        getvalue();
        window.location.href = $(this).attr('action')+'?'+$.param($(this).serializeArray());
    });
    
    /**生成提交json数据**/
    function getvalue(){
        var form_list=$("#advan_form .form-group"),arr=[];
        form_list.each(function(ind,dom){
            var tag=$(dom).find("*[tablename]").eq(0);
            savevalue(tag);
            var cur_obj={};
            cur_obj['filed']= tag.attr("for")||tag.attr("name");
            cur_obj['file_type']= tag.attr("file_type")||"undefined";
            cur_obj['tablename']=tag.attr("tablename");
            cur_obj['value']=tag.attr("value")||tag.val()||"";
            
            if(tag.attr("file_type")=="inputRange"){
                var parentWrap = tag.closest(".form-group");
            	cur_obj['value'] = parentWrap.find(".begin").val()+  "#"+ parentWrap.find(".end").val();
            }
            arr.push(cur_obj);
        });
        $("#post_data").attr("value",JSON.stringify(arr));
    }
    
    function savevalue(dom){
        var cur_dom=dom;
        if(cur_dom.hasClass("PPmultSelect")){
            var obj={
                "tablename": cur_dom.attr("tablename"),
                "value":cur_dom.attr("value"),
                "file_type":cur_dom.attr("file_type"),
                "for":cur_dom.attr("for")
            }
            savedata.PPmultSelect.push(obj);
        }else if(cur_dom.attr("file_type")=="inputRange"){
            var parentWrap=cur_dom.closest(".form-group");
        	var str=parentWrap.find(".begin").val()+  "#"+ parentWrap.find(".end").val();
            var obj={
                    "tablename": cur_dom.attr("tablename"),
                    "value":str,
                    "file_type":cur_dom.attr("file_type"),
                    "for":cur_dom.attr("for")
                }
        	savedata.inputRange.push(obj);
        }
        Zepto.localStore.set("/Sales/RoomStatus/search/#selected",JSON.stringify(savedata),1);
    }
    
    
    /**多选点击变色**/
    $(".form-ul").delegate("li","touchend",function(){
        $(this).toggleClass("select");
        var cur_parent=$(this).closest(".PPmultSelect");
        var cur_values=[];
        cur_parent.find(".select").each(function(ind,dom){
            cur_values.push($(dom).attr("value"));
        });
        cur_parent.attr("value",cur_values.toString());
    });
  	//恢复上次选择
    var selecteddata=JSON.parse(Zepto.localStore.get("/Sales/RoomStatus/search/#selected"));
    if(selecteddata){
    	/* if(selecteddata.PPmultSelect.length>0){
            for(var i=0;i<selecteddata.PPmultSelect.length;i++){
                var cur_data= selecteddata.PPmultSelect[i];
                if(cur_data.value!=null){
	                var cur_tar=$(".form-group [file_type='"+cur_data.file_type+"'][for='"+cur_data.for+"'][tablename='"+cur_data.tablename+"']");
	                cur_tar.attr("value",cur_data.value);
	                var select= cur_data.value.split(",");
	                select.forEach(function(num,ind){
	                    cur_tar.find("[value='"+num+"']").addClass("select");
	                }); 
                }
            }
        }
		if(selecteddata.inputRange.length>0){
            for(var i=0;i<selecteddata.inputRange.length;i++){
                var cur_data= selecteddata.inputRange[i];
                var begin=$(".form-group [class='begin'][file_type='"+cur_data.file_type+"'][for='"+cur_data.for+"'][tablename='"+cur_data.tablename+"']");
                var end=$(".form-group [class='end'][file_type='"+cur_data.file_type+"'][for='"+cur_data.for+"'][tablename='"+cur_data.tablename+"']");
                var range_value= cur_data.value.split("#");
                begin.val(range_value[0]);
                end.val(range_value[1]);
            }
        }
 */
    }
    
    //重置
    $("#resetAdvForm").on("click",function(){
        $(".form-ul li").removeClass("select");
        $(".form-group *[tablename]").attr("value","");
        $(".form-group input").val("");
        $(".form-group .quickdate").removeClass("quickselect");
    });
    //取消
    $("#cancel_input").bind("click",function(){
        history.back();
    });
});
</script>
<script type="text/javascript">
    var body=$("body"),sended=0;
    var statistics=statistics||{};
    statistics.proj_id=body.attr("data-projid");
    statistics.pageid=body.attr("data-pageid");
    statistics.token=body.attr("data-token");
    statistics.p1=body.attr("data-php");
    statistics.p2=body.attr("data-ram");
    function onWeixinReady() {
        WeixinJSBridge.invoke('getNetworkType', {}, function (e) {
            WeixinJSBridge.log(e.err_msg);
            network = e.err_msg.split(":")[1];
            statistics.network= network;
            clearTimeout(statistics.setid);
            if(!sended){
                sendlog(statistics);
            }
        })}
    $(function(){
        statistics.t2= +new Date()-statistics.start;
        $(window).bind("load",function(){
            document.addEventListener("WeixinJSBridgeReady", onWeixinReady, false);
            statistics.t3=+new Date()-statistics.start;
            statistics.t4=document.documentElement.innerHTML.length;
            statistics.t5=document.cookie.length;
            statistics.navtype=performance&&performance.navigation&&performance.navigation.type;
            if(statistics.navtype==null){statistics.navtype=4}
            statistics.setid=setTimeout(function(){
                sendlog(statistics);
            },3000);
        });
    });
    function sendlog(obj){
        var url="http://112.124.8.6/stat?";
        for(var key in obj){
            if(key!="setid"&&key!="start"){
                if(key.match(/t\d/)){
                    url=url+key+"="+obj[key]/1000+"&";
                }else{
                    url=url+key+"="+obj[key]+"&";
                }
            }
        }
        var img=$('<img style="height:0;width:0;position:absolute;overflow:hidden">');
        img.attr("src",url.substr(0,url.length-1));
        $("body").append(img);
        sended=1;
    }
    function sendajaxlog(obj){
        var obj= $.extend({},obj);
        obj.proj_id=statistics.proj_id;
        obj.token=statistics.token;
        obj.navtype=statistics.navtype;
        if(statistics.network){
            obj.network=statistics.network;
        }
        var url="http://112.124.8.6/stat?";
        for(var key in obj){
            url=url+key+"="+obj[key]+"&";
        }
        var img=$('<img style="height:0;width:0;position:absolute;overflow:hidden">');
        img.attr("src",url.substr(0,url.length-1));
    }
</script>


</body></html>