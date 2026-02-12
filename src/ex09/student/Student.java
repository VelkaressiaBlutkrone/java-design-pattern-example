package ex09.student;

/**
 * 1. Delegate 인터페이스 - 위임받을 객체의 계약
 *
 * 위임 패턴에서 Delegator(HomeworkDelegator)가 작업을 맡길 대상의 공통 규칙을 정의한다.
 * README의 Printer 인터페이스에 해당하며, 모든 구체 Delegate(학생)가 이 인터페이스를 구현한다.
 *
 * 구조:
 *   HomeworkDelegator ──has-a──▶ <<interface>> Student
 *                                      ▲
 *                            ┌─────────┼──────────┐
 *                       MathStudent  ScienceStudent  HistoryStudent
 */
public interface Student {
    /** 위임받은 숙제를 수행하는 메서드 (= README의 Printer.print()) */
    void doHomework();

    /** 입력된 과목명이 자신의 담당 과목과 일치하는지 판별 */
    boolean isSameHomework(String keyboard);
}
