package U15_异常.c3_自定义异常;

// 编译异常
public class ScoreException extends Exception {
    public ScoreException() {
    }

    public ScoreException(String message) {
        super(message);
    }

}
