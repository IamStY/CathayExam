# CathayExam


1. 架構 - MVVM + LiveData + RoomDatabase + Repository
2. 資料落地, 首次儲存後支援離線 , 資料全部拆解後放到Room Db
 call flow 流程
 Room query -> liveData notify ui-> api request(read file) -> api response(read file)
 -> insert data list -> Room Live Data Notify UI -> data synced
3. animations and ui enhancements
4. coroutine
5. VMFactory + OOP化 ViewModel
6. animations and ui enhancements
7. landscape / portrait OK!


收到考題時間 12-07(一)
開始作答時間 12-08(二)
最後Update時間12-09(三)
