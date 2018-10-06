package com.qworldr.mmorpg.logic.player.controller;


import com.qworldr.mmorpg.annotation.SocketController;
import com.qworldr.mmorpg.annotation.SocketRequest;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.player.protocal.TestPlayerReq;

@SocketController
public class PlayerController {

    @SocketRequest
    public void test(Player player, TestPlayerReq testPlayerReq){
    }
}
