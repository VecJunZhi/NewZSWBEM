<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<!DOCTYPE html>
<head>
<title></title>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">    
<link rel="Stylesheet" href="/mbem/mcrm/business/css/base.min.css">  
<script>
 $(function ($) {
	if (window.history && window.history.pushState) {
	$(window).on('popstate', function () {
        var hash = window.location.hash;
        if (hash === '') {
            window.location.href="/mbem/mcrm/house/saleManager/index.action";
        }
    });
    var url = window.location.url;
    window.history.pushState('forward', null, url);
    location.hash="#1";
	}
});    	 
 </script>
<script type="text/javascript" class="mysoft">

 /** file:/public/js/mysoft.js **/ 

//js后置，将载入模板中函数放入initfunc中。js库底部加载完后执行。
window.addEventListener("pageshow",function(e){
   if (e.persisted) {
      window.location.reload();
   }
},false);
if(navigator.userAgent.match(/android/gi) && window.performance&&window.performance.navigation&&window.performance.navigation.type==2){
   window.location.reload();
}
var mysoft = mysoft || {};
mysoft.initfunc = [];
mysoft.prepare  = function(func){
   this.initfunc.push(func);
};
</script>
    </head>
<body data-pageid="">
<div class="container" id="container">
    <style type="text/css">
    body {
        background-color: #f0f0f0;
    }

    #header {
        background-color: #f8f8f8;
    }
    #header .head{padding:12px; border-bottom-width:1px;}

    .easytab {
        border: 1px solid #34cca6;
        border-radius: 3px 3px 3px 3px;
        height: 30px;
        box-sizing: border-box;
        display: table;
        width: 100%;
    }

    .easytab li {
        display: table-cell;
        width: 33%;
        height: 100%;
        vertical-align: middle;
        text-align: center;
        font-size: 16px;
        cursor: pointer;
        border-right: 1px solid #34cca6;
        color: #34cca6;
    }

    .easytab li:last-child {
        border-right: none;
    }

    .easytab li.selected,.easytab li.active {
        background: #34cca6;
        color: white;
    }

    .easytab li div {
        height: 28px;
        line-height: 28px;
    }

    article {
        top: 55px;
        bottom: 62px;
    }
    .itempanel {
        background-color: #fff;
        height: 95px;
        margin: 12px 12px 17px 17px;
        position: relative;
        overflow:hidden;
    }

    .itempanel .bottombg {
        position: absolute;
        height: 0px;
        width: 0px;
        line-height: 0;
        border-width: 22px;
        border-style: solid;
        border-color: transparent transparent #ffdfbf #ffdfbf;
        left: -5px;
        bottom: -5px;
    }

    .itempanel .autoalloc {
        width: 66px;
        height: 66px;
        background: #fff;
        border-radius: 33px;
        border: 1px solid #ffa64d;
        color: #ffa64d;
        font-size: 15px;
        position: absolute;
        right: 12px;
        top: 15px;
    }

    .itempanel .autoalloc p, .itempanel .manualalloc p {
        width: 32px;
        margin: 8px 16px;
    }

    .itempanel .autoalloc:active {
        color: #fff;
        background-color: #ffa64d;
    }

    .itempanel .autoalloc2 {
        width: 66px;
        height: 66px;
        background: #fff;
        border-radius: 33px;
        border: 1px solid #18c9c9;
        color: #18c9c9;
        font-size: 15px;
        position: absolute;
        right: 12px;
        top: 15px;
        line-height: 66px;
        text-align: center;
    }

    .itempanel .autoalloc2:active {
        color: #fff;
        background-color: #18c9c9;
    }

    .itempanel .manualalloc:active {
        color: #fff;
        background-color: #ff7a4d;
    }

    .itempanel .manualalloc {
        width: 66px;
        height: 66px;
        background: #fff;
        border-radius: 33px;
        border: 1px solid #ff7a4d;
        color: #ff7a4d;
        font-size: 15px;
        position: absolute;
        right: 90px;
        top: 15px;
    }

    .itempanel .itemtext {
        margin-right: 169px;
        overflow: hidden;
        text-align: left;
        font-size: 15px;
        color: #333;
        padding: 25px 0 0 12px;
    }

    .itempanel .itemtext .itemtextinner {
        display: inline-block;
        text-align: left;
    }

    .itempanel .itemtext .itemtextinner .font1 {
        font-size: 17px;
        color: #000;
    }

    .itempanel .itemtext .itemtextinner .font2 {
        font-size: 17px;
        color: #333;
    }

    .a_align_info {
        display: table;
        vertical-align: middle;
        height: 100%;
    }

    .a_table_cell {
        display: table-cell;
        vertical-align: middle;
    }

    .a_align_info dl {
        padding-left: 12px;
    }

    .a_align_info dt {
        color: #18c9c9;
        font-size: 17px;
    }

    .a_align_ing dt {
        background: url("http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/circle_loading.gif") no-repeat right bottom;
        background-size: 25px 15px;
        padding-right: 25px;
        color: #18c9c9;
        font-size: 17px;
    }
    .a_align_info dd {
        font-size: 15px;
    }
    .a_rela {
        position: relative;
    }

    .a_li {
        width: 66px;
        height: 66px;
        margin: 15px 12px 0 0;
    }
