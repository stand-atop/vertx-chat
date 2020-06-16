package com.spring.vertx.controller;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;
import com.nhncorp.mods.socket.io.spring.DefaultEmbeddableVerticle;

public class VertxSample extends DefaultEmbeddableVerticle {
	private static SocketIOServer io = null;

	@Override
	public void start(Vertx vertx) { //DefaultEmbeddableVerticle를 상속받아서 start()메소드를 오버라이딩 해서 작성
		int port = 12345;
		HttpServer server = vertx.createHttpServer();
		
		io = new DefaultSocketIOServer(vertx, server); //vertx정보를 받아 서버(자기자신) 정보로 실행?
		
		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			public void handle(final SocketIOSocket socket) {
				
				//채팅이 작성될 때마다 매번 실행됨 - 서버측에서 다시 사용자들에게 보내주는 구조
				socket.on("msg", new Handler<JsonObject>() {//main.jsp에서 soket.emit()에서 abc라는 이름으로 받아옴
					public void handle(JsonObject event) { //event로 받아옴
						System.out.println("handler ::: " + event.getString("msg"));  //msg는 파라미터 이름으로 두개이상 받을 수 있음
						System.out.println("handler ::: " + event.getString("id"));
						io.sockets().emit("response", event); //사용자에게 다시 response로 보내줌. main.jsp에서 soket.on(response)으로 받고있음 
													//event를 다시 전달함. 서버에 연결되어 있는 모두에게 전달.
						io.sockets().emit("aaa", event);
					}
				});
				
			}
		});
		
		server.listen(port);
	}

}
