/*屏蔽微信分享功能*/
//document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
//    // 通过下面这个API隐藏右上角按钮
//    WeixinJSBridge.call('hideOptionMenu');
//    WeixinJSBridge.call('hideToolbar'); 
//});


/**===================================================================
 * @明源移动App框架
 * @author 李真志
 * ===================================================================
 */

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
 * @description MYSOFT全局配置
 */
MYSOFT.Config = {
    /**
    *浏览器对应前缀
    */
    vendors: { Webkit: 'webkit', Moz: '', O: 'o', ms: 'MS' }
};


/**
 * @namespace MYSOFT工具类，获取相关的全局数据变量
 */
MYSOFT.Util = {
    /**
    *获取移动浏览器前缀
    */
    getBrowserPrefix: function () {
        if (!MYSOFT.Util.browserPrefix) {
            var testEl = document.createElement('div');
            $.each(MYSOFT.Config.vendors, function (vendor, event) {
                if (testEl.style[vendor + 'TransitionProperty'] !== undefined) {
                    MYSOFT.Util.browserPrefix = '-' + vendor.toLowerCase() + '-';
                    return false;
                }
            });
        }
        return MYSOFT.Util.browserPrefix;
    },
    dasherize: function (str) {
        return str.replace(/::/g, '/')
               .replace(/([A-Z]+)([A-Z][a-z])/g, '$1_$2')
               .replace(/([a-z\d])([A-Z])/g, '$1_$2')
               .replace(/_/g, '-')
               .toLowerCase();
    },
    /**
    *通过模版创建Html
    */
    createHTML: function (template, data) {
        return template.replace(/\$\{(.+?)\}/g, function (all, $1) {
            return data[$1]!==undefined?data[$1]:'';
        });
    },
    /**
    *将Json对象转换成字符串
    * @param Json对象
    */
    stringify: function (obj) {
        var t = typeof (obj);
        if (t != "object" || obj === null) {
            if (t == "string") obj = '"' + obj + '"';
            return String(obj);
        }
        else {
            var n, v, json = [], arr = (obj && obj.constructor == Array);
            for (n in obj) {
                v = obj[n]; t = typeof (v);
                if (t == "string") v = '"' + v + '"';
                else if (t == "object" && v !== null) v = MYSOFT.Util.stringify(v);
                json.push((arr ? "" : '"' + n + '":') + String(v));
            }
            return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
        }
    }
};



/**
 * @namespace MYSOFT通知组件类
 */
MYSOFT.Ui.Toast = {
    //通知组件持续时间
    TOAST_DURATION: 2000,
    //通知组件模板
    TEMPLATE: {
        toast: '<a href="#">{value}</a>',
        success: '<a href="#"><i class="icon checkmark-circle"></i>{value}</a>',
        error: '<a href="#"><i class="icon cancel-circle"></i>{value}</a></div>',
        info: '<a href="#"><i class="icon info-2"></i>{value}</a>'
    },
    //默认通知类型toast|success|error|info
    TOAST_TYPE: 'toast',
    //初始化Dom对象到文档
    Init: function () {
        if (!MYSOFT.Ui.Toast.DomObj) {
            //全局只有一个实例
            $('body').append('<div id="mysoft_toast"></div>');
            MYSOFT.Ui.Toast.DomObj = $('#mysoft_toast');
        }
    },
    /**
    * 显示通知组件
    * @param type 类型  toast|success|error|info
    * @param text 文字内容
    * @param duration 持续时间 为0则不自动关闭,默认为2000ms
    */
    Show: function (type, text, duration) {
        MYSOFT.Ui.Toast.Init();
        if (MYSOFT.Ui.Toast.TIMER) {
            clearTimeout(MYSOFT.Ui.Toast.TIMER);
        }
        MYSOFT.Ui.Toast.TOAST_TYPE = type;
        MYSOFT.Ui.Toast.DomObj.attr('class', MYSOFT.Ui.Toast.TOAST_TYPE).html(MYSOFT.Ui.Toast.TEMPLATE[MYSOFT.Ui.Toast.TOAST_TYPE].replace('{value}', text)).show();
        MYSOFT.Ui.Anim.doAnimation(MYSOFT.Ui.Toast.DomObj, 'scaleIn');
        if (duration !== 0) {//为0 不自动关闭
            MYSOFT.Ui.Toast.TIMER = setTimeout(MYSOFT.Ui.Toast.Hide, duration || MYSOFT.Ui.Toast.TOAST_DURATION);
        }
    },
    /*
    隐藏通知组件
    */
    Hide: function () {
        MYSOFT.Ui.Anim.doAnimation(MYSOFT.Ui.Toast.DomObj, 'scaleOut', null, null, function () {
            MYSOFT.Ui.Toast.DomObj.hide();
            MYSOFT.Ui.Toast.DomObj.empty();
        });
    }
};
/**
 * @namespace MYSOFT动画效果组件类
 */
