/*
 * DataTables Ĭ������
 * ʵ��������Ĭ�������г�ͻʱ����$('.data-table').dataTable({})��ʼ��ʱ��д���ɡ�
 * */
$.extend( $.fn.dataTable.defaults, {
	"dom":"<''r>t<'F'ilp>",
	"ordering":false,		//�Ƿ�������
	"jQueryUI": true,
	"paging": true,
	"pagingType":"full_numbers",
    "processing": true, // �Ƿ���ʾȡ����ʱ���Ǹ��ȴ���ʾ
    "serverSide": true, //�������ָ����ͨ���������ȡ����
    "searching":false,   //���ز�ѯ
    "iDisplayLength":15,
    "lengthMenu": [15,25,50,100,200,300,500],
	"language":{
		"infoEmpty": "��������Ϊ��",
		"emptyTable":"��������Ϊ��",
		"zeroRecords": "û���ҵ���¼",
		"emptyTable":"û���ҵ���¼",
        "loadingRecords": "������...",
        "processing":"<img src='/wbem/img/loading-0.gif'/>",
		"paginate": {
			"first":"��ҳ",
			"next": "��ҳ",
			"previous": "��ҳ",
			"last":"βҳ"
		 },
		"search": "����",
		"lengthMenu": " ÿҳ��ʾ _MENU_ ��",
		"info": "�� _TOTAL_ ����¼",
		"infoFiltered": "(�� _MAX_ ����¼����)"
	}
} );


function checkbox_CheckedValue(checkboxName){
	var chk_value =[];//����һ������  
	$('input[name='+checkboxName+']:checked').each(function(){//����ÿһ������Ϊinterest�ĸ�ѡ������ѡ�е�ִ�к���      
		chk_value.push($(this).val());//��ѡ�е�ֵ��ӵ�����chk_value�� 
		//alert($(this).val());    
	}); 
	return chk_value;
}

function tabSwitch(tab){
	window.location.href = $(tab).attr("target");
}