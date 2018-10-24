package com.qworldr.mmorpg.logic.player.manager;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.qworldr.mmorpg.bean.IdentityProvider;
import com.qworldr.mmorpg.common.utils.EventPublisher;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeSourceType;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import com.qworldr.mmorpg.logic.player.Player;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import com.qworldr.mmorpg.logic.player.enu.RoleType;
import com.qworldr.mmorpg.logic.player.event.PlayerLoginEvent;
import com.qworldr.mmorpg.logic.player.resource.PlayerInitializationResource;
import com.qworldr.mmorpg.logic.player.resource.PlayerLevelResource;
import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.provider.ResourceProvider;
import com.qworldr.mmorpg.session.Session;
import com.qworldr.mmorpg.session.TcpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PlayerManager implements IdentityProvider<Player> {
    private BiMap<Session,Player> sessionPlayerMap = Maps.synchronizedBiMap(HashBiMap.create());
    @Autowired
    private EntityProvider<PlayerEntity,Long> playerEntityProvider;
    @Autowired
    private ResourceProvider<PlayerLevelResource,Integer> playerLevelResourceResourceProvider;
    @Autowired
    private ResourceProvider<PlayerInitializationResource,RoleType> playerInitializationResourceProvider;
    @Autowired
    private EventPublisher eventPublisher;
    public PlayerEntity createPlayerEnttiy(String account, String name, RoleType role, int sex){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setAccount(account);
        playerEntity.setRole(role);
        playerEntity.setSex(sex);
        playerEntity.setName(name);
        playerEntity.setLevel(1);
        PlayerLevelResource playerLevelResource = playerLevelResourceResourceProvider.get(1);
        playerEntity.setStatPoint(playerLevelResource.getStatPoint());
        //设置出生地 初始化属性 hp mp
        PlayerInitializationResource playerInitializationResource = playerInitializationResourceProvider.get(playerEntity.getRole());
        playerEntity.setMapId(playerInitializationResource.getMapId());
        playerEntity.setX(playerInitializationResource.getPosition().getX());
        playerEntity.setY(playerInitializationResource.getPosition().getY());
        playerEntity.setHp(playerInitializationResource.getAttrs().get(AttributeType.HP));
        playerEntityProvider.save(playerEntity);
        return playerEntity;
    }


    public void loginPlayer(Session session,Player player){
        player.setSession((TcpSession) session);
        sessionPlayerMap.put(session,player);
        //发布玩家登录事件
        eventPublisher.publishEvent(new PlayerLoginEvent(player));
    }
    public Session getSession(Player player){
        Session session = sessionPlayerMap.inverse().get(player);
        return session;
    }

    public Player getPlayer(Session session){
        return  sessionPlayerMap.get(session);
    }

    @Override
    public Player getIdentity(Session session) {
        return getPlayer(session);
    }

    @Override
    public void clearIdentity(Session session) {
        sessionPlayerMap.remove(session);
    }


}
