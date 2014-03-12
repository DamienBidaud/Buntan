var global = this;

esgi = esgi ||  {};
esgi.form = esgi.form ||  {};

esgi.form.Form = function(cfg) {
	var me = this;
	me.cfg = cfg;
	me.el = $('<div class="form"/>');

	$(me.cfg.renderTo).append(me.el);
	me.el.append(me.el = $('<ul/>'));

	if (cfg.inputs) {
		$.each(cfg.inputs, function(idx, item) {
			me.addInput(item);
		});
	}
	var submit = $('<button>Envoyer</button>');

	me.el.append(submit);

	submit.on('click', function() {
		var data = {};
		for (var name in me._inputs)  {
			data[name] = me._inputs[name].getValue();
			console.log(data);
		}

		$.ajax({
			url: cfg.url,
			data: data,
			method: 'POST',
			success: function(resp) {
				alert('FINI');
			}
		});
	})
}

esgi.form.Form.prototype = {
	addInput: function(inputCfg) {
		var me = this,
			line = $('<li/>');
		me._inputs = me._inputs ||  {};
		me.el.append(line);
		inputCfg.renderTo = line;
		me._inputs[inputCfg.name] = new esgi.form.inputs[inputCfg.type](inputCfg);
	}
}

esgi.form.inputs = esgi.form.inputs || {};

var proto = {
	init: function() {
		var me = this;
		$(me.cfg.renderTo).append(me.el);
	},
	getValue: function() {
		return this.el.val();
	}
};

esgi.form.inputs.Text = function(cfg) {
	this.cfg = cfg;
	this.el = $('<input/>');
	this.init();
}
esgi.form.inputs.Text.prototype = proto;

esgi.form.inputs.Textarea = function(cfg) {
	this.cfg = cfg;
	this.el = $('<textarea/>');
	this.init();
}

esgi.form.inputs.Textarea.prototype = proto;