</style>
<header id="header" class="border-1px"><div id="tabMenu" class="head border-1px">
    <ul class="easytab">
        <li data-href="/sxzhaoshengadmin/salemanage/cst/index?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f" data-ac="active">
            <div>跟进中</div>
        </li>
        <li class="selected" data-href="/sxzhaoshengadmin/salemanage/cst/clue-index?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f" data-ac="active">
            <div>待分配</div>
        </li>
        <li data-href="/sxzhaoshengadmin/salemanage/cst/public-customer?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f" data-ac="active">
            <div>公共客户</div>
        </li>
    </ul>
</div>

</header>
<!--逾期调整-->
<article id="scrollArticle">
    <div id="minpanel" style="min-height: 718px;">
    <div class="itempanel cls" data-status="2" data-name="报备客户" data-type="recommend" data-num="0">
        
        <div class="autoalloc"><p>自动分配</p></div>
        <div class="manualalloc"><p>手动分配</p></div>
        <div class="itemtext">
            <span class="itemtextinner">
                <p class="font1">报备客户</p>
                <p class="font2">0人</p>
            </span>
        </div>
        
        
    </div>
    
    <div class="itempanel cls" data-status="2" data-name="预约客户" data-type="appointment" data-num="0">
        
        <div class="autoalloc"><p>自动分配</p></div>
        <div class="manualalloc"><p>手动分配</p></div>
        <div class="itemtext">
            <span class="itemtextinner">
                <p class="font1">预约客户</p>
                <p class="font2">0人</p>
            </span>
        </div>
        
        
    </div>
    
    <div class="itempanel cls" data-status="2" data-name="小蜜蜂客户" data-type="tfans" data-num="0">
        
        <div class="autoalloc"><p>自动分配</p></div>
        <div class="manualalloc"><p>手动分配</p></div>
        <div class="itemtext">
            <span class="itemtextinner">
                <p class="font1">小蜜蜂客户</p>
                <p class="font2">0人</p>
            </span>
        </div>
        
        
    </div>
    
    <div class="itempanel cls" data-status="2" data-name="外购客户" data-type="buy" data-num="0">
        
        <div class="autoalloc"><p>自动分配</p></div>
        <div class="manualalloc"><p>手动分配</p></div>
        <div class="itemtext">
            <span class="itemtextinner">
                <p class="font1">外购客户</p>
                <p class="font2">0人</p>
            </span>
        </div>
        
        
    </div>
    </div>
    <style>
    .powered{
        margin-bottom: 6px;
        font-size: 11px;
        color: #aaa;
        text-align: center;
        width: 100%;
    }
    .powered a{
        color: #aaa;
        text-decoration: none;
    }
    .powered a:hover,.powered a:visited{
        color: #aaa;
    }
    .powered a:active{
        color: #2caf56;
    }
