package ex02;

public class App {
    public static void main(String[] args) {
        Tiger t = new Tiger();
        DoormanProxy d = new DoormanProxy();
        d.chaseOut(t);
    }
}
