package com.example.gojekassignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = GojekApplicationTest::class)
abstract class AndroidTest<A : AppCompatActivity> {

    private lateinit var context: Context

    protected lateinit var activity: A

    @get:Rule
    private lateinit var activityScenarioRule: LazyActivityScenarioRule<A>

    abstract fun createActivityInstance(intent: Intent? = null): A

    abstract fun createActivityScenario(
        launchActivity: Boolean = false,
        intent: Intent? = null
    ): LazyActivityScenarioRule<A>

    @Before
    open fun setUp() {
        context = ApplicationProvider.getApplicationContext<GojekApplicationTest>()
    }

    fun context() = context

    fun setupActivity(intent: Intent? = null) {
        activity = createActivityInstance(intent)
    }

    fun setupActivityScenario(launchActivity: Boolean = false, intent: Intent? = null) {
        activityScenarioRule = createActivityScenario(launchActivity, intent)
    }

    fun getActivityScenario() = activityScenarioRule.getScenario()

    fun launchActivity() {
        activityScenarioRule.launch()
    }

    @After
    fun cleanup() {
        //activityScenarioRule.getScenario().close()
    }
}