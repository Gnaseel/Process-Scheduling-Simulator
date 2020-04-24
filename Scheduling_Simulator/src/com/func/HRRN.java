package com.func;

public class HRRN extends Scheduling{
	
	public HRRN() {
		super();
		processorNum = 1;
	}
	// ���ť ���� �ִ� ��� ���μ����� ���ð��� 1�� ���դ��ϴ�.
	public void addWaitTime() {
		for (int i = 0; i < waitQ.size(); i++) waitQ.get(i).addWaitTime();
	}
	// ū�� ����
	public int max(int a, int b) {
		return a>b ? a:b;
	}
	// HRRN�˰��� ���ؼ� ���ť�� �ִ� ���μ����� ���� �켱������ ���� ���μ����� �ε����� ��ȯ�մϴ�.
	public int pick() {
		int maxIdx=0;
		double maxVal=0;
		for(int i=0;i<waitQ.size();i++) {
			double ratio;
			ratio = (double)waitQ.get(i).getWaitTime()/waitQ.get(i).getBurTime();
			if(ratio>maxVal) {
				maxIdx=i;
				maxVal=ratio;
			}	
		}
		return maxIdx;
	}
	// ���� �����층
	@Override
	public void doScheduling() {	
		printProcessInfo();
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time);
			for(int i=0;i<processorNum;i++) {
				checkRemain(time,i);
				if(processor[i]==null) {
					if(!waitQ.isEmpty()) {
						int idx=pick();
						processor[i] = waitQ.get(idx);
						waitQ.remove(idx);
					}	
				}else processor[i].reduRemainTime();
			}
			addWaitTime();
		}
	}
}