
define(function (require, exports, module) {
    'use strict';
    // 通过 require 引入依赖
    var $ = require('jquery');
    require('template');
    require('nprogress');
    require('bgiframe');
    //require('menu-aim');
    //require('icon');
    require('menu');
    require('util');


    //var desktop = require('desktop');

    NProgress.start();
    // 通过 exports 对外提供接口
    //exports.doSomething = 

    // 或者通过 module.exports 提供整个接口
    //module.exports = ...


    $.ajaxSetup({
        async: true
    });
 
    //系统分组图标
    var SysIcons = {
        "31": "xt_zsjgl_01",
        "04": "xt_jczcxt_01",
        "03": "xt_ysgl_01",
        "02": "xt_xmyygl_01",
        "01": "xt_khgxgl_01",
        "10": "xt_xtbgpt_01",
        "00": "xt_xtgl_01"
    };


    /*#region 切换系统*/
    var SysNav = (function () {
        var SYSNAV = {},
            SYSNAVGROUP = {},
        templateId = "sysTemplate",
        $container = $("#system-menu"),
        $parent = $("#dd_sysNav"),
        $link = $parent.find(".dd-link"),
        $text = $link.find("span"),
        codeName = "Application",
        arrtName = "appcode",
        currentNav = null,
        companycount = 0,
        _this = this;
        //获取当前选择项json数据
        var _setNav = function (code) {
            currentNav = SYSNAV[code];
            if (currentNav) {
                $text.text(currentNav.text);
            }
            $container.fadeOut();
            return currentNav;
        }
        //展示数据
        var _renderData = function (nav_json) {
            var result = [],
                childName = "sys",
                navArray = [];
            if (nav_json) {
                if ($.isArray(nav_json)) {
                    navArray = nav_json;
                } else {
                    navArray.push(nav_json);
                }
            }
            navArray.forEach(function (parent) {
                var item = { text: parent.text, allow: parent.allow },
                     childs = parent[childName];
                if ($.isArray(childs)) {
                    item.childs = childs;
                } else {
                    item.childs = [];
                    item.childs.push(childs);
                }
                item.childs.forEach(function (child) {
                    SYSNAV[child[codeName]] = child;
                });
                item.id = SYSNAVGROUP[item.text];
                item.iconClass = SysIcons[item.id];
                result.push(item);
            });
            $container.empty();
            var renderHtml = template.render(templateId, { sys: result });
            $container.html(renderHtml);

            $container.bgiframe({ height: $container.outerHeight(), width: $container.outerWidth() });
            PageLoader.itemDone("SysNav");
        }
        //绑定事件 
        var _bindEvent = function () {
            $container.on("click", "a", function () {
                /* data-example:
                Application: "0204"
                IsSelectCompany: "True"
                SelectBULevel: "2"
                SitePath: ""
                allow: "1"
                littleimg: "/cbgl/images/MaterialListcb_16_wihte.gif"
                text: "采购招投标管理"
                type: "application"
                url: "FuncNav.aspx"
                */
                var $this = $(this),
                data = SYSNAV[$this.data(arrtName)],
                appCode = data.Application,
                IsSelectCompany = data.IsSelectCompany,
                SelectBULevel = data.SelectBULevel,
                selectCompany = (IsSelectCompany.toLowerCase() == "true" && companycount == 1),
                checkCookie = ((GetCookie("mycrm_company") == null || GetCookie("mycrm_company") == "" || GetCookie("mycrm_company") == "undefined") && SelectBULevel != "0"),
                notEndCompany = (GetCookie("mycrm_isendcompany") != "1" && SelectBULevel == "1");
                // 【MysoftERP_移动产品管理后台】 打开新窗口
                if (appCode === "3007") {
                    $.get("/Frame/Common/Index.ashx",
                             { action: "getFuncNav", appCode: appCode, funcCode: "", tmp: new Date().getTime() },
                              function (nav_json) {
                                  if (!nav_json.success) { alert(nav_json.message); return; }
                                  var funcData = nav_json.data.sys.func[0],
                                  func = $.isArray(funcData.func) ? funcData.func[0] : funcData.func;
                                  window.open(func.SitePath, func.text); //此处需要信任站点
                              });
                    return false;
                }
				// 【MysoftERP_采招管理后台】 打开新窗口
                if (appCode === "0220") {
                    $.get("/Frame/Common/Index.ashx",
                             { action: "getFuncNav", appCode: appCode, funcCode: "", tmp: new Date().getTime() },
                              function (nav_json) {
                                  if (!nav_json.success) { alert(nav_json.message); return; }
                                  var funcData = nav_json.data.sys.func[0],
                                  func = $.isArray(funcData.func) ? funcData.func[0] : funcData.func;
                                  window.open(func.SitePath, func.text); //此处需要信任站点
                              });
                    return false;
                }

                var bExecSelectBu = false;
                if (selectCompany && (checkCookie || notEndCompany)) {
                    var returnValue = _selectCurrentBU("SelectBULevel=" + myEscape(SelectBULevel));
                    if (!returnValue) {
                        return;
                    } else {
                        bExecSelectBu = true;
                    }
                }

                _setNav(data.Application);

                FuncNav.bindFunc(data.Application);

                Page.backHome();
                //选择公司
                if (bExecSelectBu) {
                    setTimeout(function () {
                        Page.reload();
                        ComChoose.reload(true);
                    }, 500);
                }
            });
        }

        var _getDataCallBack = function (data) {
            companycount = data.nav.companycount;
            _renderData(data.nav.sys);
            var appCode = mysoft.userData.getItem("appCode");
            if (appCode) {
                FuncNav.bindFunc(appCode);
            } else {
                FuncNav.bindFunc();
            }
        }
        //对外接口
        var api = {
            //初始化
            init: function () {
                //初始化导航分组对象
                var navGroup = JSON.parse(UserInfo.SysNavGroup);
                navGroup.forEach(function (item) {
                    SYSNAVGROUP[item.name] = item.id;
                });
                //获取系统导航
                if (UserInfo.SysNav) {
                    var data = JSON.parse(UserInfo.SysNav);
                    _getDataCallBack(data);
                } else {
                    //兼容方案
                    $.get("/SysNav.aspx",
                 { tmp: new Date().getTime() },
                  function (navHtml) {
                      $.get("/Frame/Common/Index.ashx",
                            { action: "getSysNav", tmp: new Date().getTime() },
                            function (nav_json) {
                                if (!nav_json.success) { alert(nav_json.message); return; }
                                _getDataCallBack(nav_json.data);
                            });
                  });
                }
                _bindEvent();
            },
            //获取当前导航对象
            getNav: function () {
                return currentNav;
            },
            //设置当前导航
            setNav: function (appCode) {
                _setNav(appCode);

            }
        }
        return api;
    }());
    /*#endregion*/

    /*#region 功能菜单*/
    //功能菜单
    var FuncNav = (function () {
        var FUNCNAV = {},
            APPCODE = "",
        templateId = "funcTemplate",
        $container = $("#function-menu"),
        $funcMore = $("#func-more"),

        //$pager = $("#func-pager"),
        //$pagePre = $pager.find(".page-pre"),
        //$pageNext = $pager.find(".page-next"),
        $currentCate = null,
        //pageIndex = 1,
        //pageSize = 1,
        codeName = "FunctionCode",
        arrtName = "funccode",
        funcData = null,
        iconExtReg = /.gif|.jpg|.png/i;

        var pagerTime = null;
        var _setPager = function (time) {
            time = time || 200;
            if (pagerTime) { clearTimeout(pagerTime); }
            pagerTime = setTimeout(function () {
                _pager();

            }, time);
        }

        //展示数据
        var _renderData = function (nav_json) {
            var __isEndCompany = GetCookie("mycrm_isendcompany");

            /*
            data-example:
            DisabledScope: ""
            FunctionCode: "00000101"
            SitePath: ""
            UseScope: "0"
            allow: "0"
            bigimg: ""
            iconClass: "pzgl_01"
            littleimg: "/images/AppSyestem_16_white.gif"
            text: "应用系统设置"
            url: "/Security/Application.aspx"
            */
            var result = [],
                childName = "func",
                navArray = [];
            if (!nav_json) { nav_json = []; }
            if ($.isArray(nav_json)) {
                navArray = nav_json;
            } else if (nav_json) {
                navArray.push(nav_json);
            }
            navArray.forEach(function (parent, i) {
                var item = { text: parent.text, allow: parent.allow },
                   childs = parent[childName];
                if ($.isArray(childs)) {
                    item.childs = childs;
                } else {
                    item.childs = [];
                    item.childs.push(childs);
                }

                item.childs.forEach(function (child) {
                    var childCode = child[codeName],
                        UseScope = child.UseScope,
                        iconUrl = child.bigimg;
                    if (iconExtReg.test(child.bigimg)) {
                        child.iconClass = "img-icon";

                        child.imgIcon = iconUrl;
                    } else {

                        child.iconClass = iconUrl.indexOf("/") != -1 ?
                            iconUrl.substr(iconUrl.lastIndexOf("/") + 1) : iconUrl;
                    }

                    if ((__isEndCompany == "1" && UseScope == "1") || (__isEndCompany == "0" && UseScope == "2")) {
                        child.disabled = "disabled";
                    }
                    child.parentText = item.text;
                    FUNCNAV[childCode] = child;
                });

                result.push(item);
            });

            $container.empty();
            var renderHtml = template.render(templateId, { func: result });
            $container.html(renderHtml);
            // $container.show();
            //  return;
            $container.show().css("visibility", "hidden");
            $funcMore.hide();
            _setPager(0);
            //$container.hide().fadeIn();

            PageLoader.itemDone("FuncNav");
        }

        //计算分页
        var _pager = function () {
            var leftWidth = 260,
                rightWidth = 240,
                rightPadding = 60,
                menuMaxWidth = $(document).width() - leftWidth - rightWidth - rightPadding,
                moreTop = 60,
                totalWidth = 0,
                    isHide = false;

            $funcMore.hide();
            var $items = $container.find(">li:not(.func-home)");
            $items.each(function (i, el) {
                var $item = $(this),
                    itemWidth = $item.data("width") || $item.outerWidth();
                if (!$item.data("width")) {
                    $item.data("width", itemWidth);
                }
                totalWidth += itemWidth;

                if (totalWidth > menuMaxWidth) {

                    //  totalWidth = $item.outerWidth();
                    //pageSize += 1;
                    isHide = true;
                } else {
                    isHide = false;
                }

                $item.removeClass("more-group-wrap more-group-last more-group-first");
                if (isHide) {
                    $item.css({ top: moreTop })
                    .addClass("more-group-wrap")
                    .data("ismore", true)
                    .css("visibility", "hidden");
                    if (i == $items.length - 1) {
                        $item.addClass("more-group-last");
                    }
                    if (moreTop == 60) {
                        $item.addClass("more-group-first");
                    }
                    moreTop += 40;
                } else {
                    $item.css({ top: 0 })
                        .data("ismore", false)
                        .css("visibility", "visible");
                }
            });
            if (isHide) {
                $funcMore.css("display", "inline-block");
            }

            $container.css("visibility", "visible");
        }
        //绑定数据
        var _bindEvent = function () {


            //绑定模块点击
            $container.on("click", ".func-items-wrap a", function () {
                var $this = $(this),
                    funcCode = $this.data(arrtName),
                    func = FUNCNAV[funcCode];
                if (func.disabled) { return; }
                if (func.SitePath.lastIndexOf("/") == func.SitePath.length - 1) {
                    //去除站点路径结尾的"/"
                    func.SitePath = func.SitePath.substring(0, func.SitePath.length - 1);
                }
                if (func.url.indexOf("http://") == -1 && func.url.indexOf("/") != 0) {
                    //补齐绝对路径
                    func.url = "/" + func.url;
                }
                var url = (func.SitePath == "" || func.text == "运行设置") ? func.url :( func.SitePath + func.url);
                Page.setPage(url, func.parentText, func.text, null, func.SitePath);
                $currentCate = $this.parents(".func-items-wrap:first");
                return false;
            });

            //三角区域计算：https://rawgit.com/kamens/jQuery-menu-aim/master/example/example.html#


            //鼠标hover展开子菜单
            $container.on({
                mouseenter: function () {
                    var $this = $(this),
                        $parent = $(".func-group", $this),
                        $items = $(".func-items-wrap", $this),
                        isMore = $this.data("ismore"),
                        activeClass = isMore ? "more-active" : "group-active";

                    if ($items.length == 0) { return; }
                    $items.bgiframe({ width: $items.outerWidth(), height: $items.outerHeight() });
                    $parent.addClass(activeClass);
                    $items.show();

                    if (isMore) {
                        var _pos = $this.position(),
                            $moreArrow = $(".more-items-arrow", $this);
                        $moreArrow.css("visibility", "visible");
                        $items.css({ left: "", width: "" });
                    } else {

                        //菜单展开时超出屏幕宽度时定位调整
                        var itemsOffset = $items.offset(),
                            itemsWidth = $items.outerWidth(),
                            itemsLeft = itemsOffset.left + itemsWidth,
                            windowWidth = $(window).width();
                        if (itemsLeft > windowWidth) {
                            $items.css({ left: windowWidth - itemsLeft - 20 });
                        } else if (windowWidth - itemsLeft - 22 >= 0) {
                            //未超出屏幕宽度时重置left
                            $items.css({ left: "" });
                        }
                        //根据子菜单数量调整宽度
                        var itemsCount = $items.find("li").length;
                        var minWidth = $parent.outerWidth(),
                            itemsWidth = 88 * itemsCount;
                        if (itemsCount < 5) {
                            $items.width(Math.max(minWidth, itemsWidth));
                        }
                    }
                },
                mouseleave: function () {
                    var $this = $(this),
                        $parent = $(".func-group", $this),
                        $items = $(".func-items-wrap", $this),
                           isMore = $this.data("ismore"),
                            activeClass = isMore ? "more-active" : "group-active";
                    if ($items.length == 0) { return; }

                    $parent.removeClass(activeClass);
                    $items.hide();
                    if (isMore) {
                        var $moreArrow = $(".more-items-arrow", $this);
                        $moreArrow.css("visibility", "hidden");
                    }
                }
            }, ">li");
            //“更多”显示隐藏时间缓冲
            var showMoreTime = null;

            var hideMore = function () {
                if (showMoreTime) { clearTimeout(showMoreTime); }
                showMoreTime = setTimeout(function () {
                    $funcMore.removeClass("group-active");
                    $container.find(">li.more-group-wrap").css("visibility", "hidden");
                }, 100);
            }
            var showMore = function () {

                if (showMoreTime) { clearTimeout(showMoreTime); }

            }
            $funcMore.hover(function () {
                var $this = $(this);
                $this.addClass("group-active");
                $container.find(">li.more-group-wrap").css("visibility", "visible");
                showMore();
            }, function () {
                var $this = $(this);
                hideMore();
            });
            $container.on({
                mouseenter: function () {
                    showMore();
                },
                mouseleave: function () {
                    hideMore();
                }
            }, ".more-group-wrap");

            //菜单项鼠标移动事件绑定
            $container.on({
                mouseenter: function () {
                    var $this = $(this),
                        $parent = $this.parents(".func-group-wrap"),
                        isMore = $parent.data("ismore"),
                        hoverClass = isMore ? "more-hover" : "func-hover";
                    $this.addClass(hoverClass);
                },
                mouseleave: function () {
                    var $this = $(this),
                        $parent = $this.parents(".func-group-wrap"),
                        isMore = $parent.data("ismore"),
                        hoverClass = isMore ? "more-hover" : "func-hover";
                    $this.removeClass(hoverClass);
                    var $icon = $this.find(".icon"),
                        iconClass = $icon.attr("class");
                    setTimeout(function () {
                        //修复IE8下removeClass后图标颜色无法还原的问题
                        $icon.css("visibility", "hidden")
                        .css("visibility", "visible");
                    }, 0);

                }
            }, ".func-items-wrap>li>a");

        }

        var api = {
            //初始化
            init: function () {
                _bindEvent();

            },
            //根据系统id绑定导航菜单
            bindFunc: function (appCode, funcCode, callback) {

                funcCode = funcCode || "";
                appCode = appCode || "";
                $.get("/Frame/Common/Index.ashx",
                    { action: "getFuncNav", appCode: appCode, funcCode: funcCode, tmp: new Date().getTime() },
                     function (nav_json) {
                         if (!nav_json.success) { alert(nav_json.message); return; }

                         funcData = nav_json.data.sys.func;
                         _renderData(nav_json.data.sys.func);

                         if (typeof callback == "function") {
                             var currentFunc = FUNCNAV[funcCode];
                             if (!currentFunc) {
                                 alert("对不起，您没有该模块的权限");
                                 return;
                             }
                             appCode = currentFunc.FunctionCode.substr(0, 4);
                             callback(currentFunc);
                         }
                         APPCODE = appCode;
                         mysoft.userData.setItem("appCode", appCode);
                         SysNav.setNav(appCode);
                     });

                //  $.get("/FuncNav.aspx",
                //{ AppCode: appCode, FuncCode: funcCode, tmpl: new Date().getTime() },
                //function() {
                //    //TODO 这里需要检查用户是否有对应模块的权限

                //});
            },
            setPager: function () {
                _setPager();
            },
            openCurrentCate: function () {
                if ($currentCate && $currentCate.length > 0) {
                    $currentCate.prev().addClass("group-active");
                    $currentCate.show();

                }
            },
            reload: function () {
                api.bindFunc(APPCODE);

            }
        }
        var publicApi = {
            NavFunc: function (FuncCode, sPara) {
                api.bindFunc("", FuncCode, function (func) {
                    //Page.setPage()
                    /*{"SitePath":"",
                    "text":"我办理的流程",
                    "bigimg":"",
                    "littleimg":"/wbldlc_01",
                    "url":"/MyWorkflow/WF_ProcessHandle.aspx"
                    ,"FunctionCode":"10010102",
                    "UseScope":"0",
                    "DisabledScope":"",
                    "allow":"0","iconClass":"pzgl_01","parentText":"流程管理"}
                    */
                    if (func.disabled) { return; }
                    if (func.SitePath.lastIndexOf("/") == func.SitePath.length - 1) {
                        //去除站点路径结尾的"/"
                        func.SitePath = func.SitePath.substring(0, func.SitePath.length - 1);
                    }
                    if (func.url.indexOf("http://") == -1 && func.url.indexOf("/") != 0) {
                        //补齐绝对路径
                        func.url = "/" + func.url;
                    }
                    var url = func.SitePath == "" ? func.url : func.SitePath + func.url;
                    if (sPara) {
                        var split = "&";
                        if (url.indexOf("?") == -1 && sPara.indexOf("?") == -1) {
                            split = "?";
                        } else if (sPara.indexOf("&") != 0) {
                            split = "&";
                        }
                        else {
                            split = "";
                        }
                        url += split + sPara;
                    }
                    Page.setPage(url, func.parentText, func.text);
                });
            }
        };
        window.FuncNav = {};
        $.extend(window.FuncNav, publicApi);
        return api;
    }());
    /*#endregion*/

    /*#region 切换公司*/
    //切换公司
    var ComChoose = (function () {
        var COMS = {},
            CurrentCom = {},
        templateId = "comTemplate",
        $wrap = $("#com-choose-wrap"),
        $container = $("#com-choose"),
        $comName = $wrap.find(".bind-UserInfo"),
        codeName = "Application",
        arrtName = "appcode",
        collapseClass = "icon-gssq_01",
        expandClass = "icon-gszk_01",
        defaultClass = "icon-gs_01",
        _this = this;
        //展示数据 
        var _renderData = function (com_json) {
            com_json = com_json || [];
            if (com_json.length <= 1) {

                $container.hide();
                //$wrap.removeClass("dropdown");
                $wrap.find("a").css("cursor", "default");
                //img-com dd-arrow
                $wrap.find(".dd-arrow").hide();
                return;
            } else {
                $wrap.find(".dd-arrow").show();
                $wrap.find("a").css("cursor", "pointer");
            }
            $container.empty();
            var renderHtml = template.render(templateId, { coms: com_json });
            $container.html(renderHtml);
            $container.bgiframe({ height: 400, width: $container.outerWidth() });
        }
        var isReload = false;

        var _setComText = function (comObj) {
            comObj = comObj || COMS[GetCookie("mycrm_company")],
                comName = comObj ? comObj.name : "";
            $comName.text(comName.overflow(18, ".."));
            $comName.attr("title", comName);
        }
        var _reload = function (pageReload) {

            isReload = false;
            FuncNav.reload();
            if (pageReload) {
                Page.reload();
            }
            _setComText();
        }
        //绑定事件
        var _bindEvent = function () {
            //双击切换公司
            $container.on("click", ".com-item", function () {
                var $this = $(this),
                    guid = $this.data("id"),
                    data = COMS[guid];
                $.get("/Frame/Common/Index.ashx",
                { action: "choosecompany", BUGUID: data.guid, BUName: data.name, IsEndCompany: data.isendcompany, tmp: new Date().getTime() },
                function (data) {
                    if (data && data.success) {
                        FuncNav.reload();
                        Page.reload();
                        _setComText();
                        $container.hide();
                    }
                });
                return false;
            });


            //展开收起
            $container.on("click", [".", collapseClass, ",.", expandClass].join(""), function () {
                var $this = $(this),
                    $link = $this.parent(),
                    guid = $link.data("id"),
                    data = COMS[guid],
                    isExpand = $this.hasClass(expandClass) ? true : false,
                    $parent = $link.parent();
                var $next = $parent.next(),
                    levelClass = ".level" + (data.level + 1);
                var siblingClass = ".level" + (data.level);
                var currClassLevel = data.level;
                while ($next && $next.find(".com-item").length && !$next.find(siblingClass).length && currClassLevel >= data.level) {
                    var $nextDom = $next.children().children()

                    if (isExpand) {
                        if ($next.find(levelClass).length) {
                            $next.show();
                        }
                    } else {
                        $next.hide();
                        if (!$nextDom.hasClass("icon-gs_01")) {
                            $nextDom.removeClass(collapseClass);
                            $nextDom.addClass(expandClass);
                        }
                    }
                    $next = $next.next();

                    if ($next.length) {
                        var currClass = $next[0].children[0].className;
                        currClassLevel = currClass.substr(currClass.indexOf("level") + 5, 1);
                    }
                }
                $this.remove();
                if (isExpand) {
                    $link.prepend('<span class="icon ' + collapseClass + '"></span>');
                } else {
                    $link.prepend('<span class="icon ' + expandClass + '"></span>');
                }

                return false;
            });
        }
        var api = {
            init: function () {
                //var testJson = [{ "code": "0001", "guid": "11b11db4-e907-4f1f-8835-b9daab6e1f23", "name": "明源集团", "isendcompany": "0", "level": 1, "status": "collapse" }, { "code": "0001.0001", "guid": "428f614e-cd29-4efa-af2b-84c6350668f2", "name": "北京公司", "isendcompany": "1", "level": 2, "status": "" }, { "code": "0001.0008", "guid": "24f41971-3cdf-4e82-8860-90195ab75091", "name": "深圳明源", "isendcompany": "1", "level": 2, "status": "" }, { "code": "0001.0009", "guid": "22614fc9-9a09-4462-bf5c-90e5de67ff99", "name": "上海明源", "isendcompany": "1", "level": 2, "status": "" }];

                //return;
                var companys = JSON.parse(UserInfo.Companys);
                var result = [];
                var isExistsZB = false;
                if (companys[0].Code) {
                    isExistsZB = companys[0].Code.split('.').length == 1 ? true : false;
                }
                companys.forEach(function (com) {
                    var param = {};
                    param.code = com.Code
                    param.guid = com.GUID;
                    param.name = com.Name;
                    param.isendcompany = com.IsEndCompany;
                    param.level = param.code.split('.').length; //当前级别，从1开始
                    //param.el = this;
                    var status = expandClass;
                    if (isExistsZB) {
                        if (param.level == 1) { status = collapseClass; }
                    } else {
                        status = collapseClass;
                    }
                    param.status = param.isendcompany == 0 ? status : defaultClass;
                    result.push(param);
                    COMS[param.guid] = param;
                });

                _renderData(result);

                PageLoader.itemDone("ComChoose");

                _bindEvent();

            },
            reload: function () {
                _reload();
            }
        }
        var publicApi = {
            Reload: function () {
                _reload();
            }
        }
        window.ComChoose = publicApi;
        return api;
    }());
    /*#endregion*/

    /*#region 用户信息*/
    //用户信息
    var User = (function () {

        var bindUserInfo = function () {
            //绑定信息显示
            $(".bind-UserInfo").each(function () {
                var $this = $(this),
                    _attr = $this.data("attr"),
                    _bind = $this.data("bind"),
                    _val = "";

                if (!UserInfo) { return; }
                _val = UserInfo[_bind];
                if (_bind == "DebugModel") {
                    if (_val == "true") {
                        $this.show();
                    } else {
                        $this.hide();
                    }
                } else {
                    if (!_val) { return; }
                    _attr = _attr || "text";
                    if (_attr == "html") {
                        $this.html(_val);
                    } else if (_attr == "text") {
                        var overflow = $this.data("overflow");
                        if (overflow) {
                            $this.attr("title", _val);
                            _val = _val.overflow(parseInt(overflow), "..");
                        }
                        $this.text(_val);
                    } else {
                        $this.attr(_attr, _val);
                    }
                    $this.show();
                }

            });

        }

        var api = {
            init: function () {
                bindUserInfo();
                DelCookie("mycrm_company");
                DelCookie("mycrm_isendcompany");
                //PageLoader.callbacks.push(function () {
                //    SetCookie("mycrm_company", UserInfo.BUGUID, 1);
                //    SetCookie("mycrm_isendcompany", UserInfo.IsEndCompany, 1);
                //});

            }
        }
        return api;
    }());

    /*#endregion*/

    /*#region 页面*/
    //页面
    var Page = (function () {
        var $container = $("#page-wrap"),
            pageId = "stage1",
            $page = $("#" + pageId),
            $page_home = $("#page-home"),
            $pageNotCenter = $("#page-notification"),
            $pageWebpart = $("#page-webpart"),
            $body = $("body"),
            $header = $("#header"),
            $crumbWrap = $("#crumb-wrap"),
            $desktopNav = $("#desktop-nav"),
            $crumb = $("#crumb"),
        // $crumb_sys = $crumb.find(".crumb-sys"),
            $crumb_cate = $crumb.find(".crumb-cate"),
            $crumb_func = $crumb.find(".crumb-func"),
            $loader = $("#page-loader"),
            page_callback = null,
            home_url = "/Frame/Desktop/Desktop.aspx",
            currentUrl = "",
            unloadTime = null;

        //设置容器高度
        var setTime = null;
        var setContainerSize = function () {
            if (setTime) { clearTimeout(setTime); }
            setTime = setTimeout(function () {
                var containerHeight = $body.height() - $header.outerHeight();
                if (!$crumb.is(":hidden")) {
                    containerHeight -= $crumb.outerHeight();
                }
                $container.height(containerHeight);
            }, 200);
        }
        //面包屑导航
        var setCrumb = function (cateName, funcName) {

            var cateShow = true,
                funcShow = true;
            if (!cateName) { cateShow = false; }
            if (!funcName) { funcShow = false; }
            //  $crumb_sys.text(sysName);

            if (cateShow) {
                $crumb_cate.text(cateName).show();
            } else {
                $crumb_cate.hide();
            }
            if (funcName) {
                $crumb_func.text(funcName).show();
            } else {
                $crumb_func.hide();
            }

            $desktopNav.hide();
            $crumbWrap.show();

        }
        //加载进度
        var loader = {
            begin: function () {
                $loader.show();
            },
            end: function () {
                $loader.fadeOut();
            }
        }
        var _pageDisplayReset = function ($curPage) {
            loader.end();
            var $pages = [$pageWebpart, $page_home, $pageNotCenter, $page];
            $pages.forEach(function ($page) {
                if ($page[0] != $curPage[0]) {
                    $page.hide();
                } else {
                    $page.show();
                }
            });
        }
        //绑定事件
        var _bindEvent = function () {

            //设置页面尺寸
            $(window).resize(function () {
                setContainerSize();
                FuncNav.setPager();
            });
            $page.load(function () {
                _pageDisplayReset($page);
              
                $crumb.show();
                //Notification.showLayer();
                setContainerSize();
                if (typeof page_callback == "function") {
                    page_callback.call();
                }

                try {
                    var iframe = $page[0];
                    iframe.contentWindow.focus();
                } catch (e) {
                    //跨域引起的报错
                    logException(e);
                }

            });
            $page_home.load(function () {
                _pageDisplayReset($page_home);
                //$page_home.show();
                // $crumb.hide();
                //Notification.showLayer();
                setContainerSize();
            });

            $pageNotCenter.load(function () {
                _pageDisplayReset($pageNotCenter);
                
                // $crumb.hide();
                //Notification.hideLayer();
                setContainerSize();
            });
            $crumb_func.click(function () {
                api.reload();
            });

        }
        //页面卸载事件
        var _pageUnload = function (callback) {
            var $frm;
            try {
                //跨域站点无法访问文档对象问题
                var _window = $page[0].contentWindow.window;
                $frm = $($page[0].contentWindow);
            } catch (e) {
                if (typeof callback == "function") {
                    callback();
                }
                logException(e);
                return;
            }
            if ($frm.data("existsUnload")) { return; }
            $frm.unload(function () {
                if (unloadTime) { clearTimeout(unloadTime); }
                if (typeof callback == "function") {
                    callback();
                }
            }).data("existsUnload", true);
        }
        //返回首页
        var _backHome = function () {

            $desktopNav.show();
            $crumbWrap.hide();

            page_callback = function () {
                DeskTopNav.backHome();
            }
            $page.attr("src", "about:blank");
            $page.one("load", function () {
                $page.hide();
            });

        }
        //url编码
        var _urlEncode = function (url, sitePath) {

            var urlParamEscape = function (param) {
                if (sitePath != UserInfo.SitePath && UserInfo.ExternalSitesEncode != "true") {
                    //兼容老DSS站点使用需要特殊编码
                    return escape(param);
                }
                return myEscape(param);
            }

            var sCurrURL = url.substring(0, url.indexOf("?")) || url;
            var arrURLParams = url.substring(0, url.indexOf("?")) == "" ? [] : url.substr(url.indexOf("?") + 1).split("&");

            for (var i = 0; i < arrURLParams.length; i++) {
                var arrParam = arrURLParams[i].split("=");
                if (0 == i) {
                    sCurrURL += "?" + arrParam[0] + "=" + urlParamEscape(arrParam[1]); //myEscape(arrParam[1]);
                } else {
                    sCurrURL += "&" + arrParam[0] + "=" + urlParamEscape(arrParam[1]); //myEscape(arrParam[1]);
                }
            }
            return sCurrURL;
        }

        //绑定页面卸载前事件
        var _bindBeforeunload = function (url) {
            /*
            场景说明:给页面中的iframe注入onbeforeunload事件,事件仅处理闭加载进度条
            */
            var oldUrl,
                currentUrl;
            try {
                //跨域站点无法访问文档对象问题
                oldUrl = $page[0].contentWindow.location.href;
            } catch (e) {
                return;
            }
            var pageFrames = [];
            try {
                (function (frms) {
                    var _callee = arguments.callee,
                   _frm,
                   _onbeforeunload;
                    for (var i = 0; i < frms.length; i++) {
                        _frm = frms[0];
                        pageFrames.push(_frm);
                        _callee(_frm.document.frames);
                    }
                }($page[0].contentWindow.frames));
            } catch (e) {
                if (window.console) {
                    window.console.log(e.message);
                }
                logException(e);
            }
            var onunloadcancel = function () {
                currentUrl = $page[0].contentWindow.location.href;
                //判断当前URL和跳转前URL是否相等，相等则证明页面未跳转被阻止
                //报表服务器存在未响应情况，单独判断
                if ((oldUrl == currentUrl) && url.indexOf("RptMng.aspx") == -1) {
                    $loader.hide();
                }
            }
            pageFrames.forEach(function (frm) {
                var $frm = $(frm);

                if ($frm.data("existsBeforeunload")) { return; }
                $frm.on("beforeunload", function () {
                    unloadTime = setTimeout(function () {
                        onunloadcancel();
                    }, 5000);
                }).data("existsBeforeunload", true);
            });
        }
        var api = {
            init: function () {
                $("#function-menu").on("click", "#nav-page-home", function () {
                    _backHome();
                });
                if (UserInfo.FuncCode) {
                    //根据cookie中的FuncCode登陆后跳转页面
                    $page_home.hide();
                    $page.show();
                    window.FuncNav.NavFunc(UserInfo.FuncCode);
                } else {
                    setTimeout(function () {
                        //避免阻塞页面加载
                        $page_home.prop("src", home_url + "?rmd=" + new Date().getTime());
                        $page_home.show();
                    }, 5);
                }
                // $crumb.hide();
                setContainerSize();
                _bindEvent();
            },
            setPage: function (url, cateName, funcName, callback, sitePath) {


                url = $.trim(url);
                url = $("<div/>").html(url).text(); //编码转换 &amp; to  &
                if (url.indexOf("http://") == -1) {
                    url = url.indexOf("/") != 0 ? "/" + url : url;
                }
                url = _urlEncode(url, sitePath);

                _bindBeforeunload(url);

                loader.begin();
                //开始加载
                $page[0].contentWindow.location.href = url;

                _pageUnload(function () {
                    //页面卸载后才执行跳转逻辑
                    page_callback = callback;
                    setCrumb(cateName, funcName);
                    currentUrl = url;
                });
            },
            backHome: function () {
                _backHome();
            },
            reload: function () {
                if ($page.is(":hidden")) {
                    DeskTopNav.backHome();
                } else {
                    loader.begin();
                    var frameLocation = window.frames[pageId].location;
                    try {
                        if (frameLocation.href == currentUrl) {
                            frameLocation.href = frameLocation.href;
                        } else {
                            frameLocation.href = currentUrl;
                        }
                    } catch (e) {
                        frameLocation.href = currentUrl;

                    }
                }

            },
            showCurmbDesktopNav: function () {
                $desktopNav.show();
                $crumbWrap.hide();
            },
            showCurmbPage: function () {
                $desktopNav.hide();
                $crumbWrap.show();
            },
            loader: loader
        };


        //公开API接口
        var publicApi = {
            //跳转到指定url页面，并且更面包屑导航
            SetPage: function (url, title, options) {
                options = $.extend({}, { group: "", callback: null }, options);
                var group = options.group,
                    callback = options.callback;
                url = $.trim(url);
                title = $.trim(title);
                if (url.length == 0 || title.length == 0) { return false; }
                api.setPage(url, group, title, callback);
            },
            //刷新当前页面页面，如果当前页面内部跳转后，再次调用此方法会回到上一次页面
            Reload: function () {
                api.reload();
            }
        };
        window.Page = {};
        $.extend(window.Page, publicApi);

        return api;
    }());

    /*#endregion*/

    /*#region 页面加载进度*/
    //页面加载进度
    var PageLoader = {
        callbacks: [],
        isDone: false,
        initArray: { SysNav: false, FuncNav: false, ComChoose: false },
        done: function () {
            var _this = this;
            if (_this.isDone) { return; }
            _this.isDone = true;
            var _doneSpeed = 800;
            NProgress.done();
            setTimeout(function () {
                $("#system-loader").fadeOut();
                $("body").css("overflow-x", "auto");

                _this.callbacks.forEach(function (cb) {
                    if (typeof cb == "function") {
                        cb.call();
                    }
                });

            }, _doneSpeed);



            //开始记录性能日志
            try {
                perfLog.timeDiff.adjust();
            }
            catch (e) {
                if (e.description == perfLog.errorMsg.cchIsNull) {
                    //ignore
                }
                else {
                    throw e;
                }
            }
            //清空性能/跟踪日志
            $.ajax({
                url: '/security/logSettingSync.aspx',
                type: "POST",
                data: { "op": "clean" },
                success: function (data) { }
            });
        },
        step: function () {
            var _array = this.initArray;
            // NProgress.set(0.4) —
            var hasNotDone = false,
                _step = 0,
                _stepCount = 0;
            for (var item in _array) {
                if (!_array[item]) { hasNotDone = true; } else {
                    _step += 1;
                }
                _stepCount += 1;
            }
            if (hasNotDone) {
                NProgress.set(_step / _stepCount);
            } else {
                this.done();
            }
        },
        itemDone: function (item) {
            this.initArray[item] = true;
            this.step();
        },
        init: function () {
            var appCode = mysoft.userData.getItem("appCode");
            if (!appCode) {
                this.initArray.FuncNav = true;
            }
        }

    };
    /*#endregion*/

    /*#region 桌面导航*/
    //桌面导航
    var DeskTopNav = (function () {

        var $container = $("#desktop-nav"),
            $navItems = $container.find(".nav-item"),
            $navArrow = $container.find(".active-arrow"),
            $page = $("#stage1"),
            $pageHome = $("#page-home"),
            $pageWebpart = $("#page-webpart"),
            $pageNotCenter = $("#page-notification"),
            homeLoaded = false,
            $curPage,
            currentNavType,
            desktopApi;

        var NavType = {
            //我的导航
            nav: {
                action: function () {
                    $pageHome.show();
                    //Notification.showLayer();
                    $curPage = $pageHome;
                }
            },
            //桌面部件
            webpart: {
                url: "/Desktop/WPCenter.aspx",
                isInit: false,
                action: function () {
                    var _this = this;
                    if (!_this.isInit) {
                        //第一次加载
                        Page.loader.begin();
                        $pageWebpart.attr("src", _this.url);
                        $pageWebpart.load(function () {
                            Page.loader.end();
                        });
                        _this.isInit = true;
                    } else {
                        var currentUrl = $pageWebpart[0].contentWindow.location.pathname;
                        if (currentUrl != _this.url) {
                            Page.loader.begin();
                            $pageWebpart.attr("src", _this.url);
                        } else {
                            $pageWebpart[0].contentWindow.Refresh();
                        }
                    }

                    $pageWebpart.show();
                    //Notification.showLayer();
                    $curPage = $pageWebpart;
                }
            },
            //消息中心
            notification: {
                action: function (hash) {
                    hash = hash || "";
                    $pageNotCenter.attr("src", "about:blank");
                    $pageNotCenter.show().attr("src", "/MsgCenter/Default.aspx?t=_" + (new Date().getTime()) + hash);
                    //alert("跳转 通知中心");
                    $curPage = $pageNotCenter;
                }
            }
        }
        $navItems.each(function () {
            var $this = $(this),
                navType = $this.data("nav");
            NavType[navType].el = $this;
        });
        //绑定事件
        var _bindEvent = function () {
            $navItems.click(function () {
                _switch($(this), true);
            });
            $pageHome.load(function () {
                //解决：load事件和desktop.js中事件不一定同步，导致桌面部件偶发性无法显示
                if (currentNavType) {
                    switch (currentNavType) {
                        case "webpart":
                            $pageWebpart.show();
                            break;
                        case "desktop":
                            $pageHome.show();
                        default:
                    }
                }
            });
        }
        //导航切换
        var _switch = function (obj, hasCall, param) {
            var $this,
                navType,
                navObjet;
            Page.showCurmbDesktopNav();
            if (typeof obj == "string") {
                //接受导航名作为参数
                navType = obj;
                navObjet = NavType[navType];
                $this = navObjet.el;
            } else {
                //接受导航JQ对象作为参数
                $this = obj;
                navType = $this.data("nav");
                navObjet = NavType[navType];
            }
            var offset = $this.offset();
            $navItems.filter(".active").removeClass("active");
            $navArrow.css({ left: offset.left + 40 });
            $this.addClass("active");

            currentNavType = navType;

            if (hasCall) {
                // $pageHome.hide(); 隐藏home页面会导致图形导航无法显示的BUG
                $pageNotCenter.hide();
                $pageWebpart.hide();
                return navObjet.action(param);
            }
        }
        var api = {
            init: function () {
                _bindEvent();
            },
            goNotifaction: function (hash) {
                return _switch("notification", true, hash);
            },
            backHome: function () {
                return _switch(currentNavType, true);
            }
        }
        var publicApi = {
            InitNav: function (navType, hasCall) {
                homeLoaded = true;
                return _switch(navType, hasCall);
            }
        };
        window.DeskTopNav = publicApi;
        return api;
    }());
    /*#endregion*/

    /*#region 通知&消息提示*/
    //通知&消息提示
    var Notification = (function () {

//        var $container = $("#notification-layer"), //not-display
//            $head = $container.find(".not-head"),
//            $content = $container.find(".not-content"),
//            $btnDisplay = $head.find(".icon-display"),
//            $btnClose = $head.find(".icon-close"),
//            $taskCount = $("#taskCount"),
//            $workflowCount = $("#workflowCount"), //new-num
        var $not_taskwakeCount = $("#not-taskwakeCount"),
            $not_workflowCount = $("#not-workflowCount"),
            $notCount = $("#notCount"),
            TaskCount = 0,
            WorkFlowCount = 0,
            newNotTime = null,
            loopTime,
            loopTimeOut = 1000 * 10,
            isInit = false,
                isClose = false;
        //绑定事件
        var _bindEvent = function () {
//            //展开收起
//            $btnDisplay.click(function () {
//                notFlicker.end();
//                $container.toggleClass("not-display");
//            });
//            //关闭
//            $btnClose.click(function () {
//                if (!confirm("关闭后您将不再收到消息提示，只能在下次登录才看到消息提示，确定要关闭吗？")) { return; }
//                $container.hide();
//                clearInterval(loopTime);
//                isClose = true;
//            });
//            $taskCount.click(function () {
//                DeskTopNav.goNotifaction("#1");
//            });
//            $workflowCount.click(function () {
//                DeskTopNav.goNotifaction("#0");
//            });
            $("#nottitle").click(function () {
                DeskTopNav.goNotifaction("#0");
            });
            $("#not-taskwake").click(function () {
                DeskTopNav.goNotifaction("#1");
            });
            $("#not-workflow").click(function () {
                DeskTopNav.goNotifaction("#0");
            });
        }
//        //新通知闪烁
//        var notFlicker = {
//            begin: function () {
//                if ($container.hasClass(".not-dispaly")) { return; }
//                if (newNotTime) { clearInterval(newNotTime); }
//                newNotTime = setInterval(function () {
//                    $container.toggleClass("not-new");
//                }, 500);
//            },
//            end: function () {
//                if (newNotTime) { clearInterval(newNotTime); }
//                $container.removeClass("not-new");
//            }
//        };
        //绑定加载数据
        var _bindData = function () {


            var getTaskWakeCount = function () {

                $.post("/MsgCenter/ServicesInterface.aspx",
                  { method: "GetTaskWakeCountByUser", notice: 0, todo: 0, "x-charset": "utf-8", _t: new Date().getTime() },
                  function (taskWakejson) {
                      if (!taskWakejson) { return; }

                      var _taskCount = parseInt(taskWakejson.Notice.count),
                      _workflowCount = parseInt(taskWakejson.Todo.count);

//                      //初次加载
//                      if (!isInit) {
//                          if ((_taskCount + _workflowCount) != 0) {
//                              $container.addClass("not-display");
//                              //进入系统动画展开效果
//                              $content.height("0");
//                              $content.animate({ height: 70 }, 1000);
//                              $container.show();
//                          }
//                          isInit = true;
//                      } else {
//                          if ((!$container.hasClass("not-display"))
//                              &&
//                              (_taskCount > TaskCount || _workflowCount > WorkFlowCount)
//                              ) {
//                              $container.show();
//                              //通知
//                              notFlicker.begin();
//                          }
//                      }
//                      $taskCount.text(_taskCount);
//                      $workflowCount.text(_workflowCount);

//                      if (_taskCount > 0) {
//                          $taskCount.addClass("new-num");
//                      } else {
//                          $taskCount.removeClass("new-num");
//                      }
//                      if (_workflowCount > 0) {
//                          $workflowCount.addClass("new-num");
//                      } else {
//                          $workflowCount.removeClass("new-num");
                      //                      }
                      $not_taskwakeCount.text(_taskCount);
                      $not_workflowCount.text(_workflowCount);
                      $notCount.text(_taskCount + _workflowCount);                    

                      if (_taskCount + _workflowCount == 0) {
                          $notCount.hide();
                          $("#notIcon").hide();                        
                      } else {
                          $notCount.show();
                          $("#notIcon").show();
                      }
                      TaskCount = _taskCount;
                      WorkFlowCount = _workflowCount;

                  }, "json");
            }
            PageLoader.callbacks.push(function () {

                getTaskWakeCount();
                loopTime = setInterval(getTaskWakeCount, loopTimeOut);
            });
        }

        var api = {
            init: function () {
                _bindEvent();
                _bindData();
            }
//            ,
//            showLayer: function () {
//                if (isClose) { return; }
//                if (isInit) {
//                    $container.show();
//                }
//            },
//            hideLayer: function () {
//                $container.hide();
//            }
        };
        return api;
    }());
    /*#endregion*/

    /*#region 下拉框*/
    var Dropdown = (function () {
        var dropTime = null;

        //$(".dropdown").click(function () {
        //    var $this = $(this),
        //    $menu = $this.find(".dd-menu"),
        //    trigger = $this.data("trigger");
        //    if (trigger == "click") {
        //        $menu.toggle();
        //    }
        //});
        $(".dropdown").hover(function () {
            var $this = $(this),
                $menu = $this.find(".dd-menu"),
                trigger = $this.data("trigger");
            $this.find(".dd-link").addClass("active");
            //var menuWidth = $this.data("width") || $this.outerWidth();
            // $menu.css({ width: menuWidth });
            $menu.show();
            if (dropTime) { clearTimeout(dropTime); }
            //$menu.stop(true, true).slideDown("fast");
        }, function () {
            var $this = $(this),
                $menu = $this.find(".dd-menu");
            dropTime = setTimeout(function () {
                $this.find(".dd-link").removeClass("active");
                //$menu.stop(true, true).fadeOut();
                $menu.hide();
            }, 200);
        });
    }());
    /*#endregion*/

    /*#region 初始化*/

    User.init();

    //切换系统
    SysNav.init();

    //切换菜单
    FuncNav.init();

    //选择公司
    ComChoose.init();

    //页面
    Page.init();

    //首页加载
    PageLoader.init();

    //桌面菜单
    DeskTopNav.init();

    //消息提示
    Notification.init();
    /*#endregion*/


});


