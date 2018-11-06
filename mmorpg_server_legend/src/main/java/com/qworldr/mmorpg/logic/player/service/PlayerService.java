package com.qworldr.mmorpg.logic.player.service;

import com.qworldr.mmorpg.common.constants.MessageId;
import com.qworldr.mmorpg.common.constants.StatusCode;
import com.qworldr.mmorpg.common.exception.MessageException;
import com.qworldr.mmorpg.common.resp.Status;
import com.qworldr.mmorpg.common.utils.EventPublisher;
import com.qworldr.mmorpg.common.utils.PacketUtils;
import com.qworldr.mmorpg.common.utils.SessionUtils;
import com.qworldr.mmorpg.logic.map.SceneManager;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import com.qworldr.mmorpg.logic.player.event.PlayerModuleInitializeEvent;
import com.qworldr.mmorpg.logic.player.manager.PlayerManager;
import com.qworldr.mmorpg.logic.player.protocal.CreateRoleReq;
import com.qworldr.mmorpg.logic.player.protocal.CreateRoleResp;
import com.qworldr.mmorpg.logic.player.protocal.PlayerLoginResp;
import com.qworldr.mmorpg.logic.player.protocal.RoleListResp;
import com.qworldr.mmorpg.logic.player.protocal.vo.PlayerInfo;
import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.session.TcpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wujiazhen
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerManager playerManager;
    @Autowired
    private EntityProvider<PlayerEntity, Long> playerEntityProvider;
    @Autowired
    private SceneManager sceneManager;
    public CreateRoleResp createRole(TcpSession session, CreateRoleReq req) {
        PlayerEntity playerEnttiy = playerManager.createPlayerEnttiy(SessionUtils.getAccount(session), req.getPlayerName(), req.getRole(), req.getSex());
        CreateRoleResp createRoleResp = new CreateRoleResp();
        createRoleResp.setStatus(Status.valueOf(StatusCode.SUCCESS, MessageId.CREATE_ROLE_SUCCESS));
        createRoleResp.setId(playerEnttiy.getId());
        return createRoleResp;
    }

    public void roleLogin(TcpSession session, long playerId) {
        //判断账号是否登录
        if (!SessionUtils.isLogin(session)) {
            throw new MessageException(MessageId.NO_LOGIN);
        }
        //判断角色是否存在且属于当前账号
        PlayerEntity playerEntity = playerEntityProvider.get(playerId);
        if (playerEntity == null || !SessionUtils.getAccount(session).equals(playerEntity.getAccount())) {
            throw new MessageException(MessageId.PlAYER_NOT_EXISTS);
        }
        Player player = new Player(playerEntity);
        //玩家各个模块初始化，各个模块接受事件，对Player对象初始化
        EventPublisher.getInstance().publishEvent(new PlayerModuleInitializeEvent(player));
        //登录
        playerManager.loginPlayer(session, player);
        PacketUtils.sendPacket(session,new PlayerLoginResp(playerId,Status.valueOf(StatusCode.SUCCESS,MessageId.ROLE_LOGIN_SUCCESS)));
        //TODO 进入场景
        sceneManager.enter(player);
    }

    public RoleListResp roleList(String account) {
        List<PlayerEntity> playerEntitys = playerEntityProvider.query("selectByAccount", account);
        RoleListResp roleListResp = new RoleListResp();
        roleListResp.setPlayerInfos(new ArrayList<>());
        playerEntitys.forEach(playerEntity -> {
            roleListResp.getPlayerInfos().add(new PlayerInfo(playerEntity.getId(), playerEntity.getName(), playerEntity.getLevel()
                    , playerEntity.getRole(), playerEntity.getSex()));
        });
        return roleListResp;
    }
}
