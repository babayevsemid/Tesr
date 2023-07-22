package az.smartbee.analytics.repository

import az.smartbee.analytics.api.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal abstract class BaseRepo(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun <T> callApi(
        func: suspend () -> T
    ): Resource<T> {
        return withContext(dispatcher) {
            try {
                Resource.Success(func.invoke())
            } catch (e: Exception) {
                Resource.Error(e.message)
            }
        }
    }
}