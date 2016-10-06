		function pullDownAction() {
			load_content('refresh');
			$('#wrapper > #listPanel > ul').data('page', 1);//下拉刷新后设置当前页为第一页
			
			// Since "topOffset" is not supported with iscroll-5
			$('#wrapper > .listPanel').css({top:0});
			
		}
		function pullUpAction(callback) {
			if ($('#wrapper > #listPanel > ul').data('page')) {
				var next_page = parseInt($('#wrapper > #listPanel > ul').data('page'), 10) + 1;
			} else {
				var next_page = 2;//根据当前页来计算下一页
			}
			load_content('refresh', next_page);
			$('#wrapper > #listPanel > ul').data('page', next_page);//上拉加载后设置当前页
			
			if (callback) {
				callback();
			}
		}
		function pullActionCallback() {//下拉刷新的回调函数
			if (pullDownEl && pullDownEl.className.match('loading')) {
				
				pullDownEl.className = 'pullDown';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh';
				
				myScroll.scrollTo(0, parseInt(pullUpOffset)*(-1), 200);
				
			} else if (pullUpEl && pullUpEl.className.match('loading')) {
				
				$('.pullUp').removeClass('loading').html('');
				
			}
		}
		
		var pullActionDetect = {
			count:0,
			limit:10,
			check:function(count) {
				if (count) {
					pullActionDetect.count = 0;
				}
				// Detects whether the momentum has stopped, and if it has reached the end - 200px of the scroller - it trigger the pullUpAction
				setTimeout(function() {
					if (myScroll.y <= (myScroll.maxScrollY + 200) && pullUpEl && !pullUpEl.className.match('loading')) {
						$('.pullUp').addClass('loading').html('<span class="pullUpIcon">&nbsp;</span><span class="pullUpLabel">加载中...</span>');
						
						pullUpAction();
					} else if (pullActionDetect.count < pullActionDetect.limit) {
						pullActionDetect.check();
						pullActionDetect.count++;
					}
				}, 200);
			}
		}
		
		function trigger_myScroll(offset) {
			pullDownEl = document.querySelector('#wrapper .pullDown');
			if (pullDownEl) {
				pullDownOffset = pullDownEl.offsetHeight;
			} else {
				pullDownOffset = 0;
			}
			pullUpEl = document.querySelector('#wrapper .pullUp');	
			if (pullUpEl) {
				pullUpOffset = pullUpEl.offsetHeight;
			} else {
				pullUpOffset = 0;
			}
			
			
			$('#wrapper .pullDown').hide();
				$('#wrapper .pullUp span').hide();
			if ($('#wrapper ul > li').length < items_per_page) {
				// If we have only 1 page of result - we hide the pullup and pulldown indicators.
			//	alert("<<");
				
				offset = 0;
			} else if (!offset) {
				// If we have more than 1 page of results and offset is not manually defined - we set it to be the pullUpOffset.
				offset = pullUpOffset;
			}
			
			myScroll = new IScroll('#wrapper', {
				probeType:1, tap:true, click:false, preventDefaultException:{tagName:/.*/}, mouseWheel:true, scrollbars:true, fadeScrollbars:true, interactiveScrollbars:false, keyBindings:false,
				deceleration:0.0002,
				startY:(parseInt(offset)*(-1))
			});
			myScroll.on('scrollStart', function () {
				scroll_in_progress = true;
			});
			myScroll.on('scroll', function () {
				
				scroll_in_progress = true;
				if ($('#wrapper ul > li').length >= items_per_page) {
					if (this.y >= 5 && pullDownEl && !pullDownEl.className.match('flip')) {
						pullDownEl.className = 'pullDown flip';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Release to refresh';
						this.minScrollY = 0;
					} else if (this.y <= 5 && pullDownEl && pullDownEl.className.match('flip')) {
						pullDownEl.className = 'pullDown';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh';
						this.minScrollY = -pullDownOffset;
					}
					
					console.log(this.y);
					pullActionDetect.check(0);	
				}
				else {
					pullDownEl.className = 'pullDown flip';
					console.log(this.y);
				}
			});
			myScroll.on('scrollEnd', function () {
				console.log('scroll ended');
				setTimeout(function() {
					scroll_in_progress = false;
				}, 100);
				if ($('#wrapper ul > li').length >= items_per_page) {
					if (pullDownEl && pullDownEl.className.match('flip')) {
						pullDownEl.className = 'pullDown loading';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Loading...';
						pullDownAction();
					}
					// We let the momentum scroll finish, and if reached the end - loading the next page
					pullActionDetect.check(0);
				}
				else {
					if (pullDownEl && pullDownEl.className.match('flip')) {
						pullDownEl.className = 'pullDown loading';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Loading...';
						pullDownAction();
					}
					// We let the momentum scroll finish, and if reached the end - loading the next page
					pullActionDetect.check(0);
				}
			});
			
			// In order to prevent seeing the "pull down to refresh" before the iScoll is trigger - the wrapper is located at left:-9999px and returned to left:0 after the iScoll is initiated
			setTimeout(function() {
				$('#wrapper').css({left:0});
			}, 100);
		}