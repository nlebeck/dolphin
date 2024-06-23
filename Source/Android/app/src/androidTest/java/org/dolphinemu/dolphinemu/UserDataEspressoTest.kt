import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.dolphinemu.dolphinemu.R
import org.dolphinemu.dolphinemu.ui.main.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HelloWorldEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // TODO: better test name
    @Test
    fun settingPersisted() {
        // Click through the analytics consent dialog that pops up when the app first opens.
        // TODO: will the analytics consent dialog always pop up at the beginning of the test?
        onView(withText("Yes")).perform(click())

        // Navigate to the general settings, and verify that the "Change Discs Automatically"
        // setting is not enabled.
        onView(withId(R.id.menu_settings)).perform(click())
        onView(withText("Config")).perform(click())
        onView(withText("General")).perform(click())
        // TODO: is there a less hacky way of identifying the view with the actual switch? If not,
        //  explain.
        onView(
            allOf(
                withParent(withChild( withText("Change Discs Automatically"))),
                withId(R.id.setting_switch)
            )
        ).check(matches(isNotChecked()))

        // Enable the "Changes Discs Automatically" setting.
        onView(withText("Change Discs Automatically")).perform(click())
        onView(
            allOf(
                withParent(withChild( withText("Change Discs Automatically"))),
                withId(R.id.setting_switch)
            )
        ).check(matches(isChecked()))

        // Navigate back to the main activity.
        pressBack()
        pressBack()
        pressBack()
        // TODO: verify that we got back to the main activity?

        // Navigate to the general settings again, and verify that the "Change Discs Automatically"
        // setting is checked.
        onView(withId(R.id.menu_settings)).perform(click())
        onView(withText("Config")).perform(click())
        onView(withText("General")).perform(click())
        onView(
            allOf(
                withParent(withChild( withText("Change Discs Automatically"))),
                withId(R.id.setting_switch)
            )
        ).check(matches(isChecked()))

    }
}
