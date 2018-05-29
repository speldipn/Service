package org.androidtown.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
  public MyService() {
  }

  // 현재 서비스중인 인스턴스(서비스 자신)를 가져오기 위한 용도
  class MyBinder extends Binder {
    public MyService getSerivce() {
      return MyService.this;
    }
  }

  IBinder binder = new MyBinder();

  @Override
  public IBinder onBind(Intent intent) {
    return binder;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    return super.onStartCommand(intent, flags, startId);
  }
}
