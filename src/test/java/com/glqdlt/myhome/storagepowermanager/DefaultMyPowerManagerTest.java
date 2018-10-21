package com.glqdlt.myhome.storagepowermanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@ActiveProfiles("prod")
@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultMyPowerManagerTest {

    @Autowired
    DefaultMyPowerManager defaultMyPowerManager;

    @Test
    public void contrust() {
    }

    @Test
    public void startUp() {

        defaultMyPowerManager.startUp();

    }

    @Test
    public void shutdown() {

        defaultMyPowerManager.shutdown(0);
    }

    @Test
    public void reBoot() {

        defaultMyPowerManager.reBoot(0);
    }
}
