<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>

<div class="container-fluid">
	<div class="widget-box"> 
	 	<div class="widget-content tab-content" style="overflow:hidden;">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			    <%-- <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >楼栋</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="bldGuid" onchange="changeBld(this)">
	                   <c:forEach items="${bldList}" var="bld" varStatus="status">
              			<option value="${bld.bldGuid}">${bld.bldName}</option>
             		   </c:forEach>
	                </select>
	              </div> 
	            </div> --%> 
	            <div class="input-prepend input-append">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">选择楼栋</span>
				  <c:forEach items="${bldList}" var="bld" varStatus="status">
				  	<label class="checkbox inline"> <input type="checkbox" name="bldGuid" value="${bld.bldGuid}"/>${bld.bldName}</label>
				  </c:forEach>
				</div>
	          </form>
	        </div>
	    </div>
	 </div>
	<div class="row-fluid">
		<div class="span6">
			<div id="issueTypeInfo" style="height:370px;width:100%;border:1px solid #d4c3c3;"></div>
		</div>
		<div class="span6">
			<div id="issueStatusInfo" style="height:370px;width:100%;border:1px solid #d4c3c3;"></div>
		</div>
	</div>
	<div class="row-fluid" style="margin-top:5px;">
		<div class="span6">
			<div id="issuePositionInfo" style="height:370px;width:100%;border:1px solid #d4c3c3;"></div>
		</div>
	</div> 
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script src="/common/js/echarts/echarts.min.js"></script>
<script>
var myChart = "";
var myChart2 = "";

function loadPieOption(titleText,seriesName,seriesData) {
option = {
	    title : {
	        text: titleText,
	        x:'center',
	        textStyle:{
	        	fontSize:16,
	        	fontWeight:'bold',
	        }
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)",
	    },
	   toolbox: {
	    	feature:{
	    		saveAsImage:{
	    			title:'保存为图片',
	    		},
	    		dataView:{
	    			show:true,
	    			title:'数据视图',
	    			lang:['数据视图','关闭','刷新'],
	    			buttonColor:'#00f',
	    		},
	    		/* magicType:{
	    			show:true,
	    			type:['line','bar','stack'],
	    		} */
	    	},
	    },
	    series : [
	        {
	            name: seriesName,
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:seriesData,
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            },
	            label:{
	            	normal:{
	            		show: true,
	            		formatter: "{b} : {c}",
	            		textStyle:{
	            			fontSize:13,
	            			//fontWeight:'bold',
	            		}
		            },
	            }
	        }
	    ]
	};
}

function loadBarOption(titleText,seriesName,seriesData,yData){
	option = {
		    title: {
		        text: titleText,
		        x:'center',
		        textStyle:{
		        	fontSize:16,
		        	fontWeight:'bold',
		        }
		    },
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        },
		        formatter:'{a}<br>{b}：{c}%'
		    },
		    toolbox:{
		        show:true,
		        feature:{
		            magicType:{
		                show:true,
		                type:['line','bar'],
		                title:{
		                	line:'切换为折线图',
		                	bar:'切换为柱状图',
		                }
		            },
		            saveAsImage:{
		                type:'png',
		            },
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '10%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'value',
		        boundaryGap: [0, 0.01]
		    },
		    yAxis: {
		        type: 'category',
		        data: yData,
		    },
		    series: [
		        {
		        	type: 'bar',
		        	name: seriesName,
		            data: seriesData,
		            label:{
		                normal:{
		                    show:true,
		                    position:'right',
		                    formatter:'{c}%',
		                }
		            },
		            barWidth:'60%',
		            itemStyle:{
		                normal:{
		                    color:function(params){
		                         var colorList = [
		                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
		                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
		                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
		                        ];
		                        return colorList[params.dataIndex]
		                    }
		                }
		            }
		        },
		    ]
		};
}

$(document).ready(function(){
	var typeData = ${typeStatisticsData};
	var positionData = ${positionStatisticsData};
	var statusName = ${statusName};
	var statusValue = ${statusValue};
	myChart = echarts.init(document.getElementById('issueTypeInfo'));
	loadPieOption('验房问题分类统计','问题类型',typeData);
	myChart.setOption(option); 
	/* myChart.on('click',function(a){
		alert(a.percent+"===="+a.name+"==="+a.dataIndex+"==="+a.value+"===="+a.data);	
	}); */
	
	myChart2 = echarts.init(document.getElementById('issueStatusInfo'));
	loadBarOption('问题处理阶段统计','问题处理阶段',statusValue,statusName);
	myChart2.setOption(option);
	
	myChart3 = echarts.init(document.getElementById('issuePositionInfo'));
	loadPieOption('验房问题部位统计','问题部位',positionData);
	myChart3.setOption(option); 
	
	$("input[name='bldGuid']").click(function(){
		var bldGuid = checkbox_CheckedValue("bldGuid").toString();
		$.ajax({
			url:"/wbem/inspect/loadTypeStatisticsByBld.action",
			type:"post",
			data:{'bldGuid':bldGuid},
			dataType:"json",
			success:function(data){
				//myChart.hideLoading();
				loadPieOption('验房问题分类统计','问题类型',data);
				myChart.setOption(option);
			},
			error:function(){
				
			},
		});
		
		$.ajax({
			url:"/wbem/inspect/loadStatusStatisticsByBld.action",
			type:"post",
			data:{'bldGuid':bldGuid},
			dataType:"json",
			success:function(data){
				loadBarOption('问题处理阶段统计','问题处理阶段',data.value,data.name);
				myChart2.setOption(option);
			},
			error:function(){
				
			},
		});
		
		$.ajax({
			url:"/wbem/inspect/loadPositionStatisticsByBld.action",
			type:"post",
			data:{'bldGuid':bldGuid},
			dataType:"json",
			success:function(data){
				loadPieOption('验房问题部位统计','问题部位',data);
				myChart3.setOption(option);
			},
			error:function(){
				
			},
		});
	});
	
	$('select').select2();
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>