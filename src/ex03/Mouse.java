package ex03;

/**
 * Mouse - Animal을 직접 상속하는 내부 시스템 클래스
 *
 * Tiger와 동일. Animal(Target)을 직접 구현하므로 어댑터 불필요.
 */
public class Mouse extends Animal {
    private String name = "쥐";

    @Override
    public String getName() {
        return name;
    }
}
