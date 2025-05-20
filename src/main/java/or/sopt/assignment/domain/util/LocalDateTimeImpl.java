package or.sopt.assignment.domain.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalDateTimeImpl implements or.sopt.assignment.domain.util.LocalDateTime {

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}