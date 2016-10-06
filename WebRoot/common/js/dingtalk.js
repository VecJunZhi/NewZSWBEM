(function(win) {
  'use strict';
  //�ͻ����¼�
  var EVENTS = [
    'backbutton',
    'online',
    'offline',
    'pause',
    'resume',
    'swipeRefresh' //0.0.5
  ];
  //�����б�
  var METHODS = [
    'device.notification.alert',
    'device.notification.confirm',
    'device.notification.prompt',
    'device.notification.vibrate',
    'device.accelerometer.watchShake',
    'device.accelerometer.clearShake',
    'biz.util.open',
    'biz.util.openLink',
    'biz.util.share',
    'biz.util.ut',
    'biz.util.datepicker', //android
    'biz.util.timepicker', //android
    'biz.user.get',
    'biz.navigation.setLeft',
    'biz.navigation.setRight',
    'biz.navigation.setTitle',
    'biz.navigation.back',
    // v0.0.1
    'device.notification.toast',
    'device.notification.showPreloader',
    'device.notification.hidePreloader',
    'device.geolocation.get',
    'biz.util.uploadImage',
    'biz.util.previewImage',
    //v0.0.2
  /**
   * ios����ѡ����
   * 'biz.util.textarea' ��Ϊ ui.input.plain
   **/
    'ui.input.plain',
    'device.notification.actionSheet',
    'biz.util.qrcode',
    'device.connection.getNetworkType',
    'runtime.info',
    //v0.0.3
  /**
   * ���� biz.ding.post
   * ��绰 biz.telephone.call
   * ѡȺ�� biz.chat.chooseConversation
   **/
    'biz.ding.post',
    'biz.telephone.call',
    'biz.chat.chooseConversation',
    //v0.0.4
  /**
   * ������ϵ�����ҳ�� biz.util.open  contactAdd
   * ��Ϣ�Ự+����ҵȺ���� biz.contact.createGroup
   * select��� biz.util.chosen
   * ����+ʱ�� biz.util.datetimepicker
   **/
    'biz.contact.createGroup',
    //'biz.util.chosen', //��bug��0.0.5�濪��
    'biz.util.datetimepicker',
    //v0.0.5
  /**
   * �������iOS bugfix����һ�����
   * ��ȡͨ��Ψһʶ����
   * ��ȡ�ȵ������Ϣ
   * ���Ӧ���Ƿ�װ
   * ����������app
   * ���õ�����������ɫ
   * �����¼� swipeRefresh
   * ������Ȩ�룬��Ǹ�����
   * ��������ˢ�¹���
   * ��������ˢ�¿ؼ�
   * ��������ˢ�¹���
   * ����webview��������Ч��
   * ����webview��������Ч��
   **/
    'biz.util.chosen', //�������
    'device.base.getUUID', //��ȡͨ��Ψһʶ����
    'device.base.getInterface', //��ȡ�ȵ������Ϣ
    'device.launcher.checkInstalledApps', //���Ӧ���Ƿ�װ
    'device.launcher.launchApp', //����������app

    'ui.progressBar.setColors', //���ö�����������ɫ
    'runtime.permission.requestAuthCode', //������Ȩ�룬��Ǹ�����
    'runtime.permission.requestJsApis', //����jsapi�����ط�����ֻ��sdk�ڲ����ã�TODO: ����ʱע�͵�

    'ui.pullToRefresh.enable', //��������ˢ�¹���
    'ui.pullToRefresh.stop', //��������ˢ�¿ؼ�
    'ui.pullToRefresh.disable', //��������ˢ�¹���
    'ui.webViewBounce.enable', //����webview��������Ч��
    'ui.webViewBounce.disable', //����webview��������Ч��

    //v0.0.6
  /**
   * ��ȡ�Ự��Ϣ
   * ��ͼ����
   * ��ͼ��λ
   * ɨ��
   * �޸���ҵͨѶ¼ѡ��
   * ��ҵͨѶ¼ͬʱѡ�ˣ�ѡ����
   **/
    'biz.chat.getConversationInfo', //��ȡ�Ự��Ϣ
    'biz.map.search', //��ͼ����
    'biz.map.locate', //��ͼ��λ
    'biz.util.scan', //ɨ��
    'biz.contact.choose', //�޸���ҵͨѶ¼ѡ��
    'biz.contact.complexChoose', //��ҵͨѶ¼ͬʱѡ�ˣ�ѡ����
    'util.localStorage.getItem', //���ش洢��
    'util.localStorage.setItem', //���ش洢д
    'util.localStorage.removeItem', //���ش洢�Ƴ�����
    'biz.navigation.createEditor', //����ͨ�����
    'biz.navigation.finishEditor', //����ͨ�����

    //v0.0.7
  /**
   **/
    'biz.chat.pickConversation', //ѡȺ��

    //0.0.8
    'device.notification.modal', //������
    'biz.navigation.setIcon', //���õ���icon
    'biz.navigation.close', //�ر�webview
    'biz.util.uploadImageFromCamera',

    //0.0.9
    'internal.lwp.call',//lwp�ӿ�
    //0.1.0
    'device.geolocation.openGps',//�����ã�ֻ��android��Ч
    'biz.util.test', //���Խӿ�
    'internal.microapp.checkInstalled',//���΢Ӧ���Ƿ�װ
    'internal.user.getRole', //��ȡ��ɫ

    //0.1.1
    'device.notification.extendModal', //̸�㣬֧�ֶ���ͼƬ
    'internal.request.lwp', //lwpͨ��
    'biz.user.secretID', //��ȡ�û���¼Ψһ��ʶ
    'internal.util.encryData', //������������key
    'biz.customContact.choose', //�Զ���ѡ�����
    'biz.customContact.multipleChoose', //�Զ���ѡ���������ѡ��
    'biz.util.pageClick', //ҳ����

    //0.1.2
    'biz.chat.chooseConversationByCorpId', //ѡ����ҵ�Ựv2.6����
    'biz.chat.toConversation', //��ת����Ӧ�ĻỰv2.6����
    'biz.chat.open',//����pickConversation��ȡ��cid�򿪶�Ӧ������Ự
    'device.base.getSettings', //��ȡ�ֻ����ô���
    'internal.log.upload', //�ϴ���־v2.6����

    'biz.navigation.goBack',//nav����

    //nav����
    'ui.nav.preload',
    'ui.nav.go',
    'ui.nav.recycle',
    'ui.nav.close',
    'ui.nav.getCurrentId',

    //��Ϣ
    'runtime.message.post',
    'runtime.message.fetch',

    'biz.navigation.setMenu',//���õ����˵���ť

    //�������
    'ui.drawer.init',
    'ui.drawer.config',
    'ui.drawer.enable',
    'ui.drawer.disable',
    'ui.drawer.open',
    'ui.drawer.close',

    'biz.util.uploadAttachment',
    'biz.cspace.preview',
    'internal.hpm.get',
    'internal.hpm.update',
    'preRelease.appLink.open',
    'internal.request.getSecurityToken', //lwpͨ��
    'biz.clipboardData.setData',

    'ui.tab.init',
    'ui.tab.start',
    'ui.tab.select',
    'ui.tab.config'
  ];
  var JSSDK_VERSION = "1.0.0";
  var ua = win.navigator.userAgent;
  var matches = ua.match(/AliApp\(\w+\/([a-zA-Z0-9.-]+)\)/);
  //android���ݴ���
  if (matches === null) {
    matches = ua.match(/DingTalk\/([a-zA-Z0-9.-]+)/);
  }
  var version = matches && matches[1];
  var authorised = false; //�Ƿ���У��ͨ��
  var already = false; //�Ƿ��ѳ�ʼ��
  var config = null; //����config����
  var authMethod = 'runtime.permission.requestJsApis'; //Ȩ��У�鷽����
  var errorHandle = null; //����error�ص�
  var bridgeReady = false;
  var dd = {
    ios: (/iPhone|iPad|iPod/i).test(ua),
    android: (/Android/i).test(ua),
    version: version,
    support: version === '1.2.2' || version === '1.3.2',
    ability: '', //��Ϊ��ʼֵ������ֵ�ӿͻ��˶�ȡ����ʽΪx.x.x
    config: function(obj) {
      //������û��������Ĳ������й���
      if (!obj) {
        return;
      }
      //TODO: ��������ȷ��
      config = {
        corpId: obj.corpId,
        appId:obj.appId||-1,
        timeStamp: obj.timeStamp,
        nonceStr: obj.nonceStr,
        signature: obj.signature,
        jsApiList: obj.jsApiList
      };
      if(obj.agentId){
        config.agentId = obj.agentId;
      }
    },
    error: function(fn) {
      errorHandle = fn;
    },
    ready: function(callback) {
      //�ܿ�
      var fn = function(bridge) {
        if (!bridge) {
          return console.log('bridge��ʼ��ʧ��')
        }
        //callback(bridge);
        //TODO: �ж�config������Ȩ��У��
        if (config === null||!config.signature) {
          //console.log('û������dd.config')
          callback(bridge);
        } else {
          //console.log('������dd.config', config)
          //Ȩ��У��
          if (dd.ios) {
            bridge.callHandler(authMethod, config, function(response) {
              var data = response || {};
              var code = data.errorCode;
              var msg = data.errorMessage || '';
              var result = data.result;
              if (code === '0') {
                callback(bridge);
              } else {
                setTimeout(function() {
                  errorHandle && errorHandle({
                    message: 'Ȩ��У��ʧ�� ' + msg,
                    errorCode: 3
                  });
                });
              }
            });
          } else if (dd.android) {
            var arr = authMethod.split('.');
            var suff = arr.pop();
            var pre = arr.join('.');
            bridge(function() {
              callback(bridge);
            }, function(err) {
              setTimeout(function() {
                var msg = err&&err.errorMessage || '';
                errorHandle && errorHandle({
                  message: 'Ȩ��У��ʧ�� ' + msg,
                  errorCode: 3
                });
              });
            }, pre, suff, config);
          }
        }
        //callback(bridge);
        //��һ�γ�ʼ����Ҫ��������
        if (already === false) {
          already = true;
          //�Զ����¼�
          EVENTS.forEach(function(evt) {
            if (dd.ios) {
              bridge.registerHandler(evt, function(data, responseCallback) {
                //console.log('ע���¼�Ĭ�ϻص�', data, responseCallback);
                var e = document.createEvent('HTMLEvents');
                e.data = data;
                e.initEvent(evt);
                document.dispatchEvent(e);
                responseCallback && responseCallback({
                  errorCode: '0',
                  errorMessage: '�ɹ�'
                })
              });
            }
          });

          if (config === null) {
            var conf = {
              url: encodeURIComponent(window.location.href),
              js: JSSDK_VERSION,
              cid: config && config.corpId || ''
            };
            //���
            dd.biz.util.ut({
              key: 'dd_open_js_monitor',
              value: JSON.stringify(conf),
              onSuccess: function(res) {
                //console.log('dd_open_js_monitor ut���ɹ�', res);
              }
            });
          }
        }
      };
      //�Ѿ���ɳ�ʼ�������
      if (dd.ios && win.WebViewJavascriptBridge) {
        //��ֹready�ӳٵ��µ�����
        //init��register�ķ��������յ��ص������ַ������״δ���dd.ready��ʱ
        try {
          WebViewJavascriptBridge.init(function(data, responseCallback) {
            //�ͻ���send
            //console.log('WebViewJavascriptBridge init: ', data, responseCallback);
          });
        } catch (e) {
          console.log(e.message);
        }
        return fn(WebViewJavascriptBridge);
      } else if (dd.android && win.WebViewJavascriptBridgeAndroid) {
        return fn(WebViewJavascriptBridgeAndroid);
      }
      //��ʼ��������
      if (dd.ios) {
        //console.log('��ʼ����WebViewJavascriptBridgeReady�¼�');
        document.addEventListener('WebViewJavascriptBridgeReady', function() {
          if (typeof WebViewJavascriptBridge === 'undefined') {
            return console.log('WebViewJavascriptBridge δ����');
          }
          try {
            WebViewJavascriptBridge.init(function(data, responseCallback) {
              //�ͻ���send
              //console.log('WebViewJavascriptBridge init: ', data, responseCallback);
            });
          } catch (e) {
            console.log(e.message);
          }
          bridgeReady = true;
          fn(WebViewJavascriptBridge);

        }, false);
      } else if (dd.android) {
        var _run = function() {
          try {
            win.WebViewJavascriptBridgeAndroid = win.nuva.require();
            bridgeReady = true;
            fn(WebViewJavascriptBridgeAndroid);
          } catch (e) {
            console.log('window.nuva.require����', e.message);
            fn(null);
          }
        };
        //����
        if (win.nuva) {
          _run();
        } else {
          document.addEventListener('runtimeready', function() {
            _run();
          }, false);
        }
        //
      } else {
        return console.log('�ܱ�Ǹ����δ֧���������豸');
      }
    },
    type:function(obj){
      //"Array", "Boolean", "Date", "Number", "Object", "RegExp", "String", "Window" ,"Constructor"
      return Object.prototype.toString.call(obj).match(/^\[object\s(.*)\]$/)[1];
    },
    //�汾�ŶԱȷ����������ж�1.5.0��1.11.0�Ĵ�С
    /**
     * oldVersion �ϰ汾
     * newVersion �°汾
     * containEqual �Ƿ������ȵ����
     * result: newVersion >[=] oldVersion
     **/
    compareVersion: function(oldVersion, newVersion, containEqual) {
      if (typeof oldVersion !== 'string' || typeof newVersion !== 'string') {
        return false;
      }
      //�ָ��ַ���Ϊ['1', '0', '1']��ʽ
      var oldArray = oldVersion.split('.');
      var newArray = newVersion.split('.');
      var o, n;
      //�������ҶԱ�ֵ��ֵ��ͬ����������ͬ�򷵻ز�ͬ��ֵ
      while (o === n && newArray.length > 0) {
        o = oldArray.shift();
        n = newArray.shift();
      }
      //���ز�ֵͬ�ıȽϽ��
      if (containEqual) {
        return (n | 0) >= (o | 0);
      } else {
        return (n | 0) > (o | 0);
      }
    }
  };
  //ע�������ռ�,"device.notification.alert"����dd.device.notification.alert
  var ns = function(method, fn) {
    var arr = method.split('.');
    var namespace = dd;
    for (var i = 0, k = arr.length; i < k; i++) {
      if (i === k - 1) {
        namespace[arr[i]] = fn;
      }
      if (typeof namespace[arr[i]] === 'undefined') {
        namespace[arr[i]] = {};
      }
      namespace = namespace[arr[i]];
    }
  };
  //����Ĭ������
  function setDefaultValue(obj, defaults,flag) {
    for (var i in defaults) {
      if(flag){
        obj[i] = defaults[i];
      }else{
        obj[i] = obj[i] !== undefined ? obj[i] : defaults[i];
      }
    }
  }
  //�������������Ρ��ص��Լ����ض��������⴦��
  function generator(method, param) {
    //����λ��
    if (typeof WebViewJavascriptBridge === 'undefined') {
      return console.log('WebViewJavascriptBridgeδ���壬���ڶ���app�򿪸�ҳ��');
    }
    //��ʼ�ɻ�
    //console.log('���÷�����', method, '���Σ�', param);
    var p = param || {};
    var successCallback = function(res) {
      console.log('Ĭ�ϳɹ��ص�', method, res);
    };
    var failCallback = function(err) {
      console.log('Ĭ��ʧ�ܻص�', method, err)
    };
    var cancelCallback = function() {};
    if (p.onSuccess) {
      successCallback = p.onSuccess;
      delete p.onSuccess;
    }
    if (p.onFail) {
      failCallback = p.onFail;
      delete p.onFail;
    }
    if (p.onCancel) {
      cancelCallback = p.onCancel;
      delete p.onCancel;
    }
    //ͳһ�ص�����
    var callback = function(response) {
      //console.log('ͳһ��Ӧ��', response);
      var data = response || {};
      var code = data.errorCode;
      var result = data.result;
      if (code === '0') {
        successCallback && successCallback.call(null, result);
      } else if (code === '-1') {
        cancelCallback && cancelCallback.call(null, result);
      } else {
        failCallback && failCallback.call(null, result,code);
      }
    };
    var watch = false; //�Ƿ�Ϊ���������� ����Ǽ�������������Ҫע���¼�
    //ǰ�˰�װ
    switch (method) {
      case 'device.notification.alert':
        setDefaultValue(p, {
          title: '',
          buttonName: 'ȷ��'
        });
        break;
      case 'device.notification.confirm':
      case 'device.notification.prompt':
        setDefaultValue(p, {
          title: '',
          buttonLabels: ['ȷ��', 'ȡ��']
        });
        break;
      case 'device.notification.vibrate':
        setDefaultValue(p, {
          duration: 300
        });
        break;
      //�����෽����watch��ǣ���iOS�ͻ���Լ��ͨ��jsע���¼��ķ�ʽʵ��
      case 'device.accelerometer.watchShake':
        if (dd.ios) {
          watch = true;
          p.sensitivity = 3.2; //ios�����ֵ��ƫ����⴦��
        }
        break;
      case 'biz.util.openLink':
        setDefaultValue(p, {
          credible: true,
          showMenuBar: true
        });
        break;
      case 'biz.contact.choose':
        setDefaultValue(p, {
          multiple: true,
          startWithDepartmentId: 0,
          users: [],
          corpId: (config && config.corpId) || ''
        });
        break;
      case 'biz.contact.complexChoose':
        setDefaultValue(p, {
          startWithDepartmentId: 0,
          selectedUsers: [],
          selectedDepartments: [],
          corpId: (config && config.corpId) || ''
        });
        break;
      case 'biz.navigation.setLeft':
      case 'biz.navigation.setRight':
        if (dd.ios) {
          watch = true;
        }
        //Ĭ��ֵ
        setDefaultValue(p, {
          show: true,
          control: false,
          showIcon: true,
          text: ''
        });
        break;
      case 'ui.pullToRefresh.enable':
        if (dd.ios) {
          watch = true;
        }
        break;
      case 'device.notification.toast':
        setDefaultValue(p, {
          text: 'toast',
          duration: (dd.android ? ((dd.duration - 3 > 0) + 0) : 3), //android�豸ֻ�д���3s��С�ڵ���3s����ѡ��; iOSĬ��3s
          delay: 0
        });
        break;
      case 'device.notification.showPreloader':
        setDefaultValue(p, {
          text: '������...',
          showIcon: true
        });
        break;
      case 'biz.util.uploadImage':
        setDefaultValue(p, {
          multiple: false
        });
        break;
      case 'biz.util.scan':
        setDefaultValue(p, {
          type: 'qrCode'
        });
        break;
      case 'biz.map.search':
        setDefaultValue(p, {
          scope: 500,
        });
        break;
      case 'biz.util.ut':
        var tempValue = p.value;
        var tempStr=[];
        if(tempValue&&dd.type(tempValue)=='Object'&&window.JSON){
          if(dd.ios){
            tempValue = JSON.stringify(tempValue);
          }else if(dd.android){
            for(var i in tempValue){
              tempStr.push(i+"="+tempValue[i]);
            }
            tempValue = tempStr.join(',');
          }
        }else if(!window.JSON){
          tempValue = '';
        }


        setDefaultValue(p, {
          value: tempValue
        },true);
        break;
      case 'internal.util.encryData':
        var str = p.data;
        var tempStr=[];
        if(dd.type(str)=='Object'){
          for(var i in str){
            tempStr.push(i+"="+str[i]);
          }
          str = tempStr.join('&');
        }
        setDefaultValue(p, {
          data: str
        },true);
        break;
      case 'internal.request.lwp':
        var str = p.body;
        str = JSON.stringify(str);

        setDefaultValue(p, {
          body: str
        },true);
        break;
      case 'biz.navigation.setIcon':
        if (dd.ios) {
          watch = true;
        }
        //Ĭ��ֵ
        setDefaultValue(p, {
          showIcon: true,
          iconIndex:'1'
        });
        break;
      case 'biz.customContact.multipleChoose':
      case 'biz.customContact.choose':
        //Ĭ��ֵ
        setDefaultValue(p, {
          isShowCompanyName: false
        });
        break;
      case 'biz.navigation.setMenu':
        if (dd.ios) {
          watch = true;
        }
        break;
    }

    //��Ϣ���룺android��iOS���ִ���
    if (dd.android) {
      var arr = method.split('.');
      var suff = arr.pop();
      var pre = arr.join('.');
      //console.log('Android�Խӣ�', pre, suff, p);
      //WebViewJavascriptBridgeAndroid(successCallback, failCallback, pre, suff, p);
      //console.log(successCallback, failCallback, pre, suff, p);

      var params = p || {};
      params.onSuccess = successCallback;
      params.onFail = failCallback;
      try {
        window.nuva.require(pre)[suff](params);
      } catch (e) {
        console.log(e);
      }
    } else if (dd.ios) {
      //console.log(method, p, callback)
      if (watch) {
        WebViewJavascriptBridge.registerHandler(method, function(data, responseCallback) {
          callback({
            errorCode: '0',
            errorMessage: '�ɹ�',
            result: data
          });
          //�ش����ͻ��ˣ���ѡ
          responseCallback && responseCallback({
            errorCode: '0',
            errorMessage: '�ɹ�'
          });
        });
        WebViewJavascriptBridge.callHandler(method, p);
      } else {
        WebViewJavascriptBridge.callHandler(method, p, callback);
      }
    }
  }
  //��̬����api
  METHODS.forEach(function(method) {
    ns(method, function(param) {
      generator(method, param);
    });
  });

  dd.__ns = ns;

  dd.biz.util.pageClick = function(obj){
    var k = 'open_micro_log_record_client';
    var visitTime = +new Date();
    var corpId = obj.corpId;
    var agentId = obj.agentId;
    if(!corpId){
      corpId = (config&&config.corpId)||'';
    }
    if (!agentId) {
      agentId = (config&&config.agentId)||'';
    };

    var defaultObj = {
      visitTime:visitTime,
      clickType:2,
      clickButton:obj.clickButton||'',
      url:location.href,
      corpId:corpId,
      agentId:agentId
    };
    dd.biz.util.ut({
      key:k,
      value:defaultObj
    });
  }

  win.dd = dd;

  //���ʷ���
  if (typeof module === 'object' && module && typeof module.exports === 'object') {
    module.exports = dd;
  } else if (typeof define === 'function' && (define.amd || define.cmd)) {
    define(function() {
      return dd;
    })
  }
}(this));