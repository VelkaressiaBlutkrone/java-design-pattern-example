package ex10.command;

import ex10.receiver.Music;

/**
 * 3-b. 구체 Command - "음악 재생" 요청을 객체로 캡슐화
 *
 * LightOnCommand와 동일한 구조로 Command 인터페이스를 구현한다.
 * 새로운 명령이 필요하면 Command를 구현하는 새 클래스만 추가하면 된다.
 *
 * - execute() : Receiver(Music)의 play() 호출 → 음악 재생
 * - undo()    : Receiver(Music)의 stop() 호출 → 음악 정지
 */
public class MusicPlayCommand implements Command {
    /** Receiver - 실제 작업을 수행하는 대상 */
    private Music music;

    public MusicPlayCommand(Music music) {
        this.music = music;
    }

    /** 명령 실행: Receiver에게 "음악 재생" 위임 */
    @Override
    public void execute() {
        music.play();
    }

    /** 실행 취소: 반대 동작인 "음악 정지" 수행 */
    @Override
    public void undo() {
        music.stop();
    }
}
