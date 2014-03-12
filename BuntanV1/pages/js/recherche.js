$(document).ready(selectDep);

function selectDep(){
	$("#region").change(function(){
		var val=0;
		$.ajax({
			
			url:"departement.php",
			
			type:"POST",
			
			data:{"region": $(this).val()},
			dataType:"json",
			success:function(departement){
				if($("#departement>option").length > 1){
					removeElement();
				}
				val =val+1;
				$("#departement").attr('disabled',false);
				$(departement).each(function(){
					$("#departement").append("<option value="+val+">"+this+"</option>");
				});
			},
			
			error:function(xhr, etat, erreur){
				console.log(xhr.status+":"+erreur);
			}
		});
		
	}); 
	$("input[type=checkbox]").click(function(){
		if($("#rest").attr("checked")){
			$("#restList").attr('disabled',false);
		}
		else{
			$("#restList").attr('disabled',true);
		}
	});
}


function removeElement(){
	for(var i =0 ; i < $("#departement>option").length; i++){
		$("#departement option[value="+i+"]").remove();
	}

}