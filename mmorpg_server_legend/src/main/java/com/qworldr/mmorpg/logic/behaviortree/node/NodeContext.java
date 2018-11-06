package com.qworldr.mmorpg.logic.behaviortree.node;

import com.qworldr.mmorpg.logic.map.object.BiologyObject;

import java.util.Map;

/**
 * @Author wujiazhen
 */
public class NodeContext {

    private BiologyObject own;
    private Map<String,Object> props;
    public  static NodeContext valueOf(BiologyObject own){
        NodeContext nodeContext = new NodeContext();
        nodeContext.own=own;
        return nodeContext;
    }
    public  static NodeContext valueOf(BiologyObject own, Map props){
        NodeContext nodeContext = new NodeContext();
        nodeContext.own=own;
        nodeContext.props=props;
        return nodeContext;
    }

    public BiologyObject getOwn() {
        return own;
    }

    public void setOwn(BiologyObject own) {
        this.own = own;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }
}
