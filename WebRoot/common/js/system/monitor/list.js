var dialog;
var grid;
$(function() {
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
			hide:true
		}, {
			colkey : "cpuUsage",
			name : "cpuʹ����",
			width : "85px"
		}, {
			colkey : "setCpuUsage",
			name : "Ԥ��cpuʹ����",
			width : "115px"
		}, {
			colkey : "jvmUsage",
			name : "Jvmʹ����",
			width : "90px"
		}, {
			colkey : "setJvmUsage",
			name : "Ԥ��Jvmʹ����",
			width : "115px"
		} ,{
			colkey : "ramUsage",
			name : "Ramʹ����",
			width : "90px"
		} ,{
			colkey : "setRamUsage",
			name : "Ԥ��Ramʹ����",
			width : "115px"
		} ,{
			colkey : "email",
			name : "���͵��ʼ�"
		} ,{
			colkey : "operTime",
			name : "���͵�ʱ��"
		} ,{
			colkey : "mark",
			name : "��ע"
		} ],
		jsonUrl : rootPath + '/monitor/findByPage.shtml',
		checkbox : true
	});
	$("#searchForm").click("click", function() {//�󶨲�ѯ��Ť
		var searchParams = $("#fenye").serializeJson();
		grid.setOptions({
			data : searchParams
		});
	});
});