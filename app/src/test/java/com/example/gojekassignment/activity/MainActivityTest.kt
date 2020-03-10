package com.example.gojekassignment.activity

import android.content.Intent
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.gojekassignment.*
import com.example.gojekassignment.helper.Constants
import com.example.gojekassignment.koindi.okHttp
import com.example.gojekassignment.koindi.retrofit
import com.example.gojekassignment.network.ITrendingRepositories
import com.example.gojekassignment.viewmodel.TrendingRepositoriesViewModel
import junit.framework.Assert.assertEquals
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit

@RunWith(RobolectricTestRunner::class)
@Config(application = GojekApplicationTest::class, sdk = [28])
class MainActivityTest : AndroidTest<MainActivity>() {
    private val app: GojekApplicationTest = ApplicationProvider.getApplicationContext()

    override fun createActivityInstance(intent: Intent?): MainActivity {
        return Robolectric.buildActivity(MainActivity::class.java, intent)
            .create()
            .resume()
            .get()

    }

    override fun createActivityScenario(
        launchActivity: Boolean,
        intent: Intent?
    ): LazyActivityScenarioRule<MainActivity> {
        return lazyActivityScenarioRule(launchActivity = launchActivity, intent = intent)
    }


    @Before
    override fun setUp() {

    }

    @Test
    fun checkScreenContents() {
        val modules = module {
            single {
                okHttp()
            }
            single {
                retrofit(Constants.API_BASE_URL)
            }
            single {
                get<Retrofit>().create(ITrendingRepositories::class.java)
            }

            viewModel {
                TrendingRepositoriesViewModel(trendingRepositoryService = get())
            }
        }
        app.loadModules(modules) {
            // Start mocking from here
            ActivityScenario.launch(MainActivity::class.java)
            setupActivity()
            assertEquals(View.VISIBLE, activity.trending_repository_recycler_view.visibility)

        }
    }


}