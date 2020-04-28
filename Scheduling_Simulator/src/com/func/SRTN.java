package com.func;

import com.pro.Process;

public class SRTN extends Scheduling {
	public SRTN(int processorNum) {
		super(processorNum);
	}
	public int pick(int remainTime) {
		int idx = -1, min = remainTime;
		for (int i = 0; i < waitQ.size(); i++) {
			int tempInt=waitQ.get(i).getRemainTime();
			System.out.println(" temp = "+tempInt);
			if(tempInt<min) {
				min=tempInt;
				idx=i;
			}
		}
		System.out.println("idx = "+idx);
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
				int remainTime = Integer.MAX_VALUE;	
				if(!waitQ.isEmpty()) {
					int idx=0;
					if (processor[i] != null) {
						processor[i].reduRemainTime(); // ���μ����� ���ǰ������� remain time�� ���ҽ�ŵ�ϴ�.
						remainTime = processor[i].getRemainTime();		
					}
					idx = pick(remainTime);	
					if(idx!=-1) {
						if(processor[i]!=null) {
							Process add = processor[i];
							waitQ.offer(add);
						}
						processor[i] = waitQ.get(idx);
						waitQ.remove(idx);
					}
				}else if(processor[i]!=null)
					processor[i].reduRemainTime(); // ���μ����� ���ǰ������� remain time�� ���ҽ�ŵ�ϴ�.
			}	
		}
		return rePro;
	}
}