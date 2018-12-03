package com.qworldr.mmorpg.common.behaviortree;

import com.qworldr.mmorpg.common.behaviortree.node.BCompositeNode;
import com.qworldr.mmorpg.common.behaviortree.node.BTreeNode;

import java.util.Stack;

/**
 * @Author wujiazhen
 */
public class BehaviorTreeBuilder {
    private Stack<BCompositeNode> compositeNodes;
    /**
     *  根节点必须是一个组合节点。
     */
    private BCompositeNode root;
    public BehaviorTreeBuilder(){
        this.compositeNodes=new Stack<>();
    }
    public BehaviorTreeBuilder addBehaviorNode(BTreeNode node){
        if(root==null ){
            if( node instanceof BCompositeNode){
                this.root = (BCompositeNode) node;
            }else{
                throw new IllegalArgumentException("第一个节点必须是BCompositeNode");
            }
        }
        //只有组合节点才加入栈中
        if(node instanceof BCompositeNode){
          compositeNodes.push((BCompositeNode) node);
        }
        compositeNodes.peek().addChild(node);
        return this;
    }

    /**
     * 回退到上一层
     * @return
     */
    public BehaviorTreeBuilder back(){
        this.compositeNodes.pop();
        return this;
    }
    public BehaviorTree  build(){
        return new BehaviorTree(root);
    }
}
