import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.dolphinemu.dolphinemu.R
import org.dolphinemu.dolphinemu.ui.main.MainActivity
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
        // TODO: deal with initial analytics consent dialog

        // Navigate to the general settings, and verify that the "Change Discs Automatically"
        // setting is not checked.
        onView(withId(R.id.menu_settings)).perform(click())
        onView(withText("Config")).perform(click())
        onView(withText("General")).perform(click())
        // TODO: figure out how to match the `MaterialSwitch` specifically to see if it is checked
        onView(withText("Change Discs Automatically")).check(matches(isNotChecked()))

        // Check the "Changes Discs Automatically" setting.
        onView(withText("Change Discs Automatically")).perform(click())
        onView(withText("Change Discs Automatically")).check(matches(isChecked()))

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
        onView(withText("Change Discs Automatically")).check(matches(isChecked()))
    }
}
