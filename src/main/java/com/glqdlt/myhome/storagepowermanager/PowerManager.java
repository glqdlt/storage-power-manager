package com.glqdlt.myhome.storagepowermanager;

import org.springframework.stereotype.Component;

public interface PowerManager {
    void shutdown(Integer timer);

    void startUp();

    void reBoot(Integer timer);

}
