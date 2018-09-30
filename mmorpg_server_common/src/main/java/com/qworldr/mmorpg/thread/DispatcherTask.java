package com.qworldr.mmorpg.thread;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public interface DispatcherTask extends Runnable {
    int getDispatchCode();
}