MYSOFT.Ui.Anim = {
    //动画效果
    ANIMATIONNAME: MYSOFT.Util.getBrowserPrefix() + 'animation-name',
    //动画持续时间
    ANIMATIONDURATION: MYSOFT.Util.getBrowserPrefix() + 'animation-duration',
    //动画的速度曲线
    ANIMATIONTIMING: MYSOFT.Util.getBrowserPrefix() + 'animation-timing-function',
    //规定应用过渡效果的CSS属性的名称
    TRANSITIONPROPERTY: MYSOFT.Util.getBrowserPrefix() + 'transition-property',
    //完成过渡效果需要花费的时间
    TRANSITIONDURATION: MYSOFT.Util.getBrowserPrefix() + 'transition-duration',
    //规定过渡效果的速度曲线
    TRANSITIONTIMING: MYSOFT.Util.getBrowserPrefix() + 'transition-timing-function',
    //支持的转场效果
    SUPPORTEDTRANSFORMS: /^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,
    //动画转场函数
    TRANSITIONTIMINGFUNC: 'ease-in',
    //转场持续时间
    TRANSITIONTIME: 250,
    //结束转场事件
    TRANSITIONENDEVENT: 'webkitTransitionEnd',
    //结束动画事件
    ANIMATIONENDEVENT: 'webkitAnimationEnd',
    /*
    获取重置样式属性
    */
    getCssReset: function () {
        if (!MYSOFT.Ui.Anim.CSSRESET) {
            MYSOFT.Ui.Anim.CSSRESET = {};
            MYSOFT.Ui.Anim.CSSRESET[MYSOFT.Ui.Anim.TRANSITIONPROPERTY] =
            MYSOFT.Ui.Anim.CSSRESET[MYSOFT.Ui.Anim.TRANSITIONDURATION] =
            MYSOFT.Ui.Anim.CSSRESET[MYSOFT.Ui.Anim.TRANSITIONTIMING] =
            MYSOFT.Ui.Anim.CSSRESET[MYSOFT.Ui.Anim.ANIMATIONNAME] =
            MYSOFT.Ui.Anim.CSSRESET[MYSOFT.Ui.Anim.ANIMATIONDURATION] =
            MYSOFT.Ui.Anim.CSSRESET[MYSOFT.Ui.Anim.ANIMATIONTIMING] = '';
        }
        return MYSOFT.Ui.Anim.CSSRESET;
    },
    //开始动画效果
    /**
    开始动画效果
    @ele:Dom元素
    @properties:动画效果类型(scaleIn;scaleOut)
    @duration:时间间隔(单位秒,可以为小数)
    @ease:转场动画函数
    @callback:回调函数
    */
    doAnimation: function (ele, properties, duration, ease, callback) {
        var key, cssValues = {}, cssProperties, transforms = '',
        me = this, wrappedCallback, endEvent;
        duration = duration || MYSOFT.Ui.Anim.TRANSITIONTIME;
        duration = duration / 1000;
        ease = ease || MYSOFT.Ui.Anim.TRANSITIONTIMINGFUNC;

        if (typeof properties == 'string') {
            // keyframe animation
            cssValues[MYSOFT.Ui.Anim.ANIMATIONNAME] = properties;
            cssValues[MYSOFT.Ui.Anim.ANIMATIONDURATION] = duration + 's';
            cssValues[MYSOFT.Ui.Anim.ANIMATIONTIMING] = (ease || 'linear');
            endEvent = MYSOFT.Ui.Anim.ANIMATIONENDEVENT;
        }
        else {
            cssProperties = []
            // CSS transitions
            for (key in properties) {
                if (MYSOFT.Ui.Anim.SUPPORTEDTRANSFORMS.test(key)) {
                    transforms += key + '(' + properties[key] + ') ';
                } else {
                    cssValues[key] = properties[key], cssProperties.push(MYSOFT.Util.dasherize(key));
                }
            }
            endEvent = MYSOFT.Ui.Anim.TRANSITIONENDEVENT;
        }
        wrappedCallback = function (e) {
            ele.css(MYSOFT.Ui.Anim.getCssReset());
            callback && callback();
        }
        if (duration > 0) {
            ele.unbind(endEvent).bind(endEvent, wrappedCallback);
        }
        else {
            setTimeout(wrappedCallback, 0);
        }

        ele.css(cssValues);
    }
};
/**
 * @namespace MYSOFT幻灯片效果组件类
 * @param selector dom选择器
 * @param options 幻灯片配置属性结构
 */
