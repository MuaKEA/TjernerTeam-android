package dk.nodes.template.presentation.ui.shift

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dk.nodes.template.presentation.injection.ViewModelKey

@Module
internal abstract class ShiftBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(ShiftViewModel::class)
    abstract fun bindShiftViewModel(viewModel: ShiftViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun ShiftOverview(): ShiftOverviewActivity
}