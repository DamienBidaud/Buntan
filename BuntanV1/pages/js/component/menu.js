var global = this;

global.esgi = global.esgi ||  {};
global.esgi.html = global.esgi.html ||  {};

global.esgi.html.Menu = function(cfg) {
	this.cfg = cfg;
	this.init();
}

global.esgi.html.Menu.prototype = {
	init: function() {
		var me = this;
		me.container = $(['<ul class="', me.cfg.cls || '', '"/>'].join(''));
		$(me.cfg.renderTo).append(me.container);
		$.each(me.cfg.items, function(i, item) {
			// item, i...
			me.addMenuItem(item, me.container);
		})

		/*
		Same as $.each
		for (var i=0, len = me.cfg.items.length;i<len;i++){
			var item = me.cfg.items[i];
			// item, i...
			
		}
		*/
	},
	addMenuItem: function(item, container) {
		var me = this, li = $('<li/>');
		li.text(item.label);
		if (item.callback)
			li.on('click', function(e) {
				item.callback(e, item);
			});
		container.append(li);
		if (item.items) {
			var ctn = $('<ul/>');
			ctn.css({
				display : 'none'
			});
			container.append(ctn);
			$.each(item.items, function(i, item) {
				// item, i...
				me.addMenuItem(item, ctn);
			});
			li.on('click', function(){
				ctn.toggle(100);
			})
		}

	}
}