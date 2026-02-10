package ex03.lib;

/**
 * [Adaptee 역할] OuterRabbit - 외부 라이브러리의 기존 클래스
 *
 * 어댑터 패턴에서 Adaptee에 해당한다.
 * - Animal 시스템과 호환되지 않는 독자적인 인터페이스를 가짐
 *   → getName()이 아닌 getFullName()을 사용
 * - 외부 라이브러리이므로 이 클래스의 코드를 직접 수정할 수 없다
 * - RabbitAdaptor가 이 클래스를 감싸서 Animal 인터페이스로 변환한다
 */
public class OuterRabbit {
    private String fullName = "토끼";

    /**
     * 외부 라이브러리 고유 메서드 - Animal의 getName()과 시그니처가 다름
     * → 이 차이를 RabbitAdaptor가 변환해준다
     */
    public String getFullName() {
        return fullName;
    }
}
