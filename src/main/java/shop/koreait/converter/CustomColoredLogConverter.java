package shop.koreait.converter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

public class CustomColoredLogConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {
    @Override
    protected String getForegroundColorCode(ILoggingEvent iLoggingEvent) {
        Level level = iLoggingEvent.getLevel();
        return switch (level.toInt()) {
            case Level.ERROR_INT -> ANSIConstants.RED_FG; // 에러는 빨간색
            case Level.WARN_INT -> ANSIConstants.YELLOW_FG; // 경고는 노란색
            case Level.INFO_INT -> ANSIConstants.BLUE_FG; // INFO는 파란색
            case Level.DEBUG_INT -> ANSIConstants.MAGENTA_FG; // 디버그는 청록색
            case Level.TRACE_INT -> ANSIConstants.BLUE_FG; // 트레이스는 진한 검정색
            default -> ANSIConstants.DEFAULT_FG; // 기본색
        };
    }
}
