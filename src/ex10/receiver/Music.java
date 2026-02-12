package ex10.receiver;

/**
 * 2-b. Receiver - 실제 작업을 수행하는 객체 (음악)
 *
 * Light와 마찬가지로, Command 객체로부터 실제 동작을 위임받는다.
 */
public class Music {
    public void play() {
        System.out.println("음악 재생");
    }

    public void stop() {
        System.out.println("음악 정지");
    }
}
