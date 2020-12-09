package testing.steven.cathaytest.api

import testing.steven.cathaytest.BuildConfig

class Statics {
    companion object {
        var centerPath =
            "https://data.taipei/api/getDatasetInfo/downloadResource?id=1ed45a8a-d26a-4a5f-b544-788a4071eea2&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a"
        var plantPath =
            "https://data.taipei/api/getDatasetInfo/downloadResource?id=48c4d6a7-4b09-4d1f-9739-ee837d302bd1&rid=f18de02f-b6c9-47c0-8cda-50efad621c14"

        //keys
        var centerData = "centerData"
        var plantData = "plantData"

        init {
            // 專案若有flavor須切分endpoint
            if (BuildConfig.DEBUG) {
            }
        }
    }
}