/**
 * 
 */
var lat=0;
var lon=0;
var apiURI;
 $(function(){
	//사용자 ip 주소의 정보 위도,경도,국가,도시,동, 우편번호
	$.get("http://ip-api.com/json",function(data){
    	console.log(data);
    	console.log("ip-lat: "+data.lat);	
    	console.log("ip-lon: "+data.lon);		
    	//lat: 37.536
		//lon: 126.971
		lat=data.lat;
		lon=data.lon;
		//위경도를 이용하여 날씨정보 요청
		apiURI="https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=6d5759fbb75574c53062ce266b894e1f";
		exec();		
	})
	
});

function exec(){
	$.ajax({
		url: apiURI,
		dataType:"json",
		success: function(result){
			console.log(result);
			console.log("절대온도:"+result.main.temp);
			console.log("섭씨온도:"+parseInt(result.main.temp-273.15));
			console.log("날씨아이콘이름:"+result.weather[0].icon)
			
			var imgURL = "https://openweathermap.org/img/w/" + result.weather[0].icon + ".png";
			$("#w_icon").css("background-image", 'url('+imgURL+')');
			$("#temp").text(parseInt(result.main.temp-273.15));
		}
	});
}