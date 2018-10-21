package com.glqdlt.myhome.storagepowermanager.executer;

public class ShutdownCommanderFactory {
    public static ShutdownCommander generate(String command){
        if (command.toUpperCase().equals(ShutdownCommander.SHUTDOWN.name())) {
            return ShutdownCommander.SHUTDOWN;
        } else if (command.toUpperCase().equals(ShutdownCommander.RESTART.name())) {
            return ShutdownCommander.RESTART;
        } else if (command.toUpperCase().equals(ShutdownCommander.WAKE.name())) {
            return ShutdownCommander.WAKE;
        }
        return null;
    }
}
