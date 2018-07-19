//document.oncontextmenu = function(){return false;}
$(document).ready(function(){
	$.get("/car", function(data){
		$('#ped').html(data);
	});
	$('#menu2').hide();
	$('#chat').hide();
	$('#car').hide();
	$('#menu').click(function(){
		$('#menu2').show();
	});
	$('.closeMenu').click(function(){
		$('#menu2').hide();
	});
	$('#chaticon').click(function(){
		$('#chat').show();
		var id = $('#idUser').val();
		$.post("/messages", {id:id}, function(response) {
			$('#conversacion').innerHTML = '';
			$('#conversacion').html(response);
		});
	});
	$('#send').click(function(){
		var message = $('#message').val();
		$.post("/messages/add", {message:message}, function(response) {
			//$('#message').val('');
			$('#conversacion').innerHTML = '';
			$('#conversacion').html(response);
		});
	});
	$('#closeChat').click(function(){
		$('#chat').hide();
	});
	$('#opencar').click(function(){
		$('#car').show();
	});
	$('#closecar').click(function(){
		$('#car').hide();
	});
	$('.buttoncar').click(function(){
		alert($(this).attr('id'));
		$.get("/car?id="+$(this).attr('id'), function(data){
			$('#ped').html(data);
		});
	});
});