MYSOFT.Ui.Slider = function (selector, options) {
    //this变量重命名
    var me = this;
    //默认配置
    var defaults = {
        //图片切换后回调函数
        afterSlide: function () {
        },
        //图片切换前回调函数
        beforeSlide: function () {
            return true;
        },
        //滑动速度
        speed: 200,
        //是否自动播放
        autoPlay: false,
        //是否启用缩放
        zoom: true,
        //最小缩放比例
        minZoomScale: 1,
        //最大缩放比例
        maxZoomScale: 5
    };
    //合并参数
    me.opts = $.extend(true, defaults, options);
    //获取幻灯片对象
    me.$wrapper = $(selector).css('overflow', 'hidden');
    //获取幻灯片容器
    me.$container = me.$wrapper.children().first();
    //获取幻灯片列表
    me.$slides = me.$container.children();
    //获取幻灯片图片
    me.$imgs = me.$slides.find('img');
    //设置幻灯片数量
    me.slideNum = me.$imgs.length;
    //是否开始滑动
    me.gestureStarted = false;
    //幻灯片宽度
    me.slideWidth = me.$wrapper[0].offsetWidth;
    //设置幻灯片容器宽度
    me.$container.css('width', me.slideNum * me.slideWidth);
    //设置幻灯片列表样式
    me.$slides.css({
        'width': me.slideWidth,
        'float': 'left'
    });
    //默认播放第一张
    me.slide(0, 0);
    //是否自动播放
    if (me.opts.autoPlay) {
        me.autoPlay();
    }
    me.$imgs.bind('touchstart', function (event) {
        var e = event.originalEvent.touches[0];
        me.start = {
            pageX: e.pageX,
            pageY: e.pageY,
            time: Number(new Date())
        };
        me.isScrolling = undefined;
        me.deltaX = 0;
        me.$container[0].style.webkitTransitionDuration = 0;
        me.gestureStarted = true;
    });
    me.$imgs.bind('touchmove', function (event) {
        if (!me.gestureStarted) {
            return;
        }
        var e = event.originalEvent.touches[0];
        me.deltaX = e.pageX - me.start.pageX;
        if (typeof me.isScrolling == 'undefined') {
            //根据X、Y轴的偏移量判断用户的意图是左右滑动还是上下滑动
            me.isScrolling = Math.abs(me.deltaX) < Math.abs(e.pageY - me.start.pageY);
        }
        if (!this.isScrolling) {
            event.preventDefault();
            //判定是否达到了边界即第一个右滑、最后一个左滑
            var isPastBounds = !me.index && me.deltaX > 0 || me.index == me.slideNum - 1 && me.deltaX < 0;
            if (isPastBounds) {
                return;
            }
        }
    });
    me.$imgs.bind('touchend', function (e) {
        //判定是否跳转到下一个卡片
        //滑动时间小于250ms且滑动X轴的距离大于屏幕宽度的1/3
        var isValidSlide = (Number(new Date()) - me.start.time < 250 && Math.abs(me.deltaX) > 20) && Math.abs(me.deltaX) > me.slideWidth / 3;
        //判定是否达到了边界即第一个右滑、最后一个左滑
        var isPastBounds = !me.index && me.deltaX > 0 || me.index == me.slideNum - 1 && me.deltaX < 0;
        if (!me.isScrolling) {
            if (me.opts.beforeSlide(me.index, me.deltaX)) {
                me.slide(me.index + (isValidSlide && !isPastBounds ? (me.deltaX < 0 ? 1 : -1) : 0), me.opts.speed);
            } else {
                me.slide(me.index);
            }
        }
        me.gestureStarted = false;
    });
    if (me.opts.zoom) {
        me.$imgs.panzoom({
            minScale: me.opts.minZoomScale,
            maxScale: me.opts.maxZoomScale,
            contain: 'invert'
            //onPan: function (e, panzoom) {
            //    var zoom = panzoom.getMatrix();
            //    if (zoom[0] == "1" && zoom[3] == "1") {
            //        console.log(zoom);
            //        zoom[4] = "0";
            //        zoom[5] = "0";
            //        console.log(zoom);
            //        panzoom.setMatrix(zoom);
            //    }
            //},
            //onZoom: function (e, panzoom) {
            //    var zoom = panzoom.getMatrix();
            //    zoom[1] = "0";
            //    zoom[2] = "0";
            //    zoom[4] = "0";
            //    zoom[5] = "0";
            //    panzoom.setMatrix(zoom);
            //}
        });
    }
};
MYSOFT.Ui.Slider.WindowSize = {
    width: $(window).width(),
    height: $(window).height()
};
/**
 * @namespace MYSOFT幻灯片效果组件-滑动到指定卡片
 * @param i 索引
 * @param duration 间隔
 */
MYSOFT.Ui.Slider.prototype.slide = function (i, duration) {
    duration = duration || this.opts.speed;
    this.$container.css({
        '-webkit-transition-duration': duration + 'ms',
        '-webkit-transform': 'translate3D(' + -(i * this.slideWidth) + 'px,0,0)'
    });
    var $curimg = $(this.$imgs[i]);
    $curimg.css('width', MYSOFT.Ui.Slider.WindowSize.width);
    if (this.index != i) {
        this.index = i;
        if (this.opts.zoom) {
            $curimg.panzoom("zoom", 1.0);
            $curimg.panzoom("resetPan");
        }
        this.opts.afterSlide(this.index);
    }
};
/**
 * @namespace MYSOFT幻灯片效果组件-自动播放
 */
MYSOFT.Ui.Slider.prototype.autoPlay = function () {
    var me = this;
    setTimeout(function () {
        if (me.index == me.slideNum - 1) {
            me.slide(0);
        } else {
            me.next();
        }
        me.autoPlay();
    }, 3000);
};
/**
 * @namespace MYSOFT幻灯片效果组件-上一幻灯片
 */
MYSOFT.Ui.Slider.prototype.prev = function () {
    if (this.index) {
        this.slide(this.index - 1, this.opts.speed);
    }
};
/**
 * @namespace MYSOFT幻灯片效果组件-下一幻灯片
 */
MYSOFT.Ui.Slider.prototype.next = function () {
    if (this.index < this.slideNum - 1) {
        this.slide(this.index + 1, this.opts.speed);
    }
};
/**
 * @namespace MYSOFT幻灯片效果组件-切换到指定幻灯片
 * @param i 索引
 */
MYSOFT.Ui.Slider.prototype.index = function (i) {
    this.slide(i);
};