</style>
<div class="powered" id="powered">
    Powered&nbsp;by&nbsp;&nbsp;<a href="http://m.myunke.com">明源云客</a>
</div></article>
<script type="text/html" id="temp-header">
    <div id="tabMenu" class="head border-1px">
    <ul class="easytab">
        <li {{if action == "index"}}class="selected"{{/if}} data-href="{{tabs[0]}}" data-ac="active">
            <div>跟进中</div>
        </li>
        <li {{if action == "clue-index"}}class="selected"{{/if}} data-href="{{tabs[1]}}" data-ac="active">
            <div>待分配</div>
        </li>
        <li {{if action == "public-customer"}}class="selected"{{/if}} data-href="{{tabs[2]}}" data-ac="active">
            <div>公共客户</div>
        </li>
    </ul>
</div>
</script>
<script type="text/html" id="temp-content">
    {{each list as val key}}
    <div class="itempanel cls" data-status = "{{val.status}}" data-name="{{key | cnname}}" data-type="{{key}}" data-num="{{val.new}}">
        {{if val.status == 2}}
        <div class="autoalloc"><p>自动分配</p></div>
        <div class="manualalloc"><p>手动分配</p></div>
        <div class="itemtext">
            <span class="itemtextinner">
                <p class="font1">{{key | cnname}}</p>
                <p class="font2">{{val.new}}人</p>
            </span>
        </div>
        {{/if}}
        {{if val.status == 1}}
        <div class="fl a_align_info a_align_ing">
            <div class="a_table_cell">
                <dl>
                    <dt>{{key | cnname}}分配中</dt>
                    <dd>剩余{{val.remain}}人</dd>
                    <dd>{{val.total}}人</dd>
                </dl>
            </div>
        </div>
        <div class="fr">
            <div class="a_li a_rela ring-ring" data-percent = "{{val.success/val.total | percent}}"></div>
        </div>
        {{/if}}
    </div>
    {{/each}}
</script>
<style>
    /*弹出框效果*/
    #mysoft_popup_mask{
        background-color: #222;
        display: none;
        position: fixed;
        z-index: 999998;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        opacity: 0.3;
        height:100%;
        width:100%;
    }
    #mysoft_popup{
        color: #222;
        -webkit-box-shadow: 0px 1px 10px rgba(0, 0, 0, 0.6);
        display: none;
        position: fixed;
        left: 0;
        right: 0;
        z-index: 999999;
        min-height: 50px;
    }
    #mysoft_popup.loading {
        top: 50%;
        left: 50%;
        margin: -50px 0 0 -62px;
        opacity: .78;
        text-align: center;
        width: 125px;
        height: 100px;
        border-radius: 5px;
        background-color: #000;
    }
    #mysoft_popup.loading .img {
        border: none;
        margin-top:22px;
        width: 100%;
        height: 35px;
        background-image: url(http://ydxs2.myscrm.cn/public/dest/popup/images/loading35@35.gif);
        background-size: 35px;
        background-position: center center;
        background-repeat: no-repeat;
    }
    #mysoft_popup.alert,#mysoft_popup.confirm {
        top: 50%;
        left: 5%;
        right: 5%;
        border-radius:5px;
        background-color: #fff;
    }
    #tag_close_popup{
        color: #BDC3C7;
        position: absolute;
        top: 0;
        right: 0;
        font-size: 1em;
        padding: 5px 5px 10px 10px;
    }
    #mysoft_popup.loading p{
        font-size:17px;
        color: #fff;
        padding-top:9px;
        margin: 0px;
    }
    #mysoft_popup.loading i.icon{
        color: #fff;
        font-size: 4em;
        line-height: 110px;
        margin: 0;
        display: inline-block;
        -webkit-animation: spinner .5s infinite linear;
    }
    .popup-title {
        background:#fff;
        color: #000;
        text-align:center;
        padding: 14px;
        font-size:16px;
        font-weight:bold;
        border-radius:5px;
    }
    .popup-content {
        text-align:center;
        padding: 0 14px 10px 14px;
        line-height: 1.2em;
        font-size:14px;
        border-radius:5px;
        color:#000;
    }
    #popup_btn_container {
        text-align: center;
        margin-top: 10px;
        display: -webkit-box;
        display: box;
    }
    #popup_btn_container > a {
        font-size: 16px;
        -webkit-box-flex: 1;
        box-flex: 1;
        padding: 12px 10px;
        display: block;
        color: #0c7bf7;
        border: 1px solid #d9d9d9;
        border-left: none;
        border-bottom: none;
        border-right:0;
    }
    #popup_btn_container > a:active{
        background-color: #d9d9d9;
    }
    #popup_btn_container > a:first-child {
        border-right: 1px solid #d9d9d9;
        border-bottom-left-radius: 5px;
    }
    #popup_btn_container > a:last-child {
        border-bottom-right-radius: 5px;
    }
