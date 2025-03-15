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
import com.example.dbgroupwork.data.DataStoreManager
import com.example.dbgroupwork.data.Repository.UserRepositoryImpl
import com.example.dbgroupwork.data.local.room.FitAppDatabase.Companion.provideDatabase
import com.example.dbgroupwork.Domain.UseCaes.CheckUserToLoginUseCase
import com.example.dbgroupwork.Domain.UseCaes.GetCommentsUseCase
import com.example.dbgroupwork.Domain.UseCaes.ModifyUserDataUseCase
import com.example.dbgroupwork.Domain.UseCaes.SaveUserDataUseCase
import com.example.dbgroupwork.Domain.UserRepository
import com.example.dbgroupwork.Presentation.ViewModels.CommunityViewModel
import com.example.dbgroupwork.Presentation.ViewModels.HomeViewModel
import com.example.dbgroupwork.Presentation.ViewModels.LoginViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SettingsScreenViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SignupViewModel
import com.example.dbgroupwork.data.FireStoreLocalDataSource
import com.example.dbgroupwork.Data.local.firebase.FireStoreLocalDataSourceImpl
import com.example.dbgroupwork.Domain.FitAppRepository
import com.example.dbgroupwork.Domain.UseCaes.AddCommentUseCase
import com.example.dbgroupwork.data.FitAppRepositoryImpl
import com.example.dbgroupwork.data.local.FitAppDatabaseDatasource
import com.example.dbgroupwork.data.local.room.FitAppRoomLocalDatasourceImpl
import com.example.dbgroupwork.data.remote.FitAppRemoteDataSource
import com.example.dbgroupwork.data.remote.FitAppRemoteDataSourceImpl
import com.example.dbgroupwork.data.remote.FitAppService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="users")
object DependencyProvider {
    val commentsViewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val context = extras[APPLICATION_KEY]?.applicationContext!!
            val savedStateHandle = extras.createSavedStateHandle()
            //  val userRepository: UserRepository = getRepository(context)
            val userRepository: UserRepository = getRepository(context)
            val getCommentsUseCase = GetCommentsUseCase(userRepository)
            val addCommentUseCase = AddCommentUseCase(userRepository )

           // return CommunityViewModel(savedStateHandle, getReviewUseCase, addReviewUseCase) as T
            return CommunityViewModel(savedStateHandle,getCommentsUseCase,addCommentUseCase) as T
            }
        }


    val loginViewModel: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val context = extras[APPLICATION_KEY]?.applicationContext!!

            val userRepository: UserRepository = getRepository(context)
            val checkUserDataToLoginUseCase = CheckUserToLoginUseCase(userRepository)
            return LoginViewModel(checkUserDataToLoginUseCase, userRepository) as T
        }
    }

    val settingsScreenViewModel: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

            val context = extras[APPLICATION_KEY]?.applicationContext!!
            val userRepository: UserRepository = getRepository(context)
            val modifyUserDataUseCase = ModifyUserDataUseCase(userRepository)
            return SettingsScreenViewModel(modifyUserDataUseCase, userRepository) as T
        }
    }

    val signupViewModel: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

            val context = extras[APPLICATION_KEY]?.applicationContext!!
            val userRepository: UserRepository = getRepository(context)
            val saveUserDataUseCase = SaveUserDataUseCase(userRepository)
            return SignupViewModel(saveUserDataUseCase, userRepository) as T
        }
    }

    val homeViewModel: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

            val context = extras[APPLICATION_KEY]?.applicationContext!!
            val fitAppRepository: FitAppRepository = getRepositoryRoom(context)
            return HomeViewModel(fitAppRepository) as T
        }
    }

    private fun getRepository(context: Context): UserRepository {
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

        val firebaseDatasourceImpl: FireStoreLocalDataSource = FireStoreLocalDataSourceImpl()
        val dataStoreManager = DataStoreManager(context)

        val userRepository: UserRepository =
            UserRepositoryImpl(
                dataStoreManager,
                firebaseDatasourceImpl
            )
        return userRepository
        }

    private fun getRepositoryRoom(context: Context): FitAppRepository {

        val json = Json {
            ignoreUnknownKeys = true
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://invoicegen.gear.host/api/FitApp/")
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType(),
                ),
            )
            .build()
//
        val service: FitAppService = retrofit.create(FitAppService::class.java)
//
        val monumentRemoteDataSource: FitAppRemoteDataSource = FitAppRemoteDataSourceImpl(service)

        val database = provideDatabase(context)
        val planDao = database.getDao()
        val fitRoomLocalDatasource: FitAppDatabaseDatasource = FitAppRoomLocalDatasourceImpl(planDao)
        val fitAppRepositoryImpl: FitAppRepository = FitAppRepositoryImpl(monumentRemoteDataSource, fitRoomLocalDatasource)
        return fitAppRepositoryImpl
    }

    private fun getSaveUserDataUseCase(){
    }
}