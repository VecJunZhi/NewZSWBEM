function showChart(){
	// <!--Step:1 ΪECharts׼��һ���߱���С����ߣ���Dom-->
	// Step:3 echarts & zrender as a Global Interface by the echarts-plain.js.
	// Step:3 echarts��zrender��echarts-plain.jsд��Ϊȫ�ֽӿ�
	var myChart = echarts.init(document.getElementById('main'));
	var now = new Date();
	var res = [];
	var len = 20;
	while (len--) {
		var time = now.toLocaleTimeString().replace(/^\D*/, '');
		time = time.substr(time.indexOf(":") + 1);
		res.unshift(time);
		now = new Date(now - 1000);
	}
	option = {
		legend : {
			data : [ 'jvm�ڴ�ʹ����', '�����ڴ�ʹ����', 'cpuʹ����' ]
		},
		grid : {
			x : 40,
			y : 30,
			x2 : 10,
			y2 : 35,
			borderWidth : 0,
			borderColor : "#FFFFFF"
		},
		xAxis : [ {
			axisLabel : {
				rotate : 40,
			},
			type : 'category',// ���������ͣ�����Ĭ��Ϊ��Ŀ��'category'������Ĭ��Ϊ��ֵ��'value'
			data : res
		} ],
		yAxis : [ {
			min : 0,
			max : 100,
			axisLabel : {
				formatter : '{value}%'
			}
		} ],
		series : [
				{
					name : 'jvm�ڴ�ʹ����',
					type : 'line',
					data : [ 12, 25, 31, 11, 45, 50, 11, 09, 28, 35, 40, 24,
							28, 39, 23, 31, 14, 18, 08, 36 ]
				},
				{
					name : '�����ڴ�ʹ����',
					type : 'line',
					data : [ 55, 41, 10, 23, 15, 44, 29, 41, 27, 05, 12, 25,
							31, 11, 45, 50, 11, 09, 28, 35 ]
				},
				{
					name : 'cpuʹ����',
					type : 'line',
					data : [ 40, 24, 28, 39, 23, 31, 14, 18, 08, 36, 55, 41,
							10, 23, 15, 44, 29, 41, 27, 05 ]
				} ]
	};
	myChart.setOption(option);
	var main_one = echarts.init(document.getElementById('main_one'));
	var main_two = echarts.init(document.getElementById('main_two'));
	var main_three = echarts.init(document.getElementById('main_three'));
	one_option = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    series : [
		        {
		        	title:{
		        	    show : true,
		        	    offsetCenter: [0, "95%"],
		        	 },
		        	 pointer: {
		                 color: '#FF0000'
		             },
		             name:'���ָ��',
		            radius:[0, '95%'],
		            axisLine: {            // ��������
		                lineStyle: {       // ����lineStyle����������ʽ
		                    width: 20
		                }
		            },
		            detail : {formatter:'{value}%'},
		            type:'gauge',
		            data:[{value: 50, name: 'JVMʹ����'}]
		        }
		    ]
		};
	 two_option = {
			 tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    series : [
			        {
			            name:'���ָ��',
			            type:'gauge',
			            startAngle: 180,
			            endAngle: 0,
			            center : ['50%', '90%'],    // Ĭ��ȫ�־���
			            radius : 180,
			            axisLine: {            // ��������
			                lineStyle: {       // ����lineStyle����������ʽ
			                    width: 140
			                }
			            },
			            axisTick: {            // ������С���
			                splitNumber: 10,   // ÿ��splitϸ�ֶ��ٶ�
			                length :12,        // ����length�����߳�
			            },
			            axisLabel: {           // �������ı���ǩ�����axis.axisLabel
			               
			                textStyle: {       // ��������Ĭ��ʹ��ȫ���ı���ʽ�����TEXTSTYLE
			                    color: '#fff',
			                    fontSize: 15,
			                    fontWeight: 'bolder'
			                }
			            },
			          
			            pointer: {
			                width:10,
			                length: '80%',
			                color: 'rgba(255, 255, 255, 0.8)'
			            },
			            title : {
			                show : true,
			                offsetCenter: [0, 15],       // x, y����λpx
			               /* textStyle: {       // ��������Ĭ��ʹ��ȫ���ı���ʽ�����TEXTSTYLE
			                    color: '#fff',
			                    fontSize: 25
			                }*/
			            },
			            detail : {
			                show : true,
			                backgroundColor: 'rgba(0,0,0,0)',
			                borderWidth: 0,
			                borderColor: '#ccc',
			                offsetCenter: [0, -40],       // x, y����λpx
			                formatter:'{value}%',
			                textStyle: {       // ��������Ĭ��ʹ��ȫ���ı���ʽ�����TEXTSTYLE
			                    fontSize : 20
			                }
			            },
			            data:[{value: 50, name: 'cpuʹ����'}]
			        }
			    ]
			};

	 main_one.setOption(one_option);
	 main_two.setOption(two_option);
	 one_option.series[0].data[0].name ='�ڴ�ʹ����';
	 one_option.series[0].pointer.color='#428bca';
	 main_three.setOption(one_option);
	var axisData;
	clearInterval(timeTicket);
	var timeTicket = setInterval(function() {
		axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');
		axisData = axisData.substr(axisData.indexOf(":") + 1);
		var jvm = [];
		var ram = [];
		var cpu = [];
		$.ajax({
			type : "POST",
			url : '/wbem/system/monitor/usage.action',
			async : false,
			dataType : 'json',
			//complete: function(json) {alert("333");},
			success : function(json) {
				$("#td_jvmUsage").html(json.jvmUsage);
				$("#td_serverUsage").html(json.ramUsage);
				$("#td_cpuUsage").html(json.cpuUsage);
				
				jvm.push(json.jvmUsage);
				ram.push(json.ramUsage);
				cpu.push(json.cpuUsage);
				// ��̬���ݽӿ� addData
				myChart.addData([ [ 0, // ϵ������
				jvm, // ��������
				false, // ���������Ƿ�Ӷ���ͷ������
				false, // �Ƿ����Ӷ��г��ȣ�false���Զ�ɾ��ԭ�����ݣ���ͷ����ɾ��β����β����ɾ��ͷ
				], [ 1, // ϵ������
				ram, // ��������
				false, // ���������Ƿ�Ӷ���ͷ������
				false, // �Ƿ����Ӷ��г��ȣ�false���Զ�ɾ��ԭ�����ݣ���ͷ����ɾ��β����β����ɾ��ͷ
				], [ 2, // ϵ������
				cpu, // ��������
				false, // ���������Ƿ�Ӷ���ͷ������
				false, // �Ƿ����Ӷ��г��ȣ�false���Զ�ɾ��ԭ�����ݣ���ͷ����ɾ��β����β����ɾ��ͷ
				axisData // �������ǩ
				] ]);
				
				one_option.series[0].data[0].value =json.jvmUsage;
				one_option.series[0].data[0].name ='JVMʹ����';
				one_option.series[0].pointer.color='#FF0000';
				main_one.setOption(one_option, true);
				
				two_option.series[0].data[0].value =json.cpuUsage;
				main_two.setOption(two_option, true);
				
				one_option.series[0].data[0].value =json.ramUsage;
				one_option.series[0].data[0].name ='�ڴ�ʹ����';
				one_option.series[0].pointer.color='#428bca';
				main_three.setOption(one_option, true);
			}
		});
	}, 2000);
}