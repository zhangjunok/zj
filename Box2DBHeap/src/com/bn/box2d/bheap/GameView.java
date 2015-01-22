package com.bn.box2d.bheap;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("WrongCall")
public class GameView extends SurfaceView 
implements SurfaceHolder.Callback  //ʵ���������ڻص�ӿ�
{
	MyBox2dActivity activity;
	Paint paint;//����		
	DrawThread dt;//�����߳�
	
	public GameView(MyBox2dActivity activity) 
	{
		super(activity);
		this.activity = activity;		
		//�����������ڻص�ӿڵ�ʵ����
		this.getHolder().addCallback(this);
		//��ʼ������
		paint = new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����
		//������߳�
		dt=new DrawThread(this);
		dt.start();
	} 

	public void onDraw(Canvas canvas)
	{		
		if(canvas==null)
		{
			return;
		}
		canvas.drawARGB(255,140, 140, 140);
		
		//���Ƴ����е�����
		for(MyBody mb:activity.bl)
		{
			mb.drawSelf(canvas, paint);
		}
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) 
	{
		
	}

	public void surfaceCreated(SurfaceHolder holder) {//����ʱ������
		repaint();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//���ʱ������

	}
	
	public void repaint()
	{
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//��ȡ����
		try{
			synchronized(holder){
				onDraw(canvas);//����
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
}