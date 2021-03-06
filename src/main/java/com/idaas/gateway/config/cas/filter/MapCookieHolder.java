/**
 * Created on [2020/2/20 16:22] by Administrator
 * <p>
 * 项目名称： cas TODO(项目名称)
 * <p>
 * 本程序版权属于福建慧政通信息科技有限公司所有。
 * 任何组织和个人未经福建慧政通信息科技有限公司许可与授权,不得擅自传播、复制、更改该程序的内容。
 * 本程序受版权法和国际条约的保护。如未经授权而擅自复制或传播本程序(或其中任何部分),
 * 将受到严厉的刑事及民事制裁，并将在法律许可的范围内受到最大可能的起诉!
 * <p>
 * ©2020 福建慧政通信息科技有限公司
 */
package com.idaas.gateway.config.cas.filter;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能： TODO(用一句话描述类的功能)
 *
 * ──────────────────────────────────────────
 *   version  变更日期    修改人    修改说明
 * ------------------------------------------
 *   V1.0.0   2020/2/20    Liush     初版
 * ──────────────────────────────────────────
 */
public class MapCookieHolder implements CookieHolder {

    private  ConcurrentHashMap<String ,HashMap<String,Object>> holder=new ConcurrentHashMap<>();

    //过期时间
    private long expireTime;





    public MapCookieHolder(Long expireTime) {
        if (expireTime==null){
            throw new RuntimeException("过期时间不能为空");
        }
        this.expireTime = expireTime;
    }

    @Override
    public Object getAttr(String userKey,String key) {
        if(holder.get(userKey)==null){
            return null;
        }
        return holder.get(userKey).get(key);
    }

    @Override
    public void setAttr(String userKey,String key, Object attr) {

        HashMap<String,Object> userHolder=holder.get(userKey);
        if(userHolder!=null){
            userHolder.put(key,attr);
        }else {
            userHolder=new HashMap<>();
            userHolder.put(key,attr);
            userHolder.put("expireTime",expireTime);
            holder.put(userKey,userHolder);
        }

    }
}
