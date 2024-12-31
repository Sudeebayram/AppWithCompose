//import android.content.Context
//import androidx.room.Room
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//    @Provides
//    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
//        Room.databaseBuilder(context, AppDatabase::class.java, "user_db").build()
//
//    @Provides
//    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
//}
