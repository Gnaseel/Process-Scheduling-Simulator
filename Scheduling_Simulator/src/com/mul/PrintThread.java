package com.mul;
import javax.swing.SwingUtilities;

import com.UI.UI;

public class PrintThread implements Runnable {
	UI ui = new UI();
	public PrintThread (UI ui) {
		this.ui=ui;
	}
	@Override
	public void run() {
		int i;
		// �������� �߰� ���� i�� �� ���� runStatus ������ �� ��µ� ��
		for(i=0;i<20;i++) {
			ui.printProcess(i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

