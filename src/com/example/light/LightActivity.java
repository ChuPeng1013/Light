package com.example.light;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class LightActivity extends Activity implements android.view.View.OnClickListener
{
	private Button onebutton = null;
	private Camera camera = null;
	private Parameters parameters = null;
	public static boolean kaiguan = true;//标记开关
	
	private int back = 0;//标记按下退出键的次数
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//设置窗体全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置不弹出键盘
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD, WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		//设置窗体始终点亮
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.main);
		onebutton = (Button) findViewById(R.id.onebutton);
		onebutton.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) 
	{
		if(kaiguan)
		{
			onebutton.setBackgroundResource(R.drawable.bg1);//设置按钮图案
			camera = Camera.open();//
			parameters = camera.getParameters();//得到相机参数的对象
			parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);//开启闪光灯
			camera.setParameters(parameters);
			kaiguan = false;
		}
		else
		{
			onebutton.setBackgroundResource(R.drawable.bg);
			parameters.setFlashMode(Parameters.FLASH_MODE_OFF);//关闭闪光灯
			camera.setParameters(parameters);
			kaiguan = true;
			camera.release();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			back++;
			switch(back)
			{
				case 1:
					Toast.makeText(LightActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
				break;
				case 2:
					back = 0;
					parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(parameters);
					finish();
					break;
			}
			return true;
		}
		else
		{
			return super.onKeyDown(keyCode, event);
		}
	}
	
}






