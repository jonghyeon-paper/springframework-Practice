package paper.spring.websocket.hello;

public class ByeMessage {

    private String from;
    private String time;

    public ByeMessage(final String from, final String time) {

        this.from = from;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getFrom() {
        return from;
    }
}
