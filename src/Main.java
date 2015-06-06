import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui.DataPath guiDataPath = new gui.DataPath();
					engine.DataPath engineDataPath = new engine.DataPath();
					guiDataPath.attach(engineDataPath);
					guiDataPath.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