</style>
<script>
    mysoft.prepare(function(){
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
         * @namespace MYSOFT弹出框组件类
         */
        $.Ui={};
        $.Ui.Popup=MYSOFT.Ui.Popup = {
            //初始化Dom对象到文档
            Init: function () {
                if (!MYSOFT.Ui.Popup.DomObj) {
                    //全局只有一个实例
                    $('body').append('<div id="mysoft_popup"></div><div id="mysoft_popup_mask"></div>');
                    MYSOFT.Ui.Popup.DomObj = $('#mysoft_popup');
                    MYSOFT.Ui.Popup.MaskDomObj = $('#mysoft_popup_mask');
                }
            },
            Show: function (html,cls) {
                MYSOFT.Ui.Popup.Init();
                MYSOFT.Ui.Popup.MaskDomObj.show();
                MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass(cls).html(html).show();
                $('#tag_ok_popup').bind('click', function () {
                    MYSOFT.Ui.Popup.HidePopUp();
                });
            },
            /**
             * @method 显示加载动画
             * @param text 加载文本
             */
            ShowLoading: function (text) {
                MYSOFT.Ui.Popup.Init();
                var markup = MYSOFT.Ui.Popup.Template.loading.replace('{title}', text || '正在加载');
                //MYSOFT.Ui.Popup.MaskDomObj.show().css({'opacity':'0'});
                MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('loading').html(markup).show();
            },
            /**
             * @method 隐藏弹出框
             */
            HidePopUp: function () {
                MYSOFT.Ui.Popup.MaskDomObj.hide();
                MYSOFT.Ui.Popup.DomObj.hide().empty();
            },
            /**
             * @method 弹出框提示
             * @param title 提示标题
             * @param content 提示内容
             * @param okCall 点击确定回调函数
             */
            ShowAlert: function (title, content, okCall) {
                var markup = MYSOFT.Ui.Popup.Template.alert.replace('{title}', title||'提示信息').replace('{content}', content).replace('{ok}', '我知道了');
                MYSOFT.Ui.Popup.Init();
                MYSOFT.Ui.Popup.MaskDomObj.show();
                MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('alert').html(markup).show();
                $('#tag_ok_popup').bind('click', function () {
                    MYSOFT.Ui.Popup.HidePopUp();
                    okCall && okCall();
                });
                MYSOFT.Ui.Popup.DomObj.css('margin-top', -(MYSOFT.Ui.Popup.DomObj.height()/2));
            },
            /**
             * @method 确认框提示
             * @param title 提示标题
             * @param content 提示内容
             * @param okCall 确定回调函数
             * @param cancelCall 取消回调函数
             */
            ShowConfirm: function (title, content, okCall,cancelCall,okText,cancelText) {
                okText=okText||'确 定';
                cancelText=cancelText||'取 消';
                var markup = MYSOFT.Ui.Popup.Template.confirm.replace('{title}', title).replace('{content}', content).replace('{cancel}', cancelText).replace('{ok}', okText);
                MYSOFT.Ui.Popup.Init();
                MYSOFT.Ui.Popup.MaskDomObj.show();
                MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('confirm').html(markup).show();
                $('#tag_ok_popup').bind('click', function () {
                    MYSOFT.Ui.Popup.HidePopUp();
                    okCall && okCall();
                }).bind('touchstart',function(e){
                    e.stopPropagation();
                });
                $('#tag_cancel_popup').bind('click', function () {
                    MYSOFT.Ui.Popup.HidePopUp();
                    cancelCall && cancelCall();
                }).bind('touchstart',function(e){
                    e.stopPropagation();
                });
                MYSOFT.Ui.Popup.DomObj.css('margin-top', -(MYSOFT.Ui.Popup.DomObj.height() / 2));
            },
            /**
             * @object Popup模版
             */
            Template: {
                //加载动画
                loading: '<div class="img"></div><p>{title}</p>',
                //提示框
                alert: '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_ok_popup">{ok}</a></div>',
                //确认提示框
                confirm: '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_cancel_popup">{cancel}</a><a id="tag_ok_popup">{ok}</a></div>'
            }
        };
    });
