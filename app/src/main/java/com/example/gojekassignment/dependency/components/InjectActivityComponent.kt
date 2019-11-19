package com.example.gojekassignment.dependency.components

import com.example.gojekassignment.MainActivity
import com.example.gojekassignment.dependency.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface InjectActivityComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}
