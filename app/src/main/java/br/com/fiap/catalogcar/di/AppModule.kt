package br.com.fiap.catalogcar.di

import android.content.Context
import br.com.fiap.catalogcar.BuildConfig
import br.com.fiap.catalogcar.data.local.datasource.StoreUser
import br.com.fiap.catalogcar.data.remote.CarApi
import br.com.fiap.catalogcar.data.remote.repository.CarRepositoryImpl
import br.com.fiap.catalogcar.domain.repository.CarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCarApi(): CarApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarApi::class.java)
    }

    @Provides
    @Singleton
    fun providerCarRepository(api: CarApi): CarRepository = CarRepositoryImpl(api)

    @Provides
    @Singleton
    fun providerStoreUser(@ApplicationContext context: Context): StoreUser = StoreUser(context)
}