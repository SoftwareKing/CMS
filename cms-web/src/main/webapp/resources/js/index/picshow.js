$(function(){
	$("#rollpic").cycle({
		fx:"fadeout",
		pauseOnHover:true,
		Loader:true,
		slides:"> a",
		caption:"#rollCaption span",
		captionTemplate:"{{title}}",
		pager:"#rollPager"
	});
	$("#rollpic").hover(function(){
		$("#rollCaption").slideDown(200);
	},function(){
		$("#rollCaption").slideUp(200);
	})
	$("#rollCaption").css("opacity",0.5);
	
});