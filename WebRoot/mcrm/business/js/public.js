
if (!this.WpCenter) { this.WpCenter = {}; }

(function() {
    var ajax = {
        _url: "/Desktop/WPServices.ashx",
        _request: function(type, url, urlParams, datatype, callback, afterCallback) {
            $.ajax({
                type: type,
                url: url,
                data: urlParams,
                dataType: datatype,
                cache: false,
                timeout: 90000,
                success: function(msg) {
                    if (typeof callback !== "undefined" && typeof callback === "function")
                        callback(msg);
                    if (typeof afterCallback !== "undefined" && typeof afterCallback === "function")
                        afterCallback(msg);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert('非常抱歉,加载内容失败,请重试');
                }
            });
        },
        postUpdateDesktopWebpartSequence: function(data, callback) {
            this._request("POST", this._url + "?type=UpdateDesktopWebpartSequence", data, "text", callback);
        },
        postGetMyStartedProcesses: function(data, callback) {
            this._request("POST", this._url + "?type=GetMyStartedProcesses", data, "json", callback);
        },
        postGetSurveyOfCostParts: function(data, callback) {
            this._request("POST", this._url + "?type=GetSurveyOfCostParts", data, "json", callback);
        }
    };

    var loading = {
        show: function() {
            var divLoading = $("#loadingDiv");
            if (divLoading.length === 0) {
                $("#scrollDiv").append("<div id=\"loadingDiv\"><img alt=\"加载中\" src=\"/frame/_content/img/loading.gif\" /></div>");
                divLoading = $("#loadingDiv");
            }
            divLoading.show();
        },
        hide: function() {
            setTimeout(function() {
                $("#loadingDiv").hide();
            }, 300);
            $("#loadingDiv").addClass("loading");
        }
    };

    if (typeof WpCenter.loadingHelper !== "function") {
        WpCenter.loading = loading;
        WpCenter.ajax = ajax;
    };

})();


//本地存储
var userData = {
    userDate: null,
    name: location.hostname,
    init: function() {
        var _this = this;
        if (!_this.userDate) {
            try {
                _this.userDate = document.createElement('input');
                _this.userDate.type = "hidden";
                _this.userDate.addBehavior('#default#userData');
                document.body.appendChild(_this.userDate);
            } catch (e) {
                return false;
            }
        }
        return true;
    },
    setItem: function(key, value) {
        var _this = this;
        if (!window.localStorage) {
            if (_this.init()) {
                _this.userDate.load(_this.name);
                _this.userDate.setAttribute(key, value);
                _this.userDate.save(_this.name);
            }
        } else {
            window.localStorage.setItem(key, value);
        }
    },
    getItem: function(key) {
        var _this = this;
        if (!window.localStorage) {
            if (_this.init()) {
                _this.userDate.load(_this.name);
                return _this.userDate.getAttribute(key);
            }
        } else {
            return window.localStorage.getItem(key);
        }
    },
    removeItem: function(key) {
        var _this = this;
        if (!window.localStorage) {
            if (_this.init()) {
                _this.userDate.load(_this.name);
                _this.userDate.removeAttribute(key);
                _this.userDate.save(_this.name);
            }
        } else {
            window.localStorage.removeItem(key);
        }
    }
};

function openFullWin(sPath, sName) {
    var iX = window.screen.availWidth - 10;
    var iY = window.screen.availHeight - 50;
    if (!sName) sName = "";
    try {
        return window.open(sPath, sName, "left=0,top=0,scrollbars=yes,width=" + iX + ",height=" + iY + ",status=1,resizable=1");
    }
    catch (e) { alert(e); }
}
$.fn.eventBind = function(obj) {
    for (var ename in obj) {
        for (var selector in obj[ename]) {
            $(this).on(ename, selector, obj[ename][selector]);
        }
    }
};