package emeka;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestMatcherTest {

    @Test
    public void test(){
        List<Integer> scores = Arrays.asList(25, 67,38,94);

        //Asserts for lists
        assertThat(scores, hasSize(4));
        assertThat(scores,hasItems(25));
        assertThat(scores,everyItem(greaterThan(24)));
        assertThat(null, isEmptyOrNullString());

        //Asserts for arrays
        Integer [] array = {5,10,15};
        assertThat(array, arrayWithSize(3));
        assertThat(array, arrayContaining(5,10,15));
        assertThat(array,notNullValue() );
        assertThat(array,hasItemInArray(5));

    }
}
