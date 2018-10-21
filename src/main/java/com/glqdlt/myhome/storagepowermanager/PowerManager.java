package com.glqdlt.myhome.storagepowermanager;

import org.springframework.stereotype.Component;

public interface PowerManager {

    void shutdown();

    void startUp();

    void reBoot();

}
