package com.example.finalapp.di

import android.app.Application
import androidx.room.Room
import com.example.finalapp.featureNote.data.repositories.ItemRepositoryImp
import com.example.finalapp.featureNote.data.source.ItemDatabase
import com.example.finalapp.featureNote.domain.repositories.ItemRepository
import com.example.finalapp.featureNote.domain.useCases.AddItemUseCase
import com.example.finalapp.featureNote.domain.useCases.DeleteItemUseCase
import com.example.finalapp.featureNote.domain.useCases.GetAllItemsUseCase
import com.example.finalapp.featureNote.domain.useCases.GetByIdItemUseCase
import com.example.finalapp.featureNote.domain.useCases.ItemUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemDatabase(app: Application): ItemDatabase {
        return Room.databaseBuilder(
            app,
            ItemDatabase::class.java,
            ItemDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemRepository(db: ItemDatabase): ItemRepository {
        return ItemRepositoryImp(db.itemDAO)
    }

    @Provides
    @Singleton
    fun provideItemUseCases(repository: ItemRepository): ItemUseCases {
        return ItemUseCases(
            getAllItems = GetAllItemsUseCase(repository),
            deleteItem = DeleteItemUseCase(repository),
            addItem = AddItemUseCase(repository),
            getItemById = GetByIdItemUseCase(repository)
        )
    }

}