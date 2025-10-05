package adam.pokemon.myapp.libraries

import adam.pokemon.myapp.BuildConfig


class Debug {
    fun isDebugAble(): Boolean {
        return BuildConfig.DEBUG
    }
}