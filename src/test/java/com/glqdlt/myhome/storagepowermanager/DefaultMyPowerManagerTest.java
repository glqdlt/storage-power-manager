package com.glqdlt.myhome.storagepowermanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultMyPowerManagerTest {

    @Autowired
    PowerManager powerManager;

    @Test
    public void contrust() {
    }

    @Test
    public void startUp() {

        powerManager.startUp();

    }

    @Test
    public void shutdown() {

        powerManager.shutdown();
    }

    @Test
    public void reBoot() {

        powerManager.reBoot();
    }
}
