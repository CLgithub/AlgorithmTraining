package com.cl.learn.eightqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * �˻ʺ�������һ���Թ�������Ϊ���������⣺����ܹ���8��8�Ĺ������������Ϸ��ð˸��ʺ�
 * ʹ���κ�һ���ʺ��޷�ֱ�ӳԵ������Ļʺ�Ϊ�˴ﵽ��Ŀ�ģ��������ʺ󶼲��ܴ���ͬһ�����С����л�б���ϡ�
 * �˻ʺ���������ƹ�Ϊ��һ���n�ʺ�ڷ����⣺��ʱ���̵Ĵ�С��Ϊn��n�����ʺ����Ҳ���n�����ҽ���n = 1��n �� 4ʱ�����н�
 * @author L
 * @date 2017��9��26��
 */
public class EightQueens {
	public static void main(String[] args) {
		run(8);
	}
	private static void run(int keyNumber) {
		int count=0;
		int[][] qiPan=new int[keyNumber][keyNumber];
		List<Q> qs=new ArrayList<>();
		z:for(int i=1;i<=keyNumber;i++){
			boolean b=false;
			Q lastQ=new Q();
			if(qs.size()>0){
				lastQ = qs.get(qs.size()-1);	
			}
			a:for(int x=0;x<keyNumber;x++){
				for(int y=0;y<keyNumber;y++){
					if(!isX(x,qiPan,keyNumber)&&!isY(y,qiPan,keyNumber)&&!isR(x,y,qiPan,keyNumber)&&!isL(x,y,qiPan,keyNumber)){
						Q q=new Q(lastQ.getValues()+1);
						qiPan[x][y]=q.getValues();
						q.setX(x);
						q.setY(y);
						qs.add(q);
						b=true;
						break a;
					}else if(x==lastQ.getX()+1&&y==(keyNumber-1)){		//��������һ������һ�е����һ������������
						break a;
					}
				}
			}
			if(!b){
				for(int q4index=qs.size()-1;q4index>-1;q4index--){
					Q q2 =qs.get(q4index);	//�õ����һ��
					qiPan[q2.getX()][q2.getY()]=0;		//ȥ���������һ��
					qs.remove(qs.size()-1);//ȥ���б����һ��
					int moveNext = moveNext(qiPan, qs, q2,keyNumber);
					if(qs.size()==0) break z;
					i=qs.get(qs.size()-1).getValues();
					if(moveNext==1){
						continue;
					}else if(moveNext==0){
						break;
					}
				}
			}
			
			if(qs.get(qs.size()-1).getValues()==keyNumber){
				count++;
				System.out.println(count+"----------------------");
				for(int j=0;j<keyNumber;j++){
					System.out.println(Arrays.toString(qiPan[j]));
				}
				i=qs.get(qs.size()-1).getValues()-1;
			}
		}
	}

	private static int moveNext(int[][] qiPan, List<Q> qs, Q q2, int keyNumber) {
		Q q2_2=new Q();
		q2_2.setX(q2.getX());
		q2_2.setY(q2.getY()+1);
		q2_2.setValues(q2.getValues());
		
		if(q2_2.getY()>keyNumber-1){
			return 1;
		}else{
			if(!isX(q2_2.getX(),qiPan,keyNumber)&&!isY(q2_2.getY(),qiPan,keyNumber)
					&&!isR(q2_2.getX(),q2_2.getY(),qiPan,keyNumber)&&!isL(q2_2.getX(),q2_2.getY(),qiPan,keyNumber)){
				qiPan[q2_2.getX()][q2_2.getY()]=q2_2.getValues();//���������һ����������һ��
				qs.add(q2_2);
				return 0;
			}else{
				return moveNext(qiPan, qs, q2_2, keyNumber);
			}
		}
		
	}

	private static boolean isX(int x, int[][] qiPan, int keyNumber) {
		int sum=0;
		for(int i=0;i<keyNumber;i++){
			sum+=qiPan[x][i];
			if(sum>0) return true;
		}
		return 0!=sum;
	}

	private static boolean isY(int y, int[][] qiPan, int keyNumber) {
		int sum=0;
		for(int i=0;i<keyNumber;i++){
			sum+=qiPan[i][y];
			if(sum>0) return true;
		}
		return 0!=sum;
	}

	private static boolean isR(int x, int y, int[][] qiPan, int keyNumber) {
		int sum=0;
		if(x>=y){	//������
			int xy=x-y;
			for(int i=0;i<(keyNumber-xy);i++){
				sum+=qiPan[xy+i][i];
				if(sum>0) return true;
			}
		}else if(y>x){	//������
			int xy=y-x;
			for(int i=0;i<(keyNumber-xy);i++){
				sum+=qiPan[i][xy+i];
				if(sum>0) return true;
			}
		}
		return 0!=sum;
	}

	private static boolean isL(int x, int y, int[][] qiPan, int keyNumber) {
		int sum=0;
		for(int i=0;i<keyNumber;i++){
			for(int j=0;j<keyNumber;j++){
				if((x+y)==(i+j)){		// ���µ�����
					sum+=qiPan[i][j];
					if(sum>0) return true;
				}
			}
		}
		return 0!=sum;
	}
	
	
}
