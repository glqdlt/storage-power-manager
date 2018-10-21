package com.glqdlt.myhome.storagepowermanager;

import com.glqdlt.myhome.storagepowermanager.executer.WakeOnLanExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("classpath:power-config-${spring.profiles.active}.properties")
@Configuration
public class PowerManagerConfig {

    @Value("${ssh.user.id}")
    private String userId;
    @Value("${ssh.user.pw}")
    private String userPw;
    @Value("${ssh.host.ip}")
    private String host;
    @Value("${ssh.host.port}")
    private Integer sshPort;
    @Value("${ssh.root.pw}")
    private String rootPw;

    @Value("${wol.broadcast.port}")
    public Integer wolPort;
    @Value("${wol.broadcast.address}")
    public String wolBroadCast;
    @Value("${wol.system.first.mac}")
    public String mac;



    @Bean
    public WakeOnLanExecutor wakeOnLanExecutor(){
        WakeOnLanExecutor wakeOnLanExecutor = new WakeOnLanExecutor();
        return wakeOnLanExecutor;
    }

    @Bean
    public PowerManagerSetting powerManagerSetting(){
        return PowerManagerSetting.builder()
                .host(this.host)
                .sshPort(this.sshPort)
                .id(this.userId)
                .wolPort(this.wolPort)
                .password(this.userPw)
                .rootPassword(this.rootPw)
                .broadCast(this.wolBroadCast)
                .mac(this.mac)
                .build();
    }

    @Bean
    public DefaultMyPowerManager defaultMyPowerManager(){
        DefaultMyPowerManager defaultMyPowerManager = new DefaultMyPowerManager(powerManagerSetting(),wakeOnLanExecutor());
        return defaultMyPowerManager;
    }



}
