package com.dav01.corp.bonzong.config;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
public class SessionExpiredListener implements ApplicationListener<SessionDestroyedEvent> {

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        System.out.println("come too session Mananger");
        event.getSecurityContexts().forEach(securityContext -> {
            // 处理失效会话的逻辑，例如记录日志、发送通知等
            System.out.println("会话失效：" + securityContext.getAuthentication().getName());
        });
    }
}
