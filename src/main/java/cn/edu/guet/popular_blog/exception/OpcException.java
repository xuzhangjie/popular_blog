package cn.edu.guet.popular_blog.exception;

/**
 * @author pangjian
 * @ClassName OpcException
 * @Description 自定义异常处理
 * @date 2021/7/6 17:28
 */

public class OpcException extends RuntimeException {

    public OpcException(){
        super();
    }

    public OpcException(String msg){
        super(msg);
    }

}
