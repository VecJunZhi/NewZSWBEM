/*
 * DataTables 默认配置
 * 实际需求与默认设置有冲突时，在$('.data-table').dataTable({})初始化时覆写即可。
 * */
$.extend( $.fn.dataTable.defaults, {
	"dom":"<''r>t<'F'ilp>",
	"ordering":false,		//是否开启排序
	"jQueryUI": true,
	"paging": true,
	"pagingType":"full_numbers",
    "processing": true, // 是否显示取数据时的那个等待提示
    "serverSide": true, //这个用来指明是通过服务端来取数据
    "searching":false,   //本地查询
    "iDisplayLength":15,
    "lengthMenu": [15,25,50,100,200,300,500],
	"language":{
		"infoEmpty": "表中数据为空",
		"emptyTable":"表中数据为空",
		"zeroRecords": "没有找到记录",
		"emptyTable":"没有找到记录",
        "loadingRecords": "载入中...",
        "processing":"<img src='/wbem/img/loading-0.gif'/>",
		"paginate": {
			"first":"首页",
			"next": "下页",
			"previous": "上页",
			"last":"尾页"
		 },
		"search": "搜索",
		"lengthMenu": " 每页显示 _MENU_ 条",
		"info": "共 _TOTAL_ 条记录",
		"infoFiltered": "(从 _MAX_ 条记录过滤)"
	}
} );


function checkbox_CheckedValue(checkboxName){
	var chk_value =[];//定义一个数组  
	$('input[name='+checkboxName+']:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数      
		chk_value.push($(this).val());//将选中的值添加到数组chk_value中 
		//alert($(this).val());    
	}); 
	return chk_value;
}

function tabSwitch(tab){
	window.location.href = $(tab).attr("target");
}