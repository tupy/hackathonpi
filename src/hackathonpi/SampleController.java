package hackathonpi;
 
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
 

public class SampleController implements Initializable {
    
    @FXML
    private Label name;
    
    @FXML
    ImageView imgSource;  

    @FXML
    ImageView imgFace;  

    public void initialize(URL url, ResourceBundle rb) {
    	        
    	FaceScan faceScan = new FaceScan(this.imgSource, this.imgFace, this.name);
	    faceScan.start();
    }   
    
}