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