</script><style type="text/css">
    .toast_overlay{
        position: absolute;
        top:0;
        left:0;
        z-index:999998;
        width:100%;
        height:100%;
    }
    .toast{
        position: absolute;
        z-index: 999999;
        top: 50%;
        left: 50%;
        width: 210px;
        margin-top:-30px;
        margin-left: -105px;
        text-align: center;
        display: none;
        background-color: #111;
        border-radius: 5px;
        opacity: .85;
        padding:20px 12px;
    }
    .toast .toasttext{
        display: block;
        line-height: 20px;
        color: #fff;
        font-size: 15px;
    }
    .toast_overlay.show{
        display: block;
    }
    .toast_overlay.hide{
        display: none;
    }
    .toast.show {
        display: block;
        -webkit-animation:scaleIn .25s ease-in;
    }
    .toast.hide {
        display: none;
        -webkit-animation:scaleOut .25s ease-in;
    }
    @-webkit-keyframes scaleIn {
        0% {opacity: 0;-webkit-transform: scale(.5)}
        100% {opacity: .85;-webkit-transform: scale(1)}
    }
    @-webkit-keyframes scaleOut {
        0% {opacity: .85;-webkit-transform: scale(1)}
        100% {opacity: 0;-webkit-transform: scale(.5)}
    }
</style>
<script>
    mysoft.prepare(function(){
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
         * @namespace MYSOFT通知组件类
         */
        MYSOFT.Ui.Toast = {
            //初始化Dom对象到文档
            Init: function () {
                if (!MYSOFT.Ui.Toast.DomObj) {
                    //全局只有一个实例
                    $('body').append('<div id="mysoft_toast_overlay" class="toast_overlay"></div><div id="mysoft_toast" class="toast"><p class="toasttext"></p></div>');
                    MYSOFT.Ui.Toast.DomObj = $('#mysoft_toast,#mysoft_toast_overlay');
                    MYSOFT.Ui.Toast.DomTextObj =MYSOFT.Ui.Toast.DomObj.find('.toasttext');
                }
            },
            DomObj:null,
            DomTextObj:null,
            //定时器
            TIMER:null,
            /**
             * 显示通知组件
             * @param text 文字内容
             * @param duration 持续时间 为0则不自动关闭,默认为2000ms
             */
            Show: function (text, duration,callback) {
                var duration = duration || (duration === 0 ? 0 : 2000);
                MYSOFT.Ui.Toast.Init();
                MYSOFT.Ui.Toast.TIMER&&clearTimeout(MYSOFT.Ui.Toast.TIMER);
                MYSOFT.Ui.Toast.DomTextObj.html(text);
                MYSOFT.Ui.Toast.DomObj.removeClass('hide').addClass('show');
                if (duration === 0) {
                    return;
                }
                MYSOFT.Ui.Toast.TIMER=setTimeout(function(){
                    MYSOFT.Ui.Toast.TIMER&&clearTimeout(MYSOFT.Ui.Toast.TIMER);
                    MYSOFT.Ui.Toast.DomObj.removeClass('show').addClass('hide');
                    MYSOFT.Ui.Toast.DomTextObj.empty();
                    callback&&callback();
                },duration);
            }
        };
    });
