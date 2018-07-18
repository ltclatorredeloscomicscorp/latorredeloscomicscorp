$(document).ready(function(){
	$('#inicio').hide();
	var ventana_alto = $(window).height();
	$('#port').css('height', ventana_alto)
	$('#ex').css('top', ventana_alto);
	$('#enter').click(function(){
		$('#port').hide();
		$('#inicio').show();
	});
	$('.scene').hide();
	$('.scene').show();
});