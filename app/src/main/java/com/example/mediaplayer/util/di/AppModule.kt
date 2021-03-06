package com.example.mediaplayer.util.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mediaplayer.model.data.local.MusicRepo
import com.example.mediaplayer.view.adapter.*
import com.example.mediaplayer.exoplayer.service.MusicServiceConnector
import com.example.mediaplayer.exoplayer.MusicSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Will migrate some to ServiceComponent

    @Singleton
    @Provides
    fun provideMusicRepo(
        @ApplicationContext context: Context
    ) = MusicRepo(context)

    @Singleton
    @Provides
    fun provideMusicSource(
        repo: MusicRepo,
        @ApplicationContext context: Context
    ) = MusicSource(repo, context)

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE))

    @Singleton
    @Provides
    fun provideAppContext(
        @ApplicationContext context: Context
    ) = context

    @Singleton
    @Provides
    fun provideMusicServiceConnector(
        @ApplicationContext context: Context
    ) = MusicServiceConnector(context)

    @Named("songAdapterNS")
    @Provides
    fun provideSongAdapterNS(
        @ApplicationContext context: Context,
        glide: RequestManager
    ) = SongAdapter(glide, context)

    @Named("folderAdapterNS")
    @Provides
    fun provideFolderAdapterNS(
        @ApplicationContext context: Context,
        glide: RequestManager
    ) = FolderAdapter(glide, context)

    @Named("homeAdapterNS")
    @Provides
    fun provideHomeAdapterNS(
        @ApplicationContext context: Context,
        glide: RequestManager
    ) = HomeAdapter(glide, context)

    @Named("albumAdapterNS")
    @Provides
    fun provideAlbumAdapterNS(
        @ApplicationContext context: Context,
        glide: RequestManager
    ) = AlbumAdapter(glide, context)

    @Named("artistAdapterNS")
    @Provides
    fun provideArtistAdapterNS(
        @ApplicationContext context: Context,
        glide: RequestManager
    ) = ArtistAdapter(glide, context)

    @Named("playingAdapterNS")
    @Provides
    fun providePlayingAdapterNS(
        @ApplicationContext context: Context,
        glide: RequestManager
    ) = PlayingAdapter(glide, context)
}