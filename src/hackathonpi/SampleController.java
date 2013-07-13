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
    ImageView imgFace;  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}