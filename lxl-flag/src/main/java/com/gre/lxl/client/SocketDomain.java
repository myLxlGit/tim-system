package com.gre.lxl.client;

import lombok.Data;

import javax.websocket.Session;

/**
 * @author lxl
 * @date 2022/3/2 14:14
 */
@Data
public class SocketDomain {

    private Session session;

    private String uri;
}
