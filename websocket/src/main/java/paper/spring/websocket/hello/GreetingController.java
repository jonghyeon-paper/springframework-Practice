/**
 * 
 */
package paper.spring.websocket.hello;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    public HelloMessage hello(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new HelloMessage(message.getFrom(), time);
    }
    
    @MessageMapping("/bye")
    @SendTo("/topic/bye")
    public ByeMessage bye(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ByeMessage(message.getFrom(), time);
    }
    
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage message(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
}
