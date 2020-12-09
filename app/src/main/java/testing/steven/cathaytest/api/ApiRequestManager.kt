package testing.steven.cathaytest.api

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.datamodel.PlantsDataModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL


object ApiRequestManager {

    val gson = Gson()
      fun <T> sendRequest(
          apiStatus :MutableLiveData<Int>,
          type: Type,
          context: Context?,
          url: String
      )  : T{
          apiStatus.postValue(1)
        var resultList = ArrayList<List<String>>()
        try {
            val connUrl = URL(url)
            val conn: HttpURLConnection = connUrl.openConnection() as HttpURLConnection
            conn.setConnectTimeout(60000)
            // 見鬼了... 兩種不同編碼是怎麼回事....


            if(url.contains("1ed45a8a-d26a-4a5f-b544-788a4071eea")){
                // 考題內容編碼不同步 , csvReader不支援BIG5編碼, 這邊自己拆
                val `in` = BufferedReader(InputStreamReader(conn.getInputStream(), "BIG5"))
                var str: String?
                while (`in`.readLine().also { str = it } != null) {
                    var array = str!!.split(",")
                    resultList!!.add(array)
                }
                `in`.close()
            }else{
                resultList= csvReader().readAll(conn.getInputStream())   as  ArrayList<List<String>>
            }



        } catch (e: Exception) {
            apiStatus.postValue(-1)
            return ArrayList<T>() as T
//            throw  NoNetwork()
        }


          /****
           * cvs資料轉換成json後
           * 再利用gson轉換成datamodel
           *
           */
        var jsonArray = JSONArray()
        val dataKeys= ArrayList<String>()
        for (i in 0 until resultList!!.size) {
            if (i == 0) {
                dataKeys .addAll(resultList[0])
            } else {
                try {
                    var jsonObject = JSONObject()
                    for (k in 0 until resultList[i].size) {
                        jsonObject.put(dataKeys[k], resultList[i][k])

                    }
                    jsonArray.put(jsonObject)
                }catch (e: Exception){
                    throw  e
                }
            }
        }

        var obj = gson.fromJson<T>(jsonArray.toString(), type)
          apiStatus.postValue(0)
       return obj
    }


    fun getServerCenters(apiStatus : MutableLiveData<Int>,
          context: Context
      ) : ArrayList<CenterDataModel> {
        val functionName = Statics.centerPath
       return  sendRequest(apiStatus,object : TypeToken<ArrayList<CenterDataModel>>() {
       }.type, context, functionName)
    }
    fun getPlants(apiStatus : MutableLiveData<Int>,
        context: Context
    ) : ArrayList<PlantsDataModel> {
        val functionName = Statics.plantPath
        return  sendRequest(apiStatus,object : TypeToken<ArrayList<PlantsDataModel>>() {
        }.type, context, functionName)
    }
}