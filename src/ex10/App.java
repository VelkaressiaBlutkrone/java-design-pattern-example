package ex10;

import ex10.command.Command;
import ex10.factory.CommandFactory;
import ex10.invoker.RemoteControl;

/**
 * ex10 - 커맨드 + 팩토리 패턴 (Command + Factory Pattern)
 *
 * [개념]
 * - 커맨드 패턴: 요청(명령)을 객체로 캡슐화하여 매개변수화, 큐잉, 로깅, 실행 취소(Undo)를 가능하게 한다.
 * - 팩토리 패턴: 커맨드 객체의 생성을 팩토리가 담당하여, 클라이언트는 구체 Command 타입을 몰라도 된다.
 *
 * [구조]
 *   Client ──▶ CommandFactory.create(type) ──▶ <<interface>> Command
 *                                                    + execute()
 *                                                    + undo()
 *                                                       ▲
 *                                              ┌────────┼────────┐
 *                                         LightOnCmd  MusicPlayCmd
 *                                              │            │
 *                                              ▼            ▼
 *                                        Light(Receiver)  Music(Receiver)
 *
 * [역할 분담]
 * - Command (인터페이스)  : 실행/취소의 공통 계약
 * - LightOnCommand 등    : 요청을 객체로 캡슐화한 구체 커맨드
 * - Light, Music         : 실제 작업을 수행하는 Receiver
 * - CommandFactory       : 타입 문자열로 적절한 Command 객체를 생성 (팩토리 패턴)
 * - RemoteControl        : Command를 실행하고 이력을 관리하는 Invoker
 */
public class App {
    /**
     * 6. 사용 예시 (Client)
     *
     * 실행 흐름:
     * 1) CommandFactory로 커맨드 객체 생성 → 구체 클래스를 직접 new 하지 않음
     * 2) RemoteControl(Invoker)에게 커맨드 실행 요청
     * 3) undoLast()로 마지막 명령 취소
     */
    public static void main(String[] args) {
        // 팩토리로 커맨드 객체 생성 - 클라이언트는 구체 Command 클래스를 알 필요 없음
        CommandFactory factory = new CommandFactory();

        Command cmd1 = factory.create("light_on");    // LightOnCommand 생성
        Command cmd2 = factory.create("music_play");   // MusicPlayCommand 생성

        // Invoker(리모컨)로 커맨드 실행
        RemoteControl remote = new RemoteControl();
        remote.press(cmd1);    // "조명 켜짐"     → 이력에 저장
        remote.press(cmd2);    // "음악 재생"     → 이력에 저장

        // Undo - 마지막 명령부터 역순으로 취소
        remote.undoLast();     // "음악 정지"     → MusicPlayCommand.undo()
        remote.undoLast();     // "조명 꺼짐"     → LightOnCommand.undo()
    }
}
