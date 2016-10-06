<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK" %>
<!DOCTYPE html><html><head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/mbem/mcrm/business/css/base.min.css">
    <script type="text/javascript" src="/common/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
    	/* $(function ($) {
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
		});   */  	 
    </script>
<style>
body {background: #f0f0f0;margin:0;}
.border-1px {border-bottom-width: 1px;}
.btn-bottom {position: fixed;bottom:0;width:100%;font-size: 16px;line-height: 50px;text-align: center;}
.btn-cancel,.btn-save{width:50%;height:50px;float: left;}
.btn-cancel{background: #f7f7f7;color:#3c6;}
.btn-cancel.active {background: #d9d9d9;color:#3c6;}
.btn-save {background: #3c6;color:white;}
.btn-save.active{background: #2ead2a}
}
</style><style>
    .setting-wrapper {
        margin-bottom:58px; /*底部间隔*/
    }
    .target-panel {
        margin: 8px 0;
        background: #fff;
    }
    .target-type .title {
        line-height: 44px;
        font-size: 15px;
        color:#444;
        margin: 0;
    }
    .target-type .my-icon{
        float:left;
        width:6px;
        height:16px;
        margin-top:14px;
        margin-left:12px;
        background: #ffd24d;
        margin-right: 9px;
        border-radius: 3px;
    }
input {border:0;writing-mode:tb-rl;text-align: right;color:#ccc;font-size:15px;width:100%;}
input:focus {outline:none;}
.target-setting {
    box-sizing:border-box;
    padding-left:80px;
    padding-right: 50px;
    position: relative;
    color:#666;
    font-size: 15px;
    height: 50px;
    line-height: 50px;
    margin-left: 12px;
}
.target-range {
    position: absolute;
    left:0px;
}
.target-unit {
    width:35px;
    text-align: center;
    position: absolute;
    right:12px;
    top:0px;
}
.target-input {
    width:100%;
    text-align: right;
}
.target-input input:focus {color:#000;}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button{
    -webkit-appearance: none !important;
    margin: 0;
}
</style></head>


<body>
    <div class="setting-wrapper">
        <form id="myform" action="/Sales/Myperfor/savetarget?token=cdycid1429859342&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;projid=c5091504-41ae-4c2e-ae3b-69a008a5762f" method="post">
                 <div class="target-panel border-1px">
            <div class="target-head border-1px">
                <div class="target-type">
                    <h3 class="title"><i class="my-icon"></i>预算目标金额</h3>
                </div>
            </div>
            <div class="target-setting border-1px">
                    <div class="target-range">月度</div>
                    <div class="target-input">
                        <input type="number" name="data[1][1][target_value]" value="0">
                    	<input type="hidden" name="data[1][1][id]" value="39d4e3f6-e3c6-1cc8-04c6-6c9ccc703610" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">万元</div>
            </div>
            <div class="target-setting border-1px">
                    <div class="target-range">季度</div>
                    <div class="target-input">
                        <input type="number" name="data[1][2][target_value]" value="0" style="color: rgb(204, 204, 204);">
                    	<input type="hidden" name="data[1][2][id]" value="39d4e3f6-e3dd-4eb9-7dce-0990e3630d6d" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">万元</div>
            </div>
            <div class="target-setting">
                    <div class="target-range">年度</div>
                    <div class="target-input">
                        <input type="number" name="data[1][3][target_value]" value="0">
                    	<input type="hidden" name="data[1][3][id]" value="39d4e3f6-e3ee-7066-c3ba-05dc2a289e38" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">万元</div>
            </div>
        </div>
                <div class="target-panel border-1px">
            <div class="target-head border-1px">
                <div class="target-type">
                    <h3 class="title"><i class="my-icon"></i>预算目标套数</h3>
                </div>
            </div>
            <div class="target-setting border-1px">
                    <div class="target-range">月度</div>
                    <div class="target-input">
                        <input type="number" name="data[2][1][target_value]" value="0">
                    	<input type="hidden" name="data[2][1][id]" value="39d4e3f6-e3ca-bd72-33a3-85d9c77b0449" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">套</div>
            </div>
            <div class="target-setting border-1px">
                    <div class="target-range">季度</div>
                    <div class="target-input">
                        <input type="number" name="data[2][2][target_value]" value="0">
                    	<input type="hidden" name="data[2][2][id]" value="39d4e3f6-e3e1-cc36-b028-dda0733e19f7" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">套</div>
            </div>
            <div class="target-setting">
                    <div class="target-range">年度</div>
                    <div class="target-input">
                        <input type="number" name="data[2][3][target_value]" value="0">
                    	<input type="hidden" name="data[2][3][id]" value="39d4e3f6-e3f9-91b7-64f8-fd22a18aec6b" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">套</div>
            </div>
        </div>
                <div class="target-panel border-1px">
            <div class="target-head border-1px">
                <div class="target-type">
                    <h3 class="title"><i class="my-icon"></i>预算目标面积</h3>
                </div>
            </div>
            <div class="target-setting border-1px">
                    <div class="target-range">月度</div>
                    <div class="target-input">
                        <input type="number" name="data[3][1][target_value]" value="0">
                    	<input type="hidden" name="data[3][1][id]" value="39d4e3f6-e3cf-ad7c-670a-f55b882de668" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">㎡</div>
            </div>
            <div class="target-setting border-1px">
                    <div class="target-range">季度</div>
                    <div class="target-input">
                        <input type="number" name="data[3][2][target_value]" value="0">
                    	<input type="hidden" name="data[3][2][id]" value="39d4e3f6-e3e5-395a-6582-8f5f8688ef02" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">㎡</div>
            </div>
            <div class="target-setting">
                    <div class="target-range">年度</div>
                    <div class="target-input">
                        <input type="number" name="data[3][3][target_value]" value="0">
                    	<input type="hidden" name="data[3][3][id]" value="39d4e3f6-e3fe-9251-ce0a-9e1027e27c4a" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">㎡</div>
            </div>
        </div>
                <div class="target-panel border-1px">
            <div class="target-head border-1px">
                <div class="target-type">
                    <h3 class="title"><i class="my-icon"></i>预算目标回款</h3>
                </div>
            </div>
            <div class="target-setting border-1px">
                    <div class="target-range">月度</div>
                    <div class="target-input">
                        <input type="number" name="data[4][1][target_value]" value="0">
                    	<input type="hidden" name="data[4][1][id]" value="39d4e3f6-e3d6-4388-6473-45f8ea75d401" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">万元</div>
            </div>
            <div class="target-setting border-1px">
                    <div class="target-range">季度</div>
                    <div class="target-input">
                        <input type="number" name="data[4][2][target_value]" value="0">
                    	<input type="hidden" name="data[4][2][id]" value="39d4e3f6-e3e9-73a8-2369-228da4069977" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">万元</div>
            </div>
            <div class="target-setting">
                    <div class="target-range">年度</div>
                    <div class="target-input">
                        <input type="number" name="data[4][3][target_value]" value="0">
                    	<input type="hidden" name="data[4][3][id]" value="39d4e3f6-e405-a23b-9009-7b28b1138a8f" style="color: rgb(0, 0, 0);">
                    </div>
                    <div class="target-unit">万元</div>
            </div>
        </div>
           	 	</form>
    </div>
    <div class="btn-bottom clearfix">
        <div class="btn-cancel" data-ac="active">取消</div>
        <div class="btn-save" data-ac="active">保存</div>
    </div>
<script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
<script>
    $('input').each(function(){
        if($(this).val()!='0') {
            $(this).css('color','#000')
        }
    })
    $('input').on({'blur':function(){
        if($(this).val()!='0'&&$(this).val()!='') {
            $(this).css('color','#000')
        } else {
            $(this).val('0').css('color','#ccc')
        }
    },
    'focus':function() {
        $(this).css('color','#000');
        if($(this).val()=='0') {
            $(this).val('');
        }
    }
})
    $('.btn-cancel').tap(function(){
        setTimeout(function(){history.back();},200)
    });

    $('.btn-save').tap(function(){
        saveResult();
    })
    function saveResult() {
    		var form = $("#myform");
            var canSubmit = checkData();
            if(canSubmit) {
                $.ajax({
	                url: form.attr('action'),
	                data: form.serialize(),
	                type: form.attr('method'),
	                success: function (msg) {
	                   window.history.back();
	                }
                });
            }
    }
    //检测各变量之间是否合理，比如年度目标应大于季度目标，数值检测为正数
    function checkData() {
        var type = ['金额','套数','面积','回款'];
        var targets = document.querySelectorAll('.target-panel');
        var passed = true; //是否通过检查
        for(i = 0;i < targets.length; i++) {
            var dom = targets[i];
            var num = [];
            $(dom).find('input[type="number"]').each(function(){num.push(Number($(this).val()))});
            if(num[0] < 0 ) {
                passed = false;
                $.Ui.Popup.ShowAlert('错误提示',  '月度目标'+type[i]+'不能为负值');break;
            }
            if(num[1] < 0 ) {
                passed = false;
                $.Ui.Popup.ShowAlert('错误提示',  '季度目标'+type[i]+'不能为负值');break;
            }
            if(num[2] < 0 ) {
                passed = false;
                $.Ui.Popup.ShowAlert('错误提示',  '年度目标'+type[i]+'不能为负值');break;
            }
            if(num[1]<num[0]) {
                passed = false;
                $.Ui.Popup.ShowAlert('错误提示',  '季度目标'+type[i]+'应大于月度目标');break;
            }
            if(num[2]<num[1]) {
                passed = false ;
                $.Ui.Popup.ShowAlert('错误提示','年度目标'+type[i]+'应大于季度目标');break;
            }
        }
        return passed;
    }
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
        background-image: url(/Public/dest/popup/images/loading35@35.gif);
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
            MYSOFT.Ui.Popup.MaskDomObj.show().css({'opacity':'0'});
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
</script>

</body><iframe id="__WeixinJSBridgeIframe_SetResult" style="display: none;"></iframe><iframe id="__WeixinJSBridgeIframe" style="display: none;"></iframe></html>