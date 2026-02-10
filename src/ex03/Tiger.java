package ex03;

/**
 * Tiger - Animal을 직접 상속하는 내부 시스템 클래스
 *
 * 어댑터가 필요 없는 경우: 이미 Animal(Target) 인터페이스를 직접 구현하므로
 * Doorman과 바로 호환된다. (cf. OuterRabbit은 어댑터가 필요)
 */
public class Tiger extends Animal {
    private String name = "호랑이";

    @Override
    public String getName() {
        return name;
    }
}
