package com.bn.box2d.bheap;     
import java.util.ArrayList;
import java.util.Random;
import org.jbox2d.collision.AABB;   
import org.jbox2d.common.Vec2;    
import org.jbox2d.dynamics.World;     
import android.app.Activity;    
import android.content.pm.ActivityInfo;
import android.os.Bundle;   
import android.util.DisplayMetrics;
import android.view.Window;   
import android.view.WindowManager;  
import static com.bn.box2d.bheap.Constant.*;
  
public class MyBox2dActivity extends Activity 
{   
    AABB worldAABB;//���� һ�������ײ���=�   
    World world;
    Random random=new Random();
    //�����б�
    ArrayList<MyBody> bl=new ArrayList<MyBody>();

    public void onCreate(Bundle savedInstanceState) 
    {   
        super.onCreate(savedInstanceState);
        //����Ϊȫ��
        requestWindowFeature(Window.FEATURE_NO_TITLE);   
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,   
        WindowManager.LayoutParams. FLAG_FULLSCREEN); 
        //����Ϊ����ģʽ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		//��ȡ��Ļ�ߴ�
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        if(dm.widthPixels<dm.heightPixels)
        {
        	 SCREEN_WIDTH=dm.widthPixels;
             SCREEN_HEIGHT=dm.heightPixels;
        }
        else
        {
        	SCREEN_WIDTH=dm.heightPixels;
            SCREEN_HEIGHT=dm.widthPixels;    
        }
           
        worldAABB = new AABB();   
        
        //���½磬����Ļ�����Ϸ�Ϊ ԭ�㣬����ĸ��嵽����Ļ�ı�Ե�Ļ�����ֹͣģ��   
        worldAABB.lowerBound.set(-100.0f,-100.0f);
        worldAABB.upperBound.set(100.0f, 100.0f);//ע������ʹ�õ�����ʵ�=�ĵ�λ   
           
        Vec2 gravity = new Vec2(0.0f,10.0f);
        boolean doSleep = true;
        //�����=� 
        world = new World(worldAABB, gravity, doSleep);          
        
        //����4��
        final int kd=40;//��Ȼ�߶�
        MyRectColor mrc=Box2DUtil.createBox(kd/4, SCREEN_HEIGHT/2, kd/4, SCREEN_HEIGHT/2, true,world,0xFFe6e4FF);
        bl.add(mrc);
        mrc=Box2DUtil.createBox(SCREEN_WIDTH-kd/4, SCREEN_HEIGHT/2, kd/4, SCREEN_HEIGHT/2, true,world,0xFFe6e4FF);
        bl.add(mrc);
        mrc=Box2DUtil.createBox(SCREEN_WIDTH/2, kd/4, SCREEN_WIDTH/2, kd/4, true,world,0xFFe6e4FF);
        bl.add(mrc);
        mrc=Box2DUtil.createBox(SCREEN_WIDTH/2, SCREEN_HEIGHT-kd/4, SCREEN_WIDTH/2, kd/4, true,world,0xFFe6e4FF);
        bl.add(mrc);
        
        //����ש��
        //ש����	�м��Ϊ20     ģ����Ϊ10 	���һ��Ϊ9��
        final int bs=20;
        final int bw=(int)((SCREEN_WIDTH-2*kd-11*bs)/18);
        //============================================================
        for(int i=2;i<10;i++)
        {
        	if((i%2)==0)
        	{
        		//���6ľ��
        		for(int j=0;j<9-i;j++)
        		{
        			mrc=Box2DUtil.createBox
        			(
        				kd/2+bs+bw/2+i*(kd+5)/2+j*(kd+5)+3,
        				SCREEN_HEIGHT+bw-i*(bw+kd)/2,
        				bw/2,
        				kd/2,
        				false,
        				world,
        				ColorUtil.getColor(Math.abs(random.nextInt()))
        			);
        			bl.add(mrc);
        		}
        		//�Ҳ�6ľ��
        		for(int j=0;j<9-i;j++)
        		{
        			mrc=Box2DUtil.createBox
        			(
        				3*kd/2+bs-bw/2+i*(kd+5)/2+j*(kd+5)-3,
        				SCREEN_HEIGHT+bw-i*(bw+kd)/2,
        				bw/2,
        				kd/2,
        				false,
        				world,
        				ColorUtil.getColor(Math.abs(random.nextInt()))
        			);
        			bl.add(mrc);
        		}
        	}   
        	if((i%2)!=0)
        	{
        		for(int j=0;j<10-i;j++)
        		{
        			mrc=Box2DUtil.createBox
        			(
        				kd/2+bs+kd/2+(i-1)*(kd+5)/2+j*(kd+5),
        				SCREEN_HEIGHT-(kd-bw)/2-(i-1)*(bw+kd)/2,
        				kd/2,
        				bw/2,
        				false,
        				world,
        				ColorUtil.getColor(Math.abs(random.nextInt()))
        			);
        			bl.add(mrc);
        		}
        	}
        }
        mrc=Box2DUtil.createBox
		(
			5*kd+bs+20,
			SCREEN_HEIGHT-(kd+bw)*4-kd,
			bw/2,
			kd/2,
			false,
			world,
			ColorUtil.getColor(Math.abs(random.nextInt()))
		);
		bl.add(mrc);
        //������
        MyCircleColor ball=Box2DUtil.createCircle(SCREEN_WIDTH/2-24, kd, kd/2, world,ColorUtil.getColor(Math.abs(random.nextInt())));
        bl.add(ball);
        ball.body.setLinearVelocity(new Vec2(0,50));
           
        GameView gv= new GameView(this);   
        setContentView(gv);   
    }   
}  
