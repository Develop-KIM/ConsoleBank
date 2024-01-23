package exception;

/**
 * 예외를 처리하는 클래스
 */
public class InitException extends Exception {
    /**
     * 에러 메시지를 포함한 초기화 예외 객체를 생성
     * @param message 에러 메시지
     */
    public InitException(String message) {
        super(message);
    }
}
