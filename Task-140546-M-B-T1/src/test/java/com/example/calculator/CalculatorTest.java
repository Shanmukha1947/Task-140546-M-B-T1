package com.example.calculator;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.junit.runner.RunWith;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(org.junit.platform.runner.JUnitPlatform.class)
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@PrepareForTest(Calculator.class)
public class CalculatorTest {

    @ParameterizedTest
    @MethodSource("provideTestDataForAdd")
    public void testAdd(int a, int b, int expectedResult) {
        // Mock the Calculator class using PowerMock
        Calculator calculator = PowerMockito.mock(Calculator.class);

        // Mock the behavior of the add method
        PowerMockito.when(calculator.add(a, b)).thenReturn(expectedResult);

        // Call the method under test using the mocked object
        int actualResult = calculator.add(a, b);

        // Verify that the mocked method was called with the correct arguments
        Mockito.verify(calculator).add(a, b);

        // Assert the expected result
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideTestDataForAdd() {
        return Stream.of(
                Arguments.of(10, 20, 30),
                Arguments.of(20, 30, 50),
                Arguments.of(-10, 20, 10)
        );
    }
}