/**
 * @namespace MYSOFT Tab菜单组件类
 * @param selector dom选择器
 * @param options 菜单属性
 * {
 *      //菜单列表
 *      items:[{'text':'测试菜单1','class':'select'},{'text':'测试菜单2'},{'text':'测试菜单3'}],
 *      //默认显示的菜单数
 *      showLength:3,
 *      //菜单点击回调函数
 *      itemClick:function:(index){}
 * }
 */
MYSOFT.Ui.TabMenu = function (selector, options) {
    //重命名this变量
    var me = this;
    //默认配置
    var defaults = {
        //菜单列表[{'text':'测试菜单1','class':'select'},{'text':'测试菜单2'},{'text':'测试菜单3'}]
        items: [],
        //默认显示的菜单数
        showLength: 3,
        //向左,向右滑动Dom对象总宽度
        fixWidth: 52,
        //菜单点击回调函数
        itemClick: function (index) {
        }
    };
    //合并参数
    var opts = $.extend(true, defaults, options);
    //获取Dom对象
    me.$tabemenu = $(selector);
    //当前显示菜单第一个索引
    me.firstIndex = 0;
    //获取Dom对象Id,用于绑定菜单Id
    me.$id = me.$tabemenu.attr('id');
    //临时变量
    var contentHTML = [];
    //菜单模版
    var template = MYSOFT.Ui.TabMenu.Template.item;
    //菜单列表
    var items = opts.items;
    //菜单总数量
    me.totalLength = items.length;
    //默认显示的菜单数量
    me.showLength = opts.showLength;
    //每个菜单占的百分比
    var totalWidth = me.$tabemenu[0].offsetWidth - opts.fixWidth;
    var itemWidth = parseInt(totalWidth / me.showLength);
    var fixWidth = totalWidth - itemWidth * me.showLength;
    //创建菜单Html
    for (var i = 0; i < me.totalLength; i++) {
        if ((i + 1) % me.showLength == 0) {
            items[i].width = itemWidth + fixWidth;
        }
        else {
            items[i].width = itemWidth;
        }
        items[i].id = me.$id + '_' + i;
        items[i].class = items[i].class || '';
        contentHTML.push(MYSOFT.Util.createHTML(template, items[i]));
    }
    contentHTML = contentHTML.join('');
    var tData = {
        items: contentHTML.toString()
    };
    //设置Tab菜单Html
    me.$tabemenu.html(MYSOFT.Util.createHTML(MYSOFT.Ui.TabMenu.Template.panel, tData));
    //绑定向前滑动菜单事件
    me.$tabemenu.find('.prev').bind('click', function () {
        me.prev.call(me);
    });
    //绑定向后滑动菜单事件
    me.$tabemenu.find('.next').bind('click', function () {
        me.next.call(me);
    });
    //绑定菜单点击事件
    me.$tabemenu.find('.childtab li').bind('click', function () {
        me.$tabemenu.find('.childtab li').removeClass('select');
        var $this = $(this);
        $this.addClass('select');
        opts.itemClick && opts.itemClick(parseInt($this.attr('id').split('_')[1]));
    });
    
    //将最后一个菜单的右边框隐藏
    $('#' + me.$id + '_' + (me.firstIndex + me.showLength - 1)).addClass('noborder');
};
/**
 * @namespace MYSOFT Tab菜单组件-向前滑动菜单
 */
MYSOFT.Ui.TabMenu.prototype.prev = function () {
    var me = this;
    if (me.firstIndex == 0) {
        return;
    }
    $('#' + me.$id+'_' + (me.firstIndex - 1)).show();
    me.firstIndex -= 1;
    //将当前的右边框显示
    $('#' + me.$id + '_' + (me.firstIndex - 2 + me.showLength)).removeClass('noborder');
    //将最后一个菜单的右边框隐藏
    $('#' + me.$id + '_' + (me.firstIndex + me.showLength - 1)).addClass('noborder');
};
/**
 * @namespace MYSOFT Tab菜单组件-向后滑动菜单
 */
MYSOFT.Ui.TabMenu.prototype.next = function () {
    var me = this;
    if (me.firstIndex + me.showLength == me.totalLength) {
        return;
    }
    $('#' + me.$id+'_' + me.firstIndex).hide();
    me.firstIndex += 1;
    //将当前的右边框显示
    $('#' + me.$id + '_' + (me.firstIndex - 2 + me.showLength)).removeClass('noborder');
    //将最后一个菜单的右边框隐藏
    $('#' + me.$id + '_' + (me.firstIndex + me.showLength - 1)).addClass('noborder');
};

/**
 * @namespace MYSOFT Tab菜单模板
 */
MYSOFT.Ui.TabMenu.Template = {
    panel: 
        '<ul class="tabmenu"><li class="prev"></li>\
         <li class="items"><ul class="childtab">${items}</ul></li>\
         <li class="next"></li></ul>',
    item:
        '<li id="${id}" style="width:${width}px" class="${class}"><div>${text}</div></li>'
};


/**
 * @namespace MYSOFT EasyTab切换组件
 * @param selector dom选择器
 * @param options 菜单属性,请参考MYSOFT.Ui.EasyTab.Defaults
 */
