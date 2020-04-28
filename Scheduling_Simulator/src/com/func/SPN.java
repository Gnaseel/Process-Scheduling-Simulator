package com.func;

import com.pro.Process;

public class SPN extends Scheduling {
	public SPN(int processorNum) {
		super(processorNum);
	}
	public int pick() {
		int idx = 0;
		int minRemainTime=Integer.MAX_VALUE,tempInt=0;
		for (int i = 0; i < waitQ.size(); i++) {
			tempInt=waitQ.get(i).getRemainTime();
			if(tempInt<minRemainTime) {
				minRemainTime=tempInt;
				idx=i;
			}
		}
		return idx;
	}
	@Override
	public Process[] doScheduling() {
		int index = 0;
		Process[] rePro = new Process[15];
		printProcessInfo(); // ���μ��� ���� ���. ������
		for (int time = 0; !isEnd(); time++) {
			System.out.println("time "+time);
			setWaitQ(time); // �� �ð��������� ������ ���μ����� ���ť�� �־��ݴϴ�.
			for (int i = 0; i < processorNum; i++) { // ���μ����� ������ŭ �ݺ�
				if (checkRemain(time, i)) { // ���μ����� �������� �˻��մϴ�.
					rePro[index++] = processor[i];
					processor[i] = null;
				}
				if (processor[i] == null) { // ���μ����� ����������
					if (!waitQ.isEmpty()) // �׸��� ���ť�� ���μ����� �ִ°��
					{
						int idx=pick();
						processor[i] = waitQ.get(idx); // ���ť���� ���μ����� �����ɴϴ�.
						waitQ.remove(idx);
					}
				} else
					processor[i].reduRemainTime(); // ���μ����� ���ǰ������� remain time�� ���ҽ�ŵ�ϴ�.
			}
		}
		return rePro;
	}
}
