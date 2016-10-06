<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<title>�߼�����</title>
    <link rel="stylesheet" href="/common/js/jquery.mobile-1.4.5/jquery.mobile-1.4.5.css">
    <link rel="stylesheet" href="/common/js/jquery.mobile-1.4.5/demos/_assets/css/jqm-demos.css">
    <script  src="/common/js/jquery-1.9.1.min.js" ></script>
    <script  src="/common/js/bootstrap/bootstrap.min.js"></script>
    <script  src="/common/js/layer/layer.js"></script>
    <script  src="/common/js/jquery.mobile-1.4.5/demos/_assets/js/index.js"></script>
    <script  src="/common/js/jquery.mobile-1.4.5/jquery.mobile-1.4.5.js"></script>
    	<style id="wrap">
		.button-wrap {
			margin-left: 5px;
			margin-right: 5px;
		}
	</style>
	<style id="center">
		.center {
			text-align: center;
		}
	</style>
	<script>
		$( document ).on( "pagecreate", "#demo-page", function() {

			$( document ).on( "swipeleft swiperight", "#demo-page", function( e ) {
				
				// We check if there is no open panel on the page because otherwise
				// a swipe to close the left panel would also open the right panel (and v.v.).
				// We do this by checking the data that the framework stores on the page element (panel: open).
				if ( $( ".ui-page-active" ).jqmData( "panel" ) !== "open" ) {
					if ( e.type === "swipeleft" ) {
						$( "#right-panel" ).panel( "open" );
					} else if ( e.type === "swiperight" ) {
						$( "#left-panel" ).panel( "open" );
					}
				}
			});
		});
    </script>
    <style>
		/* Swipe works with mouse as well but often causes text selection. */
		/* We'll deny text selecton on everything but INPUTs and TEXTAREAs. */
		#demo-page :not(INPUT):not(TEXTAREA) {
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			-o-user-select: none;
			user-select: none;
		}
		/* Content styling. */
		dl { font-family: "Times New Roman", Times, serif; padding: 1em; }
		dt { font-size: 2em; font-weight: bold; }
		dt span { font-size: .5em; color: #777; margin-left: .5em; }
		dd { font-size: 1.25em;	margin: 1em 0 0; padding-bottom: 1em; border-bottom: 1px solid #eee; }
		.back-btn { float: right; margin: 0 2em 1em 0; }
	</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#cancle").click(function(){
			window.location.href = "/mbem/mcrm/house/customer/customPage.action";
		});
	});
