package com.mul;

import javax.swing.SwingUtilities;

import com.UI.UI;
import com.func.Scheduling;

public class PrintThread implements Runnable {
	UI ui = new UI();
	public Scheduling sch;

	public PrintThread(UI ui) {
		this.ui = ui;
	}
	@Override
	public void run() {
		int i;
		// �������� �߰� ���� i�� �� ���� runStatus ������ �� ��µ� ��
		for (i = 1; i < sch.endTime; i++) {
			ui.printProcess(i);
			try {
				if (!Thread.currentThread().isInterrupted())
					Thread.sleep(1);
			} catch (InterruptedException  e) {
				e.printStackTrace();
				ui.denay = false;
				return;
			}
		}
		ui.denay = false;
	}
}