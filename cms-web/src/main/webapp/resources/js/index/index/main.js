$(function(){
$(".channel_img").css("opacity","0.5");
	$(".channel_img").hover(function(){
		$(this).css("opacity","1.0");
	},function(){
		$(this).css("opacity","0.5");
});
	$("#search_btn").click(function(){
		var sc = $("#search_con").val();
		if(sc=="") {
			alert("你需要输入相应的检索内容");
		} else {
			window.location.href=$("#ctx").val()+"/search/"+sc;
		}
	});
	
});