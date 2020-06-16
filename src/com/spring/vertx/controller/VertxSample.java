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
	public void start(Vertx vertx) { //DefaultEmbeddableVerticle�� ��ӹ޾Ƽ� start()�޼ҵ带 �������̵� �ؼ� �ۼ�
		int port = 12345;
		HttpServer server = vertx.createHttpServer();
		
		io = new DefaultSocketIOServer(vertx, server); //vertx������ �޾� ����(�ڱ��ڽ�) ������ ����?
		
		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			public void handle(final SocketIOSocket socket) {
				
				//ä���� �ۼ��� ������ �Ź� ����� - ���������� �ٽ� ����ڵ鿡�� �����ִ� ����
				socket.on("msg", new Handler<JsonObject>() {//main.jsp���� soket.emit()���� abc��� �̸����� �޾ƿ�
					public void handle(JsonObject event) { //event�� �޾ƿ�
						System.out.println("handler ::: " + event.getString("msg"));  //msg�� �Ķ���� �̸����� �ΰ��̻� ���� �� ����
						System.out.println("handler ::: " + event.getString("id"));
						io.sockets().emit("response", event); //����ڿ��� �ٽ� response�� ������. main.jsp���� soket.on(response)���� �ް����� 
													//event�� �ٽ� ������. ������ ����Ǿ� �ִ� ��ο��� ����.
						io.sockets().emit("aaa", event);
					}
				});
				
			}
		});
		
		server.listen(port);
	}

}
