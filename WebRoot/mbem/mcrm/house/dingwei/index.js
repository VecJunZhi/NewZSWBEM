(function( $, undefined ) {
	//special click handling to make widget work remove after nav changes in 1.4
	var href,
		ele = "";
	
	$( document ).on( "pagebeforechange", function( e, f ){
			f.originalHref = href;
	});
	$( document ).on("pagebeforechange", function( e,f ){
		var hash = $.mobile.path.parseUrl(f.toPage).hash,
			hashEl, hashElInPage;

		try {
			hashEl = $( hash );
		} catch( e ) {
			hashEl = $();
		}

		try {
			hashElInPage = $( ".ui-page-active " + hash );
		} catch( e ) {
			hashElInPage = $();
		}

		if( typeof hash !== "undefined" &&
			hash.search( "/" ) === -1 &&
			hash !== "" &&
			hashEl.length > 0 &&
			!hashEl.hasClass( "ui-page" ) &&
			!hashEl.hasClass( "ui-popup" ) &&
			hashEl.data('role') !== "page" &&
			!hashElInPage.hasClass( "ui-panel" ) &&
			!hashElInPage.hasClass( "ui-popup" ) ) {
			//scroll to the id
			var pos = hashEl.offset().top;
			$.mobile.silentScroll( pos );
			$.mobile.navigate( hash, '', true );
		} else if( typeof f.toPage !== "object" &&
			hash !== "" &&
			$.mobile.path.parseUrl( href ).hash !== "" &&
			!hashEl.hasClass( "ui-page" ) && hashEl.attr('data-role') !== "page" &&
			!hashElInPage.hasClass( "ui-panel" ) &&
			!hashElInPage.hasClass( "ui-popup" ) ) {
			$( ele ).attr( "href", href );
			$.mobile.document.one( "pagechange", function() {
				if( typeof hash !== "undefined" &&
					hash.search( "/" ) === -1 &&
					hash !== "" &&
					hashEl.length > 0 &&
					hashElInPage.length > 0 &&
					!hashEl.hasClass( "ui-page" ) &&
					hashEl.data('role') !== "page" &&
					!hashElInPage.hasClass( "ui-panel" ) &&
					!hashElInPage.hasClass( "ui-popup" ) ) {
					hash = $.mobile.path.parseUrl( href ).hash;
					var pos = hashElInPage.offset().top;
					$.mobile.silentScroll( pos );
				}
			} );
		}
	});
	$( document ).on( "mobileinit", function(){
		hash = window.location.hash;
		$.mobile.document.one( "pageshow", function(){
			var hashEl, hashElInPage;

			try {
				hashEl = $( hash );
			} catch( e ) {
				hashEl = $();
			}

			try {
				hashElInPage = $( ".ui-page-active " + hash );
			} catch( e ) {
				hashElInPage = $();
			}

			if( hash !== "" &&
				hashEl.length > 0 &&
				hashElInPage.length > 0 &&
				hashEl.attr('data-role') !== "page" &&
				!hashEl.hasClass( "ui-page" ) &&
				!hashElInPage.hasClass( "ui-panel" ) &&
				!hashElInPage.hasClass( "ui-popup" ) &&
				!hashEl.is( "body" ) ){
				var pos = hashElInPage.offset().top;
				setTimeout( function(){
					$.mobile.silentScroll( pos );
				}, 100 );
			}
		});
	});
	//h2 widget
	$( document ).on( "mobileinit", function(){
		$.widget( "mobile.h2linker", {
			options:{
				initSelector: ":jqmData(quicklinks='true')"
			},

			_create:function(){
				var self = this,
					bodyid = "ui-page-top",
					panel = "",
					first = true,
					h2dictionary = new Object();
					if(typeof $("body").attr("id") === "undefined"){
						$("body").attr("id",bodyid);
					} else {
						bodyid =  $("body").attr("id");
					}
					this.element.find("div.jqm-content>h2").each(function(){
						var id, text = $(this).text();

						if(typeof $(this).attr("id") === "undefined"){
							id = text.replace(/[^\.a-z0-9:_-]+/gi,"");
							$(this).attr( "id", id );
						} else {
							id = $(this).attr("id");
						}

						h2dictionary[id] =  text;
						if(!first){
							$(this).before( "<a href='#" + bodyid + "' class='jqm-deeplink ui-icon-carat-u ui-alt-icon'>Top</a>");
						} else {
							$(this).before("");
						}
						first = false;
					});
					this._on(".jqm-open-quicklink-panel", {
						"click": function(){
							$(".ui-page-active .jqm-quicklink-panel").panel("open");
							return false;
						}
					});
					this._on( document, {
						"pagebeforechange": function(){
							this.element.find(".jqm-quicklink-panel").panel("close");
							this.element.find(".jqm-quicklink-panel .ui-btn-active").removeClass("ui-btn-active");
						}
					});
					if( $(h2dictionary).length > 0 ){
						this.element.prepend(panel)
						this.element.find(".jqm-quicklink-panel").panel().find("ul").listview();
					}
					$.each(h2dictionary,function(id,text){
						self.element.find(".jqm-quicklink-panel ul").append("<li><a href='#"+id+"'>"+text+"</a></li>");
					});
					self.element.find(".jqm-quicklink-panel ul").listview("refresh");

			}
		});
	});
	$( document ).bind( "pagecreate create", function( e ) {
		var initselector = $.mobile.h2linker.prototype.options.initSelector;
		if($(e.target).data("quicklinks")){
			$(e.target).h2linker();
		}
	});
})( jQuery );

