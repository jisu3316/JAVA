<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title> Spring Board </title>
	<meta charset="utf-8">
	<style>
		a{text-decoration:none}
	</style>
	<!-- Kakao Map API Key -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=11400a9267d93835389eb9255fcaad0b&libraries=services,clusterer"></script>

</head>
<div id="map" style="width:100%;height:350px;"></div>
<script>
	//var inputData = ['서울역']
	/*var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		mapOption = {
			center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level: 3 // 지도의 확대 레벨
		};
	// 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption);*/
</script>

<script>
	var inputData = ['가디역']
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		mapOption = {
			center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level: 3 // 지도의 확대 레벨
		};
	// 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption);
	var count = 0;
	var ps = new kakao.maps.services.Places();
	var bounds = new kakao.maps.LatLngBounds();
	if (inputData != null) {
		kewwordSearch(inputData[count]);
	}
	function kewwordSearch(keword) {
		ps.keywordSearch(keword, placesSearchCB);
		count = count + 1;
	}
	function placesSearchCB(data, status, pagination) {
		if (status === kakao.maps.services.Status.OK) {
			displayMarker(data[0]);
			bounds.extend(new kakao.maps.LatLng(data[0].y, data[0].x));
				if (count < inputData.length) {
					kewwordSearch(inputData[count]);
				} else if (count == inputData.length) {
					setBounds();
				}
		}
	}
	function displayMarker(place) {
		var marker = new kakao.maps.Marker({
			map: map,
			position: new kakao.maps.LatLng(place.y, place.x),
		});
		kakao.maps.event.addListener(marker, 'click', function () {
			var position = this.getPosition();
			var url = 'https://map.kakao.com/link/map/' + place.id;
			window.open(url, '_blank');
		});
	}
	function setBounds() {
		map.setBounds(bounds, 90, 30, 10, 30);
	}

</script>




</body>
</html>