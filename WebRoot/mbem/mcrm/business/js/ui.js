/*����΢�ŷ�����*/
//document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
//    // ͨ���������API�������Ͻǰ�ť
//    WeixinJSBridge.call('hideOptionMenu');
//    WeixinJSBridge.call('hideToolbar'); 
//});


/**===================================================================
 * @��Դ�ƶ�App���
 * @author ����־
 * ===================================================================
 */

/**
 * @description MYSOFT ȫ�ֶ��󣬸���ǰ�˵Ľ�����֯
 * @namespace ȫ�ֵ������ռ�
 */
MYSOFT = window.MYSOFT || {};

/**
 * @description MYSOFT������󣬸���ǰ�˵Ĺ����ؼ�
 * @namespace MYSOFT����������ռ�
 */
MYSOFT.Ui = window.MYSOFT.Ui || {};

/**
 * @description MYSOFTȫ������
 * @namespace MYSOFTȫ�����õ������ռ�
 */
MYSOFT.Config = window.MYSOFT.Config || {};

/**
 * @description MYSOFT����
 * @namespace MYSOFT���ߵ������ռ�
 */
MYSOFT.Util = window.MYSOFT.Util || {};

/**
 * @description MYSOFT Dom�����ռ�,����Dom����
 * @namespace MYSOFT Dom�����ռ�
 */
MYSOFT.Dom = window.MYSOFT.Dom || {};


/**
 * @description MYSOFTȫ������
 */
MYSOFT.Config = {
    /**
    *�������Ӧǰ׺
    */
    vendors: { Webkit: 'webkit', Moz: '', O: 'o', ms: 'MS' }
};


/**
 * @namespace MYSOFT�����࣬��ȡ��ص�ȫ�����ݱ���
 */
