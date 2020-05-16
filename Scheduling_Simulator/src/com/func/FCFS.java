package com.func;

import com.pro.Process;

public class FCFS extends Scheduling {
	public FCFS(int processorNum) {
		super(processorNum);
	}

	@Override
	public Process[] doScheduling() {
		System.out.println("---In FCFS processorNum = "+processorNum);
		int index = 0;
		Process[] rePro = new Process[15];
		printProcessInfo(); // ���μ��� ���� ���. ������
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time); // �� �ð��������� ������ ���μ����� ���ť�� �־��ݴϴ�.
			for (int i = 0; i < processorNum; i++) { // ���μ����� ������ŭ �ݺ�
				if (processor[i] != null) {
					runStatus[i][time] = processor[i].getID();
					System.out.println(i + "�� ���μ�����   " + time + " �ð���  = " + processor[i].getID());
				}
				if (checkRemain(time, i)) { // ���μ����� �������� �˻��մϴ�.
					rePro[index++] = processor[i];
					processor[i] = null;
				}
				if (processor[i] == null) { // ���μ����� ����������
					if (!waitQ.isEmpty()) // �׸��� ���ť�� ���μ����� �ִ°��
						processor[i] = waitQ.pop(); // ���ť���� ���μ����� �����ɴϴ�.
				} else {
					processor[i].reduRemainTime();

				} // ���μ����� ���ǰ������� remain time�� ���ҽ�ŵ�ϴ�.
			}
		}
		//this.rePro = rePro;
		System.out.println("---Out FCFS ");
		return rePro;
	}
}