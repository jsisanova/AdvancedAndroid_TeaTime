package com.example.android.teatime;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

// https://classroom.udacity.com/nanodegrees/nd801-ent/parts/77c0f519-b173-44b2-bff3-73feb162a500/modules/2ad3410b-6505-418c-9b74-2d4f67880313/lessons/f0084cc7-2cbc-4b8e-8644-375e8c927167/concepts/f0c53eb6-722d-4558-b317-d4205dc7822d
// https://github.com/android/testing-samples/tree/master/ui/espresso/IntentsBasicSample/app/src/main/java/com/example/android/testing/espresso/IntentsBasicSample
// https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android !!
// TODO (1) Add annotation to specify AndroidJUnitRunner class as the default test runner
@RunWith(AndroidJUnit4.class)
public class OrderSummaryActivityTest {

    private static final String emailMessage = "I just ordered a delicious tea from TeaTime. Next time you are craving a tea, check them out!";

    // TODO (2) Add the rule that indicates we want to use Espresso-Intents APIs in functional UI tests
    /**
     * IntentsTestRule is a class that extends ActivityTestRule.
     * IntentsTestRule initializes Espresso-Intents before each test (@Test) and releases it once the test is complete.
     * The associated Activity is also terminated after each test.
     */
    @Rule
    public IntentsTestRule<OrderSummaryActivity> mActivityRule = new IntentsTestRule<>(OrderSummaryActivity.class);


    // TODO (3) Finish this method which runs before each test and will STUB all external intents so all external intents will be blocked
    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
        // It uses the intending() method associated with stubbing and takes not(isInternal()) as its IntentMatcher parameter.
        // isInternal() matches an intent if its package is the same as the target package for the instrumentation test,
        // therefore not(isInternal()) checks that the intent's package does not match the target package for the test.
        //
        // If that's the case respond with:   new ActivityResult(Activity.RESULT_OK, null)
        // ActivityResult(int resultCode, Intent resultData) has 2 parameters.
        //
        // resultCode - Is the code sent back to the original activity. RESULT_OK indicates the operation was successful.
        // resultData - Is the data to send back to the original activity. null indicates no data is sent back.
    }

    // TODO (4) Finish this method which VERIFIES that the intent sent by clicking the send email
    // button matches the intent sent by the application
    @Test
    public void clickSendEmailButton_SendsEmail() {

        onView(withId(R.id.send_email_button)).perform(click());
        // intended(Matcher<Intent> matcher) asserts the given matcher matches one and only one
        // intent sent by the application.
        intended(allOf(
                hasAction(Intent.ACTION_SENDTO),
                hasExtra(Intent.EXTRA_TEXT, emailMessage)));

    }
}