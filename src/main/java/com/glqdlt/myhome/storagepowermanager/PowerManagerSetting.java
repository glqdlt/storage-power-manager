package com.glqdlt.myhome.storagepowermanager;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class PowerManagerSetting {

    private String host;
    private Integer wolPort;
    private Integer sshPort;
    private String broadCast;
    private String id;
    private String password;
    private String rootPassword;

    private String mac;

}