MYSOFT.Util = {
    /**
    *��ȡ�ƶ������ǰ׺
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
    *ͨ��ģ�洴��Html
    */
    createHTML: function (template, data) {
        return template.replace(/\$\{(.+?)\}/g, function (all, $1) {
            return data[$1]!==undefined?data[$1]:'';
        });
    },
    /**
    *��Json����ת�����ַ���
    * @param Json����
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
 * @namespace MYSOFT֪ͨ�����
 */
MYSOFT.Ui.Toast = {
    //֪ͨ�������ʱ��
    TOAST_DURATION: 2000,
    //֪ͨ���ģ��
    TEMPLATE: {
        toast: '<a href="#">{value}</a>',
        success: '<a href="#"><i class="icon checkmark-circle"></i>{value}</a>',
        error: '<a href="#"><i class="icon cancel-circle"></i>{value}</a></div>',
        info: '<a href="#"><i class="icon info-2"></i>{value}</a>'
    },
    //Ĭ��֪ͨ����toast|success|error|info
    TOAST_TYPE: 'toast',
    //��ʼ��Dom�����ĵ�
    Init: function () {
        if (!MYSOFT.Ui.Toast.DomObj) {
            //ȫ��ֻ��һ��ʵ��
            $('body').append('<div id="mysoft_toast"></div>');
            MYSOFT.Ui.Toast.DomObj = $('#mysoft_toast');
        }
    },
    /**
    * ��ʾ֪ͨ���
    * @param type ����  toast|success|error|info
    * @param text ��������
    * @param duration ����ʱ�� Ϊ0���Զ��ر�,Ĭ��Ϊ2000ms
    */
    Show: function (type, text, duration) {
        MYSOFT.Ui.Toast.Init();
        if (MYSOFT.Ui.Toast.TIMER) {
            clearTimeout(MYSOFT.Ui.Toast.TIMER);
        }
        MYSOFT.Ui.Toast.TOAST_TYPE = type;
        MYSOFT.Ui.Toast.DomObj.attr('class', MYSOFT.Ui.Toast.TOAST_TYPE).html(MYSOFT.Ui.Toast.TEMPLATE[MYSOFT.Ui.Toast.TOAST_TYPE].replace('{value}', text)).show();
        MYSOFT.Ui.Anim.doAnimation(MYSOFT.Ui.Toast.DomObj, 'scaleIn');
        if (duration !== 0) {//Ϊ0 ���Զ��ر�
            MYSOFT.Ui.Toast.TIMER = setTimeout(MYSOFT.Ui.Toast.Hide, duration || MYSOFT.Ui.Toast.TOAST_DURATION);
        }
    },
    /*
    ����֪ͨ���
    */
    Hide: function () {
        MYSOFT.Ui.Anim.doAnimation(MYSOFT.Ui.Toast.DomObj, 'scaleOut', null, null, function () {
            MYSOFT.Ui.Toast.DomObj.hide();
            MYSOFT.Ui.Toast.DomObj.empty();
        });
    }
};
/**
 * @namespace MYSOFT����Ч�������
 */
MYSOFT.Ui.Anim = {
    //����Ч��
    ANIMATIONNAME: MYSOFT.Util.getBrowserPrefix() + 'animation-name',
    //��������ʱ��
    ANIMATIONDURATION: MYSOFT.Util.getBrowserPrefix() + 'animation-duration',
    //�������ٶ�����
    ANIMATIONTIMING: MYSOFT.Util.getBrowserPrefix() + 'animation-timing-function',
    //�涨Ӧ�ù���Ч����CSS���Ե�����
    TRANSITIONPROPERTY: MYSOFT.Util.getBrowserPrefix() + 'transition-property',
    //��ɹ���Ч����Ҫ���ѵ�ʱ��
    TRANSITIONDURATION: MYSOFT.Util.getBrowserPrefix() + 'transition-duration',
    //�涨����Ч�����ٶ�����
    TRANSITIONTIMING: MYSOFT.Util.getBrowserPrefix() + 'transition-timing-function',
    //֧�ֵ�ת��Ч��
    SUPPORTEDTRANSFORMS: /^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,
    //����ת������
    TRANSITIONTIMINGFUNC: 'ease-in',
    //ת������ʱ��
    TRANSITIONTIME: 250,
    //����ת���¼�
    TRANSITIONENDEVENT: 'webkitTransitionEnd',
    //���������¼�
    ANIMATIONENDEVENT: 'webkitAnimationEnd',
    /*
    ��ȡ������ʽ����
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
    //��ʼ����Ч��
    /**
    ��ʼ����Ч��
    @ele:DomԪ��
    @properties:����Ч������(scaleIn;scaleOut)
    @duration:ʱ����(��λ��,����ΪС��)
    @ease:ת����������
    @callback:�ص�����
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
 * @namespace MYSOFT�õ�ƬЧ�������
 * @param selector domѡ����
 * @param options �õ�Ƭ�������Խṹ
 */
MYSOFT.Ui.Slider = function (selector, options) {
    //this����������
    var me = this;
    //Ĭ������
    var defaults = {
        //ͼƬ�л���ص�����
        afterSlide: function () {
        },
        //ͼƬ�л�ǰ�ص�����
        beforeSlide: function () {
            return true;
        },
        //�����ٶ�
        speed: 200,
        //�Ƿ��Զ�����
        autoPlay: false,
        //�Ƿ���������
        zoom: true,
        //��С���ű���
        minZoomScale: 1,
        //������ű���
        maxZoomScale: 5
    };
    //�ϲ�����
    me.opts = $.extend(true, defaults, options);
    //��ȡ�õ�Ƭ����
    me.$wrapper = $(selector).css('overflow', 'hidden');
    //��ȡ�õ�Ƭ����
    me.$container = me.$wrapper.children().first();
    //��ȡ�õ�Ƭ�б�
    me.$slides = me.$container.children();
    //��ȡ�õ�ƬͼƬ
    me.$imgs = me.$slides.find('img');
    //���ûõ�Ƭ����
    me.slideNum = me.$imgs.length;
    //�Ƿ�ʼ����
    me.gestureStarted = false;
    //�õ�Ƭ���
    me.slideWidth = me.$wrapper[0].offsetWidth;
    //���ûõ�Ƭ�������
    me.$container.css('width', me.slideNum * me.slideWidth);
    //���ûõ�Ƭ�б���ʽ
    me.$slides.css({
        'width': me.slideWidth,
        'float': 'left'
    });
    //Ĭ�ϲ��ŵ�һ��
    me.slide(0, 0);
    //�Ƿ��Զ�����
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
            //����X��Y���ƫ�����ж��û�����ͼ�����һ����������»���
            me.isScrolling = Math.abs(me.deltaX) < Math.abs(e.pageY - me.start.pageY);
        }
        if (!this.isScrolling) {
            event.preventDefault();
            //�ж��Ƿ�ﵽ�˱߽缴��һ���һ������һ����
            var isPastBounds = !me.index && me.deltaX > 0 || me.index == me.slideNum - 1 && me.deltaX < 0;
            if (isPastBounds) {
                return;
            }
        }
    });
    me.$imgs.bind('touchend', function (e) {
        //�ж��Ƿ���ת����һ����Ƭ
        //����ʱ��С��250ms�һ���X��ľ��������Ļ��ȵ�1/3
        var isValidSlide = (Number(new Date()) - me.start.time < 250 && Math.abs(me.deltaX) > 20) && Math.abs(me.deltaX) > me.slideWidth / 3;
        //�ж��Ƿ�ﵽ�˱߽缴��һ���һ������һ����
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
 * @namespace MYSOFT�õ�ƬЧ�����-������ָ����Ƭ
 * @param i ����
 * @param duration ���
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
 * @namespace MYSOFT�õ�ƬЧ�����-�Զ�����
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
 * @namespace MYSOFT�õ�ƬЧ�����-��һ�õ�Ƭ
 */
MYSOFT.Ui.Slider.prototype.prev = function () {
    if (this.index) {
        this.slide(this.index - 1, this.opts.speed);
    }
};
/**
 * @namespace MYSOFT�õ�ƬЧ�����-��һ�õ�Ƭ
 */
MYSOFT.Ui.Slider.prototype.next = function () {
    if (this.index < this.slideNum - 1) {
        this.slide(this.index + 1, this.opts.speed);
    }
};
/**
 * @namespace MYSOFT�õ�ƬЧ�����-�л���ָ���õ�Ƭ
 * @param i ����
 */
MYSOFT.Ui.Slider.prototype.index = function (i) {
    this.slide(i);
};

/**
 * @namespace MYSOFT Tab�˵������
 * @param selector domѡ����
 * @param options �˵�����
 * {
 *      //�˵��б�
 *      items:[{'text':'���Բ˵�1','class':'select'},{'text':'���Բ˵�2'},{'text':'���Բ˵�3'}],
 *      //Ĭ����ʾ�Ĳ˵���
 *      showLength:3,
 *      //�˵�����ص�����
 *      itemClick:function:(index){}
 * }
 */
MYSOFT.Ui.TabMenu = function (selector, options) {
    //������this����
    var me = this;
    //Ĭ������
    var defaults = {
        //�˵��б�[{'text':'���Բ˵�1','class':'select'},{'text':'���Բ˵�2'},{'text':'���Բ˵�3'}]
        items: [],
        //Ĭ����ʾ�Ĳ˵���
        showLength: 3,
        //����,���һ���Dom�����ܿ��
        fixWidth: 52,
        //�˵�����ص�����
        itemClick: function (index) {
        }
    };
    //�ϲ�����
    var opts = $.extend(true, defaults, options);
    //��ȡDom����
    me.$tabemenu = $(selector);
    //��ǰ��ʾ�˵���һ������
    me.firstIndex = 0;
    //��ȡDom����Id,���ڰ󶨲˵�Id
    me.$id = me.$tabemenu.attr('id');
    //��ʱ����
    var contentHTML = [];
    //�˵�ģ��
    var template = MYSOFT.Ui.TabMenu.Template.item;
    //�˵��б�
    var items = opts.items;
    //�˵�������
    me.totalLength = items.length;
    //Ĭ����ʾ�Ĳ˵�����
    me.showLength = opts.showLength;
    //ÿ���˵�ռ�İٷֱ�
    var totalWidth = me.$tabemenu[0].offsetWidth - opts.fixWidth;
    var itemWidth = parseInt(totalWidth / me.showLength);
    var fixWidth = totalWidth - itemWidth * me.showLength;
    //�����˵�Html
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
    //����Tab�˵�Html
    me.$tabemenu.html(MYSOFT.Util.createHTML(MYSOFT.Ui.TabMenu.Template.panel, tData));
    //����ǰ�����˵��¼�
    me.$tabemenu.find('.prev').bind('click', function () {
        me.prev.call(me);
    });
    //����󻬶��˵��¼�
    me.$tabemenu.find('.next').bind('click', function () {
        me.next.call(me);
    });
    //�󶨲˵�����¼�
    me.$tabemenu.find('.childtab li').bind('click', function () {
        me.$tabemenu.find('.childtab li').removeClass('select');
        var $this = $(this);
        $this.addClass('select');
        opts.itemClick && opts.itemClick(parseInt($this.attr('id').split('_')[1]));
    });
    
    //�����һ���˵����ұ߿�����
    $('#' + me.$id + '_' + (me.firstIndex + me.showLength - 1)).addClass('noborder');
};
/**
 * @namespace MYSOFT Tab�˵����-��ǰ�����˵�
 */
MYSOFT.Ui.TabMenu.prototype.prev = function () {
    var me = this;
    if (me.firstIndex == 0) {
        return;
    }
    $('#' + me.$id+'_' + (me.firstIndex - 1)).show();
    me.firstIndex -= 1;
    //����ǰ���ұ߿���ʾ
    $('#' + me.$id + '_' + (me.firstIndex - 2 + me.showLength)).removeClass('noborder');
    //�����һ���˵����ұ߿�����
    $('#' + me.$id + '_' + (me.firstIndex + me.showLength - 1)).addClass('noborder');
};
/**
 * @namespace MYSOFT Tab�˵����-��󻬶��˵�
 */
MYSOFT.Ui.TabMenu.prototype.next = function () {
    var me = this;
    if (me.firstIndex + me.showLength == me.totalLength) {
        return;
    }
    $('#' + me.$id+'_' + me.firstIndex).hide();
    me.firstIndex += 1;
    //����ǰ���ұ߿���ʾ
    $('#' + me.$id + '_' + (me.firstIndex - 2 + me.showLength)).removeClass('noborder');
    //�����һ���˵����ұ߿�����
    $('#' + me.$id + '_' + (me.firstIndex + me.showLength - 1)).addClass('noborder');
};

/**
 * @namespace MYSOFT Tab�˵�ģ��
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
 * @namespace MYSOFT EasyTab�л����
 * @param selector domѡ����
 * @param options �˵�����,��ο�MYSOFT.Ui.EasyTab.Defaults
 */
MYSOFT.Ui.EasyTab = function (selector, options) {
    //������this����
    var me = this;
    //Ĭ�ϲ���
    var defaults = {
        //�˵��б�[{ 'text': '���Բ˵�1', 'class': 'select' }, { 'text': '���Բ˵�2' }, { 'text': '���Բ˵�3' }]
        items: [],
        //�߿���
        fixWidth: 2,
        //�˵�����ص�����
        itemClick: function (index) { }
    };
    //�ϲ�����
    var opts = $.extend(true, defaults, options);
    //��ȡDom����
    me.$tabemenu = $(selector);
    //��ȡDom����Id,���ڰ󶨲˵�Id
    me.$id = me.$tabemenu.attr('id');
    //��ʱ����
    var contentHTML = [];
    //�˵�ģ��
    var template = MYSOFT.Ui.EasyTab.Template;
    //�˵��б�
    var items = opts.items;
    //�˵�������
    me.totalLength = items.length;
    //ÿ���˵�ռ�İٷֱ�
    var totalWidth = me.$tabemenu[0].offsetWidth - opts.fixWidth;
    var itemWidth = parseInt(totalWidth / me.totalLength);
    var fixWidth = totalWidth - itemWidth * me.totalLength;
    //û�д���˵��򷵻�
    if (me.totalLength == 0) {
        return;
    }
    contentHTML.push('<ul class="easytab">');
    //�����˵�Html
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
    //����EasyTab�˵�Html
    me.$tabemenu.html(contentHTML.toString());
    //�󶨲˵�����¼�
    me.$tabemenu.find('li').bind('click', function () {
        me.$tabemenu.find('li').removeClass('select');
        var $this = $(this);
        $this.addClass('select');
        opts.itemClick && opts.itemClick(parseInt($this.attr('id').split('_')[1]));
    });
};
/**
 * @method ���ý���
 * @param index ����
 */
MYSOFT.Ui.EasyTab.prototype.SetFocus = function (index) {
    var me = this;
    me.$tabemenu.find('li').removeClass('select');
    $('#' + me.$id+'_'+index).addClass('select');
}


/**
 * @namespace MYSOFT EasyTab�˵�ģ��
 */
MYSOFT.Ui.EasyTab.Template = '<li id="${id}" style="width:${width}px" class="${class}"><div>${text}</div></li>';

/**
 * @namespace MYSOFT�����Ǳ��������
 * @param selector domѡ����
 * @param options �����Ǳ����������Խṹ�ο�MYSOFT.Ui.Triangle.Defaults
 */
MYSOFT.Ui.Triangle = function (selector, options) {
    //������this����
    var me = this;
    //��ȡDomѡ����
    me.$container = $(selector);
    //��ȡDomѡ����Id
    me.id = me.$container.attr('id');
    //Ĭ�ϲ���
    var defaults = {
        //������
        width: 400,
        //��������Դ,��ʽΪ:[{"name":"����1","y":234}��{"name":"����2","y":234��{"name":"����3","y":234]
        data: null,
        //�����ϱ߾�
        marginTop: 0,
        //�����±߾�
        marginBottom: 0,
        //������߾�
        marginLeft: 0,
        //�����ұ߾�
        marginRight: 0,
        //ÿ�и߶�
        itemHeight: 30,
        //ÿ������߾�
        itemMargin: 1,
        //�߿�
        itemBorder: 12,
        //�ı���ֵ�߾�
        textMargin: 3,
        //��ɫֵ
        colors: ['#33aafd', '#33cc66', '#ff87a3', '#89e347', '#ffd957', '#65c3ff'],
        //�м�϶����ɫ
        marginBgColor: '#fff',
        //�е���¼�
        itemClick: function () {
        }
    };
    //�ϲ����ò���
    var opts = me.opts = $.extend(true, defaults, options);
    //��������Դ��������
    var contentHTML = [];
    //���汨������Դ
    var data = me.opts.data;
    //���ݴ��������Դ���������߶�
    var height = data.length * opts.itemHeight + opts.marginBottom + opts.marginTop + data.length * opts.itemMargin;
    contentHTML.push('<svg width="' + opts.width + '" height="' + height + '">');
    for (var i = 0, len = data.length; i < len; i++) {
        var points = [];
        //�������ǰ�������
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
        //�������ǰ�б���ɫ
        var color = opts.colors[i % opts.colors.length];
        //����ǰ��׷�ӵ����Html
        contentHTML.push('<g><path id="' + me.id+'_svgitem_'+i + '" class="svgitem" d="' + points.toString() + '" fill="' + color + '" /></g>');
        //���㵱ǰ��������ʾ����
        var x = opts.width / 2 - opts.textMargin;
        var x1 = opts.width / 2 + opts.textMargin;
        var y = 20 + i * (opts.itemHeight + opts.itemMargin);
        //����ǰ������׷�ӵ����Html
        contentHTML.push('<g><text id="' + me.id+'_svgitem1_'+i + '" class="svgitem" x="' + x + '" y="' + y + '" fill="white" style="text-anchor: end; font-size:12px;">' + data[i].name + '</text></g>');
        contentHTML.push('<g><text id="' + me.id+'_svgitem2_'+i + '" class="svgitem" x="' + x1 + '" y="' + y + '" fill="white" style="text-anchor: start; font-size:16px;">' + data[i].y + '</text></g>');
        var points1 = [];
        if (i < len - 1) {
            //������֮���϶����
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
            //׷�ӿ�϶�����Html
            contentHTML.push('<path d="' + points1.toString() + '" fill="' + opts.marginBgColor + '" />');
        }
    }
    contentHTML.push('</svg>');
    contentHTML = contentHTML.join('');
    me.$container.html(contentHTML.toString());
    //���е���¼�
    me.$container.find('.svgitem').bind('click', function () {
        var index = $(this).attr('id').split('_')[2];
        opts.itemClick(me.opts.data[parseInt(index)]);
    });
};

/**
 * @namespace MYSOFT�����������
 */
MYSOFT.Ui.Popup = {
    //��ʼ��Dom�����ĵ�
    Init: function () {
        if (!MYSOFT.Ui.Popup.DomObj) {
            //ȫ��ֻ��һ��ʵ��
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
    * @method ��ʾ���ض���
    * @param text �����ı�
    */
    ShowLoading: function (text) {
        MYSOFT.Ui.Popup.Init();
        var markup = MYSOFT.Ui.Popup.Template.loading.replace('{title}', text || '���ڼ���');
        MYSOFT.Ui.Popup.MaskDomObj.show().css({'opacity':'0'});
        MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('loading').html(markup).show();
    },
    /**
    * @method ���ص�����
    */
    HidePopUp: function () {
        MYSOFT.Ui.Popup.MaskDomObj.hide();
        MYSOFT.Ui.Popup.DomObj.hide().empty();
    },
    /**
    * @method ��������ʾ
    * @param title ��ʾ����
    * @param content ��ʾ����
    * @param okCall ���ȷ���ص�����
    */
    ShowAlert: function (title, content, okCall) {
        var markup = MYSOFT.Ui.Popup.Template.alert.replace('{title}', title||'��ʾ��Ϣ').replace('{content}', content).replace('{ok}', '��֪����');
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
    * @method ȷ�Ͽ���ʾ
    * @param title ��ʾ����
    * @param content ��ʾ����
    * @param okCall ȷ���ص�����
    * @param cancelCall ȡ���ص�����
    */
    ShowConfirm: function (title, content, okCall,cancelCall,okText,cancelText) {
        okText=okText||'ȷ ��';
        cancelText=cancelText||'ȡ ��';
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
        okText=okText||'ȷ ��';
        cancelText=cancelText||'ȡ ��';
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
    * @object Popupģ��
    */
    Template: {
        //���ض���
        loading: '<div class="img"></div><p>{title}</p>',
        //��ʾ��
        alert: '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_ok_popup">{ok}</a></div>',
        //ȷ����ʾ��
        confirm: '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_cancel_popup">{cancel}</a><a id="tag_ok_popup">{ok}</a></div>'
    }
};
/**
 * @namespace MYSOFT EasyTab�л����
 * @param selector domѡ����
 * @param options ����������
 * {
 *      //�հ���ʾ��
 *      placeholder:'',
 *      //�����ص�����
 *      onSearch:function:(value){}  
 * }
 */
MYSOFT.Ui.SearchBox = function (selector, options) {
    //������this����
    var me = this;
    //��ȡDom����
    me.$container = $(selector);
    //Ĭ�ϲ���
    var defaults = {
        //�հ���ʾ��
        placeholder: '',
        //�����ص�����
        onSearch: function (value) { }
    };
    //�ϲ�����
    var opts = me.opts = $.extend(true, defaults, options);
    //����Html�ַ���
    me.$container.html(MYSOFT.Util.createHTML(MYSOFT.Ui.SearchBox.Template, opts));
    //��ȡ�����Dom����
    me.$input = me.$container.find('input');
    //��ȡ���Dom����
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
 * @method MYSOFT �����������ֵ
 * @param  val  ֵ
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
 * @method MYSOFT ��ȡ�������ֵ
 */
MYSOFT.Ui.SearchBox.prototype.GetValue = function () {
    var me = this;
    return me.$input.val();
}

/**
 * @namespace MYSOFT �����ı���ģ��
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
 * @namespace MYSOFT ���������
 * @param selector domѡ����
 * @param options ����������,����˵����ο�MYSOFT.Ui.Scroll.Defaults
 */
MYSOFT.Ui.Scroll = function (selector, options) {
    var scroll, scrollId, $el = $(selector),
    scrollId = $el.attr('_jscroll_');
    //�������ʹ��Ƶ��������������ʡ����
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
*����������
*/
MYSOFT.Ui.Scroll.ScrollCache = {};

/**
*����������
*/
MYSOFT.Ui.Scroll.Index = 1;


/**
 * @namespace MYSOFT ����ˢ�����
 * @param selector domѡ����
 * @param options ����������,����˵����ο�MYSOFT.Ui.Scroll.Defaults
 */
MYSOFT.Ui.DownRefresh = function (selector, options) {
    var $el = $(selector), jRefreshId;
    jRefreshId = $el.attr('_jrefresh_');
    //�������������ܻ�ʹ�õıȽ�Ƶ�����ʻ���������ʡ����,��ɷ�ֹ�ظ���
    if (jRefreshId && MYSOFT.Ui.DownRefresh.RefreshCache[jRefreshId]) {
        return MYSOFT.Ui.DownRefresh.RefreshCache[jRefreshId];
    } else {
        jRefreshId = '_jrefresh_' + MYSOFT.Ui.DownRefresh.Index++;
        $el.attr('_jrefresh_', jRefreshId);
        //Ĭ�ϲ���
        var defaults = {
            minPullHeight: 10,//�������������ֵ�������Żᷭת
            releaseText: "Ready Loading..",
            refreshText: "Loding...",
            callback: undefined,
            bottomcallback:undefined
        };
        var opts = $.extend(true, defaults, options);
        var iscroll, scroller, refreshEl, iconEl, labelEl, topOffset, refreshEl1;

        /**
         * ��ʼ��dom�ڵ�
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
         * ����iscroll��������󶨻����¼�
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
                        setTimeout(function () {//�����chrome��onRefresh��ʱ���ı��޷����ĵ����⡣��ֵ����⣡
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
*����������
*/
MYSOFT.Ui.DownRefresh.RefreshCache = {};

/**
*����������
*/
MYSOFT.Ui.DownRefresh.Index = 1;

/**
 * @namespace MYSOFT ����/�������
 * @param selector domѡ����
 * @param options ����������,����˵����ο�MYSOFT.Ui.Scroll.Defaults
 */
MYSOFT.Ui.Refresh = function (selector, options) {
    var $el = $(selector), jRefreshId;
    jRefreshId = $el.attr('_jrefresh_');
    //�������������ܻ�ʹ�õıȽ�Ƶ�����ʻ���������ʡ����,��ɷ�ֹ�ظ���
    if (jRefreshId && MYSOFT.Ui.Refresh.RefreshCache[jRefreshId]) {
        return MYSOFT.Ui.Refresh.RefreshCache[jRefreshId];
    } else {
        jRefreshId = '_jrefresh_' + MYSOFT.Ui.Refresh.Index++;
        $el.attr('_jrefresh_', jRefreshId);
        //Ĭ�ϲ���
        var defaults = {
            type: 'pullDown',//pullDown|pullUp Ĭ��ΪpullDown
            minPullHeight: 10,//�������������ֵ�������Żᷭת
            pullText: "����ˢ��...",
            releaseText: "�ɿ�����ˢ��...",
            refreshText: "ˢ����...",
            refreshTip: false,//����ʱ��ʾ���ı������磺������ʱ��:2013-....
            onPullIcon: 'arrow-down-2',
            onReleaseIcon: 'icon-reverse',
            onRefreshIcon: 'spinner',
            callback: undefined
        };
        var opts = $.extend(true, defaults, options);
        var iscroll, scroller,refreshEl,iconEl,labelEl,topOffset,isPullDown,
        isPullDown = opts.type === 'pullDown' ? true : false;

        /**
         * ��ʼ��dom�ڵ�
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
         * ����iscroll��������󶨻����¼�
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
                        setTimeout(function () {//�����chrome��onRefresh��ʱ���ı��޷����ĵ����⡣��ֵ����⣡
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
*����������
*/
MYSOFT.Ui.Refresh.RefreshCache = {};

/**
*����������
*/
MYSOFT.Ui.Refresh.Index = 1;