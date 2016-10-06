<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp"%>
<link rel="stylesheet" href="/wbem/css/zsdc-gx-style.css" />
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span6">
			<section class="panel panel-info portlet-item">
				<header class="panel-heading">
					<i class="fa fa-th-list"></i>��������Ϣ
				</header>
				<div class="panel-body" style="padding: 0px">
					<div class="row-fluid">
						<div class="span12">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td>ip��ַ</td>
										<td colspan="2" id="hostIp"><a>${systemInfo.hostIp}</a></td>
									</tr>
									<tr>
										<td>������</td>
										<td colspan="2" id="hostName"><a>${systemInfo.hostName}</a></td>
									</tr>
									<tr>
										<td>����ϵͳ������</td>
										<td colspan="2" id="osName"><a>${systemInfo.osName}</a></td>
									</tr>
									<tr>
										<td>����ϵͳ�Ĺ���</td>
										<td colspan="2" id="arch"><a>${systemInfo.arch}</a></td>
									</tr>
									<tr>
										<td>����ϵͳ�İ汾</td>
										<td colspan="2" id="osVersion"><a>${systemInfo.osVersion}</a></td>
									</tr>
									<tr>
										<td>����������</td>
										<td colspan="2" id="processors"><a>${systemInfo.processors}</a></td>
									</tr>
									<tr>
										<td>Java�����л����汾</td>
										<td colspan="2" id="javaVersion"><a>${systemInfo.javaVersion}</a></td>
									</tr>
									<tr>
										<td>Java��Ӧ�̵�URL</td>
										<td colspan="2" id="javaUrl"><a>${systemInfo.javaUrl}</a></td>
									</tr>
									<tr>
										<td>Java�İ�װ·��</td>
										<td colspan="2" id="javaHome"><a>${systemInfo.javaHome}</a></td>
									</tr>
									<tr>
										<td>��ʱ�ļ�·��</td>
										<td colspan="2" id="tmpdir"><a>${systemInfo.tmpdir}</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</section>
		</div>
		<div class="span6">
			<section class="panel panel-danger portlet-item">
				<header class="panel-heading">
					<i class="fa fa-fire"></i> ʵʱ���
				</header>
				<div class="panel-body">
					<div id="main" style="height: 370px;"></div>
				</div>
			</section>
		</div>
	</div>
	<div style="width:100%; height:1%;"></div>
	<div class="row-fluid">
		<div class="span12">
			<section class="panel panel-primary portlet-item">
				<header class="panel-heading">
					<i class="fa fa-rss-square"></i>ʵʱ���
				</header>
				<div class="panel-body">
					<div style="width: auto; min-width:530px;">
						<div id="main_one" style="height: 240px;" class="span4"></div>
						<div id="main_two" style="height: 240px;" class="span4"></div>
						<div id="main_three" style="height: 240px;" class="span4"></div>
					</div>
				</div>
			</section>
		</div>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp"%>
<script src="/common/js/system/monitor/esl.js" type="text/javascript"></script>
<script src="/common/js/system/monitor/echarts-all.js" type="text/javascript"></script>
<script src="/common/js/system/monitor/systemInfo.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		showChart();
	});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp"%>

