package com.example.dbgroupwork.Presentation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.dbgroupwork.Data.DataStoreManager
import com.example.dbgroupwork.Data.FireStoreLocalDataSource
import com.example.dbgroupwork.Data.Repository.UserRepositoryImpl
import com.example.dbgroupwork.Data.local.firebase.FireStoreLocalDataSourceImpl
import com.example.dbgroupwork.Domain.UseCaes.AddCommentUseCase
import com.example.dbgroupwork.Domain.UseCaes.CheckUserToLoginUseCase
import com.example.dbgroupwork.Domain.UseCaes.GetCommentsUseCase
import com.example.dbgroupwork.Domain.UseCaes.ModifyUserDataUseCase
import com.example.dbgroupwork.Domain.UseCaes.SaveUserDataUseCase
import com.example.dbgroupwork.Domain.UserRepository
import com.example.dbgroupwork.Presentation.ViewModels.CommunityViewModel
import com.example.dbgroupwork.Presentation.ViewModels.LoginViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SettingsScreenViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SignupViewModel

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="users")

object DependencyProvider {
    val commentsViewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

            val savedStateHandle = extras.createSavedStateHandle()
            val application = extras[APPLICATION_KEY]!! as FitApp
            val userRepository: UserRepository = getRepository(application)
            //val getMonumentByIdUseCase = GetMonumentByIdUseCase(monumentRepository)
            val getReviewUseCase = GetCommentsUseCase(userRepository)
            val addReviewUseCase = AddCommentUseCase(userRepository )

            return CommunityViewModel(savedStateHandle, getReviewUseCase, addReviewUseCase) as T
            }
        }

    private fun getRepository(application: FitApp): UserRepository {
        //val json = Json {
        //    ignoreUnknownKeys = true
        //}
        //val retrofit = Retrofit.Builder()
        //    .baseUrl("https://www.zaragoza.es/sede/servicio/")
        //    .addConverterFactory(
        //        json.asConverterFactory(
        //            "application/json".toMediaType(),
        //        ),
        //    )
        //    .build()
//
        //val service: MonumentService = retrofit.create(MonumentService::class.java)
//
        //val monumentRemoteDataSource: MonumentRemoteDataSource = MonumentRemoteDataSourceImpl(service)
        //val sharedPref = application.getSharedPreferences("Monuments", Context.MODE_PRIVATE)
        //val dataStore = application.dataStore
        //val monumentPrefDataSource: PrefLocalDataSource = PrefDataStoreImpl(dataStore)
//
        //val sqliteHelper = MonumentSqlLiteHelper(application)
        //// val monumentSqliteDatasource: MonumentDatabaseDatasource = MonumentSqliteDatasourceImpl(sqliteHelper.writableDatabase)
//
        //val database = provideDatabase(application)
        //val dao = database.getDao()
        //val monumentRoomLocalDatasource: MonumentDatabaseDatasource = MonumentRoomLocalDatasourceImpl(dao)

        val firebaseDatasourceImpl: FireStoreLocalDataSource = FireStoreLocalDataSourceImpl(firestore = application.getFirestore())
        val dataStoreManager = DataStoreManager(application)


        val userRepository: UserRepository =
            UserRepositoryImpl(
                firebaseDatasourceImpl,
                dataStoreManager
            )
        return userRepository
        }

    private fun getSaveUserDataUseCase(){

    }
}