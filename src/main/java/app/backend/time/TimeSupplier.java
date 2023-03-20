package app.backend.time;

import java.time.LocalDateTime;

public interface TimeSupplier {
    LocalDateTime getTime();
}
