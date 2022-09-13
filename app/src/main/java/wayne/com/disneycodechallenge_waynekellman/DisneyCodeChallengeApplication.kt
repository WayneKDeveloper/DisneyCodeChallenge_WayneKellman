package wayne.com.disneycodechallenge_waynekellman

import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NameModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(viewModel: MainViewModel)
}
class DisneyCodeChallengeApplication: Application() {
}