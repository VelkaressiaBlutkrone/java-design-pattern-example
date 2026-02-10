package ex02;

/**
 * Mouse - "쥐" 구체 동물 클래스
 *
 * Tiger와 동일한 역할. 프록시를 통해 전달되는 데이터 객체.
 */
public class Mouse extends Animal {
    private String name = "쥐";

    @Override
    public String getName() {
        return name;
    }
}
