package com.func;
import java.util.ArrayList;
import java.util.LinkedList;
import com.pro.Process;
public abstract class Scheduling {
	int processorNum;
	
	public int getProcessorNum() {
		return processorNum;
	}
	public void setProcessorNum(int num) {
		processorNum=num;
	}
	Process[] rePro = new Process[15];
	//���߿� private �ٲܰ���
	public int[][] runStatus = new int[4][150];
	
	public void setProcessNum(int processNum) {
		rePro = new Process[processNum];
	}
	public Scheduling(int processorNum) {
		this.processorNum = processorNum;
	}
	Process[] processor = new Process[4];
	
	ArrayList<Process> pcs = new ArrayList<Process>(); 	// ��� ���μ����� ����Ǿ��ִ� ť
	LinkedList<Process> waitQ = new LinkedList<Process>(); // ������ ���μ����� ����ϴ� ť

	public ArrayList<Process> getProcessArray(){
		return pcs;
	}
	// ���ο� ���μ��� ������ �Է¹޾� ����
	public void insertPcs(int arrTime, int burTime) {
		Process pro = new Process(arrTime, burTime);
		pcs.add(pro);
	}
	public void insertPcs(int arrTime, int burTime, int ID) {
		Process pro = new Process(arrTime, burTime);
		pro.setID(ID);
		pcs.add(pro);
	}
	// ��� ������ �������� üũ�մϴ�. pcs�� ��� ���μ����� remain time�� 0�ϰ�� true�� ��ȯ�մϴ�.
	public boolean isEnd() {
		for (int i = 0; i < pcs.size(); i++)
			if (pcs.get(i).getRemainTime() != 0)
				return false;
		return true;
	}
	// ���μ��� ��ü ť���� �ð��� ���� ���μ��� ���ť�� ���μ����� �̵���ŵ�ϴ�. �� �ð��������� ȣ��˴ϴ�.
	public void setWaitQ(int time) {
		for (int i = 0; i < pcs.size(); i++)
			if (pcs.get(i).getArrTime() == time)
				waitQ.add(pcs.get(i));
	}
	// ��� �����ٸ� Ŭ������ �� �Լ��� ��ӹ޾� �����մϴ�.
	public Process[] doScheduling() {return null;};

	// Ư�� ���μ��� ������ ���μ����� ����Ǿ�� �ϴ��� �˻��ϴ� �޼ҵ��Դϴ�. ���μ����� �����Ű�� �����͸� �Է����ݴϴ�.
	public boolean checkRemain(int time, int processorIdx) {
		if (processor[processorIdx] != null && processor[processorIdx].getRemainTime() == 1) {
			processor[processorIdx].setTurnTime(time - processor[processorIdx].getArrTime());
			processor[processorIdx].setEndTime(time);
			processor[processorIdx]
					.setWaitTime(processor[processorIdx].getTurnTime() - processor[processorIdx].getBurTime());
			processor[processorIdx].reduRemainTime();
			processor[processorIdx].setNormalizedTT((float)processor[processorIdx].getTurnTime()/processor[processorIdx].getBurTime());
			System.out.println("   " + processorIdx + "��° ���μ����� ���μ��� ����  " + processor[processorIdx].toString());
			
			return true;
		}
		return false;
	}
	// ��� ���μ����� ������ ����մϴ�. �������� �� ������ �Դϴ�.
	public void printProcessInfo() {
		for (int i = 0; i < pcs.size(); i++)
			System.out.println(pcs.get(i).toString());
	}
}