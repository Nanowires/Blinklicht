import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Blinklicht_einfach {
    
    public static void main(String[] args) throws InterruptedException {
        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        // Pin #1 als Ausgang definieren
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);
        
        Thread.sleep(5000);
        
        while(true) {
        
          // Pin #1 deaktivieren
          pin.low();
          Thread.sleep(5000);
          
          // Pin #1 wieder aktivieren
          pin.high();
          Thread.sleep(5000);
          
        }
  
    }
}
