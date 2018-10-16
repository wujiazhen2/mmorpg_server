package com.qworldr.mmorpg.logic.player;

import com.qworldr.mmorpg.bean.IdentityProvider;
import com.qworldr.mmorpg.logic.player.enu.RoleType;
import com.qworldr.mmorpg.provider.EntityProvider;
import com.qworldr.mmorpg.session.Session;
import com.qworldr.mmorpg.logic.player.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PlayerManager implements IdentityProvider<Player> {
    private Map<Session,Player> sessionPlayerMap =new ConcurrentHashMap<>();
    private Map<Player,Session> playerSessionMap =new ConcurrentHashMap<>();
    @Autowired
    private EntityProvider<PlayerEntity,Long> playerEntityProvider;


    public Player createPlayer(String account, String name, RoleType role, int sex){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setAccount(account);
        playerEntity.setRole(role);
        playerEntity.setSex(sex);
        playerEntity.setName(name);
        playerEntity.setLevel(1);
        playerEntityProvider.save(playerEntity);
        Player player =new Player(playerEntity);
        return player;
    }
    public void loginPlayer(Session session,Player player){
        sessionPlayerMap.put(session,player);
        playerSessionMap.put(player,session);
    }
    public Session getSession(Player player){
        Session session = playerSessionMap.get(player);
        return session;
    }

    public Player getPlayer(Session session){
        return  sessionPlayerMap.get(session);
    }

    @Override
    public Player getIdentity(Session session) {
        return getPlayer(session);
    }
}
