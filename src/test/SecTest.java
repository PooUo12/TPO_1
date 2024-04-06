package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import prog.SecTaylor;

public class SecTest {

    private final double highEps = 0.001;
    private final double lowEps = 1;

    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI, 1.5 * Math.PI, -0.5 * Math.PI})
    public void InfTest(double x){
        Assertions.assertEquals(Double.NaN, SecTaylor.CastSecTaylor(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-3.9 * Math.PI, 2.2 * Math.PI, 2.21 * Math.PI})
    public void HighEpsTest(double x){
        Assertions.assertEquals(1.0 / Math.cos(x), SecTaylor.CastSecTaylor(x), highEps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {2.4 * Math.PI, 2.3 * Math.PI, -3.7 * Math.PI})
    public void LowEpsTest(double x){
        Assertions.assertEquals(1.0 / Math.cos(x), SecTaylor.CastSecTaylor(x), lowEps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {2.8 * Math.PI, 2.9 * Math.PI, -1.2 * Math.PI})
    public void NegTest(double x){
        Assertions.assertEquals(1.0 / Math.cos(x), SecTaylor.CastSecTaylor(x), highEps);
    }
}
