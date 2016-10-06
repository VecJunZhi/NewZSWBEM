<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/wbem/css/default.css" >
	<link type="text/css" rel="stylesheet" href="/common/css/jqueryui/jquery-ui.min.css">
	<link type="text/css" rel="stylesheet" href="/common/js/jqGrid/css/ui.jqgrid.css">
	<script type="text/javascript" src="/common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/common/js/jqGrid/js/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="/common/js/jqGrid/js/i18n/grid.locale-cn.js"></script>
	<style type="text/css">
		.rigths{
			height: 100%;
			width: 100%;
			margin-top: -10px;
			background-color:#fff;
		}
	</style>
</head>
<body style="padding: 0px;margin-top:10px;margin-left: 2%"> 
	    
<!-- jqGrid 分页 div gridPager -->
<!-- <div id="gridPager"></div> -->
 	<div class="rigths">
		<dl>
			<div class="ltop">
				<span style="width:80px; font-size:11px; float: left;">岗位成员</span>
				<span class="co4" style="width:80px; font-size:11px;">移除用户</span>
				<span class="co5" style="width:80px; font-size:11px;">添加成员</span>
				<span class="co1" style="width:80px; font-size:11px;">新增用户</span>
			</div>
		</dl>
		<input type="radio" name="lang" onclick="navigateToLang('en')" checked="checked">English&nbsp;&nbsp;
        <input type="radio" name="lang" onclick="navigateToLang('de')">German&nbsp;&nbsp;
        <input type="radio" name="lang" onclick="navigateToLang('fr')">French&nbsp;&nbsp;
        <input type="radio" name="lang" onclick="navigateToLang('es')">Spanish<br>
        <input type="radio" name="lang" onclick="navigateToLang('pt')">Portuguese&nbsp;&nbsp;
        <input type="radio" name="lang" onclick="navigateToLang('ar')">Arabic&nbsp;&nbsp;
        <input type="radio" name="lang" onclick="navigateToLang('cn')">Chinese&nbsp;&nbsp;
		<table id=jqGrid></table>
		<div id="jqGridPager"></div>
		 <input type="button" value="Select row  with ID 1014" onclick="selectRow()" />&nbsp;
    <input type="button" value="Get Selected Row" onclick="getSelectedRow()" />  
     <input type="button" value="Get Selected Rows" onclick="getSelectedRows()" />   
	</div>
	<div class="kfoot">
	</div> 

</body>
  <script type="text/javascript">
  
alert("eee");
$("#jqGrid").jqGrid({
	datatype: "local",
	height: 250,
   	colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
   	colModel:[
   		{name:'id',index:'id', width:60, sorttype:"int",  editable: true },
   		{name:'invdate',index:'invdate', width:90, sorttype:"date",  editable: true },
   		{name:'name',index:'name', width:100},
   		{name:'amount',index:'amount', width:80, align:"right",sorttype:"float",  editable: true },
   		{name:'tax',index:'tax', width:80, align:"right",sorttype:"float",formatter: "number",  editable: true },		
   		{name:'total',index:'total', width:80,align:"right",editable: true},  		
   		{name:'note',index:'note', width:150, sortable:true,  editable: true }		
   	],
   	multiselect: true,
   	 rowNum:10,
      width:'100%',
      height:'50%',
       rowList:[10,20,30],
       pager: '#jqGridPager',
       sortname: 'id',
      viewrecords: true,
      sortorder: "desc",
       // direction: "rtl", // instructs the grid to use RTL settings
        // userDataOnFooter: true,
          sortable: true,
        //  loadonce: true,
           //hoverrows: true,
   	caption: "Manipulating Array Data"
});
var mydata = [
		{id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}
		];
for(var i=0;i<=mydata.length;i++){

$("#jqGrid").jqGrid('addRowData',i+1,mydata[i]);

}
		$("#savestate").click(function(){
			$.jgrid.saveState("jqGrid");
		});
		$("#loadstate").click(function(){
			$.jgrid.loadState("jqGrid");
		})	;
		  function navigateToLang(lng) {
		  alert("efe");
            $.jgrid.setRegional('list4',{regional: lng});
        }
         function formatRating(cellValue, options, rowObject) {
        	 //alert("s "+cellValue);
        	 // alert("rowob "+rowObject);
            var color = (parseInt(cellValue) > 0) ? "green" : "red";
            var cellHtml = "<span style='color:" + color + "' originalValue='" +
                                 cellValue + "'>" + cellValue + "</span>";
			 return cellHtml;
        }
        function unformatRating(cellValue, options, cellObject) {
        alert("cellob "+cellObject.html());
            return $(cellObject.html()).attr("originalValue");
        }
        var template = "<div style='margin-left:15px;'><div> Customer ID <sup>*</sup>:</div><div> {CustomerID} </div>";
			template += "<div> Company Name: </div><div>{CompanyName} </div>";
			template += "<div> Phone: </div><div>{Phone} </div>";
			template += "<div> Postal Code: </div><div>{PostalCode} </div>";
			template += "<div> City:</div><div> {City} </div>";
			template += "<hr style='width:100%;'/>";
			template += "<div> {sData} {cData}  </div></div>";
 /*        $('#jqGrid').navGrid('#jqGridPager',
                // the buttons to appear on the toolbar of the grid
                { edit: true, add: true, del: true, search: false, refresh: false, view: false, position: "left", cloneToTop: false },
                // options for the Edit Dialog
                {
                    editCaption: "The Edit Dialog",
					template: template,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Add Dialog
                {
					template: template,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Delete Dailog
                {
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                }); */
                 function getSelectedRow() {
            var grid = $("#jqGrid");
            var rowKey = grid.jqGrid('getGridParam',"selrow");

            if (rowKey)
                alert("Selected row primary key is: " + rowKey);
            else
                alert("No rows are selected");
        }

 
		function selectRow() {
			jQuery('#jqGrid').jqGrid('setSelection','1014');
		}
		 function getSelectedRows() {
            var grid = $("#jqGrid");
            var rowKey = grid.getGridParam("selrow");
			if (!rowKey)
                alert("No rows are selected");
            else {
                var selectedIDs = grid.getGridParam("selarrrow");
                var result = "";
                for (var i = 0; i < selectedIDs.length; i++) {
                    result += selectedIDs[i] + ",";
                }

                alert(result);
            }                
        }
  </script>
</html>
