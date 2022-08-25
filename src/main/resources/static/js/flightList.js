$(function(){
	$.get("/book/flightList", function(listHtmlData){
		$("#Layer-div2>.categorys").html(listHtmlData);
	});
});