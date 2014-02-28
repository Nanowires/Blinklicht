import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;

public class Blinklicht_mit_Schalter {
    
    static boolean status;

    public static void main(String[] args) throws InterruptedException {
        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        // Pin #1 als Ausgang definieren
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);
        // Pin #4 als Eingang definieren mit Pull-Down Widerstand
        final GpioPinDigitalInput input = gpio.provisionDigitalInputPin(RaspiPin.GPIO_4, PinPullResistance.PULL_DOWN);
        
        status=false;
        
        //Listener zum Überprüfen der Spannung an Pin #4
		input.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				//System.out.println(event.getPin() + " = " + event.getState());
				if (input.getState() == PinState.HIGH){
				    status=!status;
					if (status==true) {
						System.out.println("an"); //Schreibe "an", wenn Spannung anliegt
					} else {
						System.out.println("aus");//Schreibe "aus", wenn keine Spannung anliegt
					}
				}
			}
		});
        
        Thread.sleep(500);
        
        while(status==false) {
        
          // Pin #1 deaktivieren
          pin.low();
          Thread.sleep(500);
          
          // Pin #1 wieder aktivieren
          pin.high();
          Thread.sleep(500);
          /*
          Alternative wäre:
          pin.pulse(500,true);
          Thread.sleep(500);
          */
        }
  
    }
}
