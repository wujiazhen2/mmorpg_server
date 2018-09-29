package com.qworldr.logic.player.controller;


import com.qworldr.annotation.SocketController;
import com.qworldr.annotation.SocketRequest;
import com.qworldr.logic.player.Player;
import com.qworldr.logic.player.protocal.TestPlayerReq;
import com.qworldr.session.Session;
import org.springframework.stereotype.Component;

@SocketController
public class PlayerController {

    @SocketRequest
    public void test(Player player, TestPlayerReq testPlayerReq){
        System.out.println(player.getName());
    }
}
