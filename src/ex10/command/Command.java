package ex10.command;

/**
 * 1. Command 인터페이스 - 모든 명령의 공통 계약
 *
 * 커맨드 패턴의 핵심으로, 실행할 작업을 객체로 캡슐화하기 위한 인터페이스이다.
 * 모든 구체 Command(LightOnCommand, MusicPlayCommand)가 이 인터페이스를 구현한다.
 *
 * 구조:
 *   Client ──▶ CommandFactory.create(type) ──▶ <<interface>> Command
 *                                                  + execute()
 *                                                  + undo()
 *                                                     ▲
 *                                            ┌────────┼────────┐
 *                                       LightOnCmd  MusicPlayCmd
 */
public interface Command {
    /** 명령을 실행한다 */
    void execute();

    /** 실행한 명령을 취소한다 (반대 동작 수행) */
    void undo();
}
