package app.backend.time;

import java.time.LocalDateTime;

public class CurrentTimeSupplier implements TimeSupplier {

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