</script><style type="text/css">
    .ring_ring{position:relative;overflow:hidden;}
    .ring_pie {position:absolute; }
    .ring_pie1 {-webkit-transform:rotate(0deg);}
    .ring_pie2 {-webkit-transform:rotate(0deg);}
    .ring_hold { position:absolute; z-index:1; }
    .bg {position:absolute; }
    .ring_pie-after{position: absolute; left: 0px; top:0px; display: block;}
    .ring_holdtext {position:absolute; z-index:1; text-align:center;display:-webkit-box;-webkit-box-align:center;}
    .ring_holdtext .ring_holdvalue{display:block;font-weight:normal;width:100%;}
    .ring_holdtext .ring_holdvalue span{font-size: 50%;}
    .ring_holdtext em{font-style:normal;}
    .ring_holdtext p{font-size:12px;}
</style>

<script>
    mysoft.prepare(function(){
        $.fn.ring = function(opt){
        var opt  = $.extend({
            outer:{width:104,backgroundColor:"#EC6657"},
            inner:{width:84,backgroundColor:"#FC9B90",ringColor:"#fff"},
            text:{size:30,color:"#fff"},
            percent:80,
            title:{size:12,color:"#fff",text:""}
        },opt);
        var num = opt.percent,ar1,ar2;
        if(num>=100){
            ar1 = 180;
            ar2= 180;
        }else if(num>50){
            ar1=180;
            ar2 = (num - 50)/50*180;
        }else{
            ar2=0;
            ar1 = num/50*180;
        }
        $(this).each(function(ind,dom){
            var $me = $(dom);
            $me.html(template("template-ring",{
                outer:{width:opt.outer.width,halfWidth:opt.outer.width/2,backgroundColor:opt.outer.backgroundColor},
                inner:{width:opt.inner.width,halfWidth:opt.inner.width/2,backgroundColor:opt.inner.backgroundColor,ringColor:opt.inner.ringColor},
                text:{size:opt.text.size,color:opt.text.color},
                deg1:ar1,
                deg2:ar2,
                ceilSpace:(opt.outer.width-opt.inner.width)/2,
                per:opt.percent,
                title:opt.title.text
            }));
        });
    }
    });
