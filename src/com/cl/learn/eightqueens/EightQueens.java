package com.cl.learn.eightqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 八皇后问题是一个以国际象棋为背景的问题：如何能够在8×8的国际象棋棋盘上放置八个皇后，
 * 使得任何一个皇后都无法直接吃掉其他的皇后？为了达到此目的，任两个皇后都不能处于同一条横行、纵行或斜线上。
 * 八皇后问题可以推广为更一般的n皇后摆放问题：这时棋盘的大小变为n×n，而皇后个数也变成n。当且仅当n = 1或n ≥ 4时问题有解
 * @author L
 * @date 2017年9月26日
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
					}else if(x==lastQ.getX()+1&&y==(keyNumber-1)){		//如果是最后一个的下一行的最后一个都不能落子
						break a;
					}
				}
			}
			if(!b){
				for(int q4index=qs.size()-1;q4index>-1;q4index--){
					Q q2 =qs.get(q4index);	//得到最后一个
					qiPan[q2.getX()][q2.getY()]=0;		//去掉棋盘最后一个
					qs.remove(qs.size()-1);//去掉列表最后一个
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
				qiPan[q2_2.getX()][q2_2.getY()]=q2_2.getValues();//在棋盘最后一个后放上最后一个
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
		if(x>=y){	//左下区
			int xy=x-y;
			for(int i=0;i<(keyNumber-xy);i++){
				sum+=qiPan[xy+i][i];
				if(sum>0) return true;
			}
		}else if(y>x){	//右上区
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
				if((x+y)==(i+j)){		// 左下到右上
					sum+=qiPan[i][j];
					if(sum>0) return true;
				}
			}
		}
		return 0!=sum;
	}
	
	
}
