package GameLib.fiveinarow;

import java.util.Collection;
import java.util.LinkedList;

public class Box<T> {
	int width,height;
	T[][] boxArray;
	
	public Box(T[][] BoxArray){
		if (BoxArray.length==0) {
			width=height=0;
			boxArray=BoxArray;}
		else{
			width=BoxArray[0].length;
			height=BoxArray.length;
			for (int i=1;i<BoxArray.length;i++){
				if (BoxArray[i].length!=width) throw new IllegalArgumentException();}
			boxArray=BoxArray;}}
	
	public int getWidth(){
		return width;}
	public int getHeight(){
		return height;}
	public T getBoxItem(int widthX, int heightY){
		return boxArray[heightY][widthX];}
	
	public int getSlotNumber(int widthX, int heightY){
		return (widthX) +
				(heightY) * width;}
	
	void setBoxItem(int widthX, int heightY, T item){
		boxArray[heightY][widthX]=item;}

	public boolean isSubBox(Box<T> subBox){
		int widthX1=-1, heightY1=-1;
		for (int widthX=0;widthX<=width-subBox.width;width++){
			for (int heightY=0;heightY<=height-subBox.height;heightY++){
				if (getBoxItem(widthX,heightY)==subBox.getBoxItem(widthX,heightY)){
					widthX1=widthX;
					heightY1=heightY;
					for (widthX=widthX1;widthX<widthX1+subBox.width;width++){
						for (heightY=heightY1;heightY<heightY1+subBox.height;heightY++){
							if (getBoxItem(widthX,heightY)!=subBox.getBoxItem(widthX-widthX1,heightY-heightY1)) return false;}}
					return true;}}}
		return false;}
	public Box<T> getSubBox(int widthX1, int heightY1, int widthX2, int heightY2){
		T[][] subBoxArray = (T[][])new Object[heightY2-heightY1+1][widthX2-widthX1+1];
		for (int widthX=widthX1;widthX<=widthX2;widthX++){
			for (int heightY=heightY1;heightY<=heightY2;heightY++){
				subBoxArray[heightY-heightY1][widthX-widthX1]=boxArray[heightY][widthX];}}
		return new Box<T> (subBoxArray);}
	
	public T[][] asArray(){
		return boxArray.clone();}
	public Collection<T> asCollection(){
		LinkedList<T> boxCollection = new LinkedList<T>();
		for (int widthX=0;widthX<width;widthX++){
			for (int heightY=0;heightY<height;heightY++){
				boxCollection.add(getBoxItem(widthX,heightY));}}
		return boxCollection;}}