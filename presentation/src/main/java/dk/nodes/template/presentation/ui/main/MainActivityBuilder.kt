package dk.nodes.template.presentation.ui.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dk.nodes.template.presentation.injection.ViewModelKey
import dk.nodes.template.presentation.ui.sample.SampleBuilder
import dk.nodes.template.presentation.ui.shift.ShiftOverviewFragment
import dk.nodes.template.presentation.ui.shift.shiftDetailsActivity


@Module
internal abstract class MainActivityBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewMode(viewModel: MainActivityViewModel): ViewModel

    @ContributesAndroidInjector(
        modules = [
            SampleBuilder::class
        ]
    )
    abstract fun ShiftOverviewFragment(): ShiftOverviewFragment
    @ContributesAndroidInjector
    internal abstract fun shiftDetailserActivity(): shiftDetailsActivity



}