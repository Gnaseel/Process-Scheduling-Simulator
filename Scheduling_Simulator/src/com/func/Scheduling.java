package com.func;

import java.util.ArrayList;
import java.util.LinkedList;

import com.pro.Process;

public class Scheduling {
	public Scheduling() {
		processorNum=1;
	}
	int processorNum;
	Process[] processor = new Process[4];
	ArrayList<Process> pcs = new ArrayList<Process>(); // ��� ���μ���
	LinkedList<Process> waitQ = new LinkedList<Process>(); // ���� ������ ���μ��� ���ť
	
	// ���μ��� ������ �Է¹޾� ����
	public void insertPcs(int arrTime, int burTime) {
		Process pro = new Process(arrTime, burTime);
		pcs.add(pro);
	}
	// ��� ������ �������� üũ�մϴ�.
		public boolean isEnd() {
			for (int i = 0; i < pcs.size(); i++)
				if (pcs.get(i).getRemainTime() != 0) 
					return false;
			return true;
		}
		// ���μ��� ��ü ť���� �ð��� ���� ���μ��� ���ť�� ���μ����� �̵���ŵ�ϴ�. �� �ð��������� ȣ��˴ϴ�.
		public void setWaitQ(int time) {
			for (int i = 0; i < pcs.size(); i++) if (pcs.get(i).getArrTime() == time) waitQ.add(pcs.get(i));
		}
		
		public void doScheduling() {}
		
		
		
		public void checkRemain(int time, int processorIdx) {
				if(processor[processorIdx]!=null&&processor[processorIdx].getRemainTime() == 1) {
					processor[processorIdx].setTurnTime(time  - processor[processorIdx].getArrTime());
					processor[processorIdx].setEndTime(time );
					processor[processorIdx].setWaitTime(processor[processorIdx].getTurnTime() - processor[processorIdx].getBurTime());
					processor[processorIdx].reduRemainTime();
					System.out.println("   "+processorIdx+"��° ���μ����� ���μ��� ����  " + processor[processorIdx].toString());
					processor[processorIdx]=null;
				}
		}
		public void printProcessInfo() {
			for (int i = 0; i < pcs.size(); i++)
				System.out.println(pcs.get(i).toString());
		}
}