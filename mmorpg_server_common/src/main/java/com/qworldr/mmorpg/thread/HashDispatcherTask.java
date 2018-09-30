package com.qworldr.mmorpg.thread;

public abstract class HashDispatcherTask implements DispatcherTask {

   public  abstract String getDispatcherString();

   public  int getDispatchCode(){
       return  getDispatcherString().hashCode()>>>15 & getDispatcherString().hashCode();
   }
}
