<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="/Vertx/js/jquery-1.10.2.min.js"></script>
<script src="/Vertx/js/socket.io.js"></script>
<script>
	$(document).ready(function() {
		// 메시지를 보냄
		var socket = io.connect("http://192.168.0.25:12345");  //서버연결 
		socket.on('response', function(msg){// 서버로부터 채팅메세지를 계속 받고있음. 계속 받아줘야 늦지 않게 보내줌 
			$('#msgs').append(msg.msg+'<BR>');		// 채팅 메세지 받아 출력
			//$('#msgs').append(msg.id+'<BR>');			
/* 			//조건을 넣을 때 - 해당 아이디인 사람에게로만 보내짐
			if(msg.id='java'){
				$('#msgs').append(msg.msg+'<BR>');
			} */
		});
		socket.on('aaa', function(msg){
			$("h1").html(msg.id);
		});
		
		
		// 텍스트박스내부의 채팅 내용 보내기
		$("#sendBtn").bind("click", function() {	
			//var msg = $("input[name=chat]").val();
			var msg = $("#chat").val(); //val():채팅창 textbox
			//socket.emit('abc', {msg:msg});
			socket.emit('msg',{msg:msg, id:"java"}); //파라미터이름:값
		});
	});
</script>
</head>
<body>
	<h1>Main</h1>
	<!-- <input type="text" name="chat" /> --><!-- name속성은 파라미터나 클래스에서 사용하는 속성 --> 
	<input type="text" id="chat" /><!-- id속성은 css나 스크립xm에서 사용하는 속성?/class는 한꺼번에 변경할 묶음 속성 -->
	<input type="button" value="send" id="sendBtn" />
	 <span id="msgs"></span>
</body>
</html>