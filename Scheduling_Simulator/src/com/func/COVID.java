package com.func;

import com.pro.Process;

public class COVID extends Scheduling {
	int isolation = 0;
	int INFECTED = 5;
	public COVID(int processorNum) {
		super(processorNum);
	}
	@Override
	public Process[] doScheduling() {
		isolation = processorNum / 2;
		int index = 0;
		Process[] rePro = new Process[15];
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time); // �� �ð��������� ������ ���μ����� ���ť�� �־��ݴϴ�.
			for (int i = 0; i < processorNum; i++) { // ���μ����� ������ŭ �ݺ�
				if (processor[i] != null) runStatus[i][time] = processor[i].getID();
				if (checkRemain(time, i)) { // ���μ����� �������� �˻��մϴ�.
					rePro[index++] = processor[i];
					processor[i] = null;
				}
				if (processor[i] == null) { // ���μ����� ����������
					if (!waitQ.isEmpty()) {// �׸��� ���ť�� ���μ����� �ִ°��
						int idx = getIndex(i<isolation);	//burstTime�� �����ؼ� �ݸ� ���θ� �����մϴ�.
						if(idx!=-1) {
							processor[i] = waitQ.get(idx); // ���ť���� ���μ����� �����ɴϴ�.
							waitQ.remove(idx);
						}
					}	
				} else processor[i].reduRemainTime();
				// ���μ����� ���ǰ������� remain time�� ���ҽ�ŵ�ϴ�.
			}
		}
		return rePro;
	}
	public int getIndex(boolean isIsolated) {
		if(isolation==0)
			return 0;
		int re=-1;
		for(int i=0;i<waitQ.size()&&re==-1;i++) {
			if(isIsolated) {
				if(waitQ.get(i).getBurTime()>=INFECTED) re=i;
			}else {
				if(waitQ.get(i).getBurTime()<INFECTED)re=i;
			}
		}
		return re;
	}
}
