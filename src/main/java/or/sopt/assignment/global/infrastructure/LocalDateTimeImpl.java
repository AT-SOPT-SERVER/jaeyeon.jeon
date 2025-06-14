package or.sopt.assignment.global.infrastructure;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalDateTimeImpl implements or.sopt.assignment.global.infrastructure.LocalDateTime {

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}