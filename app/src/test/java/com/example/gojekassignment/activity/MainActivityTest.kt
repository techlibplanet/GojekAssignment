package com.example.gojekassignment.activity

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.gojekassignment.*
import com.example.gojekassignment.helper.Constants
import com.example.gojekassignment.koindi.okHttp
import com.example.gojekassignment.koindi.retrofit
import com.example.gojekassignment.network.ITrendingRepositories
import com.example.gojekassignment.viewmodel.TrendingRepositoriesViewModel
import org.junit.Test
import org.koin.dsl.module
import org.robolectric.Robolectric
import retrofit2.Retrofit

class MainActivityTest : AndroidTest<MainActivity>() {
    private val app: GojekApplicationTest = ApplicationProvider.getApplicationContext()


    override fun createActivityScenario(
        launchActivity: Boolean,
        intent: Intent?
    ): LazyActivityScenarioRule<MainActivity> {
        return lazyActivityScenarioRule(launchActivity = launchActivity, intent = intent)
    }

    override fun createActivityInstance(intent: Intent?): MainActivity {
        return Robolectric.buildActivity(MainActivity::class.java, intent)
            .create()
            .resume()
            .get()
    }

    override fun setUp() {
        super.setUp()
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
            single { TrendingRepositoriesViewModel(get()) }
        }


        app.loadModules(modules) {
            ActivityScenario.launch(MainActivity::class.java)
            /*onScreen<MainScreen> {
                status {
                    hasText("This is a good service")
                }
            }*/
        }
    }

    @Test
    fun checkScreenContents() {
        setupActivity()

        //Assert.assertEquals(View.VISIBLE, activity.shimmer_view_container.visibility)

    }


}