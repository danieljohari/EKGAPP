import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;

import java.io.PrintWriter;

public class EkgGUIController {
    private TempSim tempSim = new TempSim();
    private  BPMSim bpmSim = new BPMSim();
    private SPO2Sim spo2Sim = new SPO2Sim();
    private double maxTemp =0.0;
    private double maxBPM = 0.0;
    private double maxSPO2 = 0.0;
    private  EKGAPP ekgapp;
    private PrintWriter pw;
    public TextField fornavnText;
    public Limit limit;
    public Label bpmLabel;
    public Label tempLabel;
    public Label spo2Label;
    public Label konLabel;
    public Label offLabel;
    public Label warningTemp;
    public Label warningBPM;
    public Label warningSPO2;
    public Button manB;
    public Button kvindeB;
    public Button tempB;
    public Button bpmB;
    public Button spo2B;
    public Button bOn;
    public Button bOff;
    public Polyline polyline;
    public EKGAPP getLimit;




    public void ekgKnap(MouseEvent actionEvent) throws InterruptedException {

        //alt det her tager tid, og får sin egen tråd.
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    polyline.getPoints().addAll(i * 10.0, Math.random() * 150);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }
                }

        }).start();
    }

    public void tempKnap() {
        //får sin egen thread.
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    tempLabel.setText("Patientens temperatur: " + tempSim.getTemp());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    if (tempSim.getTemp() >= maxTemp){
                        warningTemp.setText("Advarsel! temperatur over "+maxTemp);
                    } else if (tempSim.getTemp() < maxTemp){
                        warningTemp.setText("");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void BPMKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    bpmLabel.setText("Patientens BPM: " +bpmSim.getBPM());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    if (bpmSim.getBPM() >= maxBPM){
                        warningBPM.setText("Advarsel! puls over 120!");
                    } else if (bpmSim.getBPM() < maxBPM){
                        warningBPM.setText("");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void SPO2Knap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    spo2Label.setText("Patientens SPO2: " +spo2Sim.getSPO2());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    if (spo2Sim.getSPO2() <= maxSPO2) {
                        warningSPO2.setText("Advarsel! Iltmætning under 95%!");
                    } else if (spo2Sim.getSPO2() > maxSPO2){
                        warningSPO2.setText("");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void manKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                konLabel.setText("Valgt: Mand");
            }
        });
    }

    public void kvindeKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                konLabel.setText("Valgt: Kvinde");
            }
        });
    }

    public void onKnap(MouseEvent mouseEvent){
        Platform.runLater((new Runnable() {
            public void run() {
                offLabel.setText("Patient Monitoring System Online!");
                offLabel.setStyle("-fx-text-fill: green");
            }
        }));
    }



    public void offKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                offLabel.setText("Patient Monitoring System Shutting down....");
                offLabel.setStyle("-fx-text-fill: red");

            }
        });
    }

    public void granseKnap(MouseEvent mouseEvent ) {
        Limit lim = new Limit();
        maxTemp = lim.askForUrgentTemp();
        maxBPM = lim.askForUrgentBPM();
        maxSPO2 = lim.askForUrgentSPO2();
        Platform.runLater(new Runnable() {


            public void run() {

            }
        });

    }

    public void gemTextKnap (MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {




            }
        });
    }



}








