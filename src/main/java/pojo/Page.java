package pojo;

public class Page {
   private int currNo=1;
   private int currSize=2;
   private int countCurrNo;
   private int countCurrSize;
public int getCurrNo() {
	return currNo;
}
public void setCurrNo(int currNo) {
	this.currNo = currNo<=0?this.currNo:currNo
			>this.countCurrNo?this.countCurrNo:currNo;
			;
}
public int getCurrSize() {
	return currSize;
}
public void setCurrSize(int currSize) {
	this.currSize = currSize<=0?this.currSize:currSize;
}
public int getCountCurrNo() {
	return countCurrNo;
}
public void setCountCurrNo(int countCurrNo) {
	this.countCurrNo = countCurrNo;
}
public int getCountCurrSize() {
	return countCurrSize;
}
public void setCountCurrSize(int countCurrSize) {
	this.countCurrSize = countCurrSize;
	this.countCurrNo = this.countCurrSize==0?1:
		    this.countCurrSize%this.currSize==0?
		    		this.countCurrSize/this.currSize:
		    			this.countCurrSize/this.currSize+1;
}
   
   
}
