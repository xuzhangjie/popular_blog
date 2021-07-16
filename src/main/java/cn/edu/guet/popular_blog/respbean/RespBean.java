package cn.edu.guet.popular_blog.respbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pangjian
 * @ClassName RespBean
 * @Description 公共返回对象
 * @date 2021/6/2 20:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    // 状态码
    private long code;
    // 信息
    private String message;
    // 返回对象
    private Object obj;

    /**
     * @Description: 成功返回结果
     * @Param message:
     * @return cn.guet.server.RespObject.RespBean
     * @date 2021/6/2 20:29
    */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    /**
     * @Description: 成功返回结果，重载，但这个返回结果有对象
     * @Param message:
     * @Param obj:
     * @return cn.guet.server.RespObject.RespBean
     * @date 2021/6/2 20:30
    */
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }

    /**
     * @Description: 失败返回结果
     * @Param message:
     * @return cn.guet.server.RespObject.RespBean
     * @date 2021/6/2 20:31
    */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }


    /**
     * @Description: 失败返回结果
     * @Param message:
     * @Param obj:
     * @return cn.guet.server.RespObject.RespBean
     * @date 2021/6/2 20:32
    */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }

}