</script>
</head>
<body>
<div data-role="page" id="demo-page" class="jqm-demos jqm-panel-page" data-quicklinks="true" >
	<form  id="addCst" action="/mbem/mcrm/house/customer/getXsCstInfoByAdvancedSearch.action" method="post"  data-ajax="false">
	<!-- <div data-role="header" class="jqm-header"> --> <!-- data-position="fixed" -->
	<input id="defaultTel" name="defaultTel" type="hidden" value="mobileTel">
	<div data-role="header">
   		<h1>�߼�����</h1>
		<!-- <a href="#right-panel" data-role="button" class="ui-btn-right">����</a> -->
		<button type="reset" class="ui-btn-right">����</button>
  	</div>
  	<div data-role="main" class="ui-content jqm-content" >
		<div class="ui-grid-a">
			<div class="ui-block-a" style="width:22%;margin-top:16px;font-weight:bold">
				�ؼ���
     		</div>
    		<div class="ui-block-b" style="width:78%">
    			<input type="text" name="telOrName" placeholder="������ͻ��������ֻ�����">
    		</div> 
	  	</div> 
	 	<h4>���򼶱�</h4>	
		<div class="ui-grid-b">
			<div class="ui-block-a" >
				<label for="intention1">������</label>
				<input type="checkbox"  id="intention1" name="gfyx" value="������" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="intention2">һ������</label>
				<input type="checkbox"  id="intention2" name="gfyx" value="һ������" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="intention3">������</label>
				<input type="checkbox"  id="intention3" name="gfyx" value="������" data-mini="true">
			</div>
			<div class="ui-block-a">
				<label for="intention4">����</label>
				<input type="checkbox"  id="intention4" name="gfyx" value="����" data-mini="true">
			</div>
     	</div>
  		<h4>�ͻ�״̬</h4>	
		<div class="ui-grid-b">
			<div class="ui-block-a">
				<label for="cstStatus1">��Ч</label>
				<input type="checkbox"  id="cstStatus1" name="status" value="��Ч" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="cstStatus2">��ѯ</label>
				<input type="checkbox"  id="cstStatus2" name="status" value="��ѯ" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="cstStatus3">����</label>
				<input type="checkbox"  id="cstStatus3" name="status" value="����" data-mini="true">
			</div>
			<div class="ui-block-a">
				<label for="cstStatus4">�ϳ�</label>
				<input type="checkbox"  id="cstStatus4" name="status" value="�ϳ�" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="cstStatus5">�Ϲ�</label>
				<input type="checkbox"  id="cstStatus5" name="status" value="�Ϲ�" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="cstStatus6">ǩԼ</label>
				<input type="checkbox"  id="cstStatus6" name="status" value="ǩԼ" data-mini="true">
			</div>
     	</div>
     	<h4>�����</h4>	
		<div class="ui-grid-b">
			<div class="ui-block-a">
				<label for="ageGroup1">20������</label>
				<input type="checkbox"  id="ageGroup1" name="ageGroup" value="20������" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="ageGroup2">20~30��</label>
				<input type="checkbox"  id="ageGroup2" name="ageGroup" value="20~30��" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="ageGroup3">30~40��</label>
				<input type="checkbox"  id="ageGroup3" name="ageGroup" value="30~40��" data-mini="true">
			</div>
			<div class="ui-block-a">
				<label for="ageGroup4">40~50��</label>
				<input type="checkbox"  id="ageGroup4" name="ageGroup" value="40~50��" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="ageGroup5">50������</label>
				<input type="checkbox"  id="ageGroup5" name="ageGroup" value="50������" data-mini="true">
			</div>
     	</div>
     	<h4>�ͻ���Դ</h4>
     	<h4>������ʽ</h4>	
		<div class="ui-grid-b">
			<div class="ui-block-a">
				<label for="followWay1">ȥ��</label>
				<!-- <input type="checkbox"  id="followWay1" name="gjfs" value="�������" data-mini="true"> -->
				<input type="checkbox"  id="followWay1" name="gjfs" value="ȥ��" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="followWay2">����</label>
				<!-- <input type="checkbox"  id="followWay2" name="gjfs" value="�ͻ�����" data-mini="true"> -->
				<input type="checkbox"  id="followWay2" name="gjfs" value="����" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="followWay3">����</label>
				<!-- <input type="checkbox"  id="followWay3" name="gjfs" value="�ֳ��Ӵ�" data-mini="true"> -->
				<input type="checkbox"  id="followWay3" name="gjfs" value="����" data-mini="true">
			</div>
     	</div>
     	<h4>��֪;��</h4>
     	<div class="ui-grid-b">
			<div class="ui-block-a">
				<label for="cogWay1">�ɵ�</label>
				<input type="checkbox"  id="cogWay1" name="mainMediaName" value="�ĵ�" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="cogWay2">����</label>
				<input type="checkbox"  id="cogWay2" name="mainMediaName" value="����" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="cogWay3">����</label>
				<input type="checkbox"  id="cogWay3" name="mainMediaName" value="����" data-mini="true">
			</div>
			<div class="ui-block-a">
				<label for="cogWay4">������</label>
				<input type="checkbox"  id="cogWay4" name="subMediaName" value="������" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="cogWay5">չ��/�</label>
				<input type="checkbox"  id="cogWay5" name="mainMediaName" value="չ��/�" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="cogWay6">��ݹ��</label>
				<input type="checkbox"  id="cogWay6" name="mainMediaName" value="��ݹ��" data-mini="true">
			</div>
				<div class="ui-block-a">
				<label for="cogWay7">��������</label>
				<input type="checkbox"  id="cogWay7" name="mainMediaName" value="��������" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="cogWay8">��ֽ</label>
				<input type="checkbox"  id="cogWay8" name="mainMediaName" value="��ֽ" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="cogWay9">����</label>
				<input type="checkbox"  id="cogWay9" name="mainMediaName" value="����" data-mini="true">
			</div>
				<div class="ui-block-a">
				<label for="cogWay10">�㲥</label>
				<input type="checkbox"  id="cogWay10" name="mainMediaName" value="�㲥" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="cogWay11">���л</label>
				<input type="checkbox"  id="cogWay11" name="mainMediaName" value="���л" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="cogWay12">����</label>
				<input type="checkbox"  id="cogWay12" name="mainMediaName" value="����" data-mini="true">
			</div>
				<div class="ui-block-a">
				<label for="cogWay13">İ��</label>
				<input type="checkbox"  id="cogWay13" name="mainMediaName" value="İ��" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="cogWay14">��չ</label>
				<input type="checkbox"  id="cogWay14" name="mainMediaName" value="��չ" data-mini="true">
			</div>
     	</div>
     	<h4>�������</h4>	
		<div class="ui-grid-b">
			<div class="ui-block-a">
				<label for="area1">80ƽ������</label>
				<input type="checkbox"  id="area1" name="yxArea" value="80ƽ������" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="area2">80~90ƽ��</label>
				<input type="checkbox"  id="area2" name="yxArea" value="80~90ƽ��" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="area3">90~100ƽ��</label>
				<input type="checkbox"  id="area3" name="yxArea" value="90~100ƽ��" data-mini="true">
			</div>
			<div class="ui-block-a">
				<label for="area4">100~110ƽ��</label>
				<input type="checkbox"  id="area4" name="yxArea" value="100~110ƽ��" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="area5">110~130ƽ��</label>
				<input type="checkbox"  id="area5" name="yxArea" value="110~130ƽ��" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="area6">130-140ƽ��</label>
				<input type="checkbox"  id="area6" name="yxArea" value="130~140ƽ��" data-mini="true">
			</div>
			<div class="ui-block-a">
				<label for="area7">140~150ƽ��</label>
				<input type="checkbox"  id="area7" name="yxArea" value="140~150ƽ��" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="area8">150~200ƽ��</label>
				<input type="checkbox"  id="area8" name="yxArea" value="150~200ƽ��" data-mini="true">
			</div>
			<div class="ui-block-c">
				<label for="area9">200~250ƽ��</label>
				<input type="checkbox"  id="area9" name="yxArea" value="200~250ƽ��" data-mini="true">
			</div>
			<div class="ui-block-a">
				<label for="area10">250~300ƽ��</label>
				<input type="checkbox"  id="area10" name="yxArea" value="250~300ƽ��" data-mini="true">
			</div>
			<div class="ui-block-b">
				<label for="area11">300ƽ������</label>
				<input type="checkbox"  id="area11" name="yxArea" value="300ƽ������" data-mini="true">
			</div>
     	</div>
	</div>
	<div data-role="footer" data-position="fixed"> 
	    <div class="ui-grid-a">
	      	<div class="ui-block-a" data-theme="b" style="text-align:center">
	      		<div data-role="controlgroup" data-type="horizontal"> 
	      		<a href="#" data-role="button" id="cancle">ȡ��</a></div>
	      	</div>
	     	<div class="ui-block-b" style="text-align:center">
	     		<input type="submit" value="����"/>
	     	</div>
	    </div>
	 </div>
	</form>
</div> 	
</body>
</html>