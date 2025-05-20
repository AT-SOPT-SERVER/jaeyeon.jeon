package or.sopt.assignment.global.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalDateTimeImpl {

    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}