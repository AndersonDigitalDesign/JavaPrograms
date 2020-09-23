package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;


public class Controller {

    @FXML
    private TextArea messageOutput;

    @FXML
    private TextField command;

    @FXML
    private Label serverName;
    
    @FXML
    public void onEnter(){
       onclick();
    }
    
    @FXML
    void onclick() {

    	messageOutput.setText(null);
        if(serverName.getText() != null)
        {
            try {
                Process process = Runtime.getRuntime().exec("cmd /c " + command.getText());
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                messageOutput.setWrapText(true);
                while((line = reader.readLine()) != null) {
                    messageOutput.appendText(line + "\n");
                }
                command.setText(null);
                command.setPromptText("CMD Command");
            }catch(IOException e) {
                e.printStackTrace();
                messageOutput.setText("Error in iop exception");
            }
        }
    }
    public void initialize(){
        String hostname = null;
        //try to get the current server name
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
            serverName.setText(hostname);
        } catch (java.net.UnknownHostException e) {
            e.printStackTrace();
            messageOutput.setText("Error getting server name");
        }
        serverName.setText(hostname);
    }

}
