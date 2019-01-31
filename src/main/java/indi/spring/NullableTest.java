package indi.spring;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class NullableTest {

    @ParameterizedTest
    @ValueSource(strings = {""})
    void go(@Nullable @NonNull String go) {
        
    }
}
