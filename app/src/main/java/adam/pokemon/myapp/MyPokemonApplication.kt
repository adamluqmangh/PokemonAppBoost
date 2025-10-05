package adam.pokemon.myapp

import android.app.Application
import android.os.Debug
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class MyPokemonApplication: Application(){
    override fun onCreate(){
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}
