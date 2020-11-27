package  com.squareboat.iconfinder.data.repositorie


import com.squareboat.iconfinder.data.network.ApiService
import com.squareboat.iconfinder.data.network.RetrofitInstance
import com.squareboat.iconfinder.model.IconFinderResponse


/**
 * Created by Atif Qamar on 26-11-2020.
 */

class AppRepository {
    private var service: ApiService = RetrofitInstance.appService
    suspend fun getIcons(
        iconName: String,
        numberOfIcons: Int
    ): IconFinderResponse = service.getIcons(iconName, numberOfIcons)

}