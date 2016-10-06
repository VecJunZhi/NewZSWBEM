var myScroll,myScroll1,myScroll2,myScroll3,myScroll4;
var pullDownEl, pullDownOffset,pullUpEl, pullUpOffset;
var pageIndex,pageIndex1,pageIndex2,pageIndex3,pageIndex4;
var wrapperId,listId,pullDownId,pullUpId;
var cstType,cstCount;
var index;
var loadUrl;
var str;
/**
 * 滚动翻页 （自定义实现此方法）
 * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
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
			// 数据加载完成后，调用界面更新方法 Remember to refresh when contents are loaded (ie: on ajax completion)
	}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
}
/**
 * 初始化iScroll控件
 */
function loaded() {
	var myScr;
	pullDownEl = document.getElementById(pullDownId);
	pullDownOffset = pullDownEl.offsetHeight;
	pullUpEl = document.getElementById(pullUpId);	
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScr = new iScroll(wrapperId, {
		scrollbarClass: 'myScrollbar',  //重要样式 
		//useTransition: false,  //此属性不知用意，本人从true改为false 
		topOffset: pullDownOffset,
		onRefresh: function () {
			if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
			}
		},
		onScrollMove: function () {
			if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullUpEl.className.match('flip')) {
				if(cstCount-pageLen*pageIndex > 0) {
					pullUpEl.className = 'loading';
					pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';				
					pageIndex++;
					pullUpAction ();	// Execute custom function (ajax call?)
				}
				else {
					pullUpEl.className = 'loading';
					pullUpEl.querySelector('.pullUpLabel').innerHTML = '没有数据了...';
				}
			}
		}
	});
	setTimeout(function () { document.getElementById(wrapperId).style.left = '0'; }, 800);
	return myScr;
}
//初始化绑定iScroll控件 
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
