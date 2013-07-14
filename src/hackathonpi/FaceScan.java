package hackathonpi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FaceScan {
	
	private VideoCapture capture;
	private ImageView imageView;
	private ImageView outView;
	private Label label;
	private static int index = 0;
	
	public FaceScan(ImageView imageView, ImageView outView, Label label) {
		this.capture = new VideoCapture();
		this.imageView = imageView;
		this.outView = outView;
		this.label = label;
	}
	
	public void start() {
		Timer timer = new Timer();
		timer.schedule(new MonitorFolder(), 10000, 1000);
	}
	
	class MonitorFolder extends TimerTask {
		@Override
		public void run() {
//			File one = capture.captureStream()[index++];
			String folder = "/home/pi/Camera/Capture/";
//			folder = "/tmp/cam/";
			
			File one = new File(folder + "imagem" + (index++) + ".jpeg" );
			while (!one.exists()) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {}
			}
			File person = reconize(one);
			show(one, person);
		}	
	}
	
	private InputStream loadImage(File file) throws IOException {
		Path path = Paths.get(file.getAbsolutePath());
		InputStream in = new ByteArrayInputStream(Files.readAllBytes(path));
		//FIXME 
//		file.delete();
		return in;
	}
	
	private void show(final File source, final File picture) {
		
		Platform.runLater(new Runnable() {
			
			public void run() {
				try {
					
//					imageView.setImage(new Image(new FileInputStream(picture)));
					imageView.setImage(new Image(loadImage(source)));
					if (picture != null) {
						outView.setImage(new Image(loadImage(picture)));						
					} else {
						outView.setImage(null);
					}
					String name = source.getName().replace(".jpeg", "").replace(".jpg", "");
					String text = "Bem-vindo "+ name + "!";
					label.setText(text);
				} catch (Exception e) {
					System.err.println(e);
//					e.printStackTrace();
//					imageView.setImage(null);
//					label.setText("Falha ao exibir: " + source.getName() );
				}
			}
		});
		
	}
	
	private File reconize(File file) {
//		String reconizeDir = "/tmp/reconize";
		String reconizeDir = "/home/pi/Camera/Faces";
		File reconized = new File(reconizeDir, file.getName());
	
		while (!reconized.exists()) {
			try { Thread.sleep(300); } 
			catch (InterruptedException e) {}
		}
		return reconized;
//		return null;
	}

//	public static void main(String[] args) {
//		FaceScan scan = new FaceScan(null, null);
//		scan.start();
//	}
	
}
