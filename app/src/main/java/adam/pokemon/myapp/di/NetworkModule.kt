package adam.pokemon.myapp.di

import adam.pokemon.myapp.api.pokemonlist.retrofit.PokemonAPI
import adam.pokemon.myapp.config.API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)


    @Provides
    @Singleton
    fun providePokemonAPI(
        retrofit: Retrofit.Builder,
    ) = retrofit
        .baseUrl(API.API_BASE_URL)
        .build()
        .create(PokemonAPI::class.java)
}