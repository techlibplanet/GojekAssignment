package com.example.gojekassignment.dependency.components

import com.example.gojekassignment.dependency.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface InjectServiceComponent {
}