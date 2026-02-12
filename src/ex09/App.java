package ex09;

import java.util.Scanner;

/**
 * ex09 - 위임 패턴 (Delegation Pattern)
 *
 * [개념]
 * 객체가 특정 작업을 직접 수행하지 않고, 다른 객체(Delegate)에게 위임한다.
 * 상속 대신 합성(has-a)을 사용하여 코드를 재사용하는 기법이다.
 *
 * [구조] (README 기준)
 *   Delegator ──has-a──▶ Delegate
 *    + doWork()           + doWork()
 *      → delegate.doWork()
 *
 * [이 코드의 구조]
 *   HomeworkDelegator ──has-a──▶ <<interface>> Student
 *    + delegateHomework()              ▲
 *      → student.doHomework()   ┌──────┼───────┐
 *                          MathStudent  ScienceStudent  HistoryStudent
 *
 * [작업 순서]
 * 1. 규칙을 정한다 → Student 인터페이스 (Delegate 계약)
 * 2. 학생을 고른다 → MathStudent, ScienceStudent, HistoryStudent (구체 Delegate)
 * 3. 숙제를 시킨다 → HomeworkDelegator.delegateHomework() (위임 호출)
 *
 * [사용 이유]
 * - 유연성: 위임 대상을 런타임에 교체할 수 있다 (README: setDelegate())
 * - 확장성: 새로운 과목 학생을 추가해도 기존 코드를 수정하지 않는다
 * - 재사용: 전략 패턴, 프록시 패턴 등 다른 패턴의 기반이 되는 기본 원리이다
 */
public class App {
    /**
     * 4. 사용 예시 (Client) - Delegator를 생성하고 위임을 요청
     *
     * README 대응:
     *   PrintService service = new PrintService(new ConsolePrinter());
     *   service.printMessage("Hello");  // delegate에게 위임
     *
     * 이 코드:
     *   new HomeworkDelegator().delegateHomework(keyboard);  // 학생에게 숙제 위임
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input -> math or science or history");
        String keyboard = scanner.nextLine();

        // Delegator 생성 후, 입력된 과목에 해당하는 Student(Delegate)에게 숙제 수행을 위임
        new HomeworkDelegator().delegateHomework(keyboard);
    }
}
