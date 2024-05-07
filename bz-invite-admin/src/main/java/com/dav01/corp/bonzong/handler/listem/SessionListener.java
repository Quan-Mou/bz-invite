package com.dav01.corp.bonzong.handler.listem;

import com.dav01.corp.bonzong.util.MySessionContext;
import com.mysql.cj.Session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {


    private MySessionContext context = MySessionContext.getSessionContext();

    @Override
    public void sessionCreated(HttpSessionEvent e) {

        System.out.println("创建了 session'");
//        context.addSession(e.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {


        System.out.println("移除了session");


//        System.out.println("移除了sessionid是"+e.getSession().getId()); 删除map中的key value
//        context.delSession(e.getSession());
//        Session user = (Session) e.getSession().getAttribute(GlobalConstant.SESSION_NAME);
//             if (user!=null){
//                 //获取service
//                 GenericService genericService =(GenericService) getObjectFromApplication(e.getSession().getServletContext(),"genericService");
//                 //获取用户的sessionId和用户在线状态
//                 UserInfo tempUser = (UserInfo)genericService.findByid(UserInfo.class, user.getUid());
//                 //如果用户在线且sessionId和e.getSession().getId()相同说明下线，不是更替。
//                 //则修改用户的在线状态和session设置null。
//                 if(tempUser.getOnlinestatus().equals(GlobalConstant.TRUE) && e.getSession().getId().equals(tempUser.getSessionid())) {
//                     tempUser.setOnlinestatus(GlobalConstant.FALSE);
//                     tempUser.setSessionid(null);
//                     genericService.updateOne(tempUser);
//                /*     //如果直接关闭浏览器的话可以直接杀死session  不用写 会直接杀死session的
//                     e.getSession().invalidate();
//                     System.out.println("session杀死了");*/
//                 }
//             }
//        context.delSession(e.getSession());
    }

//    private Object getObjectFromApplication(ServletContext servletContext, String beanName){
//        //通过WebApplicationContextUtils 得到Spring容器的实例。
//        ApplicationContext application= WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        //返回Bean的实例。
//        return application.getBean(beanName);
//    }


}
