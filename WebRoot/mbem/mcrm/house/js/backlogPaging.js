var myScroll,myScroll1,myScroll2,myScroll3,myScroll4;
var pullDownEl, pullDownOffset,pullUpEl, pullUpOffset;
var pageIndex,pageIndex1,pageIndex2,pageIndex3,pageIndex4;
var wrapperId,listId,pullDownId,pullUpId;
var cstType,cstCount;
var index;
var loadUrl;
var str;
/**
 * ������ҳ ���Զ���ʵ�ִ˷�����
 * myScroll.refresh();		// ���ݼ�����ɺ󣬵��ý�����·���
 */
function pullUpAction () {
	index = layer.load(0, {shade: false});
	setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
		$.ajax({
            url: loadUrl,
            type:"POST",
            data: {"type":cstType,"page":pageIndex,"pageLen":pageLen,},
            dataType: "json",
            success: function (data) {
            	for(var i=0; i<data.length; i++) {
            		joinStr(data,i);
            		$("#"+listId).append(str);
            	}
            	myScroll.refresh();
            	layer.close(index);
            },
            error: function() {
            	//alert("error");
            }
		});  
			// ���ݼ�����ɺ󣬵��ý�����·��� Remember to refresh when contents are loaded (ie: on ajax completion)
	}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
}
/**
 * ��ʼ��iScroll�ؼ�
 */
function loaded() {
	var myScr;
	pullDownEl = document.getElementById(pullDownId);
	pullDownOffset = pullDownEl.offsetHeight;
	pullUpEl = document.getElementById(pullUpId);	
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScr = new iScroll(wrapperId, {
		scrollbarClass: 'myScrollbar',  //��Ҫ��ʽ 
		//useTransition: false,  //�����Բ�֪���⣬���˴�true��Ϊfalse 
		topOffset: pullDownOffset,
		onRefresh: function () {
			if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '�������ظ���...';
			}
		},
		onScrollMove: function () {
			if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '���ֿ�ʼ����...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '�������ظ���...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullUpEl.className.match('flip')) {
				if(cstCount-pageLen*pageIndex > 0) {
					pullUpEl.className = 'loading';
					pullUpEl.querySelector('.pullUpLabel').innerHTML = '������...';				
					pageIndex++;
					pullUpAction ();	// Execute custom function (ajax call?)
				}
				else {
					pullUpEl.className = 'loading';
					pullUpEl.querySelector('.pullUpLabel').innerHTML = 'û��������...';
				}
			}
		}
	});
	setTimeout(function () { document.getElementById(wrapperId).style.left = '0'; }, 800);
	return myScr;
}
//��ʼ����iScroll�ؼ� 
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
