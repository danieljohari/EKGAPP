public class BPMSim {

    double getBPM() throws InterruptedException {


            int alder = 25;
            int maxBPM = 220 - alder;
            double targetHrRandom = Math.random() * maxBPM;
            double targetHrMin = 0.45 * maxBPM;



            Thread.sleep(10);
            double targetHR = (targetHrRandom + targetHrMin) / 2;
            targetHR = Math.round(targetHR);
            //System.out.println(targetHR); //test

            // double HR = Double.parseDouble(targetHR);


            return targetHR;
        }
    }


