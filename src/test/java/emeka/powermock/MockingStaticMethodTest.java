package emeka.powermock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

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
    public void test(){

        List<Integer> allStats = Arrays.asList(1,2);
        when(dependency.retrieveAllStats()).thenReturn(allStats);
        test.methodCallingAStaticMethod();



    }
}