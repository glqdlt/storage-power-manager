package com.glqdlt.myhome.storagepowermanager;

import com.glqdlt.myhome.storagepowermanager.executer.ShutdownCommander;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Slf4j
public class DefaultMyPowerManager implements PowerManager {

    private PowerManagerSetting powerManagerSetting;


    public DefaultMyPowerManager(PowerManagerSetting powerManagerSetting) {
        this.powerManagerSetting = powerManagerSetting;
    }

    public DefaultMyPowerManager() {
    }

    private void execute(ShutdownCommander command, Integer timer){
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(this.powerManagerSetting.getId(), this.powerManagerSetting.getHost(), this.powerManagerSetting.getPort());
            session.setPassword(this.powerManagerSetting.getPassword());
            session.setConfig(config);
            session.connect();

            log.debug("Connected ==> " + this.powerManagerSetting.getId() + "@" + this.powerManagerSetting.getHost());

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setPty(true);
            ((ChannelExec) channel).setCommand("sudo -S -p '' " + command.getCommand() + timer);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            try (InputStream in = channel.getInputStream();
                 OutputStream outputStream = channel.getOutputStream()) {

                channel.connect();
                outputStream.write((this.powerManagerSetting.getRootPassword() + "\n").getBytes());
                outputStream.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            } finally {
                channel.disconnect();
                session.disconnect();

                log.debug("Disconnected ==> " + this.powerManagerSetting.getId() + "@" + this.powerManagerSetting.getHost());

            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void shutdown(Integer timer) {

        execute(ShutdownCommander.SHUTDOWN,timer);

    }

    @Override
    public void startUp() {

    }

    @Override
    public void reBoot(Integer timer) {
        execute(ShutdownCommander.RESTART,timer);
    }
}