</script><script type="text/javascript">
    mysoft.prepare(function () {
        tplData.tabs = ["/sxzhaoshengadmin/salemanage/cst/index?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f","/sxzhaoshengadmin/salemanage/cst/clue-index?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f","/sxzhaoshengadmin/salemanage/cst/public-customer?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f"];
        tplData["urlManualalloc"]="/sxzhaoshengadmin/salemanage/cst/clue-list?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f";
        tplData["ajaxAutoalloc"]="/sxzhaoshengadmin/salemanage/cst/ajax-clue-auto-assign?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f";
        tplData["ajaxRefresh"]="/sxzhaoshengadmin/salemanage/cst/ajax-get-clue-assign-status?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f";
        template.helper('cnname', function (str) { //模板插件，转换名称
            var cnname = {"appointment":"预约客户","recommend":"报备客户","tfans":"小蜜蜂客户","buy":"外购客户"};
            return cnname[str];
        });
        template.helper('percent', function (str) { //模板插件，转换名称
            return Math.floor(str*100);
        });
        $("#header").prepend(template("temp-header",tplData));
        var tempdata = {"list":tplData.allot_status};
        rendercontent(tempdata);
        $('#tabMenu li:not(.selected)').bind('click',function(){
            var url=$(this).data('href');
            window.location.replace(url);
        });
        $("#container").delegate(".manualalloc","tap",function(){
            var type = $(this).closest(".itempanel").data("type"),num = $(this).closest(".itempanel").data("num");
            if(num == 0){
                MYSOFT.Ui.Toast.Show("没有待分配的客户",2000);
                return;
            };
            location.href = tplData.urlManualalloc+"&type="+type;
        });
        $('#container').delegate('.autoalloc','click',function(){
            var type = $(this).closest(".itempanel").data("type"),num = $(this).closest(".itempanel").data("num");
            if(num == 0){
                MYSOFT.Ui.Toast.Show("没有待分配的客户",2000);
                return;
            }
            MYSOFT.Ui.Popup.ShowConfirm('提示','您确定要自动分配客户吗？',function(){
                $.ajax({
                    url:tplData.ajaxAutoalloc+"&type="+type,
                    cache:false,
                    dataType:"json",
                    success:function(data){
                        if(data.isSuccess==1){
                            MYSOFT.Ui.Toast.Show('即将开始分配!',2000);
                            refreshData();
                        }else{
                            MYSOFT.Ui.Toast.Show(data.message,3000);
                        }
                    },
                    error:function(){}
                });
            });
        });
        var interval=setInterval(function(){
            var wh=$(window).height();
            if(wh!=0){
                clearInterval(interval);
                $('#minpanel').css('min-height',$(window).height()-108);
            }
        },100);
        function refreshData(){
            clearTimeout(timeid);
            var timeid = setTimeout(function(){
                $.ajax({
                    url:tplData.ajaxRefresh,
                    cache:false,
                    dataType:"json",
                    success:function(data){
                        if(data.isSuccess==1){
                            var tempdata = {"list":data.result};
                            rendercontent(tempdata);
                            var isrefresh = 0;
                            //校验是否需要刷新
                            for(var key in data.result){
                                if(data.result[key].status == 1){
                                    isrefresh = 1;
                                }
                            }
                            isrefresh && refreshData();
                        }
                    },
                    error:function(){}
                });
            },2000);
        }
        if($(".itempanel[data-status='1']").size()){
            refreshData();
        }
        function rendercontent(data){
            $("#minpanel").html(template("temp-content", data));
            $(".ring-ring").each(function(ind,dom){
                var me = $(this);
                me.ring({
                    outer:{width:64,backgroundColor:"#fff"},
                    inner:{width:60,backgroundColor:"#EFEFEF",ringColor:"#18c9c9"},
                    text:{size:24,color:"#18c9c9"},
                    percent:me.data("percent")
                });
            });
        }
    });
</script></div>

<script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
<script type="text/javascript">
    var tplData = {"webUrl":"http:\/\/ydxs2.myscrm.cn","theme":"default","pub":"http:\/\/ydxs2.myscrm.cn\/modules\/salemanage\/themes\/pub","allot_status":{"recommend":{"status":2,"total":0,"success":0,"remain":0,"new":0},"appointment":{"status":2,"total":0,"success":0,"remain":0,"new":0},"tfans":{"status":2,"total":0,"success":0,"remain":0,"new":0},"buy":{"status":2,"total":0,"success":0,"remain":0,"new":0}},"action":"clue-index","urlparams":{"proj_id":"c5091504-41ae-4c2e-ae3b-69a008a5762f","__orgcode":"sxzhaoshengadmin"},"isAndroid":0}||{};
    tplData['projId'] = 'c5091504-41ae-4c2e-ae3b-69a008a5762f';
    tplData['ticket'] = '39d4b5db-74f6-9f59-da73-b7d861f2c887';
    tplData['pageSize'] = 12;
    tplData['oldUrl'] = "http://ydxs.myscrm.cn/Sales/Customer/personal?from=1&hideQu=1&proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342";
    for(var i = 0;i<mysoft.initfunc.length;i++){mysoft.initfunc[i]();}
</script>
<!--<div style="display: none">-->
    <!--<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256874414'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256874414' type='text/javascript'%3E%3C/script%3E"));</script>-->
<!--</div>-->


</body><iframe id="__WeixinJSBridgeIframe_SetResult" style="display: none;"></iframe><iframe id="__WeixinJSBridgeIframe" style="display: none;"></iframe></html>

