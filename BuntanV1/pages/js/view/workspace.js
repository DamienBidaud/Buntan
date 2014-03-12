


var menu = new esgi.html.Menu({
	cls : 'mymenu',
	renderTo : '#menuWrapper',
	items : [{
		label : 'Mon compte',
		callback : function(){
			console.log('Mon Compte', arguments);
		}
	},{
		label : 'Mes services',
		callback : function(){
			console.log('Mes services');
		}

	}, {
		label : 'Le catalogue',
		items : [
		{
			label : 'Les catégories',
			callback : function(){
				//Display Category List
			}
		},
		{
			label : 'Ajouter une catégorie',
			callback : function(){
				new esgi.form.Form({
					 renderTo : "#workspace",
					 url : '/services/category/add.php',
					 inputs : [
					 {
					 	name : 'title',
					 	type : 'Text',
					 	emptyText : 'Saisir le nom de la catégorie'
					 },{
					 	type : 'Textarea',
					 	name : 'description'
					 }
					 ]
				});
				//Display Category Add Form
			}
		},
		{
			label : 'Ajouter un produit',
			callback : function(){
				//Display Product Add Form
			}
		}
		]

	}]
})