MYSOFT.Ui.EasyTab = function (selector, options) {
    //重命名this变量
    var me = this;
    //默认参数
    var defaults = {
        //菜单列表[{ 'text': '测试菜单1', 'class': 'select' }, { 'text': '测试菜单2' }, { 'text': '测试菜单3' }]
        items: [],
        //边框宽度
        fixWidth: 2,
        //菜单点击回调函数
        itemClick: function (index) { }
    };
    //合并参数
    var opts = $.extend(true, defaults, options);
    //获取Dom对象
    me.$tabemenu = $(selector);
    //获取Dom对象Id,用于绑定菜单Id
    me.$id = me.$tabemenu.attr('id');
    //临时变量
    var contentHTML = [];
    //菜单模版
    var template = MYSOFT.Ui.EasyTab.Template;
    //菜单列表
    var items = opts.items;
    //菜单总数量
    me.totalLength = items.length;
    //每个菜单占的百分比
    var totalWidth = me.$tabemenu[0].offsetWidth - opts.fixWidth;
    var itemWidth = parseInt(totalWidth / me.totalLength);
    var fixWidth = totalWidth - itemWidth * me.totalLength;
    //没有传入菜单则返回
    if (me.totalLength == 0) {
        return;
    }
    contentHTML.push('<ul class="easytab">');
    //创建菜单Html
    for (var i = 0; i < me.totalLength; i++) {
        if (i == me.totalLength - 1) {
            items[i].width = itemWidth + fixWidth;
        }
        else {
            items[i].width = itemWidth;
        }
        items[i].id = me.$id + '_' + i;
        items[i].class = items[i].class || '';
        contentHTML.push(MYSOFT.Util.createHTML(template, items[i]));
    }
    contentHTML.push('</ul>');
    contentHTML = contentHTML.join('');
    //设置EasyTab菜单Html
    me.$tabemenu.html(contentHTML.toString());
    //绑定菜单点击事件
    me.$tabemenu.find('li').bind('click', function () {
        me.$tabemenu.find('li').removeClass('select');
        var $this = $(this);
        $this.addClass('select');
        opts.itemClick && opts.itemClick(parseInt($this.attr('id').split('_')[1]));
    });
};
/**
 * @method 设置焦点
 * @param index 索引
 */
MYSOFT.Ui.EasyTab.prototype.SetFocus = function (index) {
    var me = this;
    me.$tabemenu.find('li').removeClass('select');
    $('#' + me.$id+'_'+index).addClass('select');
}


/**
 * @namespace MYSOFT EasyTab菜单模板
 */
MYSOFT.Ui.EasyTab.Template = '<li id="${id}" style="width:${width}px" class="${class}"><div>${text}</div></li>';

/**
 * @namespace MYSOFT倒三角报表组件类
 * @param selector dom选择器
 * @param options 倒三角报表配置属性结构参考MYSOFT.Ui.Triangle.Defaults
 */
MYSOFT.Ui.Triangle = function (selector, options) {
    //重命名this变量
    var me = this;
    //获取Dom选择器
    me.$container = $(selector);
    //获取Dom选择器Id
    me.id = me.$container.attr('id');
    //默认参数
    var defaults = {
        //报表宽度
        width: 400,
        //报表数据源,格式为:[{"name":"数据1","y":234}，{"name":"数据2","y":234，{"name":"数据3","y":234]
        data: null,
        //报表上边距
        marginTop: 0,
        //报表下边距
        marginBottom: 0,
        //报表左边距
        marginLeft: 0,
        //报表右边距
        marginRight: 0,
        //每行高度
        itemHeight: 30,
        //每行相隔边距
        itemMargin: 1,
        //边框
        itemBorder: 12,
        //文本与值边距
        textMargin: 3,
        //颜色值
        colors: ['#33aafd', '#33cc66', '#ff87a3', '#89e347', '#ffd957', '#65c3ff'],
        //行间隙背景色
        marginBgColor: '#fff',
        //列点击事件
        itemClick: function () {
        }
    };
    //合并配置参数
    var opts = me.opts = $.extend(true, defaults, options);
    //根据数据源画出报表
    var contentHTML = [];
    //缓存报表数据源
    var data = me.opts.data;
    //根据传入的数据源计算出报表高度
    var height = data.length * opts.itemHeight + opts.marginBottom + opts.marginTop + data.length * opts.itemMargin;
    contentHTML.push('<svg width="' + opts.width + '" height="' + height + '">');
    for (var i = 0, len = data.length; i < len; i++) {
        var points = [];
        //计算出当前列坐标点
        points.push('M');
        points.push(i * opts.itemBorder + opts.marginLeft + i * opts.itemMargin);
        points.push(i * opts.itemHeight + opts.marginTop + i * opts.itemMargin);
        points.push('L');
        points.push(opts.width - opts.marginLeft - opts.marginRight - i * opts.itemBorder - i * opts.itemMargin);
        points.push(opts.marginTop + i * opts.itemHeight + i * opts.itemMargin);
        points.push('L');
        points.push(points[4] - opts.itemBorder);
        points.push(points[5] + opts.itemHeight);
        points.push('L');
        points.push(points[1] + opts.itemBorder);
        points.push(points[2] + opts.itemHeight);
        points.push('Z');
        points = points.join(' ');
        //计算出当前列背景色
        var color = opts.colors[i % opts.colors.length];
        //将当前列追加到结果Html
        contentHTML.push('<g><path id="' + me.id+'_svgitem_'+i + '" class="svgitem" d="' + points.toString() + '" fill="' + color + '" /></g>');
        //计算当前列文字显示坐标
        var x = opts.width / 2 - opts.textMargin;
        var x1 = opts.width / 2 + opts.textMargin;
        var y = 20 + i * (opts.itemHeight + opts.itemMargin);
        //将当前列文字追加到结果Html
        contentHTML.push('<g><text id="' + me.id+'_svgitem1_'+i + '" class="svgitem" x="' + x + '" y="' + y + '" fill="white" style="text-anchor: end; font-size:12px;">' + data[i].name + '</text></g>');
        contentHTML.push('<g><text id="' + me.id+'_svgitem2_'+i + '" class="svgitem" x="' + x1 + '" y="' + y + '" fill="white" style="text-anchor: start; font-size:16px;">' + data[i].y + '</text></g>');
        var points1 = [];
        if (i < len - 1) {
            //计算列之间空隙坐标
            points = points.split(' ');
            points1.push('M');
            points1.push(points[10]);
            points1.push(points[11]);
            points1.push('L');
            points1.push(points[7]);
            points1.push(points[8]);
            points1.push('L');
            points1.push(points1[4] - opts.itemMargin);
            points1.push(points1[5] + opts.itemMargin);
            points1.push('L');
            points1.push(points1[1] + opts.itemMargin);
            points1.push(points1[2] + opts.itemMargin);
            points1.push('Z');
            points1 = points1.join(' ');
            //追加空隙到结果Html
            contentHTML.push('<path d="' + points1.toString() + '" fill="' + opts.marginBgColor + '" />');
        }
    }
    contentHTML.push('</svg>');
    contentHTML = contentHTML.join('');
    me.$container.html(contentHTML.toString());
    //绑定列点击事件
    me.$container.find('.svgitem').bind('click', function () {
        var index = $(this).attr('id').split('_')[2];
        opts.itemClick(me.opts.data[parseInt(index)]);
    });
};

/**
 * @namespace MYSOFT弹出框组件类
 */
MYSOFT.Ui.Popup = {
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
    ShowConfirmRegs: function (title, content, okCall,cancelCall,okText,cancelText) {
        okText=okText||'确 定';
        cancelText=cancelText||'取 消';
        var markup = MYSOFT.Ui.Popup.Template.confirm.replace('{title}', title).replace('{content}', content).replace('{cancel}', cancelText).replace('{ok}', okText);
        MYSOFT.Ui.Popup.Init();
        MYSOFT.Ui.Popup.MaskDomObj.show();
        MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('confirm').html(markup).show();
        $('#tag_ok_popup').bind('click', function () {
            if(okCall()){
                MYSOFT.Ui.Popup.HidePopUp();
            }
/*            MYSOFT.Ui.Popup.HidePopUp();
            okCall && okCall();*/
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
/**
 * @namespace MYSOFT EasyTab切换组件
 * @param selector dom选择器
 * @param options 搜索框属性
 * {
 *      //空白提示语
 *      placeholder:'',
 *      //搜索回调函数
 *      onSearch:function:(value){}  
 * }
 */
MYSOFT.Ui.SearchBox = function (selector, options) {
    //重名名this变量
    var me = this;
    //获取Dom对象
    me.$container = $(selector);
    //默认参数
    var defaults = {
        //空白提示语
        placeholder: '',
        //搜索回调函数
        onSearch: function (value) { }
    };
    //合并参数
    var opts = me.opts = $.extend(true, defaults, options);
    //设置Html字符串
    me.$container.html(MYSOFT.Util.createHTML(MYSOFT.Ui.SearchBox.Template, opts));
    //获取输入框Dom对象
    me.$input = me.$container.find('input');
    //获取清除Dom对象
    me.$clear = me.$container.find('i');
    me.$input.bind('change', function () {
        var $this = $(this);
        opts.onSearch($this.val());
    }).bind('keyup', function () {
        var $this = $(this);
        if ($this.val().length == 0) {
            me.$clear.hide();
        }
        else {
            me.$clear.show();
        }
    });
    me.$clear.bind('click', function () {
        me.$clear.hide();
        me.$input.val('');
        opts.onSearch('');
    });
};
/**
 * @method MYSOFT 设置搜索框的值
 * @param  val  值
 */
MYSOFT.Ui.SearchBox.prototype.SetValue = function (val) {
    var me = this;
    me.$input.val(val);
    if (val != '') {
        me.$clear.show();
    }
    else {
        me.$clear.hide();
    }
}
/**
 * @method MYSOFT 获取搜索框的值
 */
MYSOFT.Ui.SearchBox.prototype.GetValue = function () {
    var me = this;
    return me.$input.val();
}

/**
 * @namespace MYSOFT 搜索文本框模板
 */
MYSOFT.Ui.SearchBox.Template =
    '<ul class="searchbox">\
        <li class="query_btn"></li>\
        <li class="query_input">\
            <input type="text" placeholder="${placeholder}" />\
            <i></i>\
        </li>\
     </ul>';

/**
 * @namespace MYSOFT 滚动条组件
 * @param selector dom选择器
 * @param options 滚动条属性,属性说明请参考MYSOFT.Ui.Scroll.Defaults
 */
MYSOFT.Ui.Scroll = function (selector, options) {
    var scroll, scrollId, $el = $(selector),
    scrollId = $el.attr('_jscroll_');
    //滚动组件使用频繁，缓存起来节省开销
    if (scrollId && MYSOFT.Ui.Scroll.ScrollCache[scrollId]) {
        scroll = MYSOFT.Ui.Scroll.ScrollCache[scrollId];
        scroll.scroller.refresh();
        return scroll;
    } else {
        scrollId = '_jscroll_' + MYSOFT.Ui.Scroll.Index++;
        $el.attr('_jscroll_', scrollId);
        var defaults = {
            hScroll: false,
            bounce: false,
            lockDirection: true,
            useTransform: true,
            useTransition: false,
            checkDOMChanges: false,
            scrollbarClass: "myScrollbar",
            onBeforeScrollStart: function (e) {
                var target = e.target;
                while (target.nodeType != 1) target = target.parentNode;
                if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA')
                    e.preventDefault();
            }
        };
        var opts = $.extend(true, defaults, options);
        var scroller = new iScroll($el[0], opts);
        return MYSOFT.Ui.Scroll.ScrollCache[scrollId] = {
            scroller: scroller,
            destroy: function () {
                scroller.destroy();
                delete MYSOFT.Ui.Scroll.ScrollCache[scrollId];
            }
        };
    };
}

/**
*滚动条缓存
*/
MYSOFT.Ui.Scroll.ScrollCache = {};

/**
*滚动条索引
*/
MYSOFT.Ui.Scroll.Index = 1;


/**
 * @namespace MYSOFT 上拉刷新组件
 * @param selector dom选择器
 * @param options 滚动条属性,属性说明请参考MYSOFT.Ui.Scroll.Defaults
 */
MYSOFT.Ui.DownRefresh = function (selector, options) {
    var $el = $(selector), jRefreshId;
    jRefreshId = $el.attr('_jrefresh_');
    //因上拉下拉可能会使用的比较频繁，故缓存起来节省开销,亦可防止重复绑定
    if (jRefreshId && MYSOFT.Ui.DownRefresh.RefreshCache[jRefreshId]) {
        return MYSOFT.Ui.DownRefresh.RefreshCache[jRefreshId];
    } else {
        jRefreshId = '_jrefresh_' + MYSOFT.Ui.DownRefresh.Index++;
        $el.attr('_jrefresh_', jRefreshId);
        //默认参数
        var defaults = {
            minPullHeight: 10,//拉动的像素相对值，超过才会翻转
            releaseText: "Ready Loading..",
            refreshText: "Loding...",
            callback: undefined,
            bottomcallback:undefined
        };
        var opts = $.extend(true, defaults, options);
        var iscroll, scroller, refreshEl, iconEl, labelEl, topOffset, refreshEl1;

        /**
         * 初始化dom节点
         * @param opts
         * @private
         */
        var _init = function () {
            scroller = $el.children()[0];
            var refreshTpl = [];
            refreshTpl.push('<div class="refresh-container">');
            refreshTpl.push('   <div class="refresh-inner-container">');
            refreshTpl.push('       <span class="refresh-icon"></span>');
            refreshTpl.push('       <span class="refresh-label">');
            refreshTpl.push(opts.releaseText);
            refreshTpl.push('       </span>');
            refreshTpl.push('   </div>');
            refreshTpl.push('</div>');
            refreshTpl = refreshTpl.join('');
            refreshEl = $(refreshTpl.toString()).prependTo(scroller);
            topOffset = refreshEl.height();
            iconEl = refreshEl.find('.refresh-icon');
            labelEl = refreshEl.find('.refresh-label');
        }

        /**
         * 构造iscroll组件，并绑定滑动事件
         * @param opts
         * @private
         */
        var _excuteScroll = function () {
            return new MYSOFT.Ui.Scroll($el[0], {
                topOffset: topOffset,
                bounce: true,
                onScrollMove: function () {
                    if (this.y > 0) {
                        refreshEl.css({ 'height': topOffset+this.y,'margin-top':-this.y,'padding-top':this.y });
                    }
                    if (this.y > opts.minPullHeight && !iconEl.hasClass('doloading')) {
                        iconEl.addClass('doloading');
                        labelEl.html(opts.releaseText);
                        this.minScrollY = 0;
                    } else if (this.y < opts.minPullHeight && iconEl.hasClass('doloading')) {
                        iconEl.removeClass('doloading');
                        labelEl.html(opts.releaseText);
                        this.minScrollY = -topOffset;
                    }
                    else if (this.y <= this.maxScrollY && this.maxScrollY < 0) {
                        opts.bottomcallback.call(this);
                    }
                },
                onScrollEnd: function () {
                    refreshEl.css({ 'height': topOffset, 'margin-top': 0, 'padding-top': 0 });
                    var _this = this;
                    if (iconEl.hasClass('doloading')) {
                        iconEl.removeClass('doloading');
                        labelEl.html(opts.refreshText);
                        setTimeout(function () {//解决在chrome下onRefresh的时候文本无法更改的问题。奇怪的问题！
                            opts.callback.call(_this);
                        }, 1);
                    }
                }
            });
        }

        _init(options);
        var refresh = _excuteScroll();
        return MYSOFT.Ui.Refresh.RefreshCache[jRefreshId] = {
            scroller: refresh.scroller,
            destroy: function () {
                delete MYSOFT.Ui.Refresh.RefreshCache[jRefreshId];
                refresh.scroller.destroy();
                $('.refresh-container').remove();
            }
        };
    }
};

/**
*滚动条缓存
*/
MYSOFT.Ui.DownRefresh.RefreshCache = {};

/**
*滚动条索引
*/
MYSOFT.Ui.DownRefresh.Index = 1;

/**
 * @namespace MYSOFT 上拉/下拉组件
 * @param selector dom选择器
 * @param options 滚动条属性,属性说明请参考MYSOFT.Ui.Scroll.Defaults
 */
MYSOFT.Ui.Refresh = function (selector, options) {
    var $el = $(selector), jRefreshId;
    jRefreshId = $el.attr('_jrefresh_');
    //因上拉下拉可能会使用的比较频繁，故缓存起来节省开销,亦可防止重复绑定
    if (jRefreshId && MYSOFT.Ui.Refresh.RefreshCache[jRefreshId]) {
        return MYSOFT.Ui.Refresh.RefreshCache[jRefreshId];
    } else {
        jRefreshId = '_jrefresh_' + MYSOFT.Ui.Refresh.Index++;
        $el.attr('_jrefresh_', jRefreshId);
        //默认参数
        var defaults = {
            type: 'pullDown',//pullDown|pullUp 默认为pullDown
            minPullHeight: 10,//拉动的像素相对值，超过才会翻转
            pullText: "下拉刷新...",
            releaseText: "松开立即刷新...",
            refreshText: "刷新中...",
            refreshTip: false,//下拉时显示的文本，比如：最后更新时间:2013-....
            onPullIcon: 'arrow-down-2',
            onReleaseIcon: 'icon-reverse',
            onRefreshIcon: 'spinner',
            callback: undefined
        };
        var opts = $.extend(true, defaults, options);
        var iscroll, scroller,refreshEl,iconEl,labelEl,topOffset,isPullDown,
        isPullDown = opts.type === 'pullDown' ? true : false;

        /**
         * 初始化dom节点
         * @param opts
         * @private
         */
        var _init = function () {
            scroller = $el.children()[0];
            var refreshTpl = '<div class="refresh-container"><span class="refresh-icon icon ' + opts.onPullIcon
                + '"></span><span class="refresh-label">'
                + opts.pullText + '</span>'
                + (opts.refreshTip ? '<div class="refresh-tip">' + opts.refreshTip + '</div>' : '') + '</div>';
            if (isPullDown) {
                refreshEl = $(refreshTpl).prependTo(scroller);
            } else {
                refreshEl = $(refreshTpl).appendTo(scroller);
            }
            topOffset = refreshEl.height();
            iconEl = refreshEl.find('.refresh-icon');
            labelEl = refreshEl.find('.refresh-label');
        }

        /**
         * 构造iscroll组件，并绑定滑动事件
         * @param opts
         * @private
         */
        var _excuteScroll = function () {
            return new MYSOFT.Ui.Scroll($el[0], {
                topOffset: isPullDown ? topOffset : 0,
                bounce: true,
                onScrollMove: function () {
                    if (this.y > opts.minPullHeight && isPullDown && !iconEl.hasClass(opts.onReleaseIcon)) {
                        iconEl.addClass(opts.onReleaseIcon);
                        labelEl.html(opts.releaseText);
                        this.minScrollY = 0;
                    } else if (this.y < opts.minPullHeight && isPullDown && iconEl.hasClass(opts.onReleaseIcon)) {
                        iconEl.removeClass(opts.onReleaseIcon);
                        labelEl.html(opts.pullText);
                        this.minScrollY = -topOffset;
                    } else if (this.y < (this.maxScrollY - opts.minPullHeight) && !isPullDown && !iconEl.hasClass(opts.onReleaseIcon)) {
                        iconEl.addClass(opts.onReleaseIcon);
                        labelEl.html(opts.releaseText);
                        this.maxScrollY = this.maxScrollY;
                    } else if (this.y > (this.maxScrollY + opts.minPullHeight) && !isPullDown && iconEl.hasClass(opts.onReleaseIcon)) {
                        iconEl.removeClass(opts.onReleaseIcon);
                        labelEl.html(opts.pullText);
                        this.maxScrollY = topOffset;
                    }
                },
                onScrollEnd: function () {
                    if (iconEl.hasClass(opts.onReleaseIcon)) {
                        iconEl.removeClass(opts.onReleaseIcon).removeClass(opts.onPullIcon).addClass(opts.onRefreshIcon);
                        labelEl.html(opts.refreshText);
                        var _this = this;
                        setTimeout(function () {//解决在chrome下onRefresh的时候文本无法更改的问题。奇怪的问题！
                            opts.callback.call(_this);
                        }, 1);

                    }
                },
                onRefresh: function () {
                    iconEl.removeClass(opts.onRefreshIcon).addClass(opts.onPullIcon);
                    labelEl.html(opts.pullText);
                }
            });
        }

        _init(options);
        var refresh = _excuteScroll();
        return MYSOFT.Ui.Refresh.RefreshCache[jRefreshId] = {
            scroller: refresh.scroller,
            destroy: function () {
                delete MYSOFT.Ui.Refresh.RefreshCache[jRefreshId];
                refresh.scroller.destroy();
                $('.refresh-container').remove();
            }
        };
    }
};

/**
*滚动条缓存
*/
MYSOFT.Ui.Refresh.RefreshCache = {};

/**
*滚动条索引
*/
MYSOFT.Ui.Refresh.Index = 1;