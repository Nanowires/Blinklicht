import java.io.IOException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Shutdown {

	public static void main(String[] args) throws InterruptedException {
		final GpioController gpio = GpioFactory.getInstance();
		final GpioPinDigitalInput input = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
		input.addListener(new GpioPinListenerDigital() {
			
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println(event.getPin() + " = " + event.getState());
				
				//Wenn der Pin#2 auf High geht, fährt sich der Rasperry Pi runter.
				if (input.getState()==PinState.HIGH) {
					try {
						Process p = Runtime.getRuntime().exec("sudo shutdown -h now");
						p.waitFor();
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		while (true) {
			Thread.sleep(1000);
		}
		
	}

}
