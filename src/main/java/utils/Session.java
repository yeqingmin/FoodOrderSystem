package utils;

import pojo.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session {
    public static Integer getCurrentId(HttpServletRequest request){
        Integer currentId=0;
        //根据session获取当前用户的id
        HttpSession session = request.getSession();
        System.out.println("session "+session);
        if (session != null) {
            // 从session中获取Constants.USER_SESSION属性
            Login userSession = (Login) session.getAttribute(Constants.USER_SESSION);

            // 检查userSession是否为null，然后根据需要处理
            if (userSession != null) {
                // 处理userSession对象
                // 例如，将其转发到JSP页面
                currentId=userSession.getCorrespondingID();
            }
        }
        return currentId;
    }
}
