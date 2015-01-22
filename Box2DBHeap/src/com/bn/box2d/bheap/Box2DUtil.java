package com.bn.box2d.bheap;

import org.jbox2d.collision.shapes.CircleDef;
import org.jbox2d.collision.shapes.PolygonDef;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import static com.bn.box2d.bheap.Constant.*;

//���������״�Ĺ�����
public class Box2DUtil 
{
	//������������(��ɫ)
	public static MyRectColor createBox
	(
		float x,//x���
		float y,//y���
	    float halfWidth,//���  
	    float halfHeight,//��� 
        boolean isStatic,//�Ƿ�Ϊ��ֹ��
        World world,//�=�
        int color//��ɫ
    )
	{    
		//����������������
		PolygonDef shape = new PolygonDef();   
		//�����ܶ�
		if(isStatic)
		{
			shape.density = 0;
		}   
		else
		{
			shape.density = 1.0f;
		}   
		//����Ħ��ϵ��
		shape.friction = 0.0f;   
		//��������ʧ�ʣ�������
		shape.restitution = 0.6f;   
		shape.setAsBox(halfWidth/RATE, halfHeight/RATE);   
		//���������������   
		BodyDef bodyDef = new BodyDef();   
		//����λ��
		bodyDef.position.set(x/RATE, y/RATE);
		//���=��д�������
		Body bodyTemp= world.createBody(bodyDef); 
		//ָ��������״
		bodyTemp.createShape(shape);   
		bodyTemp.setMassFromShapes(); 
		
		return new MyRectColor(bodyTemp,halfWidth,halfHeight,color);
	}   

	//����Բ�Σ���ɫ��
	public static MyCircleColor createCircle
	(
		float x,//x���
		float y,//y���
		float radius,//�뾶
		World world,//�=�
		int color//��ɫ
	)
	{   
		//����Բ�������
		CircleDef shape = new CircleDef();  
		//�����ܶ�
		shape.density = 2;   		
		//����Ħ��ϵ��
		shape.friction = 0.0f;   
		//��������ʧ�ʣ�������
		shape.restitution = 0.95f;   
		//���ð뾶
		shape.radius = radius/RATE;   
		
		//���������������   
		BodyDef bodyDef = new BodyDef(); 
		//����λ��
		bodyDef.position.set(x/RATE, y/RATE);   
		//���=��д�������
		Body bodyTemp = world.createBody(bodyDef); 
		//ָ��������״
		bodyTemp.createShape(shape);   
		bodyTemp.setMassFromShapes();  		
		return new MyCircleColor(bodyTemp,radius,color);
	}   
}