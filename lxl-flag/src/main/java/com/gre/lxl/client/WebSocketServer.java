package com.gre.lxl.client;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lxl
 * @date 2022/3/2 14:12
 */
@Component
@ServerEndpoint("/websocket/{userName}")
public class WebSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //在线客户端数量
    private static int  onlineCount = 0;
    //Map用来存储已连接的客户端信息
    private static final ConcurrentHashMap<String, SocketDomain> websocketMap = new ConcurrentHashMap<>();
    //当前连接客户端的Session信息
    private Session session;
    //当前客户端名称
    private String userName="";

    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName){
        if(!websocketMap.containsKey(userName)){
            WebSocketServer.onlineCount++;
        }
        this.session = session;
        this.userName = userName;
        SocketDomain socketDomain = new SocketDomain();
        socketDomain.setSession(session);
        socketDomain.setUri(session.getRequestURI().toString());
        websocketMap.put(userName, socketDomain);
        logger.info("用户连接："+ userName + "，人数:"+onlineCount);
        try {
            sendMessage("连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClose
    public void onClose(){
        if(websocketMap.containsKey(userName)){
            websocketMap.remove(userName);
            onlineCount--;
            logger.info("用户关闭："+ userName + "，人数:"+onlineCount);
        }
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        if(StringUtil.isNotEmpty(message)){
            //业务业务处理 可以做token鉴权验证
            logger.info("收到用户消息:"+userName+",报文:"+message);
            sendMessage(message);
        }
    }


    //给当前客户端发消息
    private void sendMessage(String obj) {
        synchronized (session) {
            this.session.getAsyncRemote().sendText(obj);
        }
    }

    //给指定客户端发送消息，通过UserName找到Session发送
    private void sendMessageTo(String userName,String obj){
        SocketDomain socketDomain = websocketMap.get(userName);
        try {
            if(socketDomain !=null){
                socketDomain.getSession().getAsyncRemote().sendText(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    //给除了当前客户端的其他客户端发消息
    private void sendMessageToAllExpectSelf(String message, Session fromSession) {
        for(Map.Entry<String, SocketDomain> client : websocketMap.entrySet()){
            Session toSeesion = client.getValue().getSession();
            if( !toSeesion.getId().equals(fromSession.getId())&&toSeesion.isOpen()){
                toSeesion.getAsyncRemote().sendText(message);
                logger.info("服务端发送消息给"+client.getKey()+":"+message);
            }
        }
    }
    //给包括当前客户端的全部客户端发送消息
    private void sendMessageToAll(String message){
        for(Map.Entry<String, SocketDomain> client : websocketMap.entrySet()){
            Session toSeesion = client.getValue().getSession();
            if(toSeesion.isOpen()){
                toSeesion.getAsyncRemote().sendText(message);
                logger.info("服务端发送消息给"+client.getKey()+":"+message);
            }
        }
    }
    //给外部调用的方法接口
    public void sendAll(String Message){
        sendMessageToAll(Message);
    }

    //给外部调用的方法接口
    public void sendToName(String name, String Message){
        sendMessageTo(name,Message);
    }
}
