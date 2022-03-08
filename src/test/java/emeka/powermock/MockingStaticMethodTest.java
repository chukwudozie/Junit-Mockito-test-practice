package emeka.powermock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
public class MockingStaticMethodTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Dependency dependency;

    @InjectMocks
    private SystemUnderTest test;


//    @Before
//    public void setUp() throws Exception {
//    }

    @Test
    public void testMethodCallingStaticMethod_UsingMock(){

        List<Integer> allStats = Arrays.asList(1,2,3);
        when(dependency.retrieveAllStats()).thenReturn(allStats);
        Mockito.mockStatic(UtilityClass.class);
        when(UtilityClass.staticMethod(6)).thenReturn(150);
        test.methodCallingAStaticMethod();

    }
}