<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>帮助与建议</title>
<link rel="stylesheet" href="/mbem/mcrm/house/css/base.css">      
<script>
var agt = navigator.userAgent.toLowerCase();
var is_op = (agt.indexOf("opera") != -1);
var is_ie = (agt.indexOf("msie") != -1) && document.all && !is_op;
function ResizeTextarea(a,row){
    if(!a){return}
    if(!row)
        row=5;
    var b=a.value.split("\n");
    var c=is_ie?1:0;
    c+=b.length;
    var d=a.cols;
    if(d<=20){d=40}
    for(var e=0;e<b.length;e++){
        if(b[e].length>=d){
            c+=Math.ceil(b[e].length/d)
        }
    }
    c=Math.max(c,row);
    if(c!=a.rows){
        a.rows=c;
    }
}
</script>
</head>
<body>
    <div class="container" style="padding-top: 20px;">
        <div class="feed"><p>帮助与建议</p>
         <label for="feedbackType" ></label>
          <select id="feedbackType" name="feedbackType">
                                                <option class="foodtxt" value="程序错误">程序错误</option>
                                              <option class="foodtxt" value="意见建议">意见建议</option>
                                            </select>
                                            <textarea style="overflow: hidden; width:98%; font-style: normal;  line-height: normal; " rows="10" cols="30" onfocus="javascript:ResizeTextarea(this,10);" onclick="javascript:ResizeTextarea(this,10);" onkeyup="javascript:ResizeTextarea(this,10);"></textarea>
        </div>
        <div class="form-group tow-Btn">
            <input type="button" value="提交问题" class="btn" id="saveBtn">
        </div>
    </div>

<div class="powered" id="powered">
    Powered&nbsp;by&nbsp;&nbsp;<a href="#">兆盛地产</a>
</div>
<div id="mask" style="top: 310.5px;"></div><div id="msg"></div></body></html>
