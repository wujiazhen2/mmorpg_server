global.proto=require("./proto_pb");
class ProtocalManager{

    constructor(){
        this.idToType=new Map();
        //登录请求
        this.idToType.set(11111,proto.LoginReq);
        //登录响应
        this.idToType.set(11112,proto.LoginResp);
        //注册帐号
        this.idToType.set(11113,proto.RegisterAccountReq);
        //注册帐号响应
        this.idToType.set(11114,proto.RegisterAccountResp);
        //创建角色
        this.idToType.set(11115,proto.CreateRoleReq);
        //创建角色响应
        this.idToType.set(11116,proto.CreateRoleResp);
        //角色登录响应
        this.idToType.set(11117,proto.RoleLoginReq);
        //角色进入地图响应
        this.idToType.set(11118,proto.PlayerEnterWorldResp);
        //角色登录响应
        this.idToType.set(11119,proto.PlayerLoginResp);
        //错误响应
        this.idToType.set(10000,proto.ErrorResp);
        //请求角色列表
        this.idToType.set(11120,proto.RoleListReq);
        //角色列表响应
        this.idToType.set(11121,proto.RoleListResp);
        //移动请求
        this.idToType.set(11122,proto.MoveReq);
        //玩家进去消息区域广播
        this.idToType.set(11123,proto.ObjectEnterSyncResp);
        //进入新区域同步
        this.idToType.set(11124,proto.RegionEnterResp);
        //退出区域
        this.idToType.set(11125,proto.RegionLevelResp);
        
        this.nameToType=new Map();
        let nameToType=this.nameToType;
        this.idToType.forEach(function(v,k,map){
            // nameToType.put
            nameToType.set(v,k);
        })
    }
    get(id){
       return  this.idToType.get(id);
    }
    getId(type){
        return this.nameToType.get(type);
    }
    create(type){
        let o=new type()
        o.messageId=this.getId(type);
        return o;
    }
}
export let protocalManager = new ProtocalManager();
