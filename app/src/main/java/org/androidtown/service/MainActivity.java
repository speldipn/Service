package org.androidtown.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  Button btnStartService;
  Button btnStopService;
  Button btnBindService;
  Button btnUnbindService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Service 화면이 없는 액티비티
    // 메인 스레드에서 동작
    Intent intent = new Intent(this, MyService.class);
    startService(intent);

    // 1. started service
    // 2. bound service
  }

  public void startService(View v) {
    Intent intent = new Intent(this, MyService.class);
    startService(intent);
  }

  public void stopService(View v) {
    Intent intent = new Intent(this, MyService.class);
    stopService(intent);
  }

  public void bindService(View v) {
    Intent intent = new Intent(this, MyService.class);
    bindService(intent, null, Context.BIND_AUTO_CREATE);
    // BIND_AUTO_CREATE: 항상 거의 이것을 사용.
    // startService 서비스가 독리적으로 하나 뜨는것이고 bind Serivce 액티비티와 연결되서 서로 값을 주고받으려고 사용.
    // 하나의 액티비티와 여러개의 서비스가 바인드되어 사용될 수 있다.
    // start되어 있지 않으면 생성해달라는 의미로 사용된다.
  }

  @Override
  public void unbindService(ServiceConnection conn) {
    unbindService(conn);
  }

  public void unbindService(View v) {

  }

  MyService myService;
  boolean boundService = false;

  ServiceConnection con = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      // 서비스와 연결되는 순간 호출되는 함수
      MyService.MyBinder mm = (MyService.MyBinder) service;
      myService = mm.getSerivce();
      boundService = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      // 서비스가 중단되거나 연결이 끊겼을 경우에 호출되는 함수
      // 단순히 서비스가 stop되었을 경우에는 호출되지 않는다.
      // 무언가 비정상적으로 종료되었을때 호출되는 함수
      boundService = false;
    }
  };
}
