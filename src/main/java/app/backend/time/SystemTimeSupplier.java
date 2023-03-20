package app.backend.time;

import java.time.LocalDateTime;

//TODO redo name
public class SystemTimeSupplier implements TimeSupplier {

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
