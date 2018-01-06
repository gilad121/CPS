package application;

import common.ControllerIF;
import common.Params;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClientOnlineController implements ControllerIF{

    @FXML
    private Button bOneTimeOrder;

    @FXML
    private Button bMakeRoutineSubscription;

    @FXML
    private Button bMakeFullSubscription;

    @FXML
    private Button bContactUs;

    private ApplicationMain main;
    private Params params;

    @FXML
    void bContactUsClick(ActionEvent event) {

    }

    @FXML
    void bMakeFullSubscriptionClick(ActionEvent event) {
    	System.out.println("bMakeFullSubscriptionClick");
    	main.setScene("clientFullSubscriptionView.fxml", params);
    }

    @FXML
    void bMakeRoutineSubscriptionClick(ActionEvent event) {
     	System.out.println("bMakeRoutineSubscriptionClick");
    	main.setScene("clientRoutineSubscriptionView.fxml", params);
    }

    @FXML
    void bOneTimeOrderClick(ActionEvent event) {
    	System.out.println("bOneTimeOrderClick");
    	main.setScene("clientOneTimeOrderView.fxml", params);

    }

    @Override
	public void init(ApplicationMain main, Params params) {
		this.main = main;
		this.params = params;
	}

}
