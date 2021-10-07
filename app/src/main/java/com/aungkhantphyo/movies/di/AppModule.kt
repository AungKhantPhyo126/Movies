package com.aungkhantphyo.movies.di

import android.content.Context
import com.aungkhantphyo.movies.localdatabase.AppDatabase
import com.aungkhantphyo.movies.localdatabase.dao.PopularMoviesDao
import com.aungkhantphyo.movies.localdatabase.dao.RemoteKeyDao
import com.aungkhantphyo.movies.localdatabase.dao.UpcomingMoviesDao
import com.aungkhantphyo.movies.network.MovieApiService
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

const val BASE_URL="https://api.themoviedb.org/3/movie/"
const val API_KEY="012a0a929d214b25f6dc147d762bd6e4"
const val BASE_IMG_URL="https://image.tmdb.org/t/p/w500"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideRetrofit(moshi: Moshi,okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit) = retrofit.create<MovieApiService>()


    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context)  : AppDatabase {
        return AppDatabase.create(context)
    }
    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase) : RemoteKeyDao {
        return appDatabase.remoteKeyDao()
    }

    @Provides
    fun providePopularMoviesDao(appDatabase: AppDatabase) : PopularMoviesDao {
        return appDatabase.popularMoviesDao()
    }

    @Provides
    fun provideUpcomingMoviesDao(appDatabase: AppDatabase) : UpcomingMoviesDao {
        return appDatabase.upcomingMoviesDao()
    }

    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        oKHttpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(ChuckInterceptor(context))
            addInterceptor(oKHttpLoggingInterceptor)
        }.build()
